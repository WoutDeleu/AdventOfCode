package year2023.Day5;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static year2023.Day5.AlmanacFoodProductionSolver.findClosestLocation;
import static year2023.Day5.AlmanacFoodProductionSolver.findClosestLocationEnlarged;

public class AlmanacFoodProductionSolverTest {

    @Test
    public void solveAlmanac() throws FileNotFoundException {
        long closestLocation = findClosestLocation("./src/test/java/year2023/Day5/input");
        assert closestLocation == 35;
    }
    @Test
    public void solveAlmanacV2() throws FileNotFoundException {
        long  closestLocation = findClosestLocationEnlarged("./src/test/java/year2023/Day5/input");
        assert closestLocation == 46;
    }
}
