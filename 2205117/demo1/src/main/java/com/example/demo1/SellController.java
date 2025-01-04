package com.example.demo1;

//import com.sun.net.httpserver.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo1.Database.Player;
import javafx.stage.Stage;

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
    private String username;
    @FXML
    private ListView<String> playerDetailsListView; // ListView to display player details

    @FXML
    private ListView<String> playersListView; // ListView to display player details

    private List<Player> playerList;
    public void setUsername(String a)
    {
        this.username = a;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
        playersListView.setStyle("-fx-font-family: 'Consolas';");

        // Populate the ListView with player names
        if (playersListView != null) {
            playersListView.getItems().clear(); // Clear existing items
            for (Player player : playerList) {
                String formattedText = String.format("%-20s %-15s", player.getName(), player.getPosition());
                playersListView.getItems().add(formattedText);
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
            helloController.setPlayerList((ArrayList<Player>) playerList); // Passing player list
            helloController.setSocketWrapper(socketWrapper); // Passing socket wrapper
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
    @FXML
    private void onShowPlayerDetails() {
        // Get the selected index
        int selectedIndex = playersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1 && playerList != null && selectedIndex < playerList.size()) {
            Player selectedPlayer = playerList.get(selectedIndex);
            if (selectedPlayer != null) {
                // Clear previous details
                playerDetailsListView.getItems().clear();

                // Add player details to the ListView
                playerDetailsListView.getItems().add("Name: " + selectedPlayer.getName());
                playerDetailsListView.getItems().add("Country: " + selectedPlayer.getCountry());
                playerDetailsListView.getItems().add("Age: " + selectedPlayer.getAge());
                playerDetailsListView.getItems().add("Height: " + selectedPlayer.getHeight() + " cm");
                playerDetailsListView.getItems().add("Salary: $" + selectedPlayer.getSalary());
                playerDetailsListView.getItems().add("Position: " + selectedPlayer.getPosition());
                playerDetailsListView.getItems().add("Jersey Number: " + selectedPlayer.getJersey_no());

                System.out.println("Displayed details for player: " + selectedPlayer.getName());
            } else {
                System.out.println("Selected player is null.");
            }
        } else {
            System.out.println("No player selected or invalid index.");
        }
    }




}
