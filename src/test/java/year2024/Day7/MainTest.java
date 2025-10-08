package year2024.Day7;

import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testPart1() throws IOException {
        var input = Main.readInput("./src/test/java/year2024/Day7/input");
        var result = Main.solvePart1(input);

        System.out.println("Part 1 result: " + result);
        assertEquals(3749L, result);
    }

    @Test
    public void testPart2() throws IOException {
        var input = Main.readInput("./src/test/java/year2024/Day7/input");
        var result = Main.solvePart2(input);

        System.out.println("Part 2 result: " + result);
        assertEquals(11387L, result);
    }
}
