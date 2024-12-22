package No_server.tcpstring;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.BiConsumer;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginStatusLabel;

    private BiConsumer<String, String> clientConnectionHandler;

    // Set the client connection handler
    public void setClientConnectionHandler(BiConsumer<String, String> handler) {
        this.clientConnectionHandler = handler;
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Server details
        String serverAddress = "127.0.0.1"; // Replace with your server's IP address
        int serverPort = 44444; // Replace with your server's port

        // Create and set the client instance
        Client client = new Client(serverAddress, serverPort, username, password);
        HelloApplication.setClient(client); // Make the client globally accessible

        if (client.isLoggedIn()) {
            loginStatusLabel.setText("Login Successful!");
            loginStatusLabel.setStyle("-fx-text-fill: green;");
            loadHelloView(username);
        } else {
            loginStatusLabel.setText("Invalid Username or Password.");
            loginStatusLabel.setStyle("-fx-text-fill: red;");
        }
    }


    private void loadHelloView(String username) {
        try {
            // Load the Hello View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            // Pass the username to the HelloController
            HelloController controller = loader.getController();
            controller.initialize(username);

            // Set the new scene and show the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            loginStatusLabel.setText("Error loading the next screen.");
            loginStatusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
