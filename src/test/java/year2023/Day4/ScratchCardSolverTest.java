package year2023.Day4;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static year2023.Day4.ScratchCardSolver.*;

public class ScratchCardSolverTest {
    @Test
    public void calculateScore() throws FileNotFoundException {
        List<List<int[]>> schematic = readScratchCard("./src/test/java/year2023/Day4/input");
        int score = calculateTotalScore(schematic);
        System.out.println(score);
        assert  score == 13;
    }
    @Test
    public void calculateAmountOfCards() throws FileNotFoundException {
        List<List<int[]>> schematic = readScratchCard("./src/test/java/year2023/Day4/input");
        int score = calculateTotalCardScoreExponential(schematic);
        System.out.println(score);
        assert  score == 30;
    }
}
