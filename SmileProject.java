public class SmileProject {
    public static void main(String[] args) {
        // Change this name to her name!
        String name = "Jiyanah"; 
        
        System.out.println("Running highly advanced romantic calculations...");
        delay(1500);
        System.out.println("Analyzing smile parameters...");
        delay(1200);
        System.out.println("Result found:\n");
        delay(1000);

        // Drawing the top of the heart
        System.out.println("   ******       ******   ");
        System.out.println(" **      **   **      ** ");
        System.out.println("**         ***         **");
        
        // Middle of the heart with her name centered
        System.out.println("** You're Special in my **");
        System.out.println(" **   heart " + padName(name) + "    ** ");
        
        // Drawing the bottom of the heart
        System.out.println("  **                 **  ");
        System.out.println("    **             **    ");
        System.out.println("      **         **      ");
        System.out.println("        **     **        ");
        System.out.println("          ** **          ");
        System.out.println("            *            ");
        
        System.out.println("\n(Yes, I actually coded this just to make you smile. Mission accomplished?)");
    }

    // Helper method to make the program pause between lines for a typing effect
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Helper method to keep the name perfectly centered inside the heart
    public static String padName(String name) {
        int targetLength = 7;
        if (name.length() >= targetLength) return name.substring(0, targetLength);
        StringBuilder sb = new StringBuilder(name);
        while (sb.length() < targetLength) {
            if (sb.length() % 2 == 0) sb.append(" ");
            else sb.insert(0, " ");
        }
        return sb.toString();
    }
}
