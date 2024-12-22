package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;
import com.example.demo1.Database.Player;

public class HelloController {
    @FXML
    private Text welcomeText; // This is the "Welcome to the club" text
    @FXML
    private Text usernameText; // This is the username text
    private ArrayList<Player> playerList;
    private String username;
    // Method to set the username and update the UI
    public void setUsername(String username) {
        this.username = username;  // Assign the username to the instance variable
        welcomeText.setText("Welcome to the club");
        usernameText.setText(username); // Set the username dynamically
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
        System.out.println("Player list set for " + username);
    }

    @FXML
    private void showPlayers() {
        if (playerList != null) {
            System.out.println("Players for " + username + ":");
            for (Player player : playerList) {
                System.out.println(player.toString());
            }
        } else {
            System.out.println("No players available for " + username);
        }
    }

}
