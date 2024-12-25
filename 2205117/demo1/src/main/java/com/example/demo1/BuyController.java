package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
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

    // Inject or initialize socketWrapper in your controller
    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
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

    @FXML
    private void onConfirmPurchase() {
        // Get the selected player from the ListView
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Player selectedPlayer = playerList.get(selectedIndex);
            System.out.println("Selected Player: " + selectedPlayer.getName());

            if (selectedPlayer != null) {
                System.out.println("Sending buy request for player: " + selectedPlayer);

                // Create a Request object with the "BUY" command and the selected player
                Request buyRequest = new Request("BUY", selectedPlayer,0);

                // Send the request to the server
                socketWrapper.write(buyRequest);  // Send the request object directly
                System.out.println("Buy request sent to server.");
            } else {
                System.out.println("Selected player is null.");
            }
        } else {
            System.out.println("No player selected.");
        }
    }
}

