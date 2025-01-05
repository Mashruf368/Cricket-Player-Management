package com.example.demo1;

import com.example.demo1.Database.Player;

import java.util.ArrayList;

public class DetailController {
    private ArrayList<Player> playerList;
    private String username;
    private SocketWrapper socketWrapper;
    public void setUsername(String username) {
        this.username = username;
        //welcomeText.setText("Welcome to " + username);
        //usernameText.setText(username);
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
        System.out.println("Player list set for " + username);
    }
    public void setSocketWrapper(SocketWrapper socketWrapper)
    {
        this.socketWrapper = socketWrapper;
    }

}
