import java.util.Scanner;

public class AndOrNotSimple {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" STUDENT ACCESS SYSTEM ");

        System.out.print("Are you a student? (Yes/No): ");
        boolean student = scanner.next().equalsIgnoreCase("Yes");

        System.out.print("Do you have a student ID? (Yes/No): ");
        boolean id = scanner.next().equalsIgnoreCase("Yes");

        System.out.print("Are you a faculty member? (Yes/No): ");
        boolean faculty = scanner.next().equalsIgnoreCase("Yes");

        System.out.print("Do you have library fines? (Yes/No): ");
        boolean fines = scanner.next().equalsIgnoreCase("Yes");

        // AND
        boolean labAccess = student && id;
        System.out.println("\nAND - Can access the lab: " + labAccess);

        // OR
        boolean discount = student || faculty;
        System.out.println("OR - Can get a discount: " + discount);

        // NOT
        boolean canBorrow = !fines;
        System.out.println("NOT - Can borrow books: " + canBorrow);

        scanner.close();
    }
}