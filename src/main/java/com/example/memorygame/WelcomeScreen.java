package com.example.memorygame;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WelcomeScreen {

    public VBox initWelcomeView(Stage stage) {

        VBox mainBox = new VBox(15);
        mainBox.getStyleClass().add("box");
        mainBox.setAlignment(Pos.CENTER);

        Label welcomeText = new Label("Welcome to Memory game!");
        welcomeText.getStyleClass().add("main-text");

        Button playButton = new Button("Play");
        playButton.getStyleClass().add("button");
        playButton.setOnAction(event -> displayCategoryScreen(stage));

        mainBox.getChildren().addAll(welcomeText, playButton);
        return mainBox;
    }

    private String category = null;
    private String difficultyLevel = null;

    private void displayCategoryScreen(Stage mainStage) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            VBox mainBox = new VBox(10);
            mainBox.getStyleClass().add("box");
            mainBox.setAlignment(Pos.CENTER);

            Label text = new Label("Choose category:");
            text.getStyleClass().add("main-text");

            Label emptyLabel = new Label();
            Label emptyLabel2 = new Label();
            emptyLabel2.setVisible(false);

            Button numbersButton = new Button("numbers");
            numbersButton.getStyleClass().add("not-clicked-button");
            numbersButton.setOnAction(event -> {
                emptyLabel2.setVisible(false);
                changeColorAndSetCategory(numbersButton);
            });

            Button animalsButton = new Button("animals");
            animalsButton.getStyleClass().add("not-clicked-button");
            animalsButton.setOnAction(event -> {
                emptyLabel2.setVisible(false);
                changeColorAndSetCategory(animalsButton);
            });

            Button natureButton = new Button("nature");
            natureButton.getStyleClass().add("not-clicked-button");
            natureButton.setOnAction(event -> {
                emptyLabel2.setVisible(false);
                changeColorAndSetCategory(natureButton);
            });

            Button confirmButton = new Button("confirm");
            confirmButton.getStyleClass().add("button");
            confirmButton.setOnAction(event -> {
                if (this.text == null || this.text.isEmpty()) {
                    emptyLabel2.getStyleClass().add("error-text");
                    emptyLabel2.setVisible(true);
                    emptyLabel2.setText("Please choose category.");
                } else {
                    category = this.text;
                    this.text = null;
                    previousButton = null;
                    stage.close();
                    displayDifficultyScreen(mainStage);
                }
            });

            mainBox.getChildren().addAll(text, emptyLabel, numbersButton, animalsButton, natureButton, emptyLabel2, confirmButton);
            Scene scene = new Scene(mainBox, 340, 400);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.showAndWait();
        });
    }

    private String text = null;
    private Button previousButton = null;

    private void changeColorAndSetCategory(Button button) {
        if (previousButton == null) {
            button.getStyleClass().add("clicked-button");
            text = button.getText();
            previousButton = button;
        } else {
            if (previousButton != button) {
                previousButton.getStyleClass().remove("clicked-button");
                previousButton.getStyleClass().add("not-clicked-button");
                button.getStyleClass().remove("not-clicked-button");
                button.getStyleClass().add("clicked-button");
                text = button.getText();
                previousButton = button;
            }
        }
        System.out.println(text);
    }

    //private String difficultyLevel = null;
    private void displayDifficultyScreen(Stage mainStage) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            VBox mainBox = new VBox(10);
            mainBox.getStyleClass().add("box");
            mainBox.setAlignment(Pos.CENTER);

            Label text = new Label("Choose difficulty level:");
            text.getStyleClass().add("main-text");

            Label emptyLabel = new Label();
            Label emptyLabel2 = new Label();

            Button easyButton = new Button("easy");
            easyButton.getStyleClass().add("not-clicked-button");
            easyButton.setOnAction(event -> {
                emptyLabel2.setVisible(false);
                changeColorAndSetCategory(easyButton);
            });

            Button mediumButton = new Button("medium");
            mediumButton.getStyleClass().add("not-clicked-button");
            mediumButton.setOnAction(event -> {
                emptyLabel2.setVisible(false);
                changeColorAndSetCategory(mediumButton);
            });

            Button hardButton = new Button("hard");
            hardButton.getStyleClass().add("not-clicked-button");
            hardButton.setOnAction(event -> {
                emptyLabel2.setVisible(false);
                changeColorAndSetCategory(hardButton);
            });

            Button confirmButton = new Button("confirm");
            confirmButton.getStyleClass().add("button");
            confirmButton.setOnAction(event -> {
                if (this.text == null || this.text.isEmpty()) {
                    emptyLabel2.getStyleClass().add("error-text");
                    emptyLabel2.setVisible(true);
                    emptyLabel2.setText("Please choose difficulty level.");
                } else {
                    difficultyLevel = this.text;
                    mainStage.close();
                    stage.close();
                    startGame();
                }
            });

            mainBox.getChildren().addAll(text, emptyLabel, easyButton, mediumButton, hardButton, emptyLabel2, confirmButton);
            Scene scene = new Scene(mainBox, 340, 400);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.showAndWait();
        });
    }

    private void startGame() {
        System.out.println();
        System.out.println("Final data:");
        System.out.println(category);
        System.out.println(difficultyLevel);

    }

}
