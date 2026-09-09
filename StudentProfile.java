import java.util.Scanner;

public class StudentProfile {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("STUDENT INFORMATION:");

        // Fullname
        System.out.print("Enter your Fullname: ");
        String fullName = input.nextLine();

        // Strict Age Validation (No negative numbers or 0)
        int age = -1;
        while (age <= 0) {
            System.out.print("Enter your Age: ");
            if (input.hasNextInt()) {
                age = input.nextInt();
                input.nextLine(); // Buffer clear
                if (age <= 0) {
                    System.out.println("Invalid input! Age must be a positive number.\n");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer for age.\n");
                input.nextLine(); // Clear invalid input from buffer
            }
        }

        String course = "";
        while (!course.toUpperCase().startsWith("BS") && 
               !course.toUpperCase().startsWith("BA") && 
               !course.toUpperCase().startsWith("B")) {
            System.out.print("Enter your Course: ");
            course = input.nextLine().trim();

            if (!course.toUpperCase().startsWith("BS") && 
                !course.toUpperCase().startsWith("BA") && 
                !course.toUpperCase().startsWith("B")) {
                System.out.println("Invalid course! Course must start with 'BS', 'BA', or 'B'.\n");
            }
        }

        String yearLevel = "";
        while (!yearLevel.equalsIgnoreCase("1st Year") && 
               !yearLevel.equalsIgnoreCase("2nd Year") && 
               !yearLevel.equalsIgnoreCase("3rd Year") && 
               !yearLevel.equalsIgnoreCase("4th Year") && 
               !yearLevel.equalsIgnoreCase("5th Year")) {
            System.out.print("Enter your Year Level (1st Year - 5th Year): ");
            yearLevel = input.nextLine().trim();

            if (!yearLevel.equalsIgnoreCase("1st Year") && 
                !yearLevel.equalsIgnoreCase("2nd Year") && 
                !yearLevel.equalsIgnoreCase("3rd Year") && 
                !yearLevel.equalsIgnoreCase("4th Year") && 
                !yearLevel.equalsIgnoreCase("5th Year")) {
                System.out.println("Invalid year level! Please enter '1st Year', '2nd Year', '3rd Year', '4th Year', or '5th Year'.\n");
            }
        }

        System.out.print("Enter your Section: ");
        String section = input.nextLine();

        double averageGrade = -1;
        while (averageGrade < 0 || averageGrade > 100) {
            System.out.print("Enter your Average Grade (0 - 100): ");
            if (input.hasNextDouble()) {
                averageGrade = input.nextDouble();
                input.nextLine(); // Buffer clear
                if (averageGrade < 0 || averageGrade > 100) {
                    System.out.println("Invalid grade! Average grade cannot be negative or over 100.\n");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid decimal number.\n");
                input.nextLine(); 
            }
        }

        System.out.print("Enter your Middle Initial: ");
        char middleInitial = input.nextLine().charAt(0);

        String response = "";
        while (!response.equalsIgnoreCase("Yes") && !response.equalsIgnoreCase("No")) {
            System.out.print("Enrollment Status (Yes/No): ");
            response = input.nextLine().trim();

            if (!response.equalsIgnoreCase("Yes") && !response.equalsIgnoreCase("No")) {
                System.out.println("Invalid input! Please enter only 'Yes' or 'No'.\n");
            }
        }
        
        boolean enrollmentStatus = response.equalsIgnoreCase("Yes");

        // Impormasyon ng mga MALALAKAS
        System.out.println("\n===== STUDENT DETAILS =====");
        System.out.println("Fullname: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Course/Program: " + course);
        System.out.println("Year Level: " + yearLevel);
        System.out.println("Section: " + section);
        System.out.println("Average Grade: " + averageGrade);
        System.out.println("Middle Initial: " + middleInitial);
        System.out.println("Enrollment Status: " + (enrollmentStatus ? "Yes" : "No"));

        input.close();
    }
}