package year2023.Day7;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static year2023.Day7.Main.calculateTotalRank;
import static year2023.Day7.Main.readCards;

public class MainTest {

    @Test
    public void getHandsRankTest() throws FileNotFoundException {
        List<Hand> cards= readCards("./src/test/java/year2023/Day7/input", false);
        long score = calculateTotalRank(cards, false);
        assert score == 6440;
    }
    @Test
    public void getHandsRankWithJokerTest() throws FileNotFoundException {
        List<Hand> cards = readCards("./src/test/java/year2023/Day7/input", true);
        long score = calculateTotalRank(cards, true);
        assert score == 5905;
    }
    @Test
    public void getHandsRankWithJokerTest2() throws FileNotFoundException {
        List<Hand> cards = readCards("./src/test/java/year2023/Day7/testInput", true);
        long score = calculateTotalRank(cards, true);
        assert score == 1369;
    }
}

