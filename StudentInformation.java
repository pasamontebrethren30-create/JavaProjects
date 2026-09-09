import java.util.Scanner;

public class StudentInformation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("STUDENT INFORMATION:");

        System.out.print("Enter your Fullname: ");
        String fullName = input.nextLine();

        System.out.print("Enter your Age: ");
        int age = input.nextInt();
        input.nextLine();

        System.out.print("Enter your Course: ");
        String course = input.nextLine();

        System.out.print("Enter your Year Level: ");
        String yearLevel = input.nextLine();

        System.out.print("Enter your Section: ");
        String section = input.nextLine();

        System.out.print("Enter your Average Grade: ");
        double averageGrade = input.nextDouble();
        input.nextLine(); 

        System.out.print("Enter your Middle Initial: ");
        char middleInitial = input.nextLine().charAt(0);

        System.out.print("Are you currently enrolled? (Yes or No): ");
        String enrollmentResponse = input.nextLine().trim();
        boolean enrollmentStatus = enrollmentResponse.equalsIgnoreCase("Yes");

        // Impormasyon ng mga MALALAKAS
        System.out.println("\n===== STUDENT INFORMATION =====");
        System.out.println("Fullname: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Course/Program: " + course);
        System.out.println("Year Level: " + yearLevel);
        System.out.println("Section: " + section);
        System.out.println("Average Grade: " + averageGrade);
        System.out.println("Middle Initial: " + middleInitial);
        System.out.println("Enrollment Status: " + enrollmentStatus);

        input.close();
    }
}

