package com.example.memorygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {

    private int height;
    private int width;

    public Field[][] getMap() {
        return map;
    }

    private Field[][] map;
    private ArrayList<Field> fields;
    private int numberOfImages;
    private int numberOfImagesInTotal;
    private String category;

    public GameBoard(DifficultyLevel difficultyLevel, String category) {
        this.category = category;
        if (difficultyLevel.equals(DifficultyLevel.EASY)) {
            this.numberOfImages = 4;
            this.numberOfImagesInTotal = 8;
            this.height = 2;
            this.width = 4;
        } else if (difficultyLevel.equals(DifficultyLevel.MEDIUM)) {
            this.numberOfImages = 8;
            this.numberOfImagesInTotal = 16;
            this.height = 4;
            this.width = 4;
        } else if (difficultyLevel.equals(DifficultyLevel.HARD)) {
            this.numberOfImages = 12;
            this.numberOfImagesInTotal = 24;
            this.height = 6;
            this.width = 4;
        }
        createNames();
        createFields();
        createMap();
    }

    public ArrayList<String> getImageNames() {
        return imageNames;
    }

    private ArrayList<String> imageNames;

    private void createNames() {
        imageNames = new ArrayList<>();
        for (int i = 1; i <= numberOfImages; i++) {
            imageNames.add("image" + i);
        }
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
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setOnMouseClicked(event -> doSomething());
            ImageView imageView2 = new ImageView(image);
            imageView2.setFitWidth(100);
            imageView2.setFitHeight(100);
            imageView2.setOnMouseClicked(event -> doSomething());
            Field field = new Field(false, imageView);
            Field field2 = new Field(false, imageView2);
            fields.add(field);
            fields.add(field2);
        }
        System.out.println("after createFields");
    }
    private void doSomething() {

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

}
