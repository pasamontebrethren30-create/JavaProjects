import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

public class FacultyManagementSystem extends Application {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String BACKGROUND = "#F5F7FA";
    private static final String CARD = "#FFFFFF";
    private static final String PRIMARY = "#2563EB";
    private static final String PRIMARY_DARK = "#1D4ED8";
    private static final String TEXT = "#1F2937";
    private static final String SECONDARY_TEXT = "#6B7280";
    private static final String SUCCESS = "#16A34A";
    private static final String DANGER = "#DC2626";
    private static final String BORDER = "#E5E7EB";
    private static final String SIDEBAR = "#111827";
    private static final String SIDEBAR_HOVER = "#1F2937";

    // =========================================================
    // MAIN LAYOUT
    // =========================================================

    private BorderPane mainLayout;
    private VBox contentPanel;

    // =========================================================
    // START PROGRAM
    // =========================================================

    @Override
    public void start(Stage stage) {

        stage.setTitle("Faculty Management System");

        mainLayout = new BorderPane();

        mainLayout.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        createMainUI();

        Scene scene = new Scene(
                mainLayout,
                1000,
                650
        );

        stage.setScene(scene);

        stage.setMinWidth(850);
        stage.setMinHeight(550);

        stage.show();
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createMainUI() {

        // ---------------------------------------------------------
        // SIDEBAR
        // ---------------------------------------------------------

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(245);
        sidebar.setMinWidth(245);

        sidebar.setPadding(
                new Insets(30, 20, 25, 20)
        );

        sidebar.setStyle(
                "-fx-background-color: " + SIDEBAR + ";"
        );

        Label logo = new Label("FMS");

        logo.setTextFill(Color.WHITE);

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        Label systemName = new Label(
                "Faculty\nManagement\nSystem"
        );

        systemName.setTextFill(
                Color.web("#D1D5DB")
        );

        systemName.setFont(
                Font.font("Arial", 16)
        );

        Label menuLabel = new Label("FACULTY MENU");

        menuLabel.setTextFill(
                Color.web("#9CA3AF")
        );

        menuLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Button gradeButton =
                createSidebarButton("Grade Evaluator");

        Button workloadButton =
                createSidebarButton("Workload Calculator");

        Button statusButton =
                createSidebarButton("Faculty Status");

        Button scaleButton =
                createSidebarButton("Grading Scale");

        Button exitButton =
                createSidebarButton("Exit Program");

        sidebar.getChildren().add(logo);

        sidebar.getChildren().add(
                createSpacer(8)
        );

        sidebar.getChildren().add(systemName);

        sidebar.getChildren().add(
                createSpacer(40)
        );

        sidebar.getChildren().add(menuLabel);

        sidebar.getChildren().add(
                createSpacer(12)
        );

        sidebar.getChildren().add(gradeButton);

        sidebar.getChildren().add(
                createSpacer(8)
        );

        sidebar.getChildren().add(workloadButton);

        sidebar.getChildren().add(
                createSpacer(8)
        );

        sidebar.getChildren().add(statusButton);

        sidebar.getChildren().add(
                createSpacer(8)
        );

        sidebar.getChildren().add(scaleButton);

        Region bottomSpace = new Region();

        VBox.setVgrow(
                bottomSpace,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(bottomSpace);

        sidebar.getChildren().add(exitButton);

        // ---------------------------------------------------------
        // CONTENT
        // ---------------------------------------------------------

        contentPanel = new VBox();

        contentPanel.setPadding(
                new Insets(35, 40, 35, 40)
        );

        contentPanel.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(contentPanel);

        showDashboard();

        // ---------------------------------------------------------
        // BUTTON ACTIONS
        // ---------------------------------------------------------

        gradeButton.setOnAction(
                event -> showGradeEvaluator()
        );

        workloadButton.setOnAction(
                event -> showWorkloadCalculator()
        );

        statusButton.setOnAction(
                event -> showFacultyStatus()
        );

        scaleButton.setOnAction(
                event -> showGradingScale()
        );

        exitButton.setOnAction(
                event -> confirmExit()
        );
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        contentPanel.getChildren().clear();

        VBox dashboard = new VBox();

        dashboard.setFillWidth(true);

        Label title = new Label("Faculty Dashboard");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label subtitle = new Label(
                "Welcome to your Faculty Management System."
        );

        subtitle.setFont(
                Font.font("Arial", 15)
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        dashboard.getChildren().add(title);

        dashboard.getChildren().add(
                createSpacer(5)
        );

        dashboard.getChildren().add(subtitle);

        dashboard.getChildren().add(
                createSpacer(30)
        );

        HBox cards = new HBox(18);

        cards.setFillHeight(true);

        VBox gradeCard = createInfoCard(
                "01",
                "Grade Evaluator",
                "Evaluate and classify faculty-entered grades."
        );

        VBox workloadCard = createInfoCard(
                "02",
                "Workload Calculator",
                "Calculate teaching hours and workload."
        );

        VBox statusCard = createInfoCard(
                "03",
                "Faculty Status",
                "Checking the faculty's eligibility."
        );

        HBox.setHgrow(
                gradeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                workloadCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusCard,
                Priority.ALWAYS
        );

        cards.getChildren().add(gradeCard);
        cards.getChildren().add(workloadCard);
        cards.getChildren().add(statusCard);

        dashboard.getChildren().add(cards);

        dashboard.getChildren().add(
                createSpacer(30)
        );

        VBox welcomeCard = createWhitePanel();

        Label welcomeTitle =
                new Label("Quick Start");

        welcomeTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        welcomeTitle.setTextFill(
                Color.web(TEXT)
        );

        Label welcomeText = new Label(
                "Use the sidebar to access faculty tools.\n"
                + "You can evaluate grades, calculate workload,\n"
                + "check faculty status, or view the grading scale."
        );

        welcomeText.setFont(
                Font.font("Arial", 14)
        );

        welcomeText.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        welcomeCard.getChildren().add(welcomeTitle);

        welcomeCard.getChildren().add(
                createSpacer(10)
        );

        welcomeCard.getChildren().add(welcomeText);

        dashboard.getChildren().add(welcomeCard);

        contentPanel.getChildren().add(dashboard);
    }

    // =========================================================
    // GRADE EVALUATOR
    // =========================================================

    private void showGradeEvaluator() {

        contentPanel.getChildren().clear();

        VBox panel = new VBox();

        Label title =
                new Label("Faculty Grade Evaluator");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label subtitle = new Label(
                "Enter a numerical grade from 0 to 100."
        );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        panel.getChildren().add(title);

        panel.getChildren().add(
                createSpacer(5)
        );

        panel.getChildren().add(subtitle);

        panel.getChildren().add(
                createSpacer(25)
        );

        VBox card = createWhitePanel();

        Label gradeLabel =
                new Label("Numerical Grade");

        gradeLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        gradeLabel.setTextFill(
                Color.web(TEXT)
        );

        TextField gradeField =
                createTextField();

        gradeField.setPromptText(
                "Enter grade from 0 to 100"
        );

        Button evaluateButton =
                createPrimaryButton(
                        "Evaluate Grade"
                );

        Label resultLabel =
                new Label(" ");

        resultLabel.setWrapText(true);

        resultLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        card.getChildren().add(gradeLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(gradeField);

        card.getChildren().add(
                createSpacer(18)
        );

        card.getChildren().add(evaluateButton);

        card.getChildren().add(
                createSpacer(20)
        );

        card.getChildren().add(resultLabel);

        evaluateButton.setOnAction(event -> {

            String input =
                    gradeField.getText().trim();

            if (input.isEmpty()) {

                showError(
                        "Invalid Input",
                        "Please enter a grade."
                );

                return;
            }

            try {

                double grade =
                        Double.parseDouble(input);

                // =================================================
                // RELATIONAL OPERATORS
                // =================================================

                if (grade < 0 || grade > 100) {

                    resultLabel.setTextFill(
                            Color.web(DANGER)
                    );

                    resultLabel.setText(
                            "Invalid grade. Enter a value from 0 to 100."
                    );

                    return;
                }

                double rating;
                String description;

                // =================================================
                // LOGICAL OPERATOR &&
                // =================================================

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

                // =================================================
                // RELATIONAL + LOGICAL OPERATORS
                // =================================================

                boolean passed =
                        rating <= 3.00;

                if (passed) {

                    resultLabel.setTextFill(
                            Color.web(SUCCESS)
                    );

                } else {

                    resultLabel.setTextFill(
                            Color.web(DANGER)
                    );
                }

                resultLabel.setText(
                        String.format(
                                "GRADE RESULT\n\n"
                                + "Percentage Grade: %.2f%%\n"
                                + "Equivalent Rating: %.2f\n"
                                + "Classification: %s\n"
                                + "Result: %s",
                                grade,
                                rating,
                                description,
                                passed ? "PASSED" : "FAILED"
                        )
                );

            } catch (NumberFormatException exception) {

                resultLabel.setTextFill(
                        Color.web(DANGER)
                );

                resultLabel.setText(
                        "Invalid input. Please enter a valid number."
                );
            }
        });

        panel.getChildren().add(card);

        contentPanel.getChildren().add(panel);
    }

    // =========================================================
    // WORKLOAD CALCULATOR
    // =========================================================

    private void showWorkloadCalculator() {

        contentPanel.getChildren().clear();

        VBox panel = new VBox();

        Label title =
                new Label("Faculty Workload Calculator");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label subtitle =
                new Label(
                        "Calculate teaching hours and workload."
                );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        panel.getChildren().add(title);

        panel.getChildren().add(
                createSpacer(5)
        );

        panel.getChildren().add(subtitle);

        panel.getChildren().add(
                createSpacer(25)
        );

        VBox card = createWhitePanel();

        Label subjectLabel =
                new Label("Number of Classes");

        subjectLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        subjectLabel.setTextFill(
                Color.web(TEXT)
        );

        TextField subjectField =
                createTextField();

        subjectField.setPromptText(
                "Example: 5"
        );

        Label hoursLabel =
                new Label("Hours per Class");

        hoursLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        hoursLabel.setTextFill(
                Color.web(TEXT)
        );

        TextField hoursField =
                createTextField();

        hoursField.setPromptText(
                "Example: 3"
        );

        Button calculateButton =
                createPrimaryButton(
                        "Calculate Workload"
                );

        TextArea resultArea =
                new TextArea();

        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefRowCount(8);

        resultArea.setFont(
                Font.font("Arial", 14)
        );

        resultArea.setStyle(
                "-fx-control-inner-background: #F9FAFB;"
                + "-fx-text-fill: #1F2937;"
                + "-fx-border-color: #E5E7EB;"
        );

        card.getChildren().add(subjectLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(subjectField);

        card.getChildren().add(
                createSpacer(15)
        );

        card.getChildren().add(hoursLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(hoursField);

        card.getChildren().add(
                createSpacer(18)
        );

        card.getChildren().add(calculateButton);

        card.getChildren().add(
                createSpacer(20)
        );

        card.getChildren().add(resultArea);

        calculateButton.setOnAction(event -> {

            String subjectInput =
                    subjectField.getText().trim();

            String hoursInput =
                    hoursField.getText().trim();

            if (subjectInput.isEmpty()
                    || hoursInput.isEmpty()) {

                showError(
                        "Invalid Input",
                        "Please fill in all fields."
                );

                return;
            }

            try {

                int classes =
                        Integer.parseInt(subjectInput);

                double hours =
                        Double.parseDouble(hoursInput);

                // =================================================
                // RELATIONAL OPERATORS
                // =================================================

                if (classes <= 0 || hours <= 0) {

                    showError(
                            "Invalid Input",
                            "Values must be greater than zero."
                    );

                    return;
                }

                // =================================================
                // ARITHMETIC OPERATORS
                // =================================================

                // Multiplication (*)
                double totalHours =
                        classes * hours;

                // Division (/)
                double averageHours =
                        totalHours / classes;

                // Addition (+)
                double administrativeHours =
                        totalHours + 2;

                // Subtraction (-)
                double remainingHours =
                        40 - totalHours;

                // Modulo (%)
                int remainder =
                        classes % 2;

                // =================================================
                // RELATIONAL + LOGICAL OPERATORS
                // =================================================

                String classType;

                if (classes >= 1 && classes <= 3) {

                    classType =
                            "Light Teaching Load";

                } else if (classes >= 4
                        && classes <= 6) {

                    classType =
                            "Moderate Teaching Load";

                } else {

                    classType =
                            "Heavy Teaching Load";
                }

                String parity;

                if (remainder == 0) {

                    parity =
                            "Even number of classes";

                } else {

                    parity =
                            "Odd number of classes";
                }

                resultArea.setText(
                        "FACULTY WORKLOAD RESULT\n\n"
                        + "Number of Classes: "
                        + classes
                        + "\n"
                        + "Hours per Class: "
                        + String.format(
                                "%.2f",
                                hours
                        )
                        + "\n\n"
                        + "Total Teaching Hours: "
                        + String.format(
                                "%.2f",
                                totalHours
                        )
                        + "\n"
                        + "Average Hours per Class: "
                        + String.format(
                                "%.2f",
                                averageHours
                        )
                        + "\n"
                        + "With 2 Admin Hours: "
                        + String.format(
                                "%.2f",
                                administrativeHours
                        )
                        + "\n"
                        + "Remaining from 40 Hours: "
                        + String.format(
                                "%.2f",
                                remainingHours
                        )
                        + "\n"
                        + "Class Count Remainder: "
                        + remainder
                        + "\n"
                        + "Class Count: "
                        + parity
                        + "\n"
                        + "Classification: "
                        + classType
                );

            } catch (NumberFormatException exception) {

                showError(
                        "Invalid Input",
                        "Please enter valid numbers."
                );
            }
        });

        panel.getChildren().add(card);

        contentPanel.getChildren().add(panel);
    }

    // =========================================================
    // FACULTY STATUS
    // =========================================================

    private void showFacultyStatus() {

        contentPanel.getChildren().clear();

        VBox panel = new VBox();

        Label title =
                new Label("Faculty Status Checker");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label subtitle =
                new Label(
                        "Checking the faculty's eligibility."
                );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        panel.getChildren().add(title);

        panel.getChildren().add(
                createSpacer(5)
        );

        panel.getChildren().add(subtitle);

        panel.getChildren().add(
                createSpacer(25)
        );

        VBox card = createWhitePanel();

        Label idLabel =
                new Label("Faculty ID");

        idLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        idLabel.setTextFill(
                Color.web(TEXT)
        );

        TextField idField =
                createTextField();

        idField.setPromptText(
                "Example: 26-01234"
        );

        Label experienceLabel =
                new Label("Years of Experience");

        experienceLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        experienceLabel.setTextFill(
                Color.web(TEXT)
        );

        TextField experienceField =
                createTextField();

        experienceField.setPromptText(
                "Example: 5"
        );

        Button checkButton =
                createPrimaryButton(
                        "Check Faculty Status"
                );

        Label resultLabel =
                new Label(" ");

        resultLabel.setWrapText(true);

        resultLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        card.getChildren().add(idLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(idField);

        card.getChildren().add(
                createSpacer(15)
        );

        card.getChildren().add(experienceLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(experienceField);

        card.getChildren().add(
                createSpacer(18)
        );

        card.getChildren().add(checkButton);

        card.getChildren().add(
                createSpacer(20)
        );

        card.getChildren().add(resultLabel);

        checkButton.setOnAction(event -> {

            String facultyID =
                    idField.getText().trim();

            String experienceInput =
                    experienceField.getText().trim();

            if (facultyID.isEmpty()
                    || experienceInput.isEmpty()) {

                showError(
                        "Invalid Input",
                        "Please fill in all fields."
                );

                return;
            }

            try {

                int years =
                        Integer.parseInt(experienceInput);

                // =================================================
                // RELATIONAL OPERATORS
                // =================================================

                boolean validID =
                        !facultyID.equalsIgnoreCase("NONE");

                boolean experienced =
                        years >= 3;

                boolean newFaculty =
                        years < 3;

                // =================================================
                // LOGICAL OPERATORS
                // =================================================

                // AND (&&)
                boolean regularFaculty =
                        validID && experienced;

                // OR (||)
                boolean eligibleForOrientation =
                        newFaculty || !validID;

                // NOT (!)
                boolean notNewFaculty =
                        !newFaculty;

                if (regularFaculty) {

                    resultLabel.setTextFill(
                            Color.web(SUCCESS)
                    );

                    resultLabel.setText(
                            "FACULTY STATUS: REGULAR\n\n"
                            + "Valid Faculty ID: YES\n"
                            + "Experience: 3 years or more\n"
                            + "Regular Faculty: YES\n"
                            + "Not New Faculty: "
                            + notNewFaculty
                    );

                } else if (eligibleForOrientation) {

                    resultLabel.setTextFill(
                            Color.web(PRIMARY)
                    );

                    resultLabel.setText(
                            "FACULTY STATUS: ORIENTATION REQUIRED\n\n"
                            + "The faculty member may need orientation "
                            + "because they are new or their ID needs verification."
                    );

                } else {

                    resultLabel.setTextFill(
                            Color.web(DANGER)
                    );

                    resultLabel.setText(
                            "FACULTY STATUS: FOR VERIFICATION"
                    );
                }

            } catch (NumberFormatException exception) {

                showError(
                        "Invalid Input",
                        "Years of experience must be a whole number."
                );
            }
        });

        panel.getChildren().add(card);

        contentPanel.getChildren().add(panel);
    }

    // =========================================================
    // GRADING SCALE
    // =========================================================

    private void showGradingScale() {

        contentPanel.getChildren().clear();

        VBox panel = new VBox();

        Label title =
                new Label("Grading Scale");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label subtitle =
                new Label(
                        "Official reference guide for percentage grades and equivalent ratings."
                );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        panel.getChildren().add(title);

        panel.getChildren().add(
                createSpacer(5)
        );

        panel.getChildren().add(subtitle);

        panel.getChildren().add(
                createSpacer(25)
        );

        VBox scaleCard =
                createWhitePanel();

        // =====================================================
        // TABLE HEADER
        // =====================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(
                        12,
                        20,
                        12,
                        20
                )
        );

        Label ratingHeader =
                new Label("RATING");

        ratingHeader.setPrefWidth(150);

        ratingHeader.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        ratingHeader.setTextFill(
                Color.web(TEXT)
        );

        Label percentageHeader =
                new Label("PERCENTAGE");

        percentageHeader.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        percentageHeader.setTextFill(
                Color.web(TEXT)
        );

        header.getChildren().add(ratingHeader);
        header.getChildren().add(percentageHeader);

        scaleCard.getChildren().add(header);

        scaleCard.getChildren().add(
                createSpacer(5)
        );

        // =====================================================
        // GRADING SCALE DATA
        // =====================================================

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

        // =====================================================
        // LOOP
        // =====================================================

        for (int i = 0; i < gradingScale.length; i++) {

            addScaleRow(
                    scaleCard,
                    gradingScale[i][0],
                    gradingScale[i][1]
            );
        }

        panel.getChildren().add(scaleCard);

        contentPanel.getChildren().add(panel);
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private Button createSidebarButton(
            String text) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(45);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        button.setTextFill(
                Color.web("#D1D5DB")
        );

        setSidebarNormalStyle(button);

        button.setOnMouseEntered(
                event -> setSidebarHoverStyle(button)
        );

        button.setOnMouseExited(
                event -> setSidebarNormalStyle(button)
        );

        return button;
    }

    private void setSidebarNormalStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color: "
                + SIDEBAR
                + ";"
                + "-fx-background-radius: 5;"
                + "-fx-padding: 10 15 10 15;"
                + "-fx-cursor: hand;"
        );
    }

    private void setSidebarHoverStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color: "
                + SIDEBAR_HOVER
                + ";"
                + "-fx-background-radius: 5;"
                + "-fx-padding: 10 15 10 15;"
                + "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private Button createPrimaryButton(
            String text) {

        Button button =
                new Button(text);

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setTextFill(
                Color.WHITE
        );

        button.setPadding(
                new Insets(
                        12,
                        20,
                        12,
                        20
                )
        );

        setPrimaryNormalStyle(button);

        button.setOnMouseEntered(
                event -> setPrimaryHoverStyle(button)
        );

        button.setOnMouseExited(
                event -> setPrimaryNormalStyle(button)
        );

        return button;
    }

    private void setPrimaryNormalStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color: "
                + PRIMARY
                + ";"
                + "-fx-background-radius: 6;"
                + "-fx-cursor: hand;"
        );
    }

    private void setPrimaryHoverStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color: "
                + PRIMARY_DARK
                + ";"
                + "-fx-background-radius: 6;"
                + "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private TextField createTextField() {

        TextField field =
                new TextField();

        field.setFont(
                Font.font("Arial", 15)
        );

        field.setPrefHeight(45);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color: white;"
                + "-fx-text-fill: black;"
                + "-fx-prompt-text-fill: #9CA3AF;"
                + "-fx-border-color: #E5E7EB;"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 4;"
                + "-fx-background-radius: 4;"
                + "-fx-padding: 8 12 8 12;"
        );

