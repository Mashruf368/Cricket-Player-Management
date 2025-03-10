package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Map<Player,Double> lastlist = new HashMap<>();
    private Map<Player,Double> another = new HashMap<>();

    public void setmap(Map<Player,Double> a)
    {
        this.lastlist = a;
    }

    public void setUsername(String username) {
        this.username = username;
        welcomeText.setText("Welcome to " + username);
        //usernameText.setText(username);
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
        System.out.println("Player list set for " + username);
    }


    public void setPlayerList() {
        try{
            Player dummyPlayer = new Player(); // Replace with actual Player object
            Request request = new Request("PLAYERLIST", dummyPlayer, 0.0); // Use 0.0 for price if irrelevant
            socketWrapper.write(request);
            Object response = socketWrapper.read();
            if (response instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) response;

                // Verify the elements in the list are of type Player
                if (!tempList.isEmpty() && tempList.get(0) instanceof Player) {
                    this.playerList = (ArrayList<Player>) tempList; // Safe cast
                } else {
                    System.err.println("Received an ArrayList, but elements are not of type Player.");
                    this.playerList = new ArrayList<>(); // Initialize with an empty list
                }
            } else {
                System.err.println("Unexpected response type: " + (response != null ? response.getClass().getName() : "null"));
                this.playerList = new ArrayList<>(); // Initialize with an empty list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    @FXML
    private void showPlayers() {

        try {

            Player dummyPlayer = new Player(); // Replace with actual Player object if needed
            Request request = new Request("PLAYERLIST", dummyPlayer, 0.0); // Use 0.0 for price if irrelevant
            socketWrapper.write(request); // Send the request to the server

            // Read the response from the server
            Object response = socketWrapper.read();
            ArrayList<Player> newPlayerList = new ArrayList<>();
            if (response instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) response;

                if (!tempList.isEmpty() && tempList.get(0) instanceof Player) {
                    newPlayerList = (ArrayList<Player>) tempList; // Safe cast
                } else {
                    System.err.println("Received an ArrayList, but elements are not of type Player.");
                }
            } else {
                System.err.println("Unexpected response type: " + (response != null ? response.getClass().getName() : "null"));
            }


            FXMLLoader loader = new FXMLLoader(getClass().getResource("list.fxml"));
            Parent root = loader.load();


            ListController listController = loader.getController();
            listController.setPlayerList(newPlayerList);
            listController.setusername(username);
            listController.setSocketWrapper(socketWrapper);

            // Create a new scene and open the new window
            Stage stage = (Stage) usernameText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("My Players");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


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
            another = File.read();
            //another.entrySet().removeIf(entry -> entry.getKey().getClub().equals(username));
            System.out.println(another);
            ArrayList<Player> filteredPlayerList = new ArrayList<>();
            for (Player player : playerList) {
                if (!another.containsKey(player)) {
                    filteredPlayerList.add(player);
                }
            }
            //System.out.println(filteredPlayerList);
            // Pass the player list to the SellController
            SellController sellController = loader.getController();
            sellController.setPlayerList(filteredPlayerList);
            sellController.setSocketWrapper(socketWrapper);
            sellController.setUsername(username);

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

            lastlist = File.read();
            System.out.println("in hello controller " + lastlist);

            lastlist.entrySet().removeIf(entry -> entry.getKey().getClub().equals(username));

            System.out.println("Player Price Map: " + lastlist);
            BuyController buyController = loader.getController();
            buyController.setPlayerPriceMap(lastlist);
            buyController.setSocketWrapper(socketWrapper);
            buyController.setUsername(username);
            buyController.setPlayerList(playerList);

            // Create a new scene and open the new window
            Stage stage = (Stage) usernameText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Buy Players");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void opendetails()
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("details.fxml"));
            Parent root = loader.load();
            DetailController detailController = new DetailController();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clubDetails() {
        System.out.println("Club budget is 6969");
    }


}
