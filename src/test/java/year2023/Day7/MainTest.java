package year2023.Day7;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.InputReader;

@DisplayName("2023 - Day 7")
public class MainTest {
  @Test
  public void testPart1() throws Exception {
    var input = InputReader.readLines(2023, 7, true);
    var result = Main.solvePart1(input);

    System.out.println("Part 1 result: " + result);
    assertEquals(6440L, result);
  }

  @Test
  public void testPart2() throws Exception {
    var input = InputReader.readLines(2023, 7, true);
    var result = Main.solvePart2(input);

    System.out.println("Part 2 result: " + result);
    assertEquals(5905L, result);
  }

  @Test
  public void testPart2WithTestInput() throws Exception {
    var input = InputReader.readLines("./src/test/java/year2023/Day7/testInput");
    var result = Main.solvePart2(input);

    System.out.println("Part 2 result (testInput): " + result);
    assertEquals(1369L, result);
  }

  @Test
  public void testPart2WithTestInput2() throws Exception {
    var input = InputReader.readLines("./src/test/java/year2023/Day7/testInput2");
    var result = Main.solvePart2(input);

    System.out.println("Part 2 result (testInput2): " + result);
    assertEquals(3667L, result);
  }
}