        return field;
    }

    // =========================================================
    // WHITE PANEL
    // =========================================================

    private VBox createWhitePanel() {

        VBox panel =
                new VBox();

        panel.setPadding(
                new Insets(25)
        );

        panel.setMaxWidth(
                Double.MAX_VALUE
        );

        panel.setStyle(
                "-fx-background-color: "
                + CARD
                + ";"
                + "-fx-border-color: "
                + BORDER
                + ";"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 5;"
                + "-fx-background-radius: 5;"
        );

        return panel;
    }

    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private VBox createInfoCard(
            String number,
            String title,
            String description) {

        VBox card =
                createWhitePanel();

        Label numberLabel =
                new Label(number);

        numberLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        numberLabel.setTextFill(
                Color.web(PRIMARY)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setFont(
                Font.font("Arial", 12)
        );

        descriptionLabel.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        card.getChildren().add(numberLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(titleLabel);

        card.getChildren().add(
                createSpacer(8)
        );

        card.getChildren().add(descriptionLabel);

        return card;
    }

    // =========================================================
    // GRADING SCALE ROW
    // =========================================================

    private void addScaleRow(
            VBox panel,
            String rating,
            String percentage) {

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        10,
                        20,
                        10,
                        20
                )
        );

        Label ratingLabel =
                new Label(rating);

        ratingLabel.setPrefWidth(150);

        ratingLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        ratingLabel.setTextFill(
                Color.web(PRIMARY)
        );

        Label percentageLabel =
                new Label(percentage);

        percentageLabel.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        percentageLabel.setTextFill(
                Color.web(TEXT)
        );

        row.getChildren().add(ratingLabel);

        row.getChildren().add(percentageLabel);

        panel.getChildren().add(row);
    }

    // =========================================================
    // SPACER
    // =========================================================

    private Region createSpacer(
            double height) {

        Region spacer =
                new Region();

        spacer.setMinHeight(height);
        spacer.setPrefHeight(height);
        spacer.setMaxHeight(height);

        return spacer;
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================================================
    // EXIT CONFIRMATION
    // =========================================================

    private void confirmExit() {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alert.setTitle("Exit Program");
        alert.setHeaderText(null);
        alert.setContentText(
                "Are you sure you want to exit?"
        );

        Optional<ButtonType> result =
                alert.showAndWait();

        if (result.isPresent()
                && result.get() == ButtonType.OK) {

            System.exit(0);
        }
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        launch(args);
    }
}