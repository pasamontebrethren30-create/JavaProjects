import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentInformation extends Application {

    @Override
    public void start(Stage stage) {

        // Title
        Label title = new Label("STUDENT INFORMATION");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Input fields
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Enter your full name");

        TextField ageField = new TextField();
        ageField.setPromptText("Enter your age");

        TextField courseField = new TextField();
        courseField.setPromptText("Enter your course");

        TextField yearLevelField = new TextField();
        yearLevelField.setPromptText("Enter your year level");

        TextField sectionField = new TextField();
        sectionField.setPromptText("Enter your section");

        TextField averageGradeField = new TextField();
        averageGradeField.setPromptText("Enter your average grade");

        TextField middleInitialField = new TextField();
        middleInitialField.setPromptText("Enter middle initial");

        // Enrollment status
        Label enrollmentLabel = new Label("Currently Enrolled:");

        RadioButton yesButton = new RadioButton("Yes");
        RadioButton noButton = new RadioButton("No");

        ToggleGroup enrollmentGroup = new ToggleGroup();
        yesButton.setToggleGroup(enrollmentGroup);
        noButton.setToggleGroup(enrollmentGroup);

        yesButton.setSelected(true);

        // Grid layout
        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(12);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Full Name:"), 0, 0);
        form.add(fullNameField, 1, 0);

        form.add(new Label("Age:"), 0, 1);
        form.add(ageField, 1, 1);

        form.add(new Label("Course/Program:"), 0, 2);
        form.add(courseField, 1, 2);

        form.add(new Label("Year Level:"), 0, 3);
        form.add(yearLevelField, 1, 3);

        form.add(new Label("Section:"), 0, 4);
        form.add(sectionField, 1, 4);

        form.add(new Label("Average Grade:"), 0, 5);
        form.add(averageGradeField, 1, 5);

        form.add(new Label("Middle Initial:"), 0, 6);
        form.add(middleInitialField, 1, 6);

        form.add(enrollmentLabel, 0, 7);

        VBox enrollmentBox = new VBox(10, yesButton, noButton);
        form.add(enrollmentBox, 1, 7);

        // Buttons
        Button displayButton = new Button("Display Information");
        Button clearButton = new Button("Clear");

        displayButton.setPrefWidth(160);
        clearButton.setPrefWidth(100);

        // Result area
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(9);
        resultArea.setPromptText("Student information will appear here...");

        // Display button action
        displayButton.setOnAction(event -> {

            String fullName = fullNameField.getText().trim();
            String ageText = ageField.getText().trim();
            String course = courseField.getText().trim();
            String yearLevel = yearLevelField.getText().trim();
            String section = sectionField.getText().trim();
            String gradeText = averageGradeField.getText().trim();
            String middleInitialText = middleInitialField.getText().trim();

            // Check empty fields
            if (fullName.isEmpty() ||
                ageText.isEmpty() ||
                course.isEmpty() ||
                yearLevel.isEmpty() ||
                section.isEmpty() ||
                gradeText.isEmpty() ||
                middleInitialText.isEmpty()) {

                showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
                );

                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                double averageGrade = Double.parseDouble(gradeText);
                char middleInitial = middleInitialText.charAt(0);

                boolean enrollmentStatus = yesButton.isSelected();

                // Display student information
                resultArea.setText(
                    "===== STUDENT INFORMATION =====\n\n" +
                    "Full Name: " + fullName + "\n" +
                    "Age: " + age + "\n" +
                    "Course/Program: " + course + "\n" +
                    "Year Level: " + yearLevel + "\n" +
                    "Section: " + section + "\n" +
                    "Average Grade: " + averageGrade + "\n" +
                    "Middle Initial: " + middleInitial + "\n" +
                    "Enrollment Status: " +
                    (enrollmentStatus ? "Currently Enrolled" : "Not Currently Enrolled")
                );

            } catch (NumberFormatException e) {

                showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Age must be a whole number and Average Grade must be a number."
                );
            }
        });

        // Clear button action
        clearButton.setOnAction(event -> {
            fullNameField.clear();
            ageField.clear();
            courseField.clear();
            yearLevelField.clear();
            sectionField.clear();
            averageGradeField.clear();
            middleInitialField.clear();

            yesButton.setSelected(true);

            resultArea.clear();
        });

        VBox buttons = new VBox(10, displayButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, title, form, buttons, resultArea);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 550, 750);

        stage.setTitle("Student Information System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

