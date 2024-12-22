package com.example.demo1;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketWrapper implements AutoCloseable {
    private final Socket socket;
//    private final BufferedReader input;
//    private final PrintWriter output;
    private final ObjectInputStream objectInput;
    private final ObjectOutputStream objectOutput;

    public SocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.objectOutput = new ObjectOutputStream(socket.getOutputStream()); // Initialize output first
        this.objectOutput.flush(); // Ensure header is sent
        this.objectInput = new ObjectInputStream(socket.getInputStream()); // Initialize input next
    }
//
//    public String readMessage() throws IOException {
//        return input.readLine();
//    }
//
//    public void sendMessage(String message) {
//        output.println(message);
//    }

    public void sendObject(Object obj) {
        try {
            objectOutput.writeObject(obj);  // Send the object as a serialized object
            objectOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object receiveObject() throws IOException, ClassNotFoundException {
        return objectInput.readObject();  // Receiving a serialized object
    }
    public Object read(){
        Object message =null;
        try{
            message = objectInput.readObject();
        }catch (SocketException e){
            System.out.println("remote connection disconnected");
            return null;
        }
        catch (IOException e) {
            System.out.println("Error reading from socket");
        }
        catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
        return message;
    }
    public void write(Object o){
        try{
            objectOutput.writeObject(o);
            objectOutput.flush();
        } catch (IOException e) {
            System.out.println("Error writing to socket");
        }
    }


    public void close() throws IOException {
//        input.close();
//        output.close();
        objectInput.close();
        objectOutput.close();
        socket.close();
    }

}
