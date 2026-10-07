import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FACULTYBASIC extends Application {

    private BorderPane mainLayout;
    private VBox contentArea;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Faculty Management System");

        mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(15));

        // =========================
        // HEADER
        // =========================
        Label title = new Label("FACULTY MANAGEMENT SYSTEM");
        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle = new Label("Faculty Information and Management System");
        subtitle.setStyle("-fx-font-size: 14px;");

        VBox header = new VBox(5, title, subtitle);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);

        mainLayout.setTop(header);

        // =========================
        // SIDE MENU
        // =========================
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(15));
        menu.setPrefWidth(220);

        Button gradeButton = new Button("1. Faculty Grade Evaluator");
        Button workloadButton = new Button("2. Faculty Workload Calculator");
        Button statusButton = new Button("3. Faculty Status Checker");
        Button scaleButton = new Button("4. Grading Scale");
        Button exitButton = new Button("5. Exit");

        gradeButton.setMaxWidth(Double.MAX_VALUE);
        workloadButton.setMaxWidth(Double.MAX_VALUE);
        statusButton.setMaxWidth(Double.MAX_VALUE);
        scaleButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setMaxWidth(Double.MAX_VALUE);

        menu.getChildren().addAll(
                gradeButton,
                workloadButton,
                statusButton,
                scaleButton,
                exitButton
        );

        mainLayout.setLeft(menu);

        // =========================
        // CONTENT AREA
        // =========================
        contentArea = new VBox(15);
        contentArea.setPadding(new Insets(20));

        showWelcomePage();

        mainLayout.setCenter(contentArea);

        // =========================
        // BUTTON ACTIONS
        // =========================
        gradeButton.setOnAction(e -> showGradeEvaluator());
        workloadButton.setOnAction(e -> showWorkloadCalculator());
        statusButton.setOnAction(e -> showStatusChecker());
        scaleButton.setOnAction(e -> showGradingScale());

        exitButton.setOnAction(e -> {

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Exit System");
            confirm.setHeaderText("Exit Faculty Management System?");
            confirm.setContentText("Are you sure you want to exit?");

            confirm.showAndWait().ifPresent(response -> {

                if (response.getText().equals("OK")) {
                    primaryStage.close();
                }
            });
        });

        Scene scene = new Scene(mainLayout, 1050, 650);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // =========================================================
    // WELCOME PAGE
    // =========================================================

    private void showWelcomePage() {

        contentArea.getChildren().clear();

        Label welcome = new Label("Welcome to the Faculty Management System");
        welcome.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        Label description = new Label(
                "Select a function from the menu to manage faculty information, " +
                "evaluate grades, calculate workload, or check faculty status."
        );

        description.setWrapText(true);
        description.setStyle("-fx-font-size: 15px;");

        contentArea.getChildren().addAll(
                welcome,
                description
        );
    }

    // =========================================================
    // 1. FACULTY GRADE EVALUATOR
    // =========================================================

    private void showGradeEvaluator() {

        contentArea.getChildren().clear();

        Label title = new Label("Faculty Grade Evaluator");
        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label instruction = new Label(
                "Enter the faculty member's evaluation grade."
        );

        TextField gradeField = new TextField();
        gradeField.setPromptText("Enter grade (0-100)");

        Button evaluateButton = new Button("Evaluate Grade");

        Label result = new Label();
        result.setStyle("-fx-font-size: 15px;");

        evaluateButton.setOnAction(e -> {

            String input = gradeField.getText().trim();

            if (input.isEmpty()) {
                showError("Please enter a grade.");
                return;
            }

            try {

                double grade = Double.parseDouble(input);

                // RELATIONAL OPERATORS
                if (grade > 100) {
                    showError("Grade cannot be greater than 100.");
                    return;
                }

                if (grade < 0) {
                    showError("Grade cannot be less than 0.");
                    return;
                }

                double rating;

                // RELATIONAL OPERATORS
                if (grade >= 98 && grade <= 100) {
                    rating = 1.00;

                } else if (grade >= 95 && grade <= 97) {
                    rating = 1.25;

                } else if (grade >= 92 && grade <= 94) {
                    rating = 1.50;

                } else if (grade >= 89 && grade <= 91) {
                    rating = 1.75;

                } else if (grade >= 86 && grade <= 88) {
                    rating = 2.00;

                } else if (grade >= 83 && grade <= 85) {
                    rating = 2.25;

                } else if (grade >= 80 && grade <= 82) {
                    rating = 2.50;

                } else if (grade >= 77 && grade <= 79) {
                    rating = 2.75;

                } else if (grade >= 75 && grade <= 76) {
                    rating = 3.00;

                } else {
                    rating = 5.00;
                }

                String performance;

                if (rating <= 3.00) {
                    performance = "PASSED";
                } else {
                    performance = "FAILED";
                }

                result.setText(
                        String.format(
                                "Grade: %.0f%%%nRating: %.2f%nResult: %s",
                                grade,
                                rating,
                                performance
                        )
                );

            } catch (NumberFormatException ex) {

                showError("Invalid input! Please enter a valid number.");
            }
        });

        contentArea.getChildren().addAll(
                title,
                instruction,
                gradeField,
                evaluateButton,
                result
        );
    }

    // =========================================================
    // 2. FACULTY WORKLOAD CALCULATOR
    // =========================================================

    private void showWorkloadCalculator() {

        contentArea.getChildren().clear();

        Label title = new Label("Faculty Workload Calculator");
        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label instruction = new Label(
                "Calculate total workload based on classes and hours per class."
        );

        TextField classesField = new TextField();
        classesField.setPromptText("Number of classes");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours per class");

        Button calculateButton = new Button("Calculate Workload");

        Label result = new Label();
        result.setStyle("-fx-font-size: 15px;");

        calculateButton.setOnAction(e -> {

            try {

                int classes = Integer.parseInt(
                        classesField.getText().trim()
                );

                double hoursPerClass = Double.parseDouble(
                        hoursField.getText().trim()
                );

                // RELATIONAL OPERATORS
                if (classes <= 0) {
                    showError("Number of classes must be greater than 0.");
                    return;
                }

                if (hoursPerClass <= 0) {
                    showError("Hours per class must be greater than 0.");
                    return;
                }

                // ARITHMETIC OPERATOR *
                double totalHours = classes * hoursPerClass;

                // ARITHMETIC OPERATOR /
                double averageHours = totalHours / classes;

                // ARITHMETIC OPERATOR +
                double adjustedHours = totalHours + 2;

                // ARITHMETIC OPERATOR -
                double remainingHours = 40 - totalHours;

                // ARITHMETIC OPERATOR %
                int remainder = classes % 2;

                String classType;

                // RELATIONAL + LOGICAL OPERATOR
                if (classes >= 1 && classes <= 3) {
                    classType = "Light Teaching Load";

                } else if (classes >= 4 && classes <= 6) {
                    classType = "Moderate Teaching Load";

                } else {
                    classType = "Heavy Teaching Load";
                }

                result.setText(
                        String.format(
                                "Total Workload: %.2f hours%n" +
                                "Average Hours per Class: %.2f hours%n" +
                                "Adjusted Workload (+2): %.2f hours%n" +
                                "Remaining from 40 Hours: %.2f hours%n" +
                                "Class Count Remainder: %d%n" +
                                "Workload Classification: %s",
                                totalHours,
                                averageHours,
                                adjustedHours,
                                remainingHours,
                                remainder,
                                classType
                        )
                );

            } catch (NumberFormatException ex) {

                showError(
                        "Invalid input! Please enter valid numbers."
                );
            }
        });

        contentArea.getChildren().addAll(
                title,
                instruction,
                classesField,
                hoursField,
                calculateButton,
                result
        );
    }

    // =========================================================
    // 3. FACULTY STATUS CHECKER
    // =========================================================

    private void showStatusChecker() {

        contentArea.getChildren().clear();

        Label title = new Label("Faculty Status Checker");
        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label instruction = new Label(
                "Check faculty employment status using faculty ID " +
                "and years of experience."
        );

        TextField idField = new TextField();
        idField.setPromptText("Enter Faculty ID");

        TextField yearsField = new TextField();
        yearsField.setPromptText("Years of experience");

        Button checkButton = new Button("Check Status");

        Label result = new Label();
        result.setStyle("-fx-font-size: 15px;");

        checkButton.setOnAction(e -> {

            String facultyID = idField.getText().trim();

            try {

                int years = Integer.parseInt(
                        yearsField.getText().trim()
                );

                // RELATIONAL OPERATOR
                boolean validID = !facultyID.isEmpty();

                // RELATIONAL OPERATOR
                boolean experienced = years >= 3;

                // RELATIONAL OPERATOR
                boolean newFaculty = years < 3;

                // LOGICAL OPERATOR &&
                boolean qualified = validID && experienced;

                // LOGICAL OPERATOR ||
                boolean needsOrientation = newFaculty || !validID;

                // LOGICAL OPERATOR !
                boolean notNewFaculty = !newFaculty;

                if (qualified) {

                    result.setText(
                            "Faculty Status: QUALIFIED\n" +
                            "The faculty member has a valid ID and at least " +
                            "3 years of experience.\n\n" +
                            "Experienced Faculty: " + notNewFaculty
                    );

                } else if (needsOrientation) {

                    result.setText(
                            "Faculty Status: NEEDS REVIEW\n" +
                            "The faculty member may need orientation " +
                            "or additional information."
                    );

                } else {

                    result.setText(
                            "Faculty Status: NOT QUALIFIED"
                    );
                }

            } catch (NumberFormatException ex) {

                showError(
                        "Invalid input! Please enter a valid number of years."
                );
            }
        });

        contentArea.getChildren().addAll(
                title,
                instruction,
                idField,
                yearsField,
                checkButton,
                result
        );
    }

    // =========================================================
    // 4. GRADING SCALE
    // =========================================================

    private void showGradingScale() {

        contentArea.getChildren().clear();

        Label title = new Label("Faculty Grading Scale");
        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label description = new Label(
                "Reference table for converting percentage grades into ratings."
        );

        description.setStyle("-fx-font-size: 14px;");

        GridPane table = new GridPane();

        table.setHgap(30);
        table.setVgap(8);
        table.setPadding(new Insets(15));

        // HEADER
        Label ratingHeader = new Label("RATING");
        Label percentageHeader = new Label("PERCENTAGE");

        ratingHeader.setStyle("-fx-font-weight: bold;");
        percentageHeader.setStyle("-fx-font-weight: bold;");

        table.add(ratingHeader, 0, 0);
        table.add(percentageHeader, 1, 0);

        // GRADING SCALE
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

        // LOOP
        for (int i = 0; i < gradingScale.length; i++) {

            Label rating = new Label(gradingScale[i][0]);
            Label percentage = new Label(gradingScale[i][1]);

            table.add(rating, 0, i + 1);
            table.add(percentage, 1, i + 1);
        }

        contentArea.getChildren().addAll(
                title,
                description,
                table
        );
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Invalid Input");
        alert.setHeaderText("Input Error");
        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        launch(args);
    }
}