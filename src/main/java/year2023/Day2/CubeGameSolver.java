package year2023.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CubeGameSolver {
    public static HashMap<String, Integer> countMax(String[] subsets) {
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
                if(counts.get(color) < count) counts.put(color, count);
            }
        }

        // If all subsets are processed without exceeding the target counts, the game is possible
        return counts;
    }

    public static void main(String[] args) {
        List<String> games = readInput("./src/main/java/year2023/Day2/input");

        int count = 0;
        // Iterate through each game and check if it's possible
        for (String game : games) {
            String[] subsets = game.split("; ");
            // Check if the game is possible based on the target cube counts
            HashMap<String, Integer> counts = countMax(subsets);
            int factor = 1;
            for (int v : counts.values()) {
                factor *= v;
            }
            count += factor;
        }

        // Print the sum of IDs for possible games
        System.out.println(count);
    }

    static List<String> readInput(String input) {
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
