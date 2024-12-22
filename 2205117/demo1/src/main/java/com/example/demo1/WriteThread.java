package com.example.demo1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread implements Runnable {
    private Socket socket;
    private PrintWriter output;

    public WriteThread(Socket socket) {
        this.socket = socket;
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
            output.println(message);

            if (message.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}

