import java.util.Scanner;

public class AndOrNot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Campus Library & Discount System ===");
        
        // --- Gathering Inputs (Accepting Yes/No) ---
        System.out.print("Are you a currently enrolled student? (Yes/No): ");
        String studentInput = scanner.next();
        boolean isStudent = studentInput.equalsIgnoreCase("Yes") || studentInput.equalsIgnoreCase("Y");

        System.out.print("Do you have a valid Student ID? (Yes/No): ");
        String idInput = scanner.next();
        boolean hasID = idInput.equalsIgnoreCase("Yes") || idInput.equalsIgnoreCase("Y");

        System.out.print("Are you a Faculty Member? (Yes/No): ");
        String facultyInput = scanner.next();
        boolean isFaculty = facultyInput.equalsIgnoreCase("Yes") || facultyInput.equalsIgnoreCase("Y");

        System.out.print("Do you have unpaid library fines? (Yes/No): ");
        String finesInput = scanner.next();
        boolean hasFines = finesInput.equalsIgnoreCase("Yes") || finesInput.equalsIgnoreCase("Y");

        System.out.println("\n--- Evaluation Results ---");

        // 1. LOGICAL AND (&&)
        // BOTH conditions must be true to enter the restricted research lab.
        boolean canAccessLab = isStudent && hasID;
        System.out.println("1. Research Lab Access (AND): " + canAccessLab);
        if (canAccessLab) {
            System.out.println("   -> Access Granted: You are an enrolled student AND you have an ID.");
        } else {
            System.out.println("   -> Access Denied: You must be both an enrolled student AND have an ID.");
        }

        // 2. LOGICAL OR (||)
        // AT LEAST ONE condition must be true to qualify for a bookstore discount.
        boolean getsDiscount = isStudent || isFaculty;
        System.out.println("\n2. Bookstore Discount Qualification (OR): " + getsDiscount);
        if (getsDiscount) {
            System.out.println("   -> Discount Granted: You are either a Student OR a Faculty member.");
        } else {
            System.out.println("   -> No Discount: You are neither a Student nor Faculty.");
        }

        // 3. LOGICAL NOT (!)
        // Reverses the boolean value to check if the student is clear to borrow books.
        boolean canBorrowBooks = !hasFines;
        System.out.println("\n3. Book Borrowing Eligibility (NOT): " + canBorrowBooks);
        if (canBorrowBooks) {
            System.out.println("   -> Eligible to Borrow: It is NOT true that you have unpaid fines.");
        } else {
            System.out.println("   -> Borrowing Blocked: You have unpaid fines.");
        }

        scanner.close();
    }
}