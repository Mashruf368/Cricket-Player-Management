package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.demo1.SocketWrapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.example.demo1.Database.Player;

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

        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                                                                                                // Establish socket connection with the server
        try {
            SocketWrapper socketWrapper = new SocketWrapper(socket);
            String credentials = username + ":" + password;

                                                                                                // Send credentials to the server
            //sendCredentials(socket, credentials);
            socketWrapper.write(credentials);
            System.out.println("send login credentials to server");

                                                                                                // Receive server response
            String response = (String) socketWrapper.read();
            System.out.println("RECEIVed response from server regarding login");

                                                                                                    // Display result based on server response
            if ("Login successful".equals(response)) {
                statusLabel.setText("Login successful");
                                                                                                     //openHelloView(username); // Open the Hello View

               // String playerListString = (String)receiveResponse(socket);                              // Receive the player list
                ArrayList<Player> playerList = (ArrayList<Player>) socketWrapper.read();
                if (playerList == null || playerList.isEmpty()) {
                    System.out.println("No player list received.");
                } else {
                    System.out.println("Player list received: " + playerList);
                    System.out.println("now sending to hello view playerlist");
                    //ArrayList<Player> playerList = parsePlayerList(playerListString);                       // Convert string to ArrayList
                    openHelloView(username, playerList,socketWrapper);                                                            // Pass the username and player list to HelloController
                }

            } else {
                statusLabel.setText("Invalid username or password");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void openHelloView(String username,ArrayList<Player> playerList,SocketWrapper socketWrapper) {
        // Logic to transition to the Hello view (you can use FXMLLoader for this)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Pass the username to the HelloController
            HelloController controller = loader.getController();
            controller.setUsername(username);  // Set the username in HelloController
            controller.setPlayerList(playerList);
            controller.setSocketWrapper(socketWrapper);


            // Update the stage with the new scene
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
