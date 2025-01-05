package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import com.example.demo1.Database.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListController {
    //private List<Player> playerList;
    private SocketWrapper socketWrapper;
    private String username;
    private Map<Player, Double> playerPriceMap;
    public void setUsername(String a)
    {
        this.username = a;
    }

    // Inject or initialize socketWrapper in your controller
    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }
    public void setusername(String a)
    {
        this.username = a;
    }


    @FXML
    private ListView<String> playersListView; // ListView to display formatted player details

    private List<Player> playerList;

    public void setPlayerPriceMap(Map<Player, Double> playerPriceMap) {
        this.playerPriceMap = playerPriceMap;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;

        // Populate the ListView with formatted player data
        if (playersListView != null) {
            playersListView.getItems().clear(); // Clear existing items
            playersListView.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");

            // Add column headers
            String header = String.format("%-25s %-18s %-5s %-8s %-18s %-10s %-10s",
                    "Name", "Country", "Age", "Height", "Position", "Jersey No.", "Salary");
            playersListView.getItems().add(header);
            //playersListView.getItems().add("=".repeat(70)); // Separator line

            // Add each player's details
            for (Player player : playerList) {
                String playerData = String.format("%-25s %-18s %-5d %-8.2f %-18s %-10d %-10d",
                        player.getName(),
                        player.getCountry(),
                        player.getAge(),
                        player.getHeight(),
                        player.getPosition(),
                        player.getJersey_no(),
                        player.getSalary());
                playersListView.getItems().add(playerData);
            }
        } else {
            System.out.println("playersListView is null. Check FXML mapping.");
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
            helloController.setPlayerList((ArrayList<Player>) playerList);
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
