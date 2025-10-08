package year2024.Day8;

import static utils.GridUtils.*;
import static utils.ParsingUtils.*;
import static year2024.Day8.Util.*;

import utils.InputReader;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 8);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        char[][] grid = parseInput(lines);
        Map<Character, Stack<int[]>> charMap = availableChars(grid);
        for (var c : charMap.keySet()) {
            fillAntiNodes(grid, c, charMap, false);
            printGrid(grid);
            System.out.println();
        }
        return countAntinodes(grid);
    }

    static Object solvePart2(List<String> lines) {
        char[][] grid = parseInput(lines);
        Map<Character, Stack<int[]>> charMap = availableChars(grid);
        for (var c : charMap.keySet()) {
            fillAntiNodes(grid, c, charMap, true);
        }
        return countAntinodes(grid);
    }

    private static char[][] parseInput(List<String> lines) {
        return parseGrid(lines);
    }

    private static void fillAntiNodes(
        char[][] grid, Character c, Map<Character, Stack<int[]>> charMap, boolean pt2) {
        Stack<int[]> stack = deepCopy(charMap.get(c));
        while (!stack.isEmpty()) {
            int[] currentCoordinates = stack.pop();
            List<int[]> restingCoordinates = stack.stream().toList();
            for (int[] otherCoordinates : restingCoordinates) {
                DistanceVector distanceVector = new DistanceVector(currentCoordinates, otherCoordinates);
                List<int[]> antinodes = antisemetricPoints(distanceVector, grid, pt2);
                for (int[] antinode : antinodes) {
                    grid[antinode[0]][antinode[1]] = '#';
                }
            }
        }
    }

    private static List<int[]> antisemetricPoints(
        DistanceVector distanceVector, char[][] grid, boolean pt2) {
        if (!pt2) {
            return distanceVector.antisymmetricPoints(grid);
        } else {
            return distanceVector.allAntisymmetricPoints(grid);
        }
    }
}
