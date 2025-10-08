package year2016.Day1;

import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testPart1() throws IOException {
        var input = Main.readInput("./src/test/java/year2016/Day1/input");
        var result = Main.solvePart1(input);

        System.out.println("Part 1 result: " + result);
        // TODO: Update with expected result from puzzle
        // assertEquals(expectedValue, result);
        assertNotNull(result);
    }

    @Test
    public void testPart2() throws IOException {
        var input = Main.readInput("./src/test/java/year2016/Day1/input");
        var result = Main.solvePart2(input);

        System.out.println("Part 2 result: " + result);
        // TODO: Update with expected result from puzzle
        // assertEquals(expectedValue, result);
        assertNotNull(result);
    }
}
