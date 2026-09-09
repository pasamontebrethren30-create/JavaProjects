import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickSpeedTestApp {
    private static int clickCount = 0;
    private static int timeLeft = 10;
    private static boolean isRunning = false;
    private static Timer countdownTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("⚡ Ultimate Click Speed Test ⚡");
            frame.setSize(450, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout(10, 10));
            frame.setLocationRelativeTo(null);

            // Top Status Panel
            JPanel statsPanel = new JPanel(new GridLayout(1, 2));
            statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel timeLabel = new JLabel("Time Remaining: 10s", SwingConstants.CENTER);
            JLabel scoreLabel = new JLabel("Clicks: 0", SwingConstants.CENTER);
            
            timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
            
            statsPanel.add(timeLabel);
            statsPanel.add(scoreLabel);

            // Center Action Panel
            JButton targetButton = new JButton("CLICK HERE TO START!");
            targetButton.setFont(new Font("Arial", Font.BOLD, 22));
            targetButton.setBackground(new Color(41, 128, 185)); 
            targetButton.setForeground(Color.WHITE);
            targetButton.setFocusPainted(false);

            // Bottom Control Panel
            JPanel controlPanel = new JPanel();
            JButton resetButton = new JButton("Reset Game");
            resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
            resetButton.setEnabled(false); 
            controlPanel.add(resetButton);

            frame.add(statsPanel, BorderLayout.NORTH);
            frame.add(targetButton, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);

            // Core timer loop setup
            countdownTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft--;
                    
                    if (timeLeft > 0) {
                        timeLabel.setText("Time Remaining: " + timeLeft + "s");
                    } else {
                        countdownTimer.stop();
                        isRunning = false;
                        targetButton.setEnabled(false);
                        resetButton.setEnabled(true);
                        
                        targetButton.setBackground(new Color(149, 165, 166)); 
                        timeLabel.setText("⏱️ Time's Up!");
                        
                        double cps = (double) clickCount / 10.0;
                        
                        // Dynamic Animal Ranking Logic
                        String ratingStars;
                        String animalComparison;
                        
                        if (cps < 3.0) {
                            ratingStars = "⭐";
                            animalComparison = "🐢 Sloth / Snail\n(Slow and steady, but your mouse is practically asleep!)";
                        } else if (cps < 5.0) {
                            ratingStars = "⭐⭐";
                            animalComparison = "🐈 House Cat\n(Casual movements. You are playing, but probably distracted by a laser pointer.)";
                        } else if (cps < 7.0) {
                            ratingStars = "⭐⭐⭐";
                            animalComparison = "🐇 Jackrabbit\n(Decent speed! Quick hops, a solid human average.)";
                        } else if (cps < 9.0) {
                            ratingStars = "⭐⭐⭐⭐";
                            animalComparison = "🐆 Cheetah\n(Impressive reflexes! Your fingers are a blur.)";
                        } else {
                            ratingStars = "⭐⭐⭐⭐⭐";
                            animalComparison = "🛸 Hummingbird / Autoclicker\n(Absolute madness! Your wings beat 80 times a second!)";
                        }
                        
                        // Display Results Dialog
                        JOptionPane.showMessageDialog(frame, 
                            "⏱️ 10 seconds are up!\n\n" +
                            "Total Clicks: " + clickCount + "\n" +
                            "Your Speed: " + cps + " CPS\n\n" +
                            "Your Rating: " + ratingStars + "\n" +
                            "Animal Rank: " + animalComparison, 
                            "Test Complete", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            // Target click handler
            targetButton.addActionListener(e -> {
                if (!isRunning && timeLeft == 10) {
                    isRunning = true;
                    targetButton.setText("CLICK! CLICK! CLICK!");
                    targetButton.setBackground(new Color(39, 174, 96)); 
                    countdownTimer.start();
                }
                
                if (isRunning) {
                    clickCount++;
                    scoreLabel.setText("Clicks: " + clickCount);
                }
            });

            // Reset interaction script logic
            resetButton.addActionListener(e -> {
                clickCount = 0;
                timeLeft = 10;
                isRunning = false;
                
                timeLabel.setText("Time Remaining: 10s");
                scoreLabel.setText("Clicks: 0");
                
                targetButton.setText("CLICK HERE TO START!");
                targetButton.setBackground(new Color(41, 128, 185));
                targetButton.setEnabled(true);
                resetButton.setEnabled(false);
            });

            frame.setVisible(true);
        });
    }
}
