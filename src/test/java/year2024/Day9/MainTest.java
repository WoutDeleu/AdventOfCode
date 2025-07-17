  package year2024.Day9;

import java.io.FileNotFoundException;
import org.junit.Test;

public class MainTest {


    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day9/input");
      var solution = main.solve_pt1(input);
      System.out.println("Solution: " + solution);
      assert solution == 1928;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day9/input");
      int solution = main.solve_pt2(input);
      System.out.println("Solution: " + solution);
      assert solution == 1;
    }

  }

