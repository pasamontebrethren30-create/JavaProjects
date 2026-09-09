import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char choice;

        System.out.println("=== Welcome to the Java Calculator ===");

        do {
            // 1. Request the first number
            System.out.print("\nEnter your first number: ");
            double num1 = scanner.nextDouble();

            // 2. Request the operator
            System.out.print("Choose an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            // 3. Request the second number
            System.out.print("Enter your second number: ");
            double num2 = scanner.nextDouble();

            double result;

            // 4. Perform the calculation
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
                    break;

                case '-':
                    result = num1 - num2;
                    System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
                    break;

                case '*':
                    result = num1 * num2;
                    System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
                    break;

                case '/':
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                    } else {
                        result = num1 / num2;
                        System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
                    }
                    break;

                default:
                    System.out.println("Error: Invalid mathematical operator.");
            }

            // 5. Ask if the user wants another calculation
            System.out.print("\nDo you want to calculate again? (Y/N): ");
            choice = scanner.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        System.out.println("\nThank you for using the Java Calculator!");

        scanner.close();
    }
}