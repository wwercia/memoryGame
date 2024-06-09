package com.example.memorygame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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

    public void initView() {
        System.out.println();
        System.out.println("Final data:");
        System.out.println(category);
        System.out.println(difficultyLevel);

        GameBoard gameBoard = new GameBoard(difficultyLevel, category);
        Field[][] map = gameBoard.getMap();

        Stage stage = new Stage();
        VBox root = new VBox(0);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("box");

        Label pairsLeft = new Label("Pairs left: " + gameBoard.getNumberOfPairsLeft());
        pairsLeft.getStyleClass().add("normal-text");

        Label pairsRevealed = new Label("Pairs revealed: " + gameBoard.getNumberOfPairsRevealed());
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
        root.getChildren().addAll(pairsLeft, pairsRevealed, empty, box);
        Scene scene = new Scene(root, 450, getHeight());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
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
}
