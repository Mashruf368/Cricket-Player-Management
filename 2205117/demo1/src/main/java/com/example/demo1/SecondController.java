package com.example.demo1;

import com.example.demo1.Database.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

//import javax.swing.*;
//import java.awt.event.ActionEvent;

public class SecondController {
    @FXML
    private Label secondtext;

    @FXML
    protected void onAddPlayerClick() {
        secondtext.setText("Add Player button clicked!");
    }

    @FXML
    protected void onSearchPlayerClick() {
        secondtext.setText("Search Player button clicked!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search_player.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 810, 460);
            //Stage stage = new Stage();
            Stage currentstage = (Stage) secondtext.getScene().getWindow();
//            stage.setTitle("SECOND WINDOW");
//            stage.setScene(scene);
//            stage.show();
            currentstage.setScene(scene);
            currentstage.setTitle("Third window");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @FXML
    protected void onSearchClubClick() {
        secondtext.setText("Search club button clicked!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search_club.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 810, 460);
            //Stage stage = new Stage();
            Stage currentstage = (Stage) secondtext.getScene().getWindow();
//            stage.setTitle("SECOND WINDOW");
//            stage.setScene(scene);
//            stage.show();
            currentstage.setScene(scene);
            currentstage.setTitle("Third window");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @FXML
    protected void onaddplayerclick() {
        secondtext.setText("Search club button clicked!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 540);
            //Stage stage = new Stage();
            Stage currentstage = (Stage) secondtext.getScene().getWindow();
//            stage.setTitle("SECOND WINDOW");
//            stage.setScene(scene);
//            stage.show();
            currentstage.setScene(scene);
            currentstage.setTitle("Third window");
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    @FXML
    public void onExitClick() {
        try {
            // Load the hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello1.fxml"));
            System.out.println("loaded hello1 view");
            Parent root = loader.load();

            // Pass data back to HelloController if needed
            HelloController1 helloController = loader.getController();


            // Set the new scene
            Stage stage = (Stage) secondtext.getScene().getWindow();
            stage.setScene(new Scene(root));
            //stage.setTitle("Club Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

