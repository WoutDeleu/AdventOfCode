package year2024.Day6;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.InputReader;

@DisplayName("2024 - Day 6")
public class MainTest {
  @Test
  public void testPart1() {
    var input = InputReader.readLines(2024, 6, true);
    var result = Main.solvePart1(input);

    System.out.println("Part 1 result: " + result);
    assertEquals(41, result);
  }

  @Test
  public void testPart2() {
    var input = InputReader.readLines(2024, 6, true);
    var result = Main.solvePart2(input);

    System.out.println("Part 2 result: " + result);
    assertEquals(6, result);
  }
}
