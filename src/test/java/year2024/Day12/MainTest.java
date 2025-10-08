  package year2024.Day12;

import java.io.FileNotFoundException;
import org.junit.Test;

public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = Input.read("./src/test/java/year2024/Day12/input");
      long solution = new Part1().solve_pt1(input);
      System.out.println("Solution: " + solution);
      assert solution == 1930;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = Input.read("./src/test/java/year2024/Day12/input");
      long solution = main.solve_pt2(input);
      System.out.println("Solution: " + solution);
      assert solution == 1206;
    }
  }
