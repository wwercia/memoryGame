package com.example.memorygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
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
        File file = new File("C:\\Users\\wwerc\\IdeaProjects\\LightsOut\\src\\main\\resources\\com\\example\\lightsout\\hint.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
    }

}
