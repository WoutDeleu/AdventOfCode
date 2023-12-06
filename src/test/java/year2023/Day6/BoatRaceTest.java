package year2023.Day6;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static year2023.Day6.BoatRace.*;

public class BoatRaceTest {

    @Test
    public void testMain() throws FileNotFoundException {
        List[] timesDistance = readGameInput("./src/test/java/year2023/Day6/input");
        long possibilities = calculateRacePossibilities(timesDistance);
        assert possibilities == 288;
    }
    @Test
    public void testMainV2() throws FileNotFoundException {
        List[] timesDistance = readGameInput("./src/test/java/year2023/Day6/input");
        List[] timesDistanceV2 = enlargeInput(timesDistance);
        long possibilities = calculateRacePossibilities(timesDistanceV2);
        assert 71503 == possibilities;
    }
}

