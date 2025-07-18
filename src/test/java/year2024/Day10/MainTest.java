package year2024.Day10;

import org.junit.Test;

import java.io.FileNotFoundException;

public class MainTest {
  @Test
  public void part1() throws FileNotFoundException {
    var main = new Main();
    var input = main.read("./src/test/java/year2024/Day10/input");
    long solution = main.solve_pt1(input);
    System.out.println("Solution: " + solution);
    System.out.println();
    assert solution == 36;
  }

  @Test
  public void part2() throws FileNotFoundException {
    var main = new Main();
    var input = main.read("./src/test/java/year2024/Day10/input");
    long solution = main.solve_pt2(input);
    System.out.println("Solution: " + solution);
    System.out.println();
    assert solution == 81;
  }
}
