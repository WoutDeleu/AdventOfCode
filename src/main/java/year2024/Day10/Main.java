package year2024.Day10;

import utils.InputReader;
import utils.ParsingUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 10);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        int[][] map = parseInput(lines);
        Utils.printMap(map);
        List<TrailHead> trailheads = getAllTrailHeads(map);
        for (TrailHead trailHead : trailheads) {
            trailHead.calculateAccessibleTops(map);
        }
        return calculateTotalAccessibleTops(trailheads);
    }

    static Object solvePart2(List<String> lines) {
        int[][] map = parseInput(lines);
        List<TrailHead> trailheads = getAllTrailHeads(map);
        for (TrailHead trailHead : trailheads) {
            trailHead.calculateAllPaths(map);
        }
        return calculateTotalPaths(trailheads);
    }

    private static int[][] parseInput(List<String> lines) {
        return ParsingUtils.parseIntGrid(lines);
    }

    private static long calculateTotalPaths(List<TrailHead> trailheads) {
        long score = 0;
        for (TrailHead trailHead : trailheads) {
            score += trailHead.getPathsCount();
        }
        return score;
    }

    private static long calculateTotalAccessibleTops(List<TrailHead> trailheads) {
        long score = 0;
        for (TrailHead trailHead : trailheads) {
            score += trailHead.getAccessibleTops();
        }
        return score;
    }

    private static List<TrailHead> getAllTrailHeads(int[][] input) {
        List<TrailHead> trailheads = new ArrayList<>();
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                if (input[row][col] == 0) {
                    trailheads.add(new TrailHead(row, col));
                }
            }
        }
        return trailheads;
    }
}

class Utils {
    public static void printMap(int[][] map) {
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
