import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        Random random = new Random();
        
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        int guess = 0;
        
        System.out.println("Guess a number between 1 and 100!");

        while (guess != numberToGuess) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            }
        }

        System.out.println("Correct! You won in " + attempts + " tries.");
        scanner.close();
    }
}
