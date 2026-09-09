import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PerfectMatchGame {
    private static int matchesFound = 0;
    private static JButton firstClicked = null;
    private static String firstText = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("The Perfect Match Game 🌸");
            frame.setSize(700, 600); // Expanded frame size for more cards
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(4, 5, 10, 10)); // 4x5 grid for 20 cards
            frame.setLocationRelativeTo(null);
            frame.getContentPane().setBackground(new Color(255, 240, 245));

            // 10 distinct pairs for a 20-card game
            String[] items = {
                "☕ Coffee", "✨ You & Me", "🍕 Pizza", "🍿 Movie", "🍦 Ice Cream",
                "🎵 Music", "🎮 Gaming", "📚 Reading", "🚲 Cycling", "🐱 Kittens",
                "☕ Coffee", "✨ You & Me", "🍕 Pizza", "🍿 Movie", "🍦 Ice Cream",
                "🎵 Music", "🎮 Gaming", "📚 Reading", "🚲 Cycling", "🐱 Kittens"
            };
            
            // Fisher-Yates shuffle to randomize card positions
            Random rand = new Random();
            for (int i = items.length - 1; i > 0; i--) {
                int index = rand.nextInt(i + 1);
                String temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            
            // Generate all 20 buttons
            for (int i = 0; i < 20; i++) {
                JButton button = new JButton("?");
                button.setFont(new Font("Arial", Font.BOLD, 16));
                button.setBackground(new Color(240, 128, 128));
                button.setForeground(Color.WHITE);
                button.setFocusPainted(false);
                
                final String hiddenText = items[i];

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setText(hiddenText);
                        button.setEnabled(false);

                        if (firstClicked == null) {
                            firstClicked = button;
                            firstText = hiddenText;
                        } else {
                            if (firstText.equals(hiddenText)) {
                                matchesFound++;
                                firstClicked = null; 
                                
                                // Checked against 10 total pairs
                                if (matchesFound == 10) {
                                    JOptionPane.showMessageDialog(frame, 
                                        "Congratulations! We are a perfect match. 🗺️\nLet's turn this game into a real date?", 
                                        "Victory!", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                // Block other clicks temporarily during the flip-back window
                                JButton tempFirst = firstClicked;
                                firstClicked = null; // Reset immediately to prevent multi-click bugs
                                
                                Timer timer = new Timer(600, arg -> {
                                    tempFirst.setText("?");
                                    tempFirst.setEnabled(true);
                                    button.setText("?");
                                    button.setEnabled(true);
                                });
                                timer.setRepeats(false);
                                timer.start();
                            }
                        }
                    }
                });
                frame.add(button);
            }
            frame.setVisible(true);
        });
    }
}