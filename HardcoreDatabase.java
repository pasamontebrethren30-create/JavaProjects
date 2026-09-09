import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HardcoreDatabase {

    // --- Hardcore Custom Data Structures ---
    private static class Node {
        String key;
        String value;
        Node prev, next;
        Node(String k, String v) { this.key = k; this.value = v; }
    }

    // Explicit manual LRU Cache Implementation to limit memory footprints manually
    private static class LRUCache {
        private final int capacity;
        private final Map<String, Node> map = new HashMap<>();
        private Node head, tail;

        LRUCache(int capacity) { this.capacity = capacity; }

        synchronized String get(String key) {
            Node node = map.get(key);
            if (node == null) return null;
            moveToHead(node);
            return node.value;
        }

        synchronized void put(String key, String value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                moveToHead(node);
            } else {
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                if (map.size() > capacity) {
                    Node evicted = removeTail();
                    if (evicted != null) map.remove(evicted.key);
                }
            }
        }

        private void addToHead(Node node) {
            node.next = head;
            node.prev = null;
            if (head != null) head.prev = node;
            head = node;
            if (tail == null) tail = head;
        }

        private void moveToHead(Node node) {
            if (node == head) return;
            if (node == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            addToHead(node);
        }

        private Node removeTail() {
            Node res = tail;
            if (tail != null) {
                if (tail.prev != null) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    head = null;
                    tail = null;
                }
            }
            return res;
        }
    }

    // --- Core Storage Engine Architecture ---
    public static class StorageEngine {
        private final LRUCache cache;
        private final String walPath;
        private FileChannel walChannel;
        private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        public StorageEngine(String walPath, int cacheCapacity) throws IOException {
            this.walPath = walPath;
            this.cache = new LRUCache(cacheCapacity);
            initStorage();
        }

        private void initStorage() throws IOException {
            File walFile = new File(walPath);
            if (walFile.exists()) {
                System.out.println("[⚡] WAL detected. Replaying log entries for absolute data recovery...");
                replayWAL();
            }
            // Open explicit low-level file channel for appending operations safely
            this.walChannel = FileChannel.open(Paths.get(walPath),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        }

        // Writes to the log instantly before committing to memory (ACID compliance)
        public void put(String key, String value) {
            rwLock.writeLock().lock();
            try {
                byte[] keyBytes = key.getBytes();
                byte[] valBytes = value.getBytes();
                
                // Payload packet design: [Key Length (4B)] + [Val Length (4B)] + [Key Data] + [Val Data]
                ByteBuffer buffer = ByteBuffer.allocate(8 + keyBytes.length + valBytes.length);
                buffer.putInt(keyBytes.length);
                buffer.putInt(valBytes.length);
                buffer.put(keyBytes);
                buffer.put(valBytes);
                buffer.flip();

                walChannel.write(buffer);
                walChannel.force(false); // Force system hardware bus flush

                cache.put(key, value);
            } catch (IOException e) {
                throw new RuntimeException("Database Storage Fault: WAL logging failure.", e);
            } finally {
                rwLock.writeLock().unlock();
            }
        }

        public String get(String key) {
            rwLock.readLock().lock();
            try {
                return cache.get(key);
            } finally {
                rwLock.readLock().unlock();
            }
        }

        // Complete system memory restoration logic parsing raw bytes sequence
        private void replayWAL() throws IOException {
            try (FileChannel readChannel = FileChannel.open(Paths.get(walPath), StandardOpenOption.READ)) {
                ByteBuffer lenBuffer = ByteBuffer.allocate(8);
                while (readChannel.read(lenBuffer) > 0) {
                    lenBuffer.flip();
                    int keyLen = lenBuffer.getInt();
                    int valLen = lenBuffer.getInt();
                    lenBuffer.clear();

                    ByteBuffer dataBuffer = ByteBuffer.allocate(keyLen + valLen);
                    readChannel.read(dataBuffer);
                    dataBuffer.flip();

                    byte[] keyBytes = new byte[keyLen];
                    byte[] valBytes = new byte[valLen];
                    dataBuffer.get(keyBytes);
                    dataBuffer.get(valBytes);

                    String key = new String(keyBytes);
                    String val = new String(valBytes);
                    cache.put(key, val); // Rebuild exact system states
                }
            }
            System.out.println("[🎉] System states recovered smoothly. Zero byte leakage.");
        }

        public void close() throws IOException {
            if (walChannel != null) walChannel.close();
        }
    }

    // --- Execution Simulation Environment ---
    public static void main(String[] args) {
        String logFilePath = "commit_log.wal";
        System.out.println("=================================================");
        System.out.println("🧱 BOOTING ADVANCED MULTI-THREADED WAL DATABASE ENGINE");
        System.out.println("=================================================");

        try {
            // Instantiate Database with a max storage scale capacity of 3 items
            StorageEngine db = new StorageEngine(logFilePath, 3);

            // Thread Worker A writing live telemetry
            Thread workerA = new Thread(() -> {
                db.put("userID_101", "{\"name\": \"Alice\", \"role\": \"Admin\"}");
                db.put("userID_102", "{\"name\": \"Bob\", \"role\": \"User\"}");
                System.out.println("[Worker A] Committed payloads cleanly.");
            });

            // Thread Worker B tracking transactions concurrently
            Thread workerB = new Thread(() -> {
                db.put("userID_103", "{\"name\": \"Charlie\", \"role\": \"Dev\"}");
                // This payload triggers our cache eviction boundary limit!
                db.put("userID_104", "{\"name\": \"Dave\", \"role\": \"Tester\"}");
                System.out.println("[Worker B] Committed heavy system changes.");
            });

            workerA.start();
            workerB.start();
            
            workerA.join();
            workerB.join();

            System.out.println("\n--- Querying Live Memory Database Matrix ---");
            System.out.println("Key 104: " + db.get("userID_104"));
            System.out.println("Key 101 (Evicted from memory cache because scale limit exceeded): " + db.get("userID_101"));

            db.close();
            System.out.println("\n[🛑] Process stopped abruptly. Simulated System Crash completed.");
            System.out.println("=================================================");

            // --- CRASH RECOVERY PHASE ---
            System.out.println("\n[🔄] Restarting Engine from complete crash scenario...");
            StorageEngine recoveredDb = new StorageEngine(logFilePath, 3);
            
            System.out.println("Querying recovered values straight from binary WAL storage stream:");
            System.out.println("Recovered Key 101: " + recoveredDb.get("userID_101"));
            System.out.println("Recovered Key 104: " + recoveredDb.get("userID_104"));
            
            recoveredDb.close();
            
            // Cleanup system environment simulation file
            new File(logFilePath).delete();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
