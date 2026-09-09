import java.util.Scanner;

public class BugFix {
    public static void main(String[] args) throws InterruptedException {
        Scanner inputScanner = new Scanner(System.in);
        boolean applicationIsBroken = true;
        int errorCount = 0;

        System.out.println("CRITICAL ERROR: System overflow detected in crush_algorithm.exe.");
        System.out.println("Hint: Enter the correct 'patch key' to override the loop.\n");
        Thread.sleep(1000);

        while (applicationIsBroken) {
            errorCount++;
            System.out.println("[Error #" + errorCount + "] Thinking about you too much... looping endlessly.");
            System.out.print("Enter patch key to fix: ");
            
            String userPatchAttempt = inputScanner.nextLine();
            
            // Actively tests the user input variable against the passcode
            if (userPatchAttempt.equalsIgnoreCase("is") || userPatchAttempt.contains("is so")) {
                applicationIsBroken = false; 
                System.out.println("\n[SUCCESS] Loop broken. Variables stabilized.");
                System.out.println("Total attempts required: " + errorCount);
                System.out.println("System unlocked: Let's grab dinner Friday evening! 🍕");
            } else {
                System.out.println("Incorrect patch key. System remains unstable.\n");
                Thread.sleep(500);
            }
        }
        inputScanner.close();
    }
}
