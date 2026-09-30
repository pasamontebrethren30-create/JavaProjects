import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentManagementSystem extends JFrame {

    // Modern colors
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD = Color.WHITE;
    private final Color PRIMARY = new Color(37, 99, 235);
    private final Color TEXT = new Color(31, 41, 55);
    private final Color SECONDARY_TEXT = new Color(107, 114, 128);
    private final Color SUCCESS = new Color(22, 163, 74);
    private final Color DANGER = new Color(220, 38, 38);
    private final Color BORDER = new Color(229, 231, 235);

    private JPanel contentPanel;
    public StudentManagementSystem() {

        setTitle("Student Management System");
        setSize(1000, 650);
        setMinimumSize(new Dimension(850, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMainUI();
    }

    // MAIN UI

    private void createMainUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // SIDEBAR 
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(245, 650));
        sidebar.setBackground(new Color(17, 24, 39));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(30, 20, 25, 20));

        JLabel logo = new JLabel("SMS");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel systemName = new JLabel(
                "<html>Student<br>Management<br>System</html>"
        );
        systemName.setForeground(new Color(209, 213, 219));
        systemName.setFont(new Font("Arial", Font.PLAIN, 16));
        systemName.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(systemName);
        sidebar.add(Box.createVerticalStrut(40));

        JLabel menuLabel = new JLabel("MAIN MENU");
        menuLabel.setForeground(new Color(156, 163, 175));
        menuLabel.setFont(new Font("Arial", Font.BOLD, 11));
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(menuLabel);
        sidebar.add(Box.createVerticalStrut(12));

        JButton gradeButton = createSidebarButton("Grade Evaluator");
        JButton averageButton = createSidebarButton("Average Calculator");
        JButton scaleButton = createSidebarButton("Grading Scale");
        JButton exitButton = createSidebarButton("Exit Program");

        sidebar.add(gradeButton);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(averageButton);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(scaleButton);

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(exitButton);

        // CONTENT 
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(new EmptyBorder(35, 40, 35, 40));

        showDashboard();

        // BUTTON ACTIONS 
        gradeButton.addActionListener(e -> showGradeEvaluator());
        averageButton.addActionListener(e -> showAverageCalculator());
        scaleButton.addActionListener(e -> showGradingScale());
        exitButton.addActionListener(e -> {

            // BRANCHING STATEMENT: return
            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit Program",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                // Terminates the program
                System.exit(0);
            }
        });

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    // DASHBOARD
    private void showDashboard() {

        contentPanel.removeAll();

        JPanel dashboard = new JPanel();
        dashboard.setBackground(BACKGROUND);
        dashboard.setLayout(new BoxLayout(dashboard, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Welcome to your Student Management System."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(SECONDARY_TEXT);

        dashboard.add(title);
        dashboard.add(Box.createVerticalStrut(5));
        dashboard.add(subtitle);
        dashboard.add(Box.createVerticalStrut(30));

        // Statistics cards

        JPanel cards = new JPanel(new GridLayout(1, 3, 18, 0));
        cards.setOpaque(false);

        cards.add(createInfoCard(
                "01",
                "Grade Evaluator",
                "Check and classify a student's grade."
        ));

        cards.add(createInfoCard(
                "02",
                "Average Calculator",
                "Calculate grades from multiple subjects."
        ));

        cards.add(createInfoCard(
                "03",
                "Grading Scale",
                "View the complete grading classification."
        ));

        dashboard.add(cards);

        dashboard.add(Box.createVerticalStrut(30));

        JPanel welcomeCard = createWhitePanel();

        JLabel welcomeTitle = new JLabel("Quick Start");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeTitle.setForeground(TEXT);

        JLabel welcomeText = new JLabel(
                "<html>Select an option from the sidebar to begin.<br>" +
                "You can evaluate grades, calculate averages, or view<br>" +
                "the grading scale information.</html>"
        );

        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeText.setForeground(SECONDARY_TEXT);

        welcomeCard.setLayout(new BoxLayout(welcomeCard, BoxLayout.Y_AXIS));
        welcomeCard.add(welcomeTitle);
        welcomeCard.add(Box.createVerticalStrut(10));
        welcomeCard.add(welcomeText);

        dashboard.add(welcomeCard);

        contentPanel.add(dashboard, BorderLayout.CENTER);

        refreshUI();
    }

    // GRADE EVALUATOR

    private void showGradeEvaluator() {

        contentPanel.removeAll();

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Grade Evaluator");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Enter a numerical grade from 0 to 100."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(SECONDARY_TEXT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(25));

        JPanel card = createWhitePanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel gradeLabel = new JLabel("Numerical Grade");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gradeLabel.setForeground(TEXT);

        JTextField gradeField = createTextField();

        JButton evaluateButton = createPrimaryButton("Evaluate Grade");

        JLabel resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(TEXT);

        card.add(gradeLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(gradeField);
        card.add(Box.createVerticalStrut(18));
        card.add(evaluateButton);
        card.add(Box.createVerticalStrut(20));
        card.add(resultLabel);

        evaluateButton.addActionListener(e -> {

            try {

                double grade = Double.parseDouble(
                        gradeField.getText()
                );

                // CONDITIONALS
                if (grade < 0 || grade > 100) {

                    resultLabel.setForeground(DANGER);
                    resultLabel.setText(
                            "Invalid grade. Enter a value from 0 to 100."
                    );

                } else if (grade >= 90) {

                    resultLabel.setForeground(SUCCESS);
                    resultLabel.setText(
                            "PASSED  |  Excellent (A)"
                    );

                } else if (grade >= 80) {

                    resultLabel.setForeground(SUCCESS);
                    resultLabel.setText(
                            "PASSED  |  Very Good (B)"
                    );

                } else if (grade >= 75) {

                    resultLabel.setForeground(SUCCESS);
                    resultLabel.setText(
                            "PASSED  |  Satisfactory (C)"
                    );

                } else {

                    resultLabel.setForeground(DANGER);
                    resultLabel.setText(
                            "FAILED  |  Needs Improvement (F)"
                    );
                }

            } catch (NumberFormatException ex) {

                resultLabel.setForeground(DANGER);
                resultLabel.setText(
                        "Invalid input. Please enter a number."
                );
            }
        });

        panel.add(card);

        contentPanel.add(panel, BorderLayout.CENTER);

        refreshUI();
    }

    // AVERAGE CALCULATOR

    private void showAverageCalculator() {

        contentPanel.removeAll();

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Multi-Subject Average");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Calculate the average of multiple subject grades."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(SECONDARY_TEXT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(25));

        JPanel card = createWhitePanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel countLabel = new JLabel("Number of Subjects");
        countLabel.setFont(new Font("Arial", Font.BOLD, 14));
        countLabel.setForeground(TEXT);

        JTextField countField = createTextField();

        JButton startButton = createPrimaryButton(
                "Enter Subject Grades"
        );

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setForeground(TEXT);
        resultArea.setBackground(new Color(249, 250, 251));
        resultArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        card.add(countLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(countField);
        card.add(Box.createVerticalStrut(18));
        card.add(startButton);
        card.add(Box.createVerticalStrut(20));
        card.add(resultArea);

        startButton.addActionListener(e -> {

            try {

                int subjectCount = Integer.parseInt(
                        countField.getText()
                );

                // CONDITIONAL
                if (subjectCount <= 0) {

                    resultArea.setText(
                            "Subject count must be greater than zero."
                    );

                    return;
                }

                double total = 0;
                int validCount = 0;

                // LOOP
                for (int i = 1; i <= subjectCount; i++) {

                    String input = JOptionPane.showInputDialog(
                            this,
                            "Enter grade for Subject " + i +
                                    " (0-100):",
                            "Subject " + i,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    // BRANCHING: break
                    if (input == null) {

                        resultArea.setText(
                                "Calculation cancelled by user."
                        );

                        break;
                    }

                    try {

                        double subGrade =
                                Double.parseDouble(input);

                        // BRANCHING: continue
                        if (subGrade < 0 || subGrade > 100) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Invalid grade. Skipping this subject.",
                                    "Invalid Grade",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            continue;
                        }

                        total += subGrade;
                        validCount++;

                    } catch (NumberFormatException ex) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Invalid number. This subject will be skipped.",
                                "Invalid Input",
                                JOptionPane.WARNING_MESSAGE
                        );

                        continue;
                    }
                }

                // CONDITIONAL
                if (validCount > 0) {

                    double average = total / validCount;

                    String status;

                    if (average >= 75) {
                        status = "PASSED";
                    } else {
                        status = "FAILED";
                    }

                    resultArea.setText(
                            "RESULT\n\n" +
                            "Valid Subjects: " + validCount + "\n" +
                            String.format(
                                    "Average Grade: %.2f%n",
                                    average
                            ) +
                            "Academic Status: " + status
                    );

                } else {

                    resultArea.setText(
                            "No valid grades were entered."
                    );
                }

            } catch (NumberFormatException ex) {

                resultArea.setText(
                        "Invalid subject count. Please enter a number."
                );
            }
        });

        panel.add(card);

        contentPanel.add(panel, BorderLayout.CENTER);

        refreshUI();
    }
   
    // GRADING SCALE
    private void showGradingScale() {

        contentPanel.removeAll();

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Grading Scale");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Reference guide for grade classification."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(SECONDARY_TEXT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(25));

        JPanel scaleCard = createWhitePanel();
        scaleCard.setLayout(new GridLayout(4, 2, 0, 0));

        addScaleRow(scaleCard, "90 - 100", "Excellent (A)");
        addScaleRow(scaleCard, "80 - 89", "Very Good (B)");
        addScaleRow(scaleCard, "75 - 79", "Satisfactory (C)");
        addScaleRow(scaleCard, "Below 75", "Failed (F)");

        panel.add(scaleCard);

        contentPanel.add(panel, BorderLayout.CENTER);

        refreshUI();
    }
    // UI COMPONENTS

    private JButton createSidebarButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                45
        ));

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(new Color(209, 213, 219));

        button.setBackground(new Color(17, 24, 39));

        button.setBorder(
                new EmptyBorder(10, 15, 10, 10)
        );

        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                new Color(31, 41, 55)
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                new Color(17, 24, 39)
                        );
                    }
                }
        );

        return button;
    }

    private JButton createPrimaryButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY);

        button.setFocusPainted(false);
        button.setBorder(
                new EmptyBorder(12, 20, 12, 20)
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        return field;
    }

    private JPanel createWhitePanel() {

        JPanel panel = new JPanel();

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        return panel;
    }

    private JPanel createInfoCard(
            String number,
            String title,
            String description
    ) {

        JPanel card = createWhitePanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel numberLabel = new JLabel(number);

        numberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        numberLabel.setForeground(PRIMARY);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(TEXT);

        JLabel descriptionLabel = new JLabel(
                "<html>" + description + "</html>"
        );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                SECONDARY_TEXT
        );

        card.add(numberLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(descriptionLabel);

        return card;
    }

    private void addScaleRow(
            JPanel panel,
            String grade,
            String description
    ) {

        JLabel gradeLabel = new JLabel(grade);

        gradeLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        gradeLabel.setForeground(PRIMARY);

        gradeLabel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        descriptionLabel.setForeground(TEXT);

        descriptionLabel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        panel.add(gradeLabel);
        panel.add(descriptionLabel);
    }

    // REFRESH UI

    private void refreshUI() {

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // MAIN METHOD

    public static void main(String[] args) {

        // Use Java's system look and feel
        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );

        } catch (Exception e) {

            // Continue with default look and feel
        }

        SwingUtilities.invokeLater(() -> {

            StudentManagementSystem app =
                    new StudentManagementSystem();

            app.setVisible(true);
        });
    }
}