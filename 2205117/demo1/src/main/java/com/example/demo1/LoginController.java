package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Establish socket connection with the server
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            String credentials = username + ":" + password;

            // Send credentials to the server
            sendCredentials(socket, credentials);

            // Receive server response
            String response = receiveResponse(socket);

            // Display result based on server response
            if ("Login successful".equals(response)) {
                statusLabel.setText("Login successful");
                //openHelloView(username); // Open the Hello View

                String playerListString = receiveResponse(socket); // Receive the player list
                if (playerListString == null || playerListString.isEmpty()) {
                    System.out.println("No player list received.");
                } else {
                    System.out.println("Player list received: " + playerListString);
                    ArrayList<Integer> playerList = (ArrayList<Integer>) parsePlayerList(playerListString); // Convert string to ArrayList
                    openHelloView(username, playerList);  // Pass the username and player list to HelloController
                }

            } else {
                statusLabel.setText("Invalid username or password");
            }
        }
    }

    private void sendCredentials(Socket socket, String credentials) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println(credentials); // Send username:password pair to the server
    }

    private String receiveResponse(Socket socket) throws IOException {
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return in.readLine(); // Read response from the server
    }

    private void openHelloView(String username) {
        // Logic to transition to the Hello view (you can use FXMLLoader for this)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Pass the username to the HelloController
            HelloController controller = loader.getController();
            controller.setUsername(username);  // Set the username in HelloController

            // Update the stage with the new scene
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> parsePlayerList(String playerListStr) {
        // Remove the square brackets and split by comma
        String[] playerStrings = playerListStr.substring(1, playerListStr.length() - 1).split(", ");

        // Convert to integer list
        List<Integer> playerList = new ArrayList<>();
        for (String playerString : playerStrings) {
            try {
                playerList.add(Integer.parseInt(playerString));
            } catch (NumberFormatException e) {
                e.printStackTrace();  // Handle potential number format exceptions
            }
        }

        System.out.println("Player List: " + playerList); // Debug output
        return playerList;
    }



    private void openHelloView(String username, ArrayList<Integer> playerList) {
        try {
            // Load the Hello View (hello-view.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Get the HelloController from the FXMLLoader
            HelloController helloController = loader.getController();
            helloController.setUsername(username);  // Pass the username
            helloController.setPlayerList(playerList);  // Pass the player list
            System.out.println("playerlist passed to hello controller");

            // Set the scene for the new view
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
