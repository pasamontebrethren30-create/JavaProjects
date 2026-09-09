import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpecialMessage {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("💖 A Special Message For You 💖");
            frame.setSize(500, 450);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);
            
            // Valentine Pink Background
            frame.getContentPane().setBackground(new Color(255, 230, 235));

            // Giant Heart Label
            JLabel heartLabel = new JLabel("❤️", SwingConstants.CENTER);
            heartLabel.setFont(new Font("Arial", Font.PLAIN, 70));
            heartLabel.setBounds(50, 30, 400, 80);

            // Question label
            JLabel label = new JLabel("Do you love me?", SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            label.setForeground(new Color(150, 10, 40)); // Deep Crimson
            label.setBounds(50, 120, 400, 40);

            // Button styling helper
            JButton yesButton = new JButton("Yes!  ✨");
            JButton noButton = new JButton("No  ❌");

            yesButton.setFont(new Font("Arial", Font.BOLD, 16));
            noButton.setFont(new Font("Arial", Font.BOLD, 16));

            // Sweet Valentine Colors for buttons
            yesButton.setBackground(new Color(220, 40, 80));
            yesButton.setForeground(Color.WHITE);
            yesButton.setFocusPainted(false);

            noButton.setBackground(new Color(120, 120, 120));
            noButton.setForeground(Color.WHITE);
            noButton.setFocusPainted(false);

            // Set initial positions
            yesButton.setBounds(110, 240, 120, 45);
            noButton.setBounds(270, 240, 120, 45);

            frame.add(heartLabel);
            frame.add(label);
            frame.add(yesButton);
            frame.add(noButton);

            // "Yes" button triggers success message
            yesButton.addActionListener(e -> {
                UIManager.put("OptionPane.background", new Color(255, 230, 235));
                UIManager.put("Panel.background", new Color(255, 230, 235));
                
                JOptionPane.showMessageDialog(frame, 
                    "I love you too! 🥰", 
                    "💞 Yay! 💞", 
                    JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
            });

            // "No" button escapes on hover
            noButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    int maxX = frame.getWidth() - noButton.getWidth() - 30;
                    int maxY = frame.getHeight() - noButton.getHeight() - 60;
                    
                    int randomX = (int) (Math.random() * maxX);
                    int randomY = (int) (Math.random() * maxY);
                    
                    // Prevent the button from hiding right on top of the text
                    if (randomY < 180 && randomY > 20) {
                        randomY += 160;
                    }
                    
                    noButton.setLocation(randomX, randomY);
                }
            });

            frame.setVisible(true);
        });
    }
}
