package com.example.demo1;

import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;
import com.example.demo1.Interface.Save_players;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddPlayerController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField clubField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField jerseyField;
    @FXML
    private TextField salaryField;

    // This method will be called when the Add Player button is clicked
    @FXML
    private void handleAddPlayer() {
        String name = nameField.getText();
        String country = countryField.getText();
        int age = Integer.parseInt(ageField.getText());
        double height = Double.parseDouble(heightField.getText());
        String club = clubField.getText();
        String position = positionField.getText();
        int jerseyNo = Integer.parseInt(jerseyField.getText());
        double salary = Double.parseDouble(salaryField.getText());

        // Here you would typically add the player to your database or list
        Player temp = new Player(name, country, age, height, club, position, jerseyNo, (long) salary);
        PlayerList.addplayer(temp);
        //Save_players.save();
        PlayerList.save((ArrayList<Player>) PlayerList.playerList);


        // Call the method to add the player to your data structure or database
    }
    @FXML
    public void onExitClick() {
        try {
            // Load the hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("second1.fxml"));
            System.out.println("loaded second1 view");
            Parent root = loader.load();

            // Pass data back to HelloController if needed
            SecondController helloController = loader.getController();


            // Set the new scene
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            //stage.setTitle("Club Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

