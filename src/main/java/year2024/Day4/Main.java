package year2024.Day4;

import aoc.utils.GridUtils;
import aoc.utils.ParsingUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final char[] XMAS = new char[] { 'X', 'M', 'A', 'S' };

    public static void main(String[] args) throws IOException {
        var input = readInput("./src/main/java/year2024/Day4/input");

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        char[][] matrix = parseInput(lines);
        int count = 0;
        for(int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'X') {
                    count = findSurroundingXMAS(count, 1, row, col, matrix);
                }
            }
        }
        return count;
    }

    static Object solvePart2(List<String> lines) {
        char[][] matrix = parseInput(lines);
        int count = 0;
        for(int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == 'A' && checkSurroundingCellAreValid(row, col, matrix) && surroundingCellsAreXMAS(row, col, matrix)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static char[][] parseInput(List<String> lines) {
        return ParsingUtils.parseGrid(lines);
    }

    private static int findSurroundingXMAS(int counter, int nextCharIndex, int currentRow, int currentCol, char[][] matrix) {
        char lookingFor = XMAS[nextCharIndex];
        for (int i = currentRow - 1; i <= currentRow + 1; i++) {
            for (int j = currentCol - 1; j <= currentCol + 1; j++) {
                if (!GridUtils.inBounds(i, j, matrix)) {
                    continue;
                }
                if (matrix[i][j] == lookingFor) {
                    counter = findDirectionalXMAS(counter , 1+nextCharIndex, i, j, i-currentRow, j-currentCol, matrix);
                }
            }
        }
        return counter;
    }

    private static int findDirectionalXMAS(int counter, int nextCharIndex, int currentRow, int currentCol, int factorRow, int factorCol, char[][] matrix) {
        char lookingFor = XMAS[nextCharIndex];
        if (GridUtils.inBounds(currentRow + factorRow, currentCol + factorCol, matrix)) {
            if (matrix[currentRow + factorRow][currentCol + factorCol] == lookingFor) {
                if (nextCharIndex == 3) {
                    return counter + 1;
                } else {
                    return findDirectionalXMAS(counter, ++nextCharIndex, currentRow + factorRow, currentCol + factorCol, factorRow, factorCol, matrix);
                }
            }
        }
        return counter;
    }

    private static boolean surroundingCellsAreXMAS(int row, int col, char[][] input) {
        return ((input[row+1][col-1] == 'S' && input[row-1][col+1] == 'M') || (input[row+1][col-1] == 'M' && input[row-1][col+1] == 'S')) && ((input[row-1][col-1] == 'S' && input[row+1][col+1] == 'M') || (input[row-1][col-1] == 'M' && input[row+1][col+1] == 'S'));
    }

    private static boolean checkSurroundingCellAreValid(int row, int col, char[][] input) {
        return GridUtils.inBounds(row-1, col-1, input) && GridUtils.inBounds(row+1, col+1, input);
    }

    static List<String> readInput(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }
}
