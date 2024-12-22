package No_server.tcpstring;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HelloController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label usernameLabel;

    // Class-level variable to store the username
    private String username;

    public void initialize(String username) {
        this.username = username; // Store the username in the class-level variable
        welcomeLabel.setText("Welcome to the Club!");
        usernameLabel.setText("Username: " + username);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logout clicked. Returning to login screen...");
        // Implement logout logic (e.g., navigate to login screen)
    }

    @FXML
    private void showMyPlayers(ActionEvent event) {
        System.out.println("Show My Players clicked.");
        try {
            // Ensure client is logged in
            Client client = HelloApplication.getClient();
            if (client == null || !client.isLoggedIn()) {
                System.err.println("Client is not logged in.");
                return;
            }


            System.out.println("Client is logged in. Sending SHOW_MY_TEAM request.");


            // Send "SHOW_MY_TEAM" request to the server
            ObjectOutputStream out = client.getOutputStream();
            out.writeObject("SHOW_MY_TEAM:" + username); // Use the class-level username variable
            out.flush();

            System.out.println("Request sent. Waiting for response...");

            // Read the response from the server
            ObjectInputStream in = client.getInputStream();

            System.out.println("got server input");
            String playerList = (String) in.readObject();

            // Print the list of players in the console
            System.out.println("Players in your team:");
            System.out.println(playerList);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while fetching player list: " + e.getMessage());
        }
    }

    @FXML
    private void handleClubDetails(ActionEvent event) {
        System.out.println("Club Details clicked.");
        // Logic to display club details
    }

    @FXML
    private void buyPlayers(ActionEvent event) {
        System.out.println("Buy Players clicked.");
        // Logic for buying players
    }

    @FXML
    private void sellPlayers(ActionEvent event) {
        System.out.println("Sell Players clicked.");
        // Logic for selling players
    }
}
