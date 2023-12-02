package year2023.Day2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static year2023.Day2.CubeGameSolver.countMax;
import static year2023.Day2.CubeGameSolver.readInput;


public class CubeGameSolverTest {
    @Test
    public void multiplicationTest() {
        List<String> games = readInput("./src/test/java/year2023/Day2/testInput");

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
        assert count == 2286;
    }
}
