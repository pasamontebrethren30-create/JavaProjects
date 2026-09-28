import java.util.Scanner;

public class StudentManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("   !!WELCOME TO THE STUDENT MANAGEMENT SYSTEM!!      ");

        // OUTER LOOP: Keeps the application running until the user chooses to exit
        while (true) {
            System.out.println("\n MAIN MENU ");
            System.out.println("1. Grade Evaluator & Classification");
            System.out.println("2. Multi-Subject Average Calculator");
            System.out.println("3. Display Grading Scale Info");
            System.out.println("4. Exit Program");
            System.out.print("Select an option (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue; // BRANCHING STATEMENT: skip to next loop iteration
            }

            int mainChoice = scanner.nextInt();

            // SWITCH STATEMENT: Directs flow based on menu choice
            switch (mainChoice) {
                case 1:
                    System.out.println("\n GRADE EVALUATOR ");
                    System.out.print("Enter numerical grade (0-100): ");
                    
                    if (scanner.hasNextDouble()) {
                        double grade = scanner.nextDouble();

                        // CONDITIONALS -  validation and evaluation
                        if (grade < 0 || grade > 100) {
                            System.out.println("Error: Grade must be between 0 and 100.");
                        } else if (grade >= 90) {
                            System.out.println("Status: PASSED | Performance: Excellent (A)");
                        } else if (grade >= 80) {
                            System.out.println("Status: PASSED | Performance: Very Good (B)");
                        } else if (grade >= 75) {
                            System.out.println("Status: PASSED | Performance: Satisfactory (C)");
                        } else {
                            System.out.println("Status: FAILED | Performance: Needs Improvement (F)");
                        }
                    } else {
                        System.out.println("Invalid entry. Grade must be a number.");
                        scanner.next();
                    }
                    break; // BRANCHING STATEMENT: Exit switch block

                case 2:
                    System.out.println("\n MULTI-SUBJECT AVERAGE CALCULATOR ");
                    System.out.print("How many subjects do you want to calculate? ");
                    
                    if (scanner.hasNextInt()) {
                        int subjectCount = scanner.nextInt();

                        // CONDITIONALS -  validation
                        if (subjectCount <= 0) {
                            System.out.println("Subject count must be greater than zero.");
                            break;
                        }

                        double total = 0;
                        int validCount = 0;

                        // LOOP (FOR LOOP): Iterate through subjects
                        for (int i = 1; i <= subjectCount; i++) {
                            System.out.print("Enter grade for Subject " + i + " (or -1 to skip rest): ");
                            double subGrade = scanner.nextDouble();

                            // BRANCHING STATEMENT: Early exit using break
                            if (subGrade == -1) {
                                System.out.println("Calculation interrupted by user.");
                                break; // Stops adding further subjects
                            }

                            // BRANCHING STATEMENT: Skip invalid inputs using continue
                            if (subGrade < 0 || subGrade > 100) {
                                System.out.println("Skipping invalid grade: " + subGrade);
                                continue; // Skip to next iteration without adding to total
                            }

                            total += subGrade;
                            validCount++;
                        }

                        // CONDITIONALS & Output
                        if (validCount > 0) {
                            double average = total / validCount;
                            System.out.printf("Total Valid Subjects: %d\n", validCount);
                            System.out.printf("Average Grade: %.2f\n", average);
                            
                            if (average >= 75.0) {
                                System.out.println("Overall Academic Status: PASSED");
                            } else {
                                System.out.println("Overall Academic Status: FAILED");
                            }
                        } else {
                            System.out.println("No valid grades were entered.");
                        }
                    } else {
                        System.out.println("Invalid subject count.");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.println("\n GRADING SCALE INFORMATION ");
                    System.out.println("Grade Ranges:");
                    System.out.println("  90 - 100 : Excellent");
                    System.out.println("  80 - 89  : Very Good");
                    System.out.println("  75 - 79  : Satisfactory");
                    System.out.println("  Below 75 : Failed");
                    break;

                case 4:
                    System.out.println("\nExiting program. Thank you!");
                    scanner.close();
                    return; // BRANCHING STATEMENT: Terminates the main method/program

                default:
                    System.out.println("Invalid option! Please pick between 1 and 4.");
                    break;
            }
        }
    }
}