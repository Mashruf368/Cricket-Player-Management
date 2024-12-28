package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.example.demo1.Database.Player;

public class BuyController {
    @FXML
    private Text playerNameText;
    @FXML
    private Text playerCountryText;
    @FXML
    private Text playerPositionText;
    @FXML
    private Text playerAgeText;
    @FXML
    private Button confirmPurchaseButton; // Button for confirming purchase

    @FXML
    private ListView<String> playersListView; // ListView to display player details

    private ArrayList<Player> playerList;

    private SocketWrapper socketWrapper;
    private Map<Player, Double> playerPriceMap;

    // Inject or initialize socketWrapper in your controller
    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

//    public void setPlayerList(ArrayList<Player> playerList) {
//        this.playerList = playerList;
//
//        // Populate the ListView with player names
//        if (playersListView != null) {
//            playersListView.getItems().clear(); // Clear existing items
//            for (Player player : playerList) {
//                playersListView.getItems().add(player.getName() + " - " + player.getPosition());
//            }
//        } else {
//            System.out.println("playersListView is null. Check FXML mapping.");
//        }
//    }
public void setPlayerPriceMap(Map<Player, Double> playerPriceMap) {
    this.playerPriceMap = playerPriceMap;

    // Populate the ListView with all entries
    if (playersListView != null) {
        playersListView.getItems().clear(); // Clear existing items

        playerPriceMap.forEach((player, price) -> {
            // Format player details for display
            String playerDetails = String.format(
                    "%-20s %-15s %-20s %-10.2f",
                    player.getName(),
                    player.getPosition(),
                    player.getClub(),
                    price
            );

            // Add the formatted details to the ListView
            playersListView.getItems().add(playerDetails);
            System.out.println("testing map " +playerPriceMap);
        });
    } else {
        System.out.println("playersListView is null. Check FXML mapping.");
    }
}


    @FXML
    private void onConfirmPurchase() {
        // Get the selected index from the ListView
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            // Iterate through the map to find the player at the selected index
            int currentIndex = 0;
            Player selectedPlayer = null;
            Double selectedPrice = null;

            for (Map.Entry<Player, Double> entry : playerPriceMap.entrySet()) {
                if (currentIndex == selectedIndex) {
                    selectedPlayer = entry.getKey();
                    selectedPrice = entry.getValue();
                    break;
                }
                currentIndex++;
            }

            // Check if a player was found
            if (selectedPlayer != null && selectedPrice != null) {
                System.out.println("Selected Player: " + selectedPlayer.getName() + " with price: " + selectedPrice);

                // Create and send the request to the server
                Request buyRequest = new Request("BUY", selectedPlayer, selectedPrice);
                socketWrapper.write(buyRequest);
                System.out.println("Buy request sent to server.");
            } else {
                System.out.println("Failed to find the selected player in the map.");
            }
        } else {
            System.out.println("No player selected.");
        }
    }




}

