import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInformationUI extends JFrame {

    // Input fields
    private JTextField txtFullName, txtAge, txtSection, txtAverageGrade, txtMiddleInitial;
    private JComboBox<String> cbCourse, cbYearLevel;
    private JRadioButton rbEnrolledYes, rbEnrolledNo;
    private ButtonGroup bgEnrollment;
    private JTextArea txtOutput;

    public StudentInformationUI() {
        // Window Configuration
        setTitle("Student Information System");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JLabel lblHeader = new JLabel("STUDENT INFORMATION FORM", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(lblHeader, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Initialize Form Fields
        txtFullName = new JTextField();
        txtAge = new JTextField();
        
        String[] courses = {"BSIT", "BSCS", "BSIS", "BSEMC", "Engineering", "Business"};
        cbCourse = new JComboBox<>(courses);

        String[] yearLevels = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        cbYearLevel = new JComboBox<>(yearLevels);

        txtSection = new JTextField();
        txtAverageGrade = new JTextField();
        txtMiddleInitial = new JTextField();

        // Radio buttons for enrollment status
        rbEnrolledYes = new JRadioButton("Yes", true);
        rbEnrolledNo = new JRadioButton("No");
        bgEnrollment = new ButtonGroup();
        bgEnrollment.add(rbEnrolledYes);
        bgEnrollment.add(rbEnrolledNo);

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelRadio.add(rbEnrolledYes);
        panelRadio.add(rbEnrolledNo);

        // Adding components to form panel
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

        // Button Panel
        JButton btnSubmit = new JButton("Submit Information");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(formPanel, BorderLayout.NORTH);

        // Output Display Area
        txtOutput = new JTextArea(8, 30);
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Display Summary"));

        JPanel bottomContainer = new JPanel(new BorderLayout(10, 10));
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));
        bottomContainer.add(btnSubmit, BorderLayout.NORTH);
        bottomContainer.add(scrollPane, BorderLayout.CENTER);

        // Add main sections to frame
        add(centerPanel, BorderLayout.CENTER);
        add(bottomContainer, BorderLayout.SOUTH);

        // Submit Button Action
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAndDisplayInfo();
            }
        });
    }

    private void processAndDisplayInfo() {
        try {
            // Read values from form components
            String fullName = txtFullName.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String course = (String) cbCourse.getSelectedItem();
            String yearLevel = (String) cbYearLevel.getSelectedItem();
            String section = txtSection.getText().trim();
            double averageGrade = Double.parseDouble(txtAverageGrade.getText().trim());
            
            String miText = txtMiddleInitial.getText().trim();
            char middleInitial = miText.isEmpty() ? ' ' : miText.charAt(0);
            
            boolean enrollmentStatus = rbEnrolledYes.isSelected();

            // Display in Output Area
            StringBuilder summary = new StringBuilder();
            summary.append("===== STUDENT INFORMATION =====\n");
            summary.append("Fullname: ").append(fullName).append("\n");
            summary.append("Age: ").append(age).append("\n");
            summary.append("Course/Program: ").append(course).append("\n");
            summary.append("Year Level: ").append(yearLevel).append("\n");
            summary.append("Section: ").append(section).append("\n");
            summary.append("Average Grade: ").append(averageGrade).append("\n");
            summary.append("Middle Initial: ").append(middleInitial).append("\n");
            summary.append("Enrollment Status: ").append(enrollmentStatus);

            txtOutput.setText(summary.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for Age and Average Grade.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Launch GUI safely on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentInformationUI().setVisible(true);
            }
        });
    }
}