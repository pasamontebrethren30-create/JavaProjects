public class Heart {
    public static void main(String[] args) throws InterruptedException {
        String msg = "Connecting to heartbeat server...\nAccess Granted.\nI just wanted to say...";
        
        for (char c : msg.toCharArray()) {
            System.out.print(c);
            Thread.sleep(80); // Creates a typing effect
        }
        System.out.println("\n");
        Thread.sleep(1000);

        // Prints a giant ASCII Heart
        System.out.println("   ******       ******   ");
        System.out.println(" ********     ******** ");
        System.out.println("**********   **********");
        System.out.println(" ********************* ");
        System.out.println("  *******************  ");
        System.out.println("    ***************    ");
        System.out.println("      ***********      ");
        System.out.println("        *******        ");
        System.out.println("          ***          ");
        System.out.println("           *           ");
        
        System.out.println("\nI love you! ");
    }
}
