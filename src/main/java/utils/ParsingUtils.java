package aoc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Common parsing utilities for Advent of Code puzzles
 */
public class ParsingUtils {

    /**
     * Parse a line of space-separated integers
     */
    public static List<Integer> parseInts(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    /**
     * Parse a line of integers with custom delimiter
     */
    public static List<Integer> parseInts(String line, String delimiter) {
        return Arrays.stream(line.trim().split(delimiter))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    /**
     * Parse a line of space-separated longs
     */
    public static List<Long> parseLongs(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }

    /**
     * Parse a line of longs with custom delimiter
     */
    public static List<Long> parseLongs(String line, String delimiter) {
        return Arrays.stream(line.trim().split(delimiter))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }

    /**
     * Extract all integers from a string (including negatives)
     */
    public static List<Integer> extractInts(String text) {
        List<Integer> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers;
    }

    /**
     * Extract all longs from a string (including negatives)
     */
    public static List<Long> extractLongs(String text) {
        List<Long> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            numbers.add(Long.parseLong(matcher.group()));
        }
        return numbers;
    }

    /**
     * Extract all words (alphabetic sequences) from a string
     */
    public static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }

    /**
     * Parse a grid from list of strings
     */
    public static char[][] parseGrid(List<String> lines) {
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        return grid;
    }

    /**
     * Parse a grid of integers from list of strings
     */
    public static int[][] parseIntGrid(List<String> lines) {
        int[][] grid = new int[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            grid[i] = new int[line.length()];
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        return grid;
    }

    /**
     * Split input into blocks separated by blank lines
     */
    public static List<List<String>> splitBlocks(List<String> lines) {
        List<List<String>> blocks = new ArrayList<>();
        List<String> currentBlock = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                if (!currentBlock.isEmpty()) {
                    blocks.add(new ArrayList<>(currentBlock));
                    currentBlock.clear();
                }
            } else {
                currentBlock.add(line);
            }
        }

        if (!currentBlock.isEmpty()) {
            blocks.add(currentBlock);
        }

        return blocks;
    }

    /**
     * Parse comma-separated values
     */
    public static List<String> parseCSV(String line) {
        return Arrays.stream(line.split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    /**
     * Parse comma-separated integers
     */
    public static List<Integer> parseCSVInts(String line) {
        return Arrays.stream(line.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    /**
     * Parse comma-separated longs
     */
    public static List<Long> parseCSVLongs(String line) {
        return Arrays.stream(line.split(","))
            .map(String::trim)
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }

    /**
     * Parse a coordinate from "x,y" format
     */
    public static int[] parseCoordinate(String coord) {
        String[] parts = coord.split(",");
        return new int[]{
            Integer.parseInt(parts[0].trim()),
            Integer.parseInt(parts[1].trim())
        };
    }

    /**
     * Parse a range from "start-end" format
     */
    public static int[] parseRange(String range) {
        String[] parts = range.split("-");
        return new int[]{
            Integer.parseInt(parts[0].trim()),
            Integer.parseInt(parts[1].trim())
        };
    }

    /**
     * Parse hexadecimal string to integer
     */
    public static int parseHex(String hex) {
        return Integer.parseInt(hex.replaceFirst("^#?", ""), 16);
    }

    /**
     * Parse binary string to integer
     */
    public static int parseBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    /**
     * Convert character to digit (0-9 for '0'-'9')
     */
    public static int charToDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        throw new IllegalArgumentException("Character is not a digit: " + c);
    }

    /**
     * Check if string is numeric
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Remove all whitespace from string
     */
    public static String removeWhitespace(String str) {
        return str.replaceAll("\\s+", "");
    }

    /**
     * Parse a list of lines into a map of key-value pairs (split by delimiter)
     */
    public static java.util.Map<String, String> parseKeyValues(List<String> lines, String delimiter) {
        java.util.Map<String, String> map = new java.util.HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(delimiter, 2);
            if (parts.length == 2) {
                map.put(parts[0].trim(), parts[1].trim());
            }
        }
        return map;
    }
}
