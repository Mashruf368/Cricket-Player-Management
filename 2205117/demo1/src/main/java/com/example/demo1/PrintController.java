package com.example.demo1;

import javafx.fxml.FXML;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PrintController {


    @FXML
    private TextArea showname;

    @FXML
    private TextArea showcountry;

    @FXML
    private TextArea showage;
    @FXML
    private TextArea showheight;
    @FXML
    private TextArea showclub;
    @FXML
    private TextArea showposition;
    @FXML
    private TextArea showjersey;
    @FXML
    private TextArea showsalary;


    public void setdetails(String name,String country,int age,double height,String club,String position,int jersey,long salary)
    {
        showname.setText(name);
        showcountry.setText(country);
        showage.setText(age + "");
        showheight.setText(height + "");
        showclub.setText(club);
        showposition.setText(position);
        showjersey.setText(jersey+"");
        showsalary.setText(salary + "");

    }
    @FXML
    public void onExitClick() {
        try {
            // Load the hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search_player.fxml"));
            System.out.println("loaded second1 view");
            Parent root = loader.load();

            // Pass data back to HelloController if needed
            Searchplayer helloController = loader.getController();


            // Set the new scene
            Stage stage = (Stage) showname.getScene().getWindow();
            stage.setScene(new Scene(root));
            //stage.setTitle("Club Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
