package com.example.demo1;

import java.io.*;
import java.net.Socket;

public class SocketWrapper implements AutoCloseable {
    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;

    public SocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    public String readMessage() throws IOException {
        return input.readLine();
    }

    public void sendMessage(String message) {
        output.println(message);
    }


    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
