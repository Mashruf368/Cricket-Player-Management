package com.example.demo1;

//import com.sun.net.httpserver.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo1.Database.Player;

public class SellController {
//    @FXML
//    private ListView<Player> playersListView;
    @FXML
    private Text playerNameText;
    @FXML
    private Text playerCountryText;
    @FXML
    private Text playerPositionText;
    @FXML
    private Text playerAgeText;
    @FXML
    private Button confirmTransferButton; // Button for confirming transfer

    @FXML
    private ListView<String> playersListView; // ListView to display player details

    private List<Player> playerList;

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;

        // Populate the ListView with player names
        if (playersListView != null) {
            playersListView.getItems().clear(); // Clear existing items
            for (Player player : playerList) {
                playersListView.getItems().add(player.getName() + " - " + player.getPosition());
            }
        } else {
            System.out.println("playersListView is null. Check FXML mapping.");
        }
    }
    private SocketWrapper socketWrapper;

    // Inject or initialize socketWrapper in your controller
    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    // Handle player selection
//    @FXML
//    private void onPlayerSelected() {
//        String selectedPlayerName = playersListView.getSelectionModel().getSelectedItem();
//
//        if (selectedPlayerName != null) {
//            // Find the selected player by name
//            Player selectedPlayer = null;
//            for (Player player : playerList) {
//                if (player.getName().equals(selectedPlayerName)) {
//                    selectedPlayer = player;
//                    break;
//                }
//            }
//
//            if (selectedPlayer != null) {
//                // Show the selected player's details
//                playerNameText.setText("Name: " + selectedPlayer.getName());
//                playerCountryText.setText("Country: " + selectedPlayer.getCountry());
//                playerPositionText.setText("Position: " + selectedPlayer.getPosition());
//                playerAgeText.setText("Age: " + selectedPlayer.getAge());
//                // Add other player details as necessary
//            }
//        }
//    }
//    @FXML
//    private void sellPlayer() {
//        String selectedPlayerName = playersListView.getSelectionModel().getSelectedItem();
//
//        if (selectedPlayerName != null) {
//            // Find the selected player and sell them
//            Player selectedPlayer = null;
//            for (Player player : playerList) {
//                if (player.getName().equals(selectedPlayerName)) {
//                    selectedPlayer = player;
//                    break;
//                }
//            }
//
//            if (selectedPlayer != null) {
//                // Logic to sell the player
//                System.out.println("Selling player: " + selectedPlayer.getName());
//                // Remove the player from the list or update as needed
//                playerList.remove(selectedPlayer);
//                playersListView.getItems().remove(selectedPlayerName);
//            }
//        }
//    }

    @FXML
    private void onConfirmTransfer() {
        // Get the selected player from the ListView
        Server.sell = true;
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Player selectedPlayer = playerList.get(selectedIndex);
            System.out.println("Selected Player: " + selectedPlayer.getName());
            if (selectedPlayer != null) {
                System.out.println("Sending player: " + selectedPlayer);

                socketWrapper.write(selectedPlayer); // Send the player object directly
                System.out.println("Player transfer sent to server.");
            } else {
                System.out.println("Selected player is null.");
            }
        } else {
            System.out.println("No player selected.");
        }
    }

}
