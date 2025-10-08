package aoc;

import aoc.utils.InputReader;

import java.util.List;

/**
 * Base class for Advent of Code solutions
 */
public abstract class Solution {
    protected final int year;
    protected final int day;

    public Solution(int year, int day) {
        this.year = year;
        this.day = day;
    }

    /**
     * Solve part 1 of the puzzle
     * @param input The puzzle input
     * @return The solution
     */
    public abstract Object solvePart1(List<String> input);

    /**
     * Solve part 2 of the puzzle
     * @param input The puzzle input
     * @return The solution
     */
    public abstract Object solvePart2(List<String> input);

    /**
     * Run both parts with the actual input
     */
    public void solve() {
        System.out.println("=== Advent of Code " + year + " - Day " + day + " ===");

        List<String> input = InputReader.readLines(year, day);

        long startPart1 = System.nanoTime();
        Object resultPart1 = solvePart1(input);
        long endPart1 = System.nanoTime();

        System.out.printf("Part 1: %s (%.2f ms)%n", resultPart1, (endPart1 - startPart1) / 1_000_000.0);

        long startPart2 = System.nanoTime();
        Object resultPart2 = solvePart2(input);
        long endPart2 = System.nanoTime();

        System.out.printf("Part 2: %s (%.2f ms)%n", resultPart2, (endPart2 - startPart2) / 1_000_000.0);
    }

    /**
     * Run both parts with test input
     */
    public void solveTest() {
        System.out.println("=== Test - Advent of Code " + year + " - Day " + day + " ===");

        List<String> input = InputReader.readLines(year, day, true);

        System.out.println("Part 1 (test): " + solvePart1(input));
        System.out.println("Part 2 (test): " + solvePart2(input));
    }
}
