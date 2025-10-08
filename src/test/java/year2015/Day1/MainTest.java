package year2015.Day1;

import org.junit.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testPart1() throws IOException {
        // Test various string inputs
        assertEquals(0, Main.solvePart1(List.of("(())")));
        assertEquals(0, Main.solvePart1(List.of("()()")));
        assertEquals(3, Main.solvePart1(List.of("(((")));
        assertEquals(3, Main.solvePart1(List.of("(()(()(")));
        assertEquals(3, Main.solvePart1(List.of("))(((((")));
        assertEquals(-1, Main.solvePart1(List.of("())")));
        assertEquals(-1, Main.solvePart1(List.of("))(")));
        assertEquals(-3, Main.solvePart1(List.of(")))")));
        assertEquals(-3, Main.solvePart1(List.of(")())())")));
    }

    @Test
    public void testPart2() throws IOException {
        // Test various string inputs
        assertEquals(1, Main.solvePart2(List.of(")")));
        assertEquals(5, Main.solvePart2(List.of("()())")));
    }
}
