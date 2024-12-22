package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the login page (login.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
