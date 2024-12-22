package com.example.demo1;

import java.io.*;
import java.net.Socket;

public class ReadThread implements Runnable {
    private Socket socket;
    private BufferedReader input;

    public ReadThread(Socket socket) {
        this.socket = socket;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
