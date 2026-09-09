import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n1. Add Task | 2. View Tasks | 3. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            if (choice == 1) {
                System.out.print("Enter task description: ");
                tasks.add(scanner.nextLine());
            } else if (choice == 2) {
                System.out.println("\n--- Your Tasks ---");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }
}
