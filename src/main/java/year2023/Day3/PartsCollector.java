package year2023.Day3;

import java.util.*;

public class PartsCollector {
    static int collectParts(char[][] schematic) {
        List<Integer> numbers = new ArrayList<>();
        Set<List<Integer>> visited = new HashSet<>();
        for(int row = 0; row < schematic.length; row++) {
            for(int col = 0; col < schematic[row].length; col++) {
                char c = schematic[row][col];
                if (!Character.isDigit(c) && c  != '.') {
                    collectSurroundings(null, schematic, visited, numbers, row, col);
                }
            }
        }

        int sum = 0;
        for(int n : numbers) {
            sum += n;
        }
        return sum;
    }

    private static void collectSurroundings(StringBuilder currentNumber, char[][] schematic, Set<List<Integer>> visited, List<Integer> numbers, int row, int col) {
        if(currentNumber != null) {
            // fill out current number
            for(int i = -1; i <= 1; i++) {
                if(isValidDigit(schematic, visited, row, col+i) && i != 0) {
                    if(i < 0) {
                        currentNumber.insert(0, schematic[row][col + i]);
                    }
                    else {
                        currentNumber.append(schematic[row][col + i]);
                    }
                    visited.add(Arrays.asList(row, col + i));
                    collectSurroundings(currentNumber, schematic, visited, numbers, row, col + i);
                }
            }
        }
        else {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if(isValidDigit(schematic, visited, row+i, col+j)) {
                        // Create new number
                        currentNumber = new StringBuilder(Character.toString(schematic[row + i][col + j]));
                        visited.add(List.of(row + i, col + j));
                        collectSurroundings(currentNumber, schematic, visited, numbers, row + i, col + j);
                        numbers.add(Integer.parseInt(currentNumber.toString()));
                    }
                }
            }
        }
    }


    private static boolean isValidDigit(char[][] schematic, Set<List<Integer>> visited, int row, int col) {
        if (row >= 0 && col >= 0 && row < schematic.length && col < schematic[0].length)
            return Character.isDigit(schematic[row][col]) && !visited.contains(List.of(row, col));
        return false;
    }
}

