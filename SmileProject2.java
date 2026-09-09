public class SmileProject2 {
    public static void main(String[] args) {
        // Change this name to his name!
        String name = "Jarelle"; 
        
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
        System.out.println("**       Roxanne        **");
        System.out.println(" **    love " + padName(name) + "    ** ");
        
        // Drawing the bottom of the heart
        System.out.println("  **                 **  ");
        System.out.println("    **             **    ");
        System.out.println("      **         **      ");
        System.out.println("        **     **        ");
        System.out.println("          ** **          ");
        System.out.println("            *            ");
        
        System.out.println("\n(A couple that we failed to protect. )");
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
