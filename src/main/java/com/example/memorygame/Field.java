package com.example.memorygame;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Field {

    private boolean isRevealed;
    private ImageView image;

    public Field(boolean isRevealed, ImageView image){
        this.isRevealed = isRevealed;
        this.image = image;
    }

    public static void main(String[] args) {

        Image image = new Image(Field.class.getResource("/com/example/memorygame/nature/o5wafmm1.png").toExternalForm());
        ImageView imageView = new ImageView(image);

    }

    public ImageView getImage() {
        return image;
    }


}
