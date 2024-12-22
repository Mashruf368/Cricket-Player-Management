package No_server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the login window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/login.fxml"));
            Parent root = loader.load();

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

    public static void main(String[] args) {
        launch(args);
    }
}


