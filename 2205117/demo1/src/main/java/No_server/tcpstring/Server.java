//package No_server.tcpstring;
//
//import com.example.demo1.ReadThread;
//import com.example.demo1.SocketWrapper;
//import com.example.demo1.WriteThread;
//
//import java.io.*;
//import java.net.*;
//import java.util.*;
//
//public class Server {
//    private ServerSocket serverSocket;
//    private Map<String, String> userCredentials;
//
//    Server() {
//        loadCredentials(); // Load credentials from users.txt
//
//        try {
//            serverSocket = new ServerSocket(44444);
//            System.out.println("Server started on port 44444.");
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                serve(clientSocket);
//            }
//        } catch (Exception e) {
//            System.out.println("Server error: " + e);
//        }
//    }
//
//    private Map<String, List<String>> clubPlayers;
//
//    private void loadClubPlayers() {
//        clubPlayers = new HashMap<>();
//        clubPlayers.put("ClubA", Arrays.asList("Player1", "Player2", "Player3"));
//        clubPlayers.put("ClubB", Arrays.asList("Player4", "Player5"));
//        clubPlayers.put("ClubC", Arrays.asList("Player6", "Player7", "Player8"));
//    }
//
//
//    private void loadCredentials() {
//        userCredentials = new HashMap<>();
//
//        // Load the file from the classpath
//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("C:/Users/Hp/Downloads/2205117 (6)/2205117/demo1/src/main/resources/com/example/demo1/users.txt")) {
//            if (inputStream == null) {
//                throw new FileNotFoundException("users.txt not found in resources folder.");
//            }
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    userCredentials.put(parts[0].trim(), parts[1].trim());
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error loading user credentials: " + e.getMessage());
//        }
//    }
//
//
//    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
//        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
//
//        // Handle login
//        String loginMessage = (String) socketWrapper.read();
//        if (loginMessage.startsWith("LOGIN")) {
//            String[] parts = loginMessage.split(":", 3);
//            String username = parts[1];
//            String password = parts[2];
//
//            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
//                socketWrapper.write("LOGIN_SUCCESS");
//
//                while (true) {
//                    String clientCommand = (String) socketWrapper.read();
//                    if (clientCommand.equals("SHOW_MY_TEAM")) {
//                        List<String> players = clubPlayers.getOrDefault(username, new ArrayList<>());
//                        socketWrapper.write(String.join(",", players));
//                    }
//                }
//            } else {
//                socketWrapper.write("LOGIN_FAILED");
//                clientSocket.close();
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        new Server();
//    }
//}



//server management

package No_server.tcpstring;

import java.io.*;
import java.net.*;
import java.util.*;
import com.example.demo1.Database.PlayerList;
import com.example.demo1.Database.Player;
import com.example.demo1.Database.Search;
import com.example.demo1.Clubs.Club;
import com.example.demo1.Clubs.Club_search;

public class Server {
    private ServerSocket serverSocket;
    private Map<String, String> userCredentials;
    private int clientCount = 0;

    public Server() {
        loadCredentials(); // Hardcoded credentials

        PlayerList.makelist();
        PlayerList.printlist();


        try {
            serverSocket = new ServerSocket(44444);
            System.out.println("Server started on port 44444.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCount++;  // Increment the client count for each new connection
                System.out.println("New client connected. Port: " + clientSocket.getPort());
                System.out.println("Total connected clients: " + clientCount);
                //serve(clientSocket);

                new Thread(() -> {
                    try {
                        serve(clientSocket);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error serving client: " + e.getMessage());
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            System.out.println("Error closing client socket: " + e.getMessage());
                        }
                    }
                }).start();


            }
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }

    // Hardcoded login credentials for 3 clubs
    private void loadCredentials() {
        userCredentials = new HashMap<>();
        userCredentials.put("Mumbai Indians", "admin123");
        userCredentials.put("Royal Challengers Bangalore", "rcb12");
        userCredentials.put("Delhi Capitals", "dc12");
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);

        // Handle login
        String loginMessage = (String) socketWrapper.read();
        if (loginMessage.startsWith("LOGIN")) {
            String[] parts = loginMessage.split(":", 3);
            String username = parts[1];
            String password = parts[2];

            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                socketWrapper.write("LOGIN_SUCCESS");

                //login done now work on individual client
                //username detected

                Club club = new Club(username);
                List<Player> teamPlayers = club.formclub();

//                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
//                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
//                String clientCommand = (String) in.readObject();
                /// /extra addition
                while (true) {
                    System.out.println("inside loop");

                    try {
                        String clientCommand = (String) socketWrapper.read();
                        if (clientCommand.equals("SHOW_MY_TEAM")) {
                            System.out.println("in server");
                            if (teamPlayers != null && !teamPlayers.isEmpty()) {
                                socketWrapper.write(serializePlayers(teamPlayers));
                                System.out.println("mssg sent to client");
                            } else {
                                socketWrapper.write("No players found for your club.");
                            }
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                //endssss

            } else {
                socketWrapper.write("LOGIN_FAILED");
                clientSocket.close();
            }
        }



    }

    private String serializePlayers(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.getName()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "No players available.";
    }



    public static void main(String[] args) {

        new Server();
    }
}

