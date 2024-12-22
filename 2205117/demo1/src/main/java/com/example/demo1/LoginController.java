package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

                                                                                                // Establish socket connection with the server
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
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
                    openHelloView(username, playerList);                                                            // Pass the username and player list to HelloController
                }

            } else {
                statusLabel.setText("Invalid username or password");
            }
        }
    }



    private void openHelloView(String username,ArrayList<Player> playerList) {
        // Logic to transition to the Hello view (you can use FXMLLoader for this)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Pass the username to the HelloController
            HelloController controller = loader.getController();
            controller.setUsername(username);  // Set the username in HelloController
            controller.setPlayerList(playerList);

            // Update the stage with the new scene
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}



/*
private void sendCredentials(Socket socket, String credentials) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(credentials); // Send the credentials as a serialized object
        out.flush();
    }

    private String receiveResponse(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return (String) in.readObject(); // Receive the response as a serialized object
    }



//    private void openHelloView(String username, ArrayList<Player> playerList) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//            Parent root = loader.load();
//
//            HelloController helloController = loader.getController();
//            helloController.setUsername(username);
//            helloController.setPlayerList(playerList);
//
//            Stage stage = (Stage) statusLabel.getScene().getWindow();
//            stage.setScene(new Scene(root));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private ArrayList<Player> parsePlayerList(String playerListString) {
        ArrayList<Player> players = new ArrayList<>();

        // Ensure splitting by a semicolon with a newline before the semicolon
        String[] playerStrings = playerListString.split(";\n");

        for (String playerString : playerStrings) {
            // Trim extra spaces and newlines
            playerString = playerString.trim();
            if (playerString.isEmpty()) continue;

            // Split by line breaks to get attributes
            String[] attributes = playerString.split("\n");

            if (attributes.length < 8) {
                System.out.println("Skipping incomplete player entry: " + playerString);
                continue;  // Skip incomplete entries
            }

            // Parse attributes manually and handle potential parsing issues
            try {
                String name = attributes[0].split(" : ")[1].trim();
                String country = attributes[1].split(" : ")[1].trim();
                int age = Integer.parseInt(attributes[2].split(" : ")[1].trim());
                double height = Double.parseDouble(attributes[3].split(" : ")[1].replace("m", "").trim());
                String club = attributes[4].split(" : ")[1].trim();
                String position = attributes[5].split(" : ")[1].trim();
                int jersey_no = attributes[6].contains("Unavailable") ? 0 : Integer.parseInt(attributes[6].split(" : ")[1].trim());
                long salary = Long.parseLong(attributes[7].split(" : ")[1].trim());

                // Add the player to the list
                players.add(new Player(name, country, age, height, club, position, jersey_no, salary));
            } catch (Exception e) {
                System.out.println("Error parsing player data: " + playerString);
                e.printStackTrace();
            }
        }

        return players;
    }




    private Object receiveObject(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readObject();  // Receive the serialized object (e.g., ArrayList<Player>)
    }




 */
