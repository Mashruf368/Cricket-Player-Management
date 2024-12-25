package com.example.demo1;

//import com.sun.net.httpserver.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private TextField offerPriceField; // TextField for entering the offer price

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


    @FXML
    private void onConfirmTransfer() {
        // Get the selected player from the ListView
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Player selectedPlayer = playerList.get(selectedIndex);

            if (selectedPlayer != null) {
                String offerPriceText = offerPriceField.getText();
                try {
                    double offerPrice = Double.parseDouble(offerPriceText);

                    System.out.println("Selected Player: " + selectedPlayer.getName());
                    System.out.println("Offer Price: " + offerPrice);

                    // Create a Request object with the "TRANSFER" command, player, and offer price
                    Request transferRequest = new Request("TRANSFER", selectedPlayer, offerPrice);

                    // Send the request to the server
                    socketWrapper.write(transferRequest);
                    System.out.println("Transfer request sent to server.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid offer price entered.");
                }
            } else {
                System.out.println("Selected player is null.");
            }
        } else {
            System.out.println("No player selected.");
        }
    }


}
