package com.example.demo1;
import com.example.demo1.Database.Player;

import java.io.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class File {

    private static final String FILE_PATH = "transfer.txt";

    public static synchronized void write(Player player, double price) {
        final String FILE_PATH = "transfer.txt"; // Path to the transfer file

        try {
            // Check if the player's name already exists in the file
            if (isPlayerAlreadyInFile(player.getName(), FILE_PATH)) {
                System.out.println("Player already exists in the transfer file: " + player.getName());
                return; // Skip writing if the player already exists
            }

            // Open the file in append mode to add new data
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                // Format player data with the price
                String playerData = formatPlayerData(player, price);
                // Write the formatted data to the file
                writer.write(playerData);
                writer.newLine(); // Add a new line after the entry
                System.out.println("Player written to file successfully: " + player.getName());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Helper method to check if a player's name already exists in the file
    private static boolean isPlayerAlreadyInFile(String playerName, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the player's name
                if (line.startsWith(playerName + ",")) {
                    return true; // Player name found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false; // Player name not found
    }

    // Helper method to format player data including price
    private static String formatPlayerData(Player player, double price) {
        return String.format(
                "%s,%s,%d,%.2f,%s,%s,%d,%d,%.2f",
                player.getName(),
                player.getCountry(),
                player.getAge(),
                player.getHeight(),
                player.getClub(),
                player.getPosition(),
                player.getJersey_no(),
                player.getSalary(),
                price // Add the price at the end
        );
    }




    public static Map<Player, Double> read() {
        Map<Player, Double> playerPriceMap = new HashMap<>();

        final String FILE_PATH = "transfer.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using comma as the delimiter
                String[] parts = line.split(",");

                // Ensure the line has the required number of fields (8 for player + 1 for price)
                if (parts.length == 9) {
                    // Parse the player fields
                    String name = parts[0].trim();
                    String country = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    double height = Double.parseDouble(parts[3].trim());
                    String club = parts[4].trim();
                    String position = parts[5].trim();
                    int jersey_no = Integer.parseInt(parts[6].trim());
                    long salary = Long.parseLong(parts[7].trim());

                    // Parse the price (9th field)
                    double price = Double.parseDouble(parts[8].trim());

                    // Create the Player object
                    Player player = new Player(name, country, age, height, club, position, jersey_no, salary);

                    // Add to the map
                    playerPriceMap.put(player, price);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return playerPriceMap;
    }

}
