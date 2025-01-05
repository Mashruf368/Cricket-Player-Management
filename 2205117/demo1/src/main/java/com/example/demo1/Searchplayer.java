package com.example.demo1;


import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;
import com.example.demo1.Database.Search;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Searchplayer {
    @FXML
    private Label searchplayertext;

    @FXML
    private TextField playername;
    @FXML
    private TextField playercountry;
    @FXML
    private TextField playerclub;

    @FXML
    private TextField minsalary;

    @FXML
    private TextField maxsalary;
    @FXML
    private TextField playerposition;





    @FXML
    public void onByname(ActionEvent actionEvent) {
        System.out.println(playername.getText());
        Player here = Search.by_name(playername.getText());
        here.printplayer();


        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("print.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            //Stage stage = new Stage();
            PrintController printController = fxmlLoader.getController();
            Stage currentstage = (Stage) searchplayertext.getScene().getWindow();
//            stage.setTitle("SECOND WINDOW");
//            stage.setScene(scene);
//            stage.show();
            currentstage.setScene(scene);
            currentstage.setTitle("Third window");
            printController.setdetails(here.getName(), here.getCountry(),here.getAge(), here.getHeight(), here.getClub(),here.getPosition(), here.getJersey_no(), here.getSalary());




        }
        catch(Exception e)
        {
            System.out.println("error");
            e.printStackTrace();
        }

    }

    @FXML
    public void onBycountry(ActionEvent actionEvent) {


        Search finder = new Search();
        System.out.println(playercountry.getText());
        Vector<Player> here;
        Vector<Player> there = new Vector<>();

        if(playerclub.getText().equalsIgnoreCase("any") || playerclub.getText().equals(""))
        {
            there = finder.by_country(playercountry.getText());
            //PlayerList.printlist(here);
            PlayerList.printvector(there);
        }
        else
        {
            here = finder.by_country(playercountry.getText());
            there = finder.by_club(playerclub.getText(),here);
            PlayerList.printvector(there);


        }

        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the list of players to the controller
            listController.setPlayerList(there,"player");

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) searchplayertext.getScene().getWindow();
            Scene scene = new Scene(root, 800, 400);
            currentStage.setScene(scene);
            currentStage.setTitle("Player List");
        } catch (Exception e) {
            e.printStackTrace();
        }






    }

    @FXML
    public void onBysalary(ActionEvent actionEvent) {


        Search finder = new Search();

        int min = Integer.parseInt(minsalary.getText());
        int max = Integer.parseInt(maxsalary.getText());

        Vector<Player> here = finder.by_salaryrange(min,max);

        PlayerList.printvector(here);

        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the list of players to the controller
            listController.setPlayerList(here,"player");

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) searchplayertext.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            currentStage.setScene(scene);
            currentStage.setTitle("Player List");
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    @FXML
    public void onPosition(ActionEvent actionEvent) {


        Search finder = new Search();


        Vector<Player> here = finder.by_position(playerposition.getText());


        PlayerList.printvector(here);

        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the list of players to the controller
            listController.setPlayerList(here,"player");

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) searchplayertext.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            currentStage.setScene(scene);
            currentStage.setTitle("Player List");
        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    @FXML
    public void oncountrycount(ActionEvent actionEvent) {


        Search finder = new Search();


        HashMap<String,Integer> here = Search.country_count();

        try {
            // Load the list.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("list1.fxml"));
            AnchorPane root = loader.load();

            // Get the controller for the list.fxml
            ListController1 listController = loader.getController();

            // Pass the HashMap to the controller
            listController.setCountryCount(here);

            // Create the scene with the list view and set it to the current stage
            Stage currentStage = (Stage) searchplayertext.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            currentStage.setScene(scene);
            currentStage.setTitle("Country Count List");

        } catch (Exception e) {
            e.printStackTrace();
        }


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
            Stage stage = (Stage) searchplayertext.getScene().getWindow();
            stage.setScene(new Scene(root));
            //stage.setTitle("Club Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
