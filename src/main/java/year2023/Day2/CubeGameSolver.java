package year2023.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CubeGameSolver {
    public static boolean isPossible(String[] subsets, HashMap<String, Integer> targetCounts) {
        // Initialize counts for each color
        HashMap<String, Integer> counts = new HashMap<>();
        counts.put("red", 0);
        counts.put("green", 0);
        counts.put("blue", 0);

        // Iterate through each subset in the game
        for (String subset : subsets) {
            // Parse the subset to get color and count
            String[] parts = subset.split(", ");
            for(String block : parts) {
                String[] subparts = block.split(" ");
                String color = subparts[1];
                int count = Integer.parseInt(subparts[0]);

                // Update the count for the corresponding color
                counts.put(color, count);

                // Check if the counts exceed the target counts
                if (counts.get("red") > targetCounts.get("red") ||
                        counts.get("green") > targetCounts.get("green") ||
                        counts.get("blue") > targetCounts.get("blue")) {
                    return false;
                }
            }
        }

        // If all subsets are processed without exceeding the target counts, the game is possible
        return true;
    }

    public static void main(String[] args) {
        // Set the target cube counts
        HashMap<String, Integer> targetCounts = new HashMap<>();
        targetCounts.put("red", 12);
        targetCounts.put("green", 13);
        targetCounts.put("blue", 14);

        List<String> games = readInput("./src/main/java/year2023/Day2/input");

        // Initialize the sum of IDs for possible games
        int sumOfIds = 0;

        // Iterate through each game and check if it's possible
        int gameId = 1;
        for (String game : games) {
            String[] subsets = game.split("; ");

            // Check if the game is possible based on the target cube counts
            if (isPossible(subsets, targetCounts)) {
                sumOfIds += gameId;
            }
            gameId ++;
        }

        // Print the sum of IDs for possible games
        System.out.println(sumOfIds);
    }

    private static List<String> readInput(String input) {
        try {
            File file = new File(input);
            Scanner scanner = new Scanner(file);
            List<String> games = new ArrayList<>();
            while (scanner.hasNextLine()) {
                games.add(scanner.nextLine().split(": ")[1]);
            }
            scanner.close();
            return games;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
