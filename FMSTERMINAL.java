import java.util.Scanner;

public class FMSTERMINAL {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n");
            System.out.println(" FACULTY MANAGEMENT SYSTEM");
            System.out.println("");
            System.out.println("1. Grade Evaluator");
            System.out.println("2. Workload Calculator");
            System.out.println("3. Faculty Status");
            System.out.println("4. Grading Scale");
            System.out.println("5. Exit Program");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showGradeEvaluator();
                    break;
                case "2":
                    showWorkloadCalculator();
                    break;
                case "3":
                    showFacultyStatus();
                    break;
                case "4":
                    showGradingScale();
                    break;
                case "5":
                    System.out.println("Thank you for using the Faculty Management System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private static void showGradeEvaluator() {
        System.out.println("\n--- FACULTY GRADE EVALUATOR ---");
        System.out.println("Enter a numerical grade from 0 to 100.");
        System.out.print("Numerical Grade: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a grade.");
            return;
        }

        try {
            double grade = Double.parseDouble(input);

            if (!Double.isFinite(grade) || grade < 0 || grade > 100) {
                System.out.println("Invalid grade. Enter a value from 0 to 100.");
                return;
            }

            double rating;
            String description;

            if (grade >= 98 && grade <= 100) {
                rating = 1.00;
                description = "Excellent";
            } else if (grade >= 95 && grade < 98) {
                rating = 1.25;
                description = "Excellent";
            } else if (grade >= 92 && grade < 95) {
                rating = 1.50;
                description = "Very Good";
            } else if (grade >= 89 && grade < 92) {
                rating = 1.75;
                description = "Very Good";
            } else if (grade >= 86 && grade < 89) {
                rating = 2.00;
                description = "Very Good";
            } else if (grade >= 83 && grade < 86) {
                rating = 2.25;
                description = "Good";
            } else if (grade >= 80 && grade < 83) {
                rating = 2.50;
                description = "Good";
            } else if (grade >= 77 && grade < 80) {
                rating = 2.75;
                description = "Satisfactory";
            } else if (grade >= 75 && grade < 77) {
                rating = 3.00;
                description = "Satisfactory";
            } else {
                rating = 5.00;
                description = "Failed";
            }

            boolean passed = rating <= 3.00;
            String result = passed ? "PASSED" : "FAILED";

            System.out.println("\nGRADE RESULT");
            System.out.printf("Percentage Grade: %.2f%%%n", grade);
            System.out.printf("Equivalent Rating: %.2f%n", rating);
            System.out.println("Classification: " + description);
            System.out.println("Result: " + result);

        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private static void showWorkloadCalculator() {
        System.out.println("\n--- FACULTY WORKLOAD CALCULATOR ---");
        System.out.print("Number of Classes: ");
        String subjectInput = scanner.nextLine().trim();
        System.out.print("Hours per Class: ");
        String hoursInput = scanner.nextLine().trim();

        if (subjectInput.isEmpty() || hoursInput.isEmpty()) {
            System.out.println("Invalid input. Please fill in all fields.");
            return;
        }

        try {
            int classes = Integer.parseInt(subjectInput);
            double hours = Double.parseDouble(hoursInput);

            if (classes <= 0 || !Double.isFinite(hours) || hours <= 0) {
                System.out.println("Invalid input. Number of classes and hours must be greater than zero.");
                return;
            }

            double totalHours = classes * hours;
            double averageHours = totalHours / classes;
            double administrativeHours = totalHours + 2;
            double remainingHours = 40 - totalHours;
            int remainder = classes % 2;

            String classType;
            if (classes >= 1 && classes <= 3) {
                classType = "Light Teaching Load";
            } else if (classes >= 4 && classes <= 6) {
                classType = "Moderate Teaching Load";
            } else {
                classType = "Heavy Teaching Load";
            }

            String parity = remainder == 0
                    ? "Even number of classes"
                    : "Odd number of classes";

            System.out.println("\nFACULTY WORKLOAD RESULT");
            System.out.println("Number of Classes: " + classes);
            System.out.printf("Hours per Class: %.2f%n", hours);
            System.out.printf("Total Teaching Hours: %.2f%n", totalHours);
            System.out.printf("Average Hours per Class: %.2f%n", averageHours);
            System.out.printf("With 2 Admin Hours: %.2f%n", administrativeHours);
            System.out.printf("Remaining from 40 Hours: %.2f%n", remainingHours);
            System.out.println("Class Count Remainder: " + remainder);
            System.out.println("Class Count: " + parity);
            System.out.println("Classification: " + classType);

        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Please enter a whole number for classes and a valid number for hours.");
        }
    }

    private static void showFacultyStatus() {
        System.out.println("\n--- FACULTY STATUS CHECKER ---");
        System.out.print("Faculty ID (format: 26-01234): ");
        String facultyID = scanner.nextLine().trim();
        System.out.print("Years of Experience: ");
        String experienceInput = scanner.nextLine().trim();

        if (facultyID.isEmpty() || experienceInput.isEmpty()) {
            System.out.println("Invalid input. Please fill in all fields.");
            return;
        }

        // Strict format: exactly two digits, a hyphen, then five digits.
        if (!facultyID.matches("\\d{2}-\\d{5}")) {
            System.out.println("Invalid Faculty ID. Required format: 26-01234 (two digits, hyphen, five digits).");
            return;
        }

        try {
            int years = Integer.parseInt(experienceInput);

            if (years < 0) {
                System.out.println("Invalid input. Years of experience cannot be negative.");
                return;
            }

            boolean validID = facultyID.matches("\\d{2}-\\d{5}");
            boolean experienced = years >= 3;
            boolean newFaculty = years < 3;

            // AND (&&)
            boolean regularFaculty = validID && experienced;
            // OR (||)
            boolean eligibleForOrientation = newFaculty || !validID;
            // NOT (!)
            boolean notNewFaculty = !newFaculty;

            if (regularFaculty) {
                System.out.println("\nFACULTY STATUS: REGULAR");
                System.out.println("Valid Faculty ID: YES");
                System.out.println("Experience: 3 years or more");
                System.out.println("Regular Faculty: YES");
                System.out.println("Not New Faculty: " + notNewFaculty);
            } else if (eligibleForOrientation) {
                System.out.println("\nFACULTY STATUS: ORIENTATION REQUIRED");
                System.out.println("The faculty member may need orientation because they are new or their ID needs verification.");
            } else {
                System.out.println("\nFACULTY STATUS: FOR VERIFICATION");
            }

        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Years of experience must be a whole number.");
        }
    }

    private static void showGradingScale() {
        System.out.println("\n--- GRADING SCALE ---");
        System.out.printf("%-12s %s%n", "RATING", "PERCENTAGE");
        System.out.println("------------------------------");

        String[][] gradingScale = {
            {"1.00", "98–100%"},
            {"1.25", "95–97%"},
            {"1.50", "92–94%"},
            {"1.75", "89–91%"},
            {"2.00", "86–88%"},
            {"2.25", "83–85%"},
            {"2.50", "80–82%"},
            {"2.75", "77–79%"},
            {"3.00", "75–76%"},
            {"5.00", "Below 75%"}
        };

        for (int i = 0; i < gradingScale.length; i++) {
            System.out.printf("%-12s %s%n", gradingScale[i][0], gradingScale[i][1]);
        }
    }
}
