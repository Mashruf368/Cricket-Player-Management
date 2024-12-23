package com.example.demo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo1.Database.*;
import com.example.demo1.Clubs.*;


public class Server {
    private static final int PORT = 12345;

    // Hardcoded credentials
    private static final Map<String, String> credentials = new HashMap<>();
    private static final Map<String, ArrayList<Integer>> playerData = new HashMap<>(); // Maps usernames to player lists
    private static final Map<String, ArrayList<Player>> alldata = new HashMap<>();

    static {
        // Hardcoded credentials in the server
        PlayerList.makelist();


        credentials.put("one", "12");
        credentials.put("Mumbai Indians", "mi12");
        credentials.put("Royal Challengers Bangalore", "rcb12");
        credentials.put("Kolkata Knight Riders", "kkr12");
        credentials.put("Delhi Capitals", "dc12");

        Club r = new Club("Mumbai Indians");
        alldata.put("Mumbai Indians", new ArrayList<>(r.formclub()));
        r = new Club("Royal Challengers Bangalore");
        alldata.put("Royal Challengers Bangalore",new ArrayList<>(r.formclub()));
         r = new Club("Delhi Capitals");
        alldata.put("Delhi Capitals", new ArrayList<>(r.formclub()));
        r = new Club("Kolkata Knight Riders");
        alldata.put("Kolkata Knight Riders",new ArrayList<>(r.formclub()));



        playerData.put("one", new ArrayList<>(List.of(1, 2, 3, 4)));
        playerData.put("Mumbai Indians", new ArrayList<>(List.of(10, 20, 30, 40)));
        playerData.put("Royal Challengers Bangalore", new ArrayList<>(List.of(100, 200, 300, 400)));


    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Start a new thread for handling communication with the client
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // A handler class to handle client communication
    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (SocketWrapper socketWrapper = new SocketWrapper(socket)) {
                String credentials = (String) socketWrapper.read();
                System.out.println("Received credentials from client");


                String[] credentialsParts = credentials.split(":");
                String username = credentialsParts[0];
                String password = credentialsParts[1];

                // Authenticate credentials
                if (authenticate(username, password)) {
                    socketWrapper.write("Login successful");

                    ArrayList<Integer> playerList = playerData.get(username);
                    ArrayList<Player> curlist = alldata.get(username);
                    if (curlist != null) {
                        // Send the player list to the client as a serialized object
                        System.out.println("Sending player list to client: " + curlist);
                        socketWrapper.write(curlist);
                    } else {
                        System.out.println("No players available for username: " + username);
                        socketWrapper.write(new ArrayList<>()); // Send an empty list if no players are found
                    }

                } else {
                    socketWrapper.write("Invalid username or password");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Method to authenticate the credentials
        private boolean authenticate(String username, String password) {
            return credentials.containsKey(username) && credentials.get(username).equals(password);
        }
    }
}
