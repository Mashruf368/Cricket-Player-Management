package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Text welcomeText; // This is the "Welcome to the club" text
    @FXML
    private Text usernameText; // This is the username text

    // Method to set the username and update the UI
    public void setUsername(String username) {
        welcomeText.setText("Welcome to the club");
        usernameText.setText(username); // Set the username dynamically
    }
}
