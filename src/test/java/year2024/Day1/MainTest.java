package year2024.Day1;

import org.junit.Test;

import java.io.FileNotFoundException;

import static year2024.Day1.Main.*;

// todo - refactor
public class MainTest {
  @Test
  public void part2() throws FileNotFoundException {
    var cards = read("./src/test/java/year2024/Day1/input");
    assert solve_pt2(cards) == 31;
  }

  @Test
  public void part1() throws FileNotFoundException {
    var cards = read("./src/test/java/year2024/Day1/input");
    assert solve_pt1(cards) == 11;
  }
}