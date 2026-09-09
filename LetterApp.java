import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class LetterApp extends Application {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) {
        // Main Container matching the pink pixel art aesthetic
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f7d2d6;"); 

        // Outer Pixel Window Border
        VBox letterContainer = new VBox(15);
        letterContainer.setAlignment(Pos.CENTER);
        letterContainer.setMaxSize(360, 420);
        letterContainer.setStyle(
                "-fx-background-color: #fff1f3;" +
                "-fx-border-color: #4a2830;" +
                "-fx-border-width: 4px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 20px;"
        );

        // 1. Header Title
        Label titleLabel = new Label("Will you be my Valentine?");
        titleLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#4a2830"));

        // 2. Text Graphic Container (Replacing the GIF)
        VBox faceContainer = new VBox(5);
        faceContainer.setAlignment(Pos.CENTER);
        faceContainer.setPrefHeight(180);

        Label catFaceLabel = new Label("(✿ • 💛 • ✿)");
        catFaceLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 32));
        catFaceLabel.setTextFill(Color.web("#4a2830"));

        Label catBodyLabel = new Label("/| ___ |\\");
        catBodyLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        catBodyLabel.setTextFill(Color.web("#4a2830"));

        faceContainer.getChildren().addAll(catFaceLabel, catBodyLabel);

        // 3. Buttons Area (Absolute positioning layout)
        Pane buttonContainer = new Pane();
        buttonContainer.setPrefHeight(100);

        // Yes Button
        Button yesBtn = new Button("YES");
        yesBtn.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        yesBtn.setStyle(
            "-fx-background-color: #ffb6c1;" +
            "-fx-border-color: #4a2830;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 4px;" +
            "-fx-background-radius: 4px;" +
            "-fx-text-fill: #4a2830;" +
            "-fx-cursor: hand;"
        );
        yesBtn.setPrefSize(80, 40);
        yesBtn.setLayoutX(60);
        yesBtn.setLayoutY(20);

        // No Button
        Button noBtn = new Button("NO");
        noBtn.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        noBtn.setStyle(
            "-fx-background-color: #d3d3d3;" +
            "-fx-border-color: #4a2830;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 4px;" +
            "-fx-background-radius: 4px;" +
            "-fx-text-fill: #4a2830;" +
            "-fx-cursor: hand;"
        );
        noBtn.setPrefSize(80, 40);
        noBtn.setLayoutX(210);
        noBtn.setLayoutY(20);

        buttonContainer.getChildren().addAll(yesBtn, noBtn);

        // Assemble initial screen elements
        letterContainer.getChildren().addAll(titleLabel, faceContainer, buttonContainer);
        root.getChildren().add(letterContainer);

        // --- INTERACTIVE LOGIC ---

        Random random = new Random();
        
        // Make the "NO" button teleport away when the mouse hovers over it
        noBtn.setOnMouseEntered(event -> {
            // Change expression to sad face
            catFaceLabel.setText("( 😭 • 🖤 • 😭 )");
            
            // Keep the coordinates within the boundary of the inner card
            double maxLeft = letterContainer.getWidth() - 90;
            double maxTop = letterContainer.getHeight() - 60;
            
            // Generate coordinates relative to the letterContainer card
            double newX = random.nextDouble() * maxLeft;
            double newY = random.nextDouble() * maxTop;

            // Shift it to absolute coordinates relative to the buttonContainer
            noBtn.setLayoutX(newX - letterContainer.getPadding().getLeft());
            noBtn.setLayoutY(newY - titleLabel.getHeight() - faceContainer.getPrefHeight() - 40);
        });

        // Revert cat face back when cursor leaves the NO button area
        noBtn.setOnMouseExited(event -> {
            catFaceLabel.setText("(✿ • 💛 • ✿)");
        });

        // "YES" button displays final valentine celebration text
        yesBtn.setOnAction(event -> {
            letterContainer.getChildren().clear();
            
            catFaceLabel.setText("( 💖 • ‿ • 💖 )");
            
            Label finalLabel = new Label("Valentine Date!\nDress fancy!");
            finalLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 22));
            finalLabel.setTextFill(Color.web("#e64a19"));
            finalLabel.setAlignment(Pos.CENTER);
            
            letterContainer.getChildren().addAll(faceContainer, finalLabel);
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Valentine Letter Animation");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}