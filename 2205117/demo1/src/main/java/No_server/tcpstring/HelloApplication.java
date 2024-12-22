package No_server.tcpstring;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private static Client client;

    @Override
    public void start(Stage stage) {
        try {
            // Load the login window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Pass the client instance to the login controller
            LoginController controller = loader.getController();
            controller.setClientConnectionHandler(this::connectToServer);

            // Set up the scene
            Scene scene = new Scene(root);
            stage.setTitle("Player Database - Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading login window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to connect to the server as a client
    public void connectToServer(String username, String password) {
        String serverAddress = "localhost";
        int serverPort = 44444;

        // Attempt to connect as a client
        client = new Client(serverAddress, serverPort, username, password);

        // Check if login is successful
        if (client.isLoggedIn()) {
            System.out.println("Connected to server as " + username);
        } else {
            System.out.println("Failed to connect to server.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client clientInstance) {
        client = clientInstance;
    }
}
