import javax.swing.*;
import java.awt.*;

public class StudentInformationUI extends JFrame {

    // Input fields
    private JTextField txtFullName;
    private JTextField txtAge;
    private JTextField txtSection;
    private JTextField txtAverageGrade;
    private JTextField txtMiddleInitial;

    // Combo boxes
    private JComboBox<String> cbCourse;
    private JComboBox<String> cbYearLevel;

    // Enrollment radio buttons
    private JRadioButton rbEnrolledYes;
    private JRadioButton rbEnrolledNo;
    private ButtonGroup bgEnrollment;

    // Output area
    private JTextArea txtOutput;

    public StudentInformationUI() {

        // Window Configuration
        setTitle("Student Information System");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // =========================
        // HEADER
        // =========================
        JLabel lblHeader = new JLabel(
                "STUDENT INFORMATION FORM",
                SwingConstants.CENTER
        );

        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 10, 10)
        );

        add(lblHeader, BorderLayout.NORTH);

        // =========================
        // FORM PANEL
        // =========================
        JPanel formPanel = new JPanel(
                new GridLayout(8, 2, 8, 8)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        // Initialize input fields
        txtFullName = new JTextField();
        txtAge = new JTextField();
        txtSection = new JTextField();
        txtAverageGrade = new JTextField();
        txtMiddleInitial = new JTextField();

        // Course choices
        String[] courses = {
                "BSIT",
                "BSCS",
                "BSIS",
                "BSEMC",
                "Engineering",
                "Business"
        };

        cbCourse = new JComboBox<>(courses);

        // Year level choices
        String[] yearLevels = {
                "1st Year",
                "2nd Year",
                "3rd Year",
                "4th Year"
        };

        cbYearLevel = new JComboBox<>(yearLevels);

        // =========================
        // ENROLLMENT RADIO BUTTONS
        // =========================
        rbEnrolledYes = new JRadioButton("Yes", true);
        rbEnrolledNo = new JRadioButton("No");

        bgEnrollment = new ButtonGroup();
        bgEnrollment.add(rbEnrolledYes);
        bgEnrollment.add(rbEnrolledNo);

        JPanel panelRadio = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 0, 0)
        );

        panelRadio.add(rbEnrolledYes);
        panelRadio.add(rbEnrolledNo);

        // =========================
        // ADD COMPONENTS
        // =========================

        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(txtFullName);

        formPanel.add(new JLabel("Age:"));
        formPanel.add(txtAge);

        formPanel.add(new JLabel("Course/Program:"));
        formPanel.add(cbCourse);

        formPanel.add(new JLabel("Year Level:"));
        formPanel.add(cbYearLevel);

        formPanel.add(new JLabel("Section:"));
        formPanel.add(txtSection);

        formPanel.add(new JLabel("Average Grade:"));
        formPanel.add(txtAverageGrade);

        formPanel.add(new JLabel("Middle Initial:"));
        formPanel.add(txtMiddleInitial);

        formPanel.add(new JLabel("Currently Enrolled?"));
        formPanel.add(panelRadio);

        // =========================
        // SUBMIT BUTTON
        // =========================
        JButton btnSubmit = new JButton("Submit Information");

        btnSubmit.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        // =========================
        // CENTER PANEL
        // =========================
        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // =========================
        // OUTPUT AREA
        // =========================
        txtOutput = new JTextArea(8, 30);

        txtOutput.setEditable(false);
        txtOutput.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane = new JScrollPane(txtOutput);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Display Summary"
                )
        );

        // =========================
        // BOTTOM CONTAINER
        // =========================
        JPanel bottomContainer = new JPanel(
                new BorderLayout(10, 10)
        );

        bottomContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 20, 15, 20
                )
        );

        bottomContainer.add(
                btnSubmit,
                BorderLayout.NORTH
        );

        bottomContainer.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Add panels to window
        add(centerPanel, BorderLayout.CENTER);
        add(bottomContainer, BorderLayout.SOUTH);

        // =========================
        // BUTTON ACTION
        // =========================
        btnSubmit.addActionListener(e -> processAndDisplayInfo());
    }

    // =========================
    // PROCESS STUDENT INFORMATION
    // =========================
    private void processAndDisplayInfo() {

        try {

            // Get text values
            String fullName = txtFullName.getText().trim();
            String section = txtSection.getText().trim();
            String miText = txtMiddleInitial.getText().trim();

            // Check required fields
            if (fullName.isEmpty()) {
                showError("Please enter the student's full name.");
                return;
            }

            if (section.isEmpty()) {
                showError("Please enter the student's section.");
                return;
            }

            if (miText.isEmpty()) {
                showError("Please enter the middle initial.");
                return;
            }

            // Validate middle initial
            if (!miText.matches("[a-zA-Z]")) {
                showError(
                        "Middle Initial must contain only one letter."
                );
                return;
            }

            // Convert middle initial to uppercase
            char middleInitial =
                    Character.toUpperCase(miText.charAt(0));

            // =========================
            // AGE VALIDATION
            // =========================
            int age;

            try {
                age = Integer.parseInt(
                        txtAge.getText().trim()
                );
            } catch (NumberFormatException ex) {
                showError("Age must be a valid whole number.");
                return;
            }

            if (age < 1 || age > 100) {
                showError(
                        "Please enter a valid age between 1 and 100."
                );
                return;
            }

            // =========================
            // GRADE VALIDATION
            // =========================
            double averageGrade;

            try {
                averageGrade = Double.parseDouble(
                        txtAverageGrade.getText().trim()
                );
            } catch (NumberFormatException ex) {
                showError(
                        "Average Grade must be a valid number."
                );
                return;
            }

            if (averageGrade < 0 || averageGrade > 100) {
                showError(
                        "Average Grade must be between 0 and 100."
                );
                return;
            }

            // =========================
            // GET OTHER VALUES
            // =========================
            String course =
                    (String) cbCourse.getSelectedItem();

            String yearLevel =
                    (String) cbYearLevel.getSelectedItem();

            String enrollmentStatus;

            if (rbEnrolledYes.isSelected()) {
                enrollmentStatus = "Yes";
            } else {
                enrollmentStatus = "No";
            }

            // =========================
            // CREATE SUMMARY
            // =========================
            StringBuilder summary = new StringBuilder();

            summary.append(
                    "===== STUDENT INFORMATION =====\n"
            );

            summary.append("Full Name: ")
                    .append(fullName)
                    .append("\n");

            summary.append("Age: ")
                    .append(age)
                    .append("\n");

            summary.append("Course/Program: ")
                    .append(course)
                    .append("\n");

            summary.append("Year Level: ")
                    .append(yearLevel)
                    .append("\n");

            summary.append("Section: ")
                    .append(section)
                    .append("\n");

            summary.append("Average Grade: ")
                    .append(String.format("%.2f", averageGrade))
                    .append("\n");

            summary.append("Middle Initial: ")
                    .append(middleInitial)
                    .append("\n");

            summary.append("Enrollment Status: ")
                    .append(enrollmentStatus)
                    .append("\n");

            summary.append(
                    "================================"
            );

            // Display summary
            txtOutput.setText(
                    summary.toString()
            );

        } catch (Exception ex) {

            showError(
                    "An unexpected error occurred. "
                    + "Please check your input."
            );
        }
    }

    // =========================
    // ERROR MESSAGE METHOD
    // =========================
    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =========================
    // MAIN METHOD
    // =========================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentInformationUI window =
                    new StudentInformationUI();

            window.setVisible(true);
        });
    }
}