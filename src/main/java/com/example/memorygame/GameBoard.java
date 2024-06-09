package com.example.memorygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard {

    private int height;
    private int width;
    private Field[][] map;
    private ArrayList<Field> fields;
    private int numberOfImages;
    Image unrevealedImage = new Image(Field.class.getResource("/com/example/memorygame/question.png").toExternalForm());
    private final String category;
    private int numberOfPairsLeft;
    private int numberOfPairsRevealed;
    private final GameView gameView;

    public GameBoard(GameView gameView, DifficultyLevel difficultyLevel, String category) {
        this.gameView = gameView;
        this.category = category;
        this.numberOfPairsRevealed = 0;
        if (difficultyLevel.equals(DifficultyLevel.EASY)) {
            this.numberOfImages = 4;
            this.numberOfPairsLeft = 4;
            this.height = 2;
            this.width = 4;
        } else if (difficultyLevel.equals(DifficultyLevel.MEDIUM)) {
            this.numberOfImages = 8;
            this.numberOfPairsLeft = 8;
            this.height = 4;
            this.width = 4;
        } else if (difficultyLevel.equals(DifficultyLevel.HARD)) {
            this.numberOfImages = 12;
            this.numberOfPairsLeft = 12;
            this.height = 6;
            this.width = 4;
        }
        createFields();
        createMap();
    }

    public Field[][] getMap() {
        return map;
    }

    public int getNumberOfPairsLeft() {
        return numberOfPairsLeft;
    }

    public int getNumberOfPairsRevealed() {
        return numberOfPairsRevealed;
    }
    private void createFields() {
        System.out.println();
        fields = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();
        for (int i = 1; i < numberOfImages + 1; i++) {
            names.add("image" + i);
            System.out.println("image" + i);
        }

        System.out.println();

        for (int i = 0; i < numberOfImages; i++) {
            System.out.println(names.get(i));
            Image image = new Image(Field.class.getResource("/com/example/memorygame/" + category + "/" + names.get(i) + ".png").toExternalForm());
            ImageView imageView = new ImageView(unrevealedImage);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            ImageView imageView2 = new ImageView(unrevealedImage);
            imageView2.setFitWidth(100);
            imageView2.setFitHeight(100);

            Field field = new Field(image, imageView);
            Field field2 = new Field(image, imageView2);

            imageView.setOnMouseClicked(event -> {
                try {
                    reveal(field);
                } catch (InterruptedException e) {
                    System.out.println("something went wrong");
                }
            });
            imageView2.setOnMouseClicked(event -> {
                try {
                    reveal(field2);
                } catch (InterruptedException e) {
                    System.out.println("something went wrong");
                }
            });

            fields.add(field);
            fields.add(field2);
        }
        System.out.println("after createFields");
    }

    private Field firstRevealedField = null;
    private Field secondRevealedField = null;

    private void reveal(Field field) throws InterruptedException {
        if (firstRevealedField != null && secondRevealedField != null) {
            firstRevealedField.getImageView().setImage(unrevealedImage);
            secondRevealedField.getImageView().setImage(unrevealedImage);
            firstRevealedField = null;
            secondRevealedField = null;
        }
        if (firstRevealedField == null) {
            field.getImageView().setImage(field.getImage());
            firstRevealedField = field;
        } else {
            field.getImageView().setImage(field.getImage());
            secondRevealedField = field;
        }
        if (firstRevealedField != null && secondRevealedField != null) {
            if (firstRevealedField.getImage().equals(secondRevealedField.getImage())) {
                numberOfPairsRevealed++;
                gameView.getPairsRevealed().setText("Revealed pairs: " + numberOfPairsRevealed);
                numberOfPairsLeft--;
                gameView.getPairsToReveal().setText("Pairs to reveal: " + numberOfPairsLeft);
                firstRevealedField.getImageView().setOnMouseClicked(event -> {
                });
                secondRevealedField.getImageView().setOnMouseClicked(event -> {
                });
                firstRevealedField = null;
                secondRevealedField = null;
            }
        }
        checkWin();
    }

    private void createMap() {
        System.out.println("in createMap");
        map = new Field[height][width];

        Random random = new Random();

        for (Field field : fields) {
            int height = random.nextInt(this.height);
            int width = random.nextInt(this.width);

            while (map[height][width] != null) {
                height = random.nextInt(this.height);
                width = random.nextInt(this.width);
            }
            map[height][width] = field;

        }
        System.out.println("after createMap");
    }

    private void checkWin() {

        if(numberOfPairsLeft == 0){
            gameView.displayWinningScreen();
        }

    }
}
