package No_server;

import com.example.demo1.Clubs.Club;
import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import com.example.demo1.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class HelloController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Launch the client with login credentials
        try {
            new Thread(() -> new Client("127.0.0.1", 44444, username, password)).start();

            // Close login window
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            showAlert("Login Failed", "Error connecting to server.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private Label usernameLabel; // Add this label in hello-view.fxml

    private String username;

    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText("Welcome, " + username + "!"); // Set the heading text
    }

    @FXML
    private void handleLogout() {
        try {
            // Load the login-view.fxml
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showMyPlayers() {
        try {
            // Load the list.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the player list
            ListController listController = loader.getController();

            // Sample player data to pass
            //List<Player> players = new ArrayList<>();
            PlayerList.makelist();
            //PlayerList.printlist();
            Club myteam = new Club(username);
            Vector<Player> s = myteam.formclub();
            PlayerList.printvector(s);

            List<Player> players = new ArrayList<>(s);
            listController.setPlayerList((ArrayList<Player>) players);

            // Create and display a new stage
            Stage stage = new Stage();
            stage.setTitle("Player List");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


