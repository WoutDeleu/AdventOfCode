package year2023.Day7;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static year2023.Day7.Main.calculateTotalRank;
import static year2023.Day7.Main.readCards;

public class MainTest {

    @Test
    public void getHandsRankTest() throws FileNotFoundException {
        List<Hand> cards= readCards("./src/test/java/year2023/Day7/input");
        long score = calculateTotalRank(cards);
        assert score == 6440;
    }
    // @Test
    // public void testMainV2() throws FileNotFoundException {
    //     int[] cards = read("./src/test/java/year2023/Day7/input");
    //     int score = solve(cards);
    //     assert score == 35;
    // }
}

