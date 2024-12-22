package No_server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private final HashMap<String, String> credentials = new HashMap<>();

    // Initialize method to load hardcoded login credentials
    @FXML
    public void initialize() {
        loadCredentials(); // Load usernames and passwords from the code itself
    }

    // Hardcode the username and password credentials here
    private void loadCredentials() {
        credentials.put("Mumbai Indians", "admin123"); // Example username and password
        credentials.put("Royal Challengers Bangalore", "rcb12");
        credentials.put("Delhi Capitals", "dc12");
    }

    // Handle login button click event
    @FXML
    public void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Login Successful!");
            switchToMainWindow(username); // Pass the username
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password!");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void switchToMainWindow(String username) {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Pass the username to the HelloController
            HelloController helloController = loader.getController();
            helloController.setUsername(username);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Player Database Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}


