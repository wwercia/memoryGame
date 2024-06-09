package com.example.memorygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field {
    private final Image image;
    private final ImageView imageView;

    public Field(Image image, ImageView imageView) {
        this.image = image;
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }
    public Image getImage() {
        return image;
    }

}
