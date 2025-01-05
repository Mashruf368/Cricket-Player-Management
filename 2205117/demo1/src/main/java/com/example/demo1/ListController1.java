package com.example.demo1;

import com.example.demo1.Database.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListController1 {

    @FXML
    private ListView<String> playerListView;

    private String prev = "";

    public void setPlayerList(List<Player> players,String a) {
        // Clear any existing items in the ListView
        playerListView.getItems().clear();

        // Set a monospaced font for better alignment
        playerListView.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 16;");

        // Define column widths with more space for Position and Jersey No
        int nameWidth = 20;
        int countryWidth = 15;
        int ageWidth = 5;
        int heightWidth = 8;
        int clubWidth = 38;  // 38 spaces for Club name
        int positionWidth = 15;  // Increased width for Position (Wicketkeeper longest)
        int jerseyWidth = 18;  // Increased width for Jersey No (including 'Unavailable')
        int salaryWidth = 15;

        // Add header row
        StringBuilder header = new StringBuilder();
        header.append(padRight("Name", nameWidth))
                .append(padRight("Country", countryWidth))
                .append(padRight("Age", ageWidth))
                .append(padRight("Height", heightWidth))
                .append(padRight("Club", clubWidth))
                .append(padRight("Position", positionWidth))
                .append(padRight("Jersey No", jerseyWidth))
                .append(padRight("Salary", salaryWidth));
        playerListView.getItems().add(header.toString());
        playerListView.setStyle(playerListView.getStyle() + "; -fx-font-weight: bold; -fx-font-size: 20;"); // Bold and larger size for the header



        // Add a separator line
        //playerListView.getItems().add("-".repeat(header.length()));

        // Add player rows
        for (Player player : players) {
            StringBuilder row = new StringBuilder();
            row.append(padRight(player.getName() != null ? player.getName() : "N/A", nameWidth))
                    .append(padRight(player.getCountry() != null ? player.getCountry() : "N/A", countryWidth))
                    .append(padRight(String.valueOf(player.getAge()), ageWidth))
                    .append(padRight(String.format("%.2f", player.getHeight()), heightWidth))
                    .append(padRight(player.getClub() != null ? player.getClub() : "N/A", clubWidth))
                    .append(padRight(player.getPosition() != null ? player.getPosition() : "N/A", positionWidth))
                    .append(padRight(player.getJersey_no() != 0 ? String.valueOf(player.getJersey_no()) : "Unavailable", jerseyWidth))
                    .append(padRight(String.format("%,d USD", player.getSalary()), salaryWidth));
            playerListView.getItems().add(row.toString());
        }

        prev = a;
    }

    // Helper method to pad strings to a fixed width
//    private String padRight(String text, int width) {
//        if (text == null) {
//            text = "";
//        }
//        return text + " ".repeat(Math.max(0, width - text.length()));
//    }





    public void setCountryCount(HashMap<String, Integer> countryCount) {
        // Clear any existing items in the ListView
        playerListView.getItems().clear();

        // Set a monospaced font for better alignment
        playerListView.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14;");

        // Define column widths
        int countryWidth = 30;  // For Country
        int countWidth = 10;    // For Count

        // Add header row
        StringBuilder header = new StringBuilder();
        header.append(padRight("Country", countryWidth))
                .append(padRight("Number of Players", countWidth));
        playerListView.getItems().add(header.toString());

        // Add a separator line
        playerListView.getItems().add("-".repeat(header.length()));

        // Add country-count rows
        for (Map.Entry<String, Integer> entry : countryCount.entrySet()) {
            StringBuilder row = new StringBuilder();
            row.append(padRight(entry.getKey(), countryWidth))
                    .append(padRight(String.valueOf(entry.getValue()), countWidth));
            playerListView.getItems().add(row.toString());
        }
        prev  = "player";
    }

    // Helper method to pad strings to a fixed width
    private String padRight(String text, int width) {
        if (text == null) {
            text = "";
        }
        return text + " ".repeat(Math.max(0, width - text.length()));
    }

    @FXML
    public void onExitClick() {
        if(prev.equals("player")){
            try {
                // Load the hello-view.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Search_player.fxml"));
                System.out.println("loaded second1 view");
                Parent root = loader.load();

                // Pass data back to HelloController if needed
                Searchplayer helloController = loader.getController();


                // Set the new scene
                Stage stage = (Stage) playerListView.getScene().getWindow();
                stage.setScene(new Scene(root));
                //stage.setTitle("Club Dashboard");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(prev.equals("club"))
        {
            try {
                // Load the hello-view.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Search_club.fxml"));
                System.out.println("loaded second1 view");
                Parent root = loader.load();

                // Pass data back to HelloController if needed
                Searchclub helloController = loader.getController();


                // Set the new scene
                Stage stage = (Stage) playerListView.getScene().getWindow();
                stage.setScene(new Scene(root));
                //stage.setTitle("Club Dashboard");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }







}
