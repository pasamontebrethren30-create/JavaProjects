import java.util.Scanner;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("How many grades do you want to enter? ");
        int totalStudents = scanner.nextInt();
        int[] grades = new int[totalStudents];

        for (int i = 0; i < totalStudents; i++) {
            System.out.print("Enter grade for student " + (i + 1) + ": ");
            grades[i] = scanner.nextInt();
        }

        int sum = 0;
        int highest = grades[0];
        int lowest = grades[0];

        for (int grade : grades) {
            sum += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }

        System.out.println("\n--- Statistics ---");
        System.out.println("Average Grade: " + ((double) sum / totalStudents));
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
        scanner.close();
    }
}
