package year2024.Day10;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.InputReader;

@DisplayName("2024 - Day 10")
public class MainTest {
  @Test
  public void testPart1() {
    var input = InputReader.readLines(2024, 10, true);
    var result = Main.solvePart1(input);

    System.out.println("Part 1 result: " + result);
    assertEquals(36L, result);
  }

  @Test
  public void testPart2() {
    var input = InputReader.readLines(2024, 10, true);
    var result = Main.solvePart2(input);

    System.out.println("Part 2 result: " + result);
    assertEquals(81L, result);
  }
}
