package year2024.Day6;

import utils.GridUtils;
import utils.InputReader;
import utils.ParsingUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 6);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        char[][] grid = parseInput(lines);
        int[] cors = findStartingCords(grid);
        int i = cors[0];
        int j = cors[1];

        int previousI = i;
        int previousJ = j;
        do {
            i += getDirection(grid[previousI][previousJ])[0];
            j += getDirection(grid[previousI][previousJ])[1];
            if(GridUtils.inBounds(i, j, grid)) {
                if (grid[i][j] == 'X' || grid[i][j] == '.') {
                    grid[i][j] = grid[previousI][previousJ];
                    grid[previousI][previousJ] = 'X';
                    previousI = i;
                    previousJ = j;
                } else {
                    grid[previousI][previousJ] = rotateInput(grid[previousI][previousJ]);
                    i = previousI;
                    j = previousJ;
                }
            }
        } while (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length);
        grid[previousI][previousJ] = 'X';
        return countX(grid);
    }

    static Object solvePart2(List<String> lines) {
        char[][] grid = parseInput(lines);
        var copyInput = copyMatrix(grid);
        int[] cors = findStartingCords(grid);

        // Get original path
        solvePart1Helper(grid);
        List<int[]> originalPath = getAllXsesPath(grid);

        int count = 0;
        for (int[] entry : originalPath) {
            int q = entry[0];
            int w = entry[1];

            int i = cors[0];
            int j = cors[1];
            int previousI = i;
            int previousJ = j;
            grid = copyMatrix(copyInput);
            if (grid[q][w] == '#' || grid[q][w] == '^' || grid[q][w] == 'v' || grid[q][w] == '<' || grid[q][w] == '>') {
                continue;
            }
            else {
                grid[q][w] = '#';
            }
            List<int[]> path = new ArrayList<>();
            List<Character> directions = new ArrayList<>();
            int steps = 0;
            do {
                i += getDirection(grid[previousI][previousJ])[0];
                j += getDirection(grid[previousI][previousJ])[1];
                if(i >= 0 && i < grid.length && j >= 0 && j < grid[i].length) {
                    if (grid[i][j] == 'X' || grid[i][j] == '.') {
                        grid[i][j] = grid[previousI][previousJ];
                        path.add(new int[]{previousI, previousJ});
                        directions.add(grid[previousI][previousJ]);
                        grid[previousI][previousJ] = 'X';
                        previousI = i;
                        previousJ = j;
                    } else {
                        grid[previousI][previousJ] = rotateInput(grid[previousI][previousJ]);
                        i = previousI;
                        j = previousJ;
                    }
                }
                steps++;
                if (steps > 10000) {
                    count++;
                    break;
                }
            } while (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length);
            grid[previousI][previousJ] = 'X';
            grid[q][w] = '#';
        }
        return count;
    }

    private static void solvePart1Helper(char[][] grid) {
        int[] cors = findStartingCords(grid);
        int i = cors[0];
        int j = cors[1];

        int previousI = i;
        int previousJ = j;
        do {
            i += getDirection(grid[previousI][previousJ])[0];
            j += getDirection(grid[previousI][previousJ])[1];
            if(GridUtils.inBounds(i, j, grid)) {
                if (grid[i][j] == 'X' || grid[i][j] == '.') {
                    grid[i][j] = grid[previousI][previousJ];
                    grid[previousI][previousJ] = 'X';
                    previousI = i;
                    previousJ = j;
                } else {
                    grid[previousI][previousJ] = rotateInput(grid[previousI][previousJ]);
                    i = previousI;
                    j = previousJ;
                }
            }
        } while (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length);
        grid[previousI][previousJ] = 'X';
    }

    private static char[][] parseInput(List<String> lines) {
        return ParsingUtils.parseGrid(lines);
    }

    private static int countX(char[][] input) {
        return GridUtils.countChar(input, 'X');
    }

    private static int[] findStartingCords(char[][] input) {
        int[] cords = new int[2];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '^' || input[i][j] == 'v' || input[i][j] == '<' || input[i][j] == '>') {
                    cords[0] = i;
                    cords[1] = j;
                    return cords;
                }
            }
        }
        return cords;
    }

    private static int[] getDirection(char c) {
        int[] direction = new int[2];
        return switch (c) {
            case '^' -> {
                direction[0] = -1;
                yield direction;
            }
            case 'v' -> {
                direction[0] = 1;
                yield direction;
            }
            case '<' -> {
                direction[1] = -1;
                yield direction;
            }
            case '>' -> {
                direction[1] = 1;
                yield direction;
            }
            default -> direction;
        };
    }

    private static char rotateInput(char c) {
        return switch (c) {
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            case '<' -> '^';
            default -> c;
        };
    }

    private static List<int[]> getAllXsesPath(char[][] input) {
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i<input.length; i++) {
            for(int j = 0; j<input[i].length; j++) {
                if(input[i][j] == 'X') result.add(new int[]{i, j});
            }
        }
        return result;
    }

    private static char[][] copyMatrix(char[][] input) {
        return GridUtils.copyGrid(input);
    }
}
