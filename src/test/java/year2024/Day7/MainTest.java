package year2024.Day7;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.InputReader;

@DisplayName("2024 - Day 7")
public class MainTest {
  @Test
  public void testPart1() {
    var input = InputReader.readLines(2024, 7, true);
    var result = Main.solvePart1(input);

    System.out.println("Part 1 result: " + result);
    assertEquals(3749L, result);
  }

  @Test
  public void testPart2() {
    var input = InputReader.readLines(2024, 7, true);
    var result = Main.solvePart2(input);

    System.out.println("Part 2 result: " + result);
    assertEquals(11387L, result);
  }
}
