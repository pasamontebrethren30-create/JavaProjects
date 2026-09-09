public class FriendPrank {
    public static void main(String[] args) {
        // ANSI escape codes for coloring text
        String red = "\u001B[31m";
        String reset = "\u001B[0m";

        System.out.println("Processing urgent broadcast...");
        delay(1000);
        System.out.println("Target: Bisaya People");
        delay(1000);
        System.out.println("\nRendering image...\n");
        delay(1200);

        // More detailed and realistic hand orientation
        System.out.println("          .---.        ");
        System.out.println("          |   |        ");
        System.out.println("          |   |        ");
        System.out.println("          |   |        ");
        System.out.println("      .---|.  |---.    ");
        System.out.println("      |   ||  ||  |    ");
        System.out.println("   _  |   ||  ||  |  _ ");
        System.out.println("  | |_|   ||  ||  |_| |");
        System.out.println("  |   |   ||  ||  |   |");
        System.out.println("  \\_                  /");
        System.out.println("    |                | ");
        System.out.println("    |                | ");
        System.out.println("    |________________| ");

        // The bold message below the art
        System.out.println(red + "\n--- FUCK YOU! ---" + reset);
    }

    // Helper method to create a brief cinematic delay
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
