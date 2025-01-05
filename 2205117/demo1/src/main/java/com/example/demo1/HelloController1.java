package com.example.demo1;

//import com.example.test1.Database.PlayerList;
import com.example.demo1.Database.PlayerList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController1 {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void nextButton(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("second1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 600);
            //Stage stage = new Stage();
            Stage currentstage = (Stage) welcomeText.getScene().getWindow();
//            stage.setTitle("SECOND WINDOW");
//            stage.setScene(scene);
//            stage.show();
            currentstage.setScene(scene);
            currentstage.setTitle("Second window");

            PlayerList.makelist();
            //PlayerList.printlist();
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
        System.out.println("next clicked");

    }
}