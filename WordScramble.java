import java.util.Random;
import java.util.Scanner;

public class WordScramble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] wordBank = {"java", "developer", "compiler", "variable", "database", "algorithm", "Cloud","Programming", "Calculator"};
        int score = 0;
        int totalRounds = 7;

        System.out.println("🧠 Welcome to Word Scramble! 🧠");
        System.out.println("Unscramble the programming words below. You have " + totalRounds + " rounds!");

        for (int round = 1; round <= totalRounds; round++) {
            String originalWord = wordBank[random.nextInt(wordBank.length)];
            
            // Turn string into an array of characters to jumble them up
            char[] letters = originalWord.toCharArray();
            for (int i = 0; i < letters.length; i++) {
                int randomIndex = random.nextInt(letters.length);
                char temp = letters[i];
                letters[i] = letters[randomIndex];
                letters[randomIndex] = temp;
            }
            
            String scrambledWord = new String(letters);
            System.out.println("\n--- Round " + round + " ---");
            System.out.println("Scrambled letters: " + scrambledWord);
            System.out.print("Your guess: ");
            
            String playerGuess = scanner.nextLine().trim().toLowerCase();

            if (playerGuess.equals(originalWord)) {
                System.out.println("✨ Correct! +10 Points.");
                score += 10;
            } else {
                System.out.println("❌ Wrong! The correct word was: " + originalWord);
            }
        }

        System.out.println("\n=================================");
        System.out.println("Game Finished! Your total score: " + score + " points.");
        System.out.println("=================================");
        scanner.close();
    }
}
