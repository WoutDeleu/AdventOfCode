package year2023.Day8;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static year2023.Day8.MapSolver.*;

public class MapSolverTest {

    @Test
    public void testMain() throws FileNotFoundException {
        List<RightLeft> directions = readSteps("./src/test/java/year2023/Day8/input");
        Map<String, String[]> nodes = readNodes("./src/test/java/year2023/Day8/input");
        long amountOfSteps = calculateAmountOfSteps("AAA", "ZZZ", directions, nodes);
        assert amountOfSteps == 2;
    }
    @Test
    public void testMainV2() throws FileNotFoundException {
        List<RightLeft> directions = readSteps("./src/test/java/year2023/Day8/input2");
        Map<String, String[]> nodes = readNodes("./src/test/java/year2023/Day8/input2");
        long amountOfSteps = calculateAmountOfSteps("AAA", "ZZZ", directions, nodes);
        assert amountOfSteps == 6;
    }
    @Test
    public void testMainpt2() throws FileNotFoundException {
        List<RightLeft> directions = readSteps("./src/test/java/year2023/Day8/input_pt2");
        Map<String,String[]> nodes = readNodes("./src/test/java/year2023/Day8/input_pt2");
        long amountOfSteps = calculateAmountOfStepsMultiPath(directions, nodes);
        assert amountOfSteps == 6;
    }
    @Test
    public void testMain2pt2() throws FileNotFoundException {
        List<RightLeft> directions = readSteps("./src/test/java/year2023/Day8/input2_pt2");
        Map<String,String[]> nodes = readNodes("./src/test/java/year2023/Day8/input2_pt2");
        long amountOfSteps = calculateAmountOfStepsMultiPath(directions, nodes);
        assert amountOfSteps ==21;
    }
}

