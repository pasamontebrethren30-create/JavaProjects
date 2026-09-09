import java.util.Scanner;

public class MadLibsGame {
    public static void main(String[] args) {
        // Initialize the Scanner object for user console input
        Scanner input = new Scanner(System.in != null ? System.in : System.in);

        System.out.println("=== Welcome to the Java Mad Libs Game! ===");
        System.out.println("Please provide the requested types of words below.\n");

        // 1. Gather all inputs from the player
        System.out.print("Enter an adjective (descriptive word): ");
        String adjective1 = input.nextLine();

        System.out.print("Enter a nationality (e.g., American, Martian): ");
        String nationality = input.nextLine();

        System.out.print("Enter a person's name: ");
        String personName = input.nextLine();

        System.out.print("Enter a noun (an object): ");
        String noun1 = input.nextLine();

        System.out.print("Enter an adjective: ");
        String adjective2 = input.nextLine();

        System.out.print("Enter a plural noun: ");
        String pluralNoun = input.nextLine();

        System.out.print("Enter a verb ending in '-ing': ");
        String verbIng = input.nextLine();

        System.out.print("Enter a body part: ");
        String bodyPart = input.nextLine();

        // 2. Print out the completed funny story
        System.out.println("\n--- Here is your Mad Libs Story! ---");
        System.out.println("Our story begins on a very " + adjective1 + " morning.");
        System.out.println("A brave " + nationality + " astronaut named " + personName + " was getting ready.");
        System.out.println("They securely packed a " + noun1 + " inside their spacesuit.");
        System.out.println("Suddenly, a " + adjective2 + " alien appeared outside the window!");
        System.out.println("The alien was throwing " + pluralNoun + " and aggressively " + verbIng + ".");
        System.out.println("Shocked by this sight, " + personName + " accidentally tripped over their own " + bodyPart + ".");
        System.out.println("What a wild adventure into outer space!");

        // 3. Close the scanner resource safely
        input.close();
    }
}
