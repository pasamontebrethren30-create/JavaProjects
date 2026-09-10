import java.util.Scanner;

public class AdditionProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        int number1 = input.nextInt();

        System.out.print("Enter the second number: ");
        int number2 = input.nextInt();
        
        System.out.print("Enter the third number: ");
        int number3 = input.nextInt();
        
        System.out.print("Enter the fourth number: ");
        int number4 = input.nextInt();

        int total = number1 + number2 + number3 + number4;

        System.out.println("The total is: " + total);

        input.close();
    }
}