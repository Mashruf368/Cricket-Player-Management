package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo1.Database.Player;
import javafx.stage.Stage;

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
    private Label purchaseStatusLabel;


    @FXML
    private ListView<String> playersListView; // ListView to display player details

    private List<Player> playerList;
    private SocketWrapper socketWrapper;
    private Map<Player, Double> playerPriceMap;
    private String username;
    public void setUsername(String a)
    {
        this.username = a;
    }

    // Inject or initialize socketWrapper in your controller
    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

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
public void setPlayerPriceMap1()
{
    this.playerPriceMap = File.read();
    playerPriceMap.entrySet().removeIf(entry -> entry.getKey().getClub().equals(username));
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

                String a = (String) socketWrapper.read();
                if(a.equals("DONE")) {
                    purchaseStatusLabel.setText("Purchase successful!");
                    purchaseStatusLabel.setStyle("-fx-text-fill: green;");
                }
                else if(a.equals("NOT_AVAILABLE")) {purchaseStatusLabel.setText("Purchase failed: Player unavailable.");
                    purchaseStatusLabel.setStyle("-fx-text-fill: red;");}
                else
                {
                    System.out.println("failed");
                    purchaseStatusLabel.setText("Purchase failed: Not enough budget.");
                    purchaseStatusLabel.setStyle("-fx-text-fill: red;");
                }

            } else {
                System.out.println("Failed to find the selected player in the map.");
            }
        } else {
            System.out.println("No player selected.");
        }
    }
    @FXML
    private void onbackbutton() {
        try {
            // Load the hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            System.out.println("loaded hello view");
            Parent root = loader.load();

            // Pass data back to HelloController if needed
            HelloController helloController = loader.getController();
            helloController.setUsername(username); // Example of setting username
            helloController.setmap(playerPriceMap);
            helloController.setSocketWrapper(socketWrapper); // Passing socket wrapper
            //helloController.setPlayerList((ArrayList<Player>) playerList);
            helloController.setPlayerList();
            System.out.println("loaded variables");

            // Set the new scene
            Stage stage = (Stage) playersListView.getScene().getWindow();
            stage.setScene(new Scene(root));
            //stage.setTitle("Club Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

