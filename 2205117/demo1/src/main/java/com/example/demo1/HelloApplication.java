package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the login.fxml file using the correct path
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            AnchorPane loginPane = loader.load();  // Load the FXML

            // Set up the scene and stage
            Scene scene = new Scene(loginPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Test");
            primaryStage.show();  // Show the stage (window)

        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if loading fails
        }
    }

    public static void main(String[] args) {
        launch();  // Start the JavaFX application
    }
}
