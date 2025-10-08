package aoc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    /**
     * Reads input file from resources as a list of lines
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @return List of lines from the input file
     */
    public static List<String> readLines(int year, int day) {
        return readLines(year, day, false);
    }

    /**
     * Reads input file from resources as a list of lines
     * @param year The year (e.g., 2024)
     * @param day The day (e.g., 1)
     * @param isTest Whether to read test input
     * @return List of lines from the input file
     */
    public static List<String> readLines(int year, int day, boolean isTest) {
        String filename = String.format("/inputs/%d/day%02d%s.txt", year, day, isTest ? "_test" : "");
        try (InputStream is = InputReader.class.getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed to read input file: " + filename, e);
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
