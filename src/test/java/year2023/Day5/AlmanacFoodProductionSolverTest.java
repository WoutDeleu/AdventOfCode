package year2023.Day5;

import static year2023.Day5.AlmanacFoodProductionSolver.findClosestLocation;
import static year2023.Day5.AlmanacFoodProductionSolver.findClosestLocationEnlarged;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("2023 - Day 5")
public class AlmanacFoodProductionSolverTest {

  @Test
  public void solveAlmanac() throws FileNotFoundException {
    long closestLocation = findClosestLocation("./src/test/java/year2023/Day5/input");
    assert closestLocation == 35;
  }

  @Test
  public void solveAlmanacV2() throws FileNotFoundException {
    long closestLocation = findClosestLocationEnlarged("./src/test/java/year2023/Day5/input");
    assert closestLocation == 46;
  }
}
