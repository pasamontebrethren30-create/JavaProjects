import java.util.Scanner;

public class SimpleATMSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000000.00; 
        int choice = 0;

        System.out.println("=== Welcome to the Java ATM ===");

        // The loop continues until the user types 4
        while (choice != 4) {
            System.out.println("\nATM Menu: 1. Balance | 2. Deposit | 3. Withdraw | 4. Exit");
            System.out.print("Select an option: ");
            
            // Check if the user actually typed a number to prevent crashes
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number (1-4).");
                scanner.next(); // Clear the bad input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Current balance: $" + balance);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double deposit = scanner.nextDouble();
                    balance += deposit;
                    System.out.println("Success! Deposited: $" + deposit);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawal = scanner.nextDouble();
                    if (withdrawal > balance) {
                        System.out.println("Error: Insufficient funds.");
                    } else {
                        balance -= withdrawal;
                        System.out.println("Success! Withdrew: $" + withdrawal);
                    }
                    break;
                case 4:
                    System.out.println("Thank you for banking with us.");
                    break;
                default:
                    System.out.println("Invalid option. Choose 1, 2, 3, or 4.");
            }
        }
        
        scanner.close(); // Clean up system memory
    }
}
