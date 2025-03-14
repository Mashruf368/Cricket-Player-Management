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
    static ArrayList<Player> onsale = new ArrayList<>();
    public static volatile boolean sell = false;
    public static volatile boolean buy = false;
    public static Map <String,Double> club_details = new HashMap<>();

    static {
        // Hardcoded credentials in the server
        PlayerList.makelist();
        //System.out.println(PlayerList.playerList);
        club_details = File.readclub();


        credentials.put("one", "12");
        credentials.put("Mumbai Indians", "mi12");
        credentials.put("Royal Challengers Bangalore", "rcb12");
        credentials.put("Kolkata Knight Riders", "kkr12");
        credentials.put("Delhi Capitals", "dc12");
        credentials.put("Chennai Super Kings","csk12");
        credentials.put("Gujarat Titans","gt12");
        credentials.put("Rajasthan Royals","rr12");
        credentials.put("Lucknow Super Giants","lsg12");
        credentials.put("Punjab Kings","kxip12");

        Club r = new Club("Mumbai Indians");
        alldata.put("Mumbai Indians", new ArrayList<>(r.formclub()));
        r = new Club("Royal Challengers Bangalore");
        alldata.put("Royal Challengers Bangalore",new ArrayList<>(r.formclub()));
         r = new Club("Delhi Capitals");
        alldata.put("Delhi Capitals", new ArrayList<>(r.formclub()));
        r = new Club("Kolkata Knight Riders");
        alldata.put("Kolkata Knight Riders",new ArrayList<>(r.formclub()));
        r = new Club("Punjab Kings");
        alldata.put("Punjab Kings",new ArrayList<>(r.formclub()));
        r = new Club("Lucknow Super Giants");
        alldata.put("Lucknow Super Giants",new ArrayList<>(r.formclub()));
        r = new Club("Rajasthan Royals");
        alldata.put("Rajasthan Royals",new ArrayList<>(r.formclub()));
        r = new Club("Gujarat Titans");
        alldata.put("Gujarat Titans",new ArrayList<>(r.formclub()));
        r = new Club("Chennai Super Kings");
        alldata.put("Chennai Super Kings",new ArrayList<>(r.formclub()));



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
                System.out.println("Client connected from port: " + clientSocket.getPort());


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
            SocketWrapper socketWrapper = null;
            try {
                socketWrapper = new SocketWrapper(socket);
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
                    while(true)
                        handleClientRequests(socketWrapper, username);
//                    Player one = (Player) socketWrapper.read();
//                    System.out.println("player received" + one);
                    //System.out.println("passed test already");

                } else {
                    socketWrapper.write("Invalid username or password");
                }
                //socketWrapper.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (socketWrapper != null) {
                        socketWrapper.close();
                    }
                    if (socket != null && !socket.isClosed()) {
                        socket.close(); // Close the socket when done
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Method to authenticate the credentials
        private boolean authenticate(String username, String password) {
            return credentials.containsKey(username) && credentials.get(username).equals(password);
        }
    }

    private static void handleClientRequests(SocketWrapper socketWrapper, String username) {
        try{
            while (true) {
                // Read the incoming request
                Object receivedObject = socketWrapper.read();
                if (receivedObject instanceof Request) {
                    Request request = (Request) receivedObject;
                    String command = request.getCommand();
                    Object data = request.getData();
                    double offerPrice = request.getOfferPrice();
                    System.out.println("Received command: " + command);


                    switch (command) {
                        case "TRANSFER":
                            handleTransfer((Player) data, username, offerPrice);
                            break;
                        case "BUY":
                            handleBuy((Player) data, username, offerPrice, socketWrapper);
                            break;
                        case "PLAYERLIST":
                            handlePlayerList(username, socketWrapper);
                            break;
                        default:
                            System.out.println("Unknown command: " + command);
                    }
                } else {
                    System.out.println("Invalid object received: " + receivedObject);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (socketWrapper != null) {
                    socketWrapper.close();
                    System.out.println("closing socketwrapper");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void handleTransfer(Player player, String username,double offerprice) {
        System.out.println("Processing transfer for player: " + player + " from " + username+" at price "+offerprice);
        File.write(player,offerprice);
        System.out.println("Player transferred successfully.");
    }

    private static void handleBuy(Player player, String username,double offerprice,SocketWrapper socketWrapper) {
        System.out.println("Processing purchase for player: " + player + " for " + username + "for " + offerprice);
        // Add player to the user's list and implement the purchase logic
        //alldata.get(username).add(player);
        String source = "";
        String dest = username;

        if (club_details.get(dest) < offerprice) socketWrapper.write("FAIL");
        else {
            Map<Player, Double> transferlist = File.read();
            if (!transferlist.containsKey(player)) {
                System.out.println("Player not available for purchase in transfer list: " + player);
                socketWrapper.write("NOT_AVAILABLE");
                return;
            }


            for (Player p : PlayerList.playerList) {
                if (p.getName().equals(player.getName())) {
                    source = p.getClub();
                    p.setClub(username);
                }
                //System.out.println(p);
            }
            //System.out.println(PlayerList.playerList);
            PlayerList.save((ArrayList<Player>) PlayerList.playerList);

            transferlist.remove(player);
            System.out.println(transferlist);
            File.write(transferlist);
            club_details.put(source, club_details.get(source) + offerprice);
            club_details.put(dest, club_details.get(dest) - offerprice);
            File.writeclub(club_details);


            System.out.println("Player purchased successfully.");
            socketWrapper.write("DONE");
        }

    }
    public static ArrayList<Player> handlePlayerList(String username,SocketWrapper socketWrapper)
    {
        System.out.println("request method reached in server");
        PlayerList.playerList = new ArrayList<Player>();
        PlayerList.makelist();
        Club ekhn = new Club(username);
        ArrayList<Player> eta = new ArrayList<>(ekhn.formclub());
        System.out.println(eta);
        socketWrapper.write(eta);
        return eta;

    }
}


/*
Virat Kohli,India,35,1.75,Royal Challengers Bangalore,Batsman,18,17000000
Jos Buttler,England,34,1.8,Rajasthan Royals,Wicketkeeper,63,17500000
Rashid Khan,Afghanistan,26,1.78,Gujarat Titans,Bowler,19,16000000
Ravindra Jadeja,India,35,1.73,Chennai Super Kings,Allrounder,8,16500000
David Warner,Australia,37,1.7,Delhi Capitals,Batsman,31,15000000
Jasprit Bumrah,India,30,1.78,Mumbai Indians,Bowler,93,17500000
MS Dhoni,India,43,1.78,Chennai Super Kings,Wicketkeeper,7,20000000
Andre Russell,West Indies,36,1.88,Kolkata Knight Riders,Allrounder,,18000000
Rohit Sharma,India,37,1.74,Mumbai Indians,Batsman,45,19000000
Glenn Maxwell,Australia,35,1.82,Royal Challengers Bangalore,Allrounder,32,18500000
Shubman Gill,India,25,1.78,Gujarat Titans,Batsman,77,15000000
KL Rahul,India,32,1.8,Lucknow Super Giants,Wicketkeeper,1,18500000
Suryakumar Yadav,India,34,1.78,Mumbai Indians,Batsman,63,17000000
Sam Curran,England,26,1.75,Punjab Kings,Allrounder,58,20000000
Mohammed Shami,India,34,1.73,Gujarat Titans,Bowler,1,14000000
Yuzvendra Chahal,India,33,1.7,Rajasthan Royals,Bowler,3,15000000
Cameron Green,Australia,25,1.93,Mumbai Indians,Allrounder,25,21000000
Nicholas Pooran,West Indies,29,1.83,Lucknow Super Giants,Wicketkeeper,1,16500000
Hardik Pandya,India,30,1.83,Gujarat Titans,Allrounder,33,18500000
Faf du Plessis,South Africa,40,1.78,Royal Challengers Bangalore,Batsman,18,17500000
Pat Cummins,Australia,31,1.92,Kolkata Knight Riders,Bowler,30,17500000
Sanju Samson,India,30,1.79,Rajasthan Royals,Wicketkeeper,9,17000000
Marcus Stoinis,Australia,35,1.85,Lucknow Super Giants,Allrounder,17,17500000
Deepak Chahar,India,32,1.79,Chennai Super Kings,Bowler,1,15000000
Kuldeep Yadav,India,29,1.74,Delhi Capitals,Bowler,23,15000000
Quinton de Kock,South Africa,31,1.7,Lucknow Super Giants,Wicketkeeper,12,16000000
Trent Boult,New Zealand,35,1.85,Rajasthan Royals,Bowler,18,17500000
Shivam Dube,India,31,1.83,Chennai Super Kings,Allrounder,25,13000000
Kane Williamson,New Zealand,34,1.83,Gujarat Titans,Batsman,22,16000000
Shikhar Dhawan,India,38,1.8,Punjab Kings,Batsman,42,17000000
Kagiso Rabada,South Africa,29,1.91,Punjab Kings,Bowler,,19000000
Axar Patel,India,30,1.83,Delhi Capitals,Allrounder,20,17500000
Mitchell Marsh,Australia,33,1.91,Delhi Capitals,Allrounder,31,18000000
Dinesh Karthik,India,39,1.7,Royal Challengers Bangalore,Wicketkeeper,21,16000000
Ruturaj Gaikwad,India,27,1.78,Chennai Super Kings,Batsman,31,15000000
Bhuvneshwar Kumar,India,34,1.78,Sunrisers Hyderabad,Bowler,15,14000000
Aiden Markram,South Africa,30,1.83,Sunrisers Hyderabad,Allrounder,16,16000000
Prithvi Shaw,India,24,1.68,Delhi Capitals,Batsman,100,12000000
Sunil Narine,West Indies,36,1.8,Kolkata Knight Riders,Allrounder,74,18000000
Washington Sundar,India,25,1.75,Sunrisers Hyderabad,Allrounder,5,14000000
Venkatesh Iyer,India,29,1.88,Kolkata Knight Riders,Allrounder,19,16000000
Tim David,Australia,28,1.96,Mumbai Indians,Allrounder,21,15000000
Mohammed Siraj,India,30,1.79,Royal Challengers Bangalore,Bowler,2,17000000
Rassie van der Dussen,South Africa,35,1.87,Rajasthan Royals,Batsman,78,15000000
Krunal Pandya,India,33,1.8,Lucknow Super Giants,Allrounder,24,16000000
Ishan Kishan,India,26,1.68,Mumbai Indians,Wicketkeeper,23,20000000
Lockie Ferguson,New Zealand,33,1.85,Kolkata Knight Riders,Bowler,69,17500000
Harshal Patel,India,33,1.75,Royal Challengers Bangalore,Bowler,1,15000000
Mustafizur Rahman,Bangladesh,29,1.8,Delhi Capitals,Bowler,90,14000000
Litton Das,Bangladesh,30,1.7,Kolkata Knight Riders,Wicketkeeper,,10000000
rafin,bangladesh,23,1.1,rising pune supergiants,batsman,99,1234321
 */


