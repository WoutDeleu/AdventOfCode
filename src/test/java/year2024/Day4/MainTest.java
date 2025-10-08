package year2024.Day4;

import org.junit.Test;
import utils.InputReader;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testPart1() {
        var input = InputReader.readLines(2024, 4, true);
        var result = Main.solvePart1(input);

        System.out.println("Part 1 result: " + result);
        assertEquals(18, result);
    }

    @Test
    public void testPart2() {
        var input = InputReader.readLines("./src/test/java/year2024/Day4/input2");
        var result = Main.solvePart2(input);

        System.out.println("Part 2 result: " + result);
        assertEquals(9, result);
    }
}
