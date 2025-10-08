package aoc.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for common grid/matrix operations in Advent of Code puzzles
 */
public class GridUtils {

    /**
     * Print a 2D char array (grid) to console
     */
    public static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     * Print a 2D int array (grid) to console
     */
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }

    /**
     * Print grid with custom separator
     */
    public static void printGrid(char[][] grid, String separator) {
        for (char[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) {
                    System.out.print(separator);
                }
            }
            System.out.println();
        }
    }

    /**
     * Check if coordinates are within grid bounds
     */
    public static boolean inBounds(int row, int col, char[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    /**
     * Check if coordinates are within grid bounds
     */
    public static boolean inBounds(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    /**
     * Check if coordinates are within grid bounds (generic)
     */
    public static boolean inBounds(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Get all 4-directional neighbors (up, down, left, right)
     */
    public static List<int[]> getNeighbors4(int row, int col, int rows, int cols) {
        List<int[]> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (inBounds(newRow, newCol, rows, cols)) {
                neighbors.add(new int[]{newRow, newCol});
            }
        }
        return neighbors;
    }

    /**
     * Get all 8-directional neighbors (including diagonals)
     */
    public static List<int[]> getNeighbors8(int row, int col, int rows, int cols) {
        List<int[]> neighbors = new ArrayList<>();
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (inBounds(newRow, newCol, rows, cols)) {
                neighbors.add(new int[]{newRow, newCol});
            }
        }
        return neighbors;
    }

    /**
     * Count occurrences of a character in the grid
     */
    public static int countChar(char[][] grid, char target) {
        int count = 0;
        for (char[] row : grid) {
            for (char c : row) {
                if (c == target) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Find all positions of a character in the grid
     */
    public static List<int[]> findAll(char[][] grid, char target) {
        List<int[]> positions = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == target) {
                    positions.add(new int[]{r, c});
                }
            }
        }
        return positions;
    }

    /**
     * Find the first position of a character in the grid
     */
    public static int[] findFirst(char[][] grid, char target) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == target) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    /**
     * Create a deep copy of a 2D char array
     */
    public static char[][] copyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, grid[i].length);
        }
        return copy;
    }

    /**
     * Create a deep copy of a 2D int array
     */
    public static int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, grid[i].length);
        }
        return copy;
    }

    /**
     * Transpose a grid (swap rows and columns)
     */
    public static char[][] transpose(char[][] grid) {
        char[][] transposed = new char[grid[0].length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                transposed[j][i] = grid[i][j];
            }
        }
        return transposed;
    }

    /**
     * Rotate grid 90 degrees clockwise
     */
    public static char[][] rotateClockwise(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        char[][] rotated = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = grid[i][j];
            }
        }
        return rotated;
    }

    /**
     * Flip grid horizontally
     */
    public static char[][] flipHorizontal(char[][] grid) {
        char[][] flipped = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                flipped[i][grid[0].length - 1 - j] = grid[i][j];
            }
        }
        return flipped;
    }

    /**
     * Flip grid vertically
     */
    public static char[][] flipVertical(char[][] grid) {
        char[][] flipped = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[grid.length - 1 - i], 0, flipped[i], 0, grid[0].length);
        }
        return flipped;
    }

    /**
     * Fill a region with a character (flood fill)
     */
    public static void floodFill(char[][] grid, int row, int col, char oldChar, char newChar) {
        if (!inBounds(row, col, grid) || grid[row][col] != oldChar || oldChar == newChar) {
            return;
        }

        grid[row][col] = newChar;
        floodFill(grid, row - 1, col, oldChar, newChar);
        floodFill(grid, row + 1, col, oldChar, newChar);
        floodFill(grid, row, col - 1, oldChar, newChar);
        floodFill(grid, row, col + 1, oldChar, newChar);
    }
}
