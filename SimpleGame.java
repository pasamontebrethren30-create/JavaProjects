import java.util.Random;
import java.util.Scanner;

public class SimpleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Game choices array
        String[] rps = {"rock", "paper", "scissors"};
        
        System.out.println("=================================");
        System.out.println("  Welcome to Rock, Paper, Scissors! ");
        System.out.println("=================================");

        while (true) {
            // Get computer choice (random index 0, 1, or 2)
            String computerChoice = rps[random.nextInt(3)];
            String userChoice;

            // Loop until user enters a valid choice
            while (true) {
                System.out.print("\nEnter rock, paper, or scissors (or 'quit' to exit): ");
                userChoice = scanner.nextLine().toLowerCase().trim();

                if (userChoice.equals("quit") || userChoice.equals("rock") || 
                    userChoice.equals("paper") || userChoice.equals("scissors")) {
                    break;
                }
                System.out.println("Invalid choice. Please try again.");
            }

            // Check if player wants to exit
            if (userChoice.equals("quit")) {
                System.out.println("\nThanks for playing! Goodbye!");
                break;
            }

            System.out.println("Computer chose: " + computerChoice);

            // Determine the game winner
            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))
            ) {
                System.out.println("🎉 You win!");
            } else {
                System.out.println("😢 Computer wins!");
            }
        }
        
        scanner.close();
    }
}
