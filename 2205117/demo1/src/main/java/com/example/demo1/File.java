package com.example.demo1;
import com.example.demo1.Database.Player;

import java.io.*;
import java.util.ArrayList;

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


    static ArrayList<Player> read()
    {
        ArrayList<Player> a = new ArrayList<>();


        return a;
    }
}
