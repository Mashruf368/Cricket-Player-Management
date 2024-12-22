package com.example.demo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345;

    // Hardcoded credentials
    private static final Map<String, String> credentials = new HashMap<>();

    static {
        // Hardcoded credentials in the server
        credentials.put("one", "12");
        credentials.put("Mumbai Indians", "mi12");
        credentials.put("Royal Challengers Bangalore", "rcb12");
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
                String credentials = socketWrapper.readMessage(); // Receive credentials from the client
                String[] credentialsParts = credentials.split(":");
                String username = credentialsParts[0];
                String password = credentialsParts[1];

                // Authenticate credentials
                if (authenticate(username, password)) {
                    socketWrapper.sendMessage("Login successful");
                } else {
                    socketWrapper.sendMessage("Invalid username or password");
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
