package com.example.memorygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field {
    private boolean isRevealed;
    private Image image;
    private ImageView imageView;

    public Field(boolean isRevealed, Image image, ImageView imageView) {
        this.isRevealed = isRevealed;
        this.image = image;
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public Image getImage() {
        return image;
    }

}
