package com.example.memorygame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        WelcomeScreen view = new WelcomeScreen();
        VBox root = view.initWelcomeView(stage);
        Scene scene = new Scene(root, 400, 180);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
