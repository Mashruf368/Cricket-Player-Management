package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.demo1.Database.Player;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Text welcomeText; // This is the "Welcome to the club" text
    @FXML
    private Text usernameText; // This is the username text
    private ArrayList<Player> playerList;
    private String username;
    private SocketWrapper socketWrapper;
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
    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
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

    @FXML
    private void openSellView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sell.fxml"));
            //loader.setController(new SellController());
            Parent root = loader.load();

            // Pass the player list to the SellController
            SellController sellController = loader.getController();
            sellController.setPlayerList(playerList);
            sellController.setSocketWrapper(socketWrapper);

            // Create a new scene and open the new window
            Stage stage = (Stage) usernameText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sell Players");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBuyView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Buy.fxml"));
            Parent root = loader.load();

            // Pass the player list to the BuyController
            Map<Player,Double> lastlist = new HashMap<>();
            lastlist = File.read();
            System.out.println("Player Price Map: " + lastlist);
            BuyController buyController = loader.getController();
            buyController.setPlayerPriceMap(lastlist);
            buyController.setSocketWrapper(socketWrapper);

            // Create a new scene and open the new window
            Stage stage = (Stage) usernameText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Buy Players");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
