package year2024.Day2;

import org.junit.Test;

import java.io.FileNotFoundException;

import static year2024.Day2.Main.*;

// todo - refactor
public class MainTest {
  @Test
  public void part1() throws FileNotFoundException {
    var cards = read("./src/test/java/year2024/Day2/input");
    int solution = solve_pt1(cards);
    assert solution == 2;
  }

  @Test
  public void part2() throws FileNotFoundException {
    var cards = read("./src/test/java/year2024/Day2/input");
    assert solve_pt2(cards) == 2;
  }
}
