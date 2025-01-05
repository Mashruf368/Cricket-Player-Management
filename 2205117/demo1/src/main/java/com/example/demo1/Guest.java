package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Guest extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Adjust the FXML file path
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

