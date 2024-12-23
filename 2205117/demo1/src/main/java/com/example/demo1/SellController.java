package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
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

}
