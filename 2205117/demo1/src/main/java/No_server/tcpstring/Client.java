package No_server.tcpstring;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean loggedIn = false;

    public Client(String serverAddress, int port, String username, String password) {
        try {
            socket = new Socket(serverAddress, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Send login message
            out.writeObject("LOGIN:" + username + ":" + password);
            out.flush();

            // Read response
            String response = (String) in.readObject();
            if ("LOGIN_SUCCESS".equals(response)) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Getter for ObjectOutputStream
    public ObjectOutputStream getOutputStream() {
        return out;
    }

    // Getter for ObjectInputStream
    public ObjectInputStream getInputStream() {
        return in;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
