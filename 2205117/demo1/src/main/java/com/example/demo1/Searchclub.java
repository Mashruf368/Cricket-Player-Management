package com.example.demo1;

import com.example.demo1.Clubs.Club;
import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;
import com.example.demo1.Database.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Searchclub {

    @FXML
    private TextField clubname;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel2;
    @FXML
    private ComboBox<String> clubDropdown;


//    private boolean validateInput() {
//        String input = clubname.getText().trim();
//        if (input.isEmpty()) {
//            error1.setText("Enter valid club name!");
//            return false;
//        }
//        error1.setText(""); // Clear error if input is valid
//        return true;
//    }



    @FXML
    public void onmaxsalary(ActionEvent actionEvent) {
        //System.out.println(playername.getText());

        //if (!validateInput()) return;
        String w = clubname.getText();
        if(w.equals("")) {errorLabel2.setText("enter valid club name"); return;}
        //error1.setText("");
        Club team = new Club(clubname.getText());
        team.formclub();

        Vector<Player> here = team.max_salary();
        PlayerList.printvector(here);

        if(here.size()==0) {errorLabel2.setText("No club found with given name");return;}



        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the list of players to the controller
            listController.setPlayerList(here,"club");

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) clubname.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            currentStage.setScene(scene);
            currentStage.setTitle("Player List");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void onmaxheight(ActionEvent actionEvent) {
        //System.out.println(playername.getText());

        String w = clubname.getText();
        if(w.equals("")) {errorLabel2.setText("enter valid club name"); return;}


        String s = clubname.getText();

        Club team = new Club(clubname.getText());



        team.formclub();

        Vector<Player> here = team.max_height();
        PlayerList.printvector(here);

        if(here.size()==0) {errorLabel2.setText("No club found with given name");return;}

        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the list of players to the controller
            listController.setPlayerList(here,"club");

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) clubname.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            currentStage.setScene(scene);
            currentStage.setTitle("Player List");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    public void onmaxage(ActionEvent actionEvent) {
        //System.out.println(playername.getText());


        String w = clubname.getText();
        if(w.equals("")) {errorLabel2.setText("enter valid club name"); return;}

        Club team = new Club(clubname.getText());
        team.formclub();

        Vector<Player> here = team.max_age();
        PlayerList.printvector(here);

        if(here.size()==0) {errorLabel2.setText("No club found with given name");return;}

        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the list of players to the controller
            listController.setPlayerList(here,"club");

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) clubname.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            currentStage.setScene(scene);
            currentStage.setTitle("Player List");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void ontotalsalary(ActionEvent actionEvent) {
        //System.out.println(playername.getText());

        String w = clubname.getText();
        if(w.equals("")) {errorLabel2.setText("enter valid club name"); return;}

        Club team = new Club(clubname.getText());
        team.formclub();

        //if(team.size()==0) {errorLabel2.setText("No club found with given name");return;}

        long d = team.total_yearly_salary();
        System.out.println(d);

        errorLabel.setText("The total salary of your club is " + d + "!");






    }





    @FXML
    public void initialize() {
        // Get the club names and populate the ComboBox
        ArrayList<String> clubNames = PlayerList.getClubNames();
        ObservableList<String> dropdownItems = FXCollections.observableArrayList(clubNames);
        clubDropdown.setItems(dropdownItems);

        // Set the TextField when a ComboBox item is selected
        clubDropdown.setOnAction(event -> {
            String selectedClub = clubDropdown.getSelectionModel().getSelectedItem();
            if (selectedClub != null) {
                clubname.setText(selectedClub);
                errorLabel.setText(""); // Clear any error messages
            }
        });

    }

    @FXML
    public void onExitClick() {
        try {
            // Load the hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("second1.fxml"));
            System.out.println("loaded second1 view");
            Parent root = loader.load();

            // Pass data back to HelloController if needed
            SecondController helloController = loader.getController();


            // Set the new scene
            Stage stage = (Stage) clubname.getScene().getWindow();
            stage.setScene(new Scene(root));
            //stage.setTitle("Club Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

