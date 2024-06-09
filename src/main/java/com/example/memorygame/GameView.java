package com.example.memorygame;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameView {

    private DifficultyLevel difficultyLevel;
    private String category;

    public GameView(DifficultyLevel difficultyLevel, String category) {
        this.difficultyLevel = difficultyLevel;
        this.category = category;
    }

    private Label pairsToReveal;
    private Label pairsRevealed;
    private Stage stage;

    public void initView() {
        Platform.runLater(() -> {
            System.out.println();
            System.out.println("Final data:");
            System.out.println(category);
            System.out.println(difficultyLevel);

            GameBoard gameBoard = new GameBoard(this, difficultyLevel, category);
            Field[][] map = gameBoard.getMap();

            stage = new Stage();
            VBox root = new VBox(0);
            root.setAlignment(Pos.CENTER);
            root.getStyleClass().add("box");

            pairsToReveal = new Label("Pairs to reveal: " + gameBoard.getNumberOfPairsLeft());
            pairsToReveal.getStyleClass().add("normal-text");

            pairsRevealed = new Label("Revealed pairs: " + gameBoard.getNumberOfPairsRevealed());
            pairsRevealed.getStyleClass().add("normal-text");

            Label empty = new Label();

            VBox box = new VBox(5);
            box.setAlignment(Pos.CENTER);
            for (int i = 0; i < map.length; i++) {
                HBox hbox = new HBox(5);
                hbox.setAlignment(Pos.CENTER);
                for (int j = 0; j < map[i].length; j++) {
                    Field field = map[i][j];
                    hbox.getChildren().add(field.getImageView());
                }
                box.getChildren().add(hbox);
            }
            root.getChildren().addAll(pairsToReveal, pairsRevealed, empty, box);
            Scene scene = new Scene(root, 450, getHeight());
            stage.setTitle("Tic Tac Toe");
            stage.setScene(scene);
            stage.show();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        });
    }

    private int getHeight() {
        if (difficultyLevel.equals(DifficultyLevel.EASY)) {
            return 320;
        } else if (difficultyLevel.equals(DifficultyLevel.MEDIUM)) {
            return 520;
        } else {
            return 735;
        }
    }

    public Label getPairsToReveal() {
        return pairsToReveal;
    }

    public Label getPairsRevealed() {
        return pairsRevealed;
    }

    private Stage winningStage;
    public void displayWinningScreen() {
        Platform.runLater(() -> {
            winningStage = new Stage();
            VBox mainBox = new VBox(6);
            mainBox.setAlignment(Pos.CENTER);
            mainBox.getStyleClass().add("box");

            Label label = new Label("You won!");
            label.getStyleClass().add("main-text");
            Label label2 = new Label("Congratulations!");
            label2.getStyleClass().add("main-text");

            Button playAgainButton = new Button("Play again");
            playAgainButton.getStyleClass().add("button2");
            playAgainButton.setOnAction(event -> {
                stage.close();
                winningStage.close();
                GameView gameView = new GameView(difficultyLevel, category);
                gameView.initView();
            });

            Button playAgainWithDifferentSettingsButton = new Button("Play again with different settings");
            playAgainWithDifferentSettingsButton.getStyleClass().add("button2");
            playAgainWithDifferentSettingsButton.setOnAction(event -> {
                stage.close();
                winningStage.close();
                playWithDifferentSettings();
            });

            mainBox.getChildren().addAll(label, label2, playAgainButton, playAgainWithDifferentSettingsButton);
            Scene scene = new Scene(mainBox, 300, 250);
            winningStage.setTitle("Tic Tac Toe");
            winningStage.setScene(scene);
            winningStage.show();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        });
    }

    private void playWithDifferentSettings(){
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.displayCategoryScreen(new Stage());
    }

}
