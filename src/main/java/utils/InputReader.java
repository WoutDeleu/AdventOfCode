package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {

    /**
     * Reads input file from file system as a list of lines
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @return List of lines from the input file
     */
    public static List<String> readLines(int year, int day) {
        return readLines(year, day, false);
    }

    /**
     * Reads input file from file system as a list of lines
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @param isTest Whether to read test input
     * @return List of lines from the input file
     */
    public static List<String> readLines(int year, int day, boolean isTest) {
        String basePath = isTest ? "./src/test/java" : "./src/main/java";
        String path = String.format("%s/year%d/Day%d/input", basePath, year, day);
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input file: " + path, e);
        }
    }

    /**
     * Reads input file from a custom path
     * @param path The file path
     * @return List of lines from the input file
     */
    public static List<String> readLines(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input file: " + path, e);
        }
    }

    /**
     * Reads input file as a single string
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @return The entire file as a string
     */
    public static String readAsString(int year, int day) {
        return readAsString(year, day, false);
    }

    /**
     * Reads input file as a single string
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @param isTest Whether to read test input
     * @return The entire file as a string
     */
    public static String readAsString(int year, int day, boolean isTest) {
        return String.join("\n", readLines(year, day, isTest));
    }

    /**
     * Reads input file as a 2D character array
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @return 2D char array representing the grid
     */
    public static char[][] readAsGrid(int year, int day) {
        return readAsGrid(year, day, false);
    }

    /**
     * Reads input file as a 2D character array
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @param isTest Whether to read test input
     * @return 2D char array representing the grid
     */
    public static char[][] readAsGrid(int year, int day, boolean isTest) {
        List<String> lines = readLines(year, day, isTest);
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        return grid;
    }
}
