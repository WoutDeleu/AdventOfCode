package year2024.Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var input = readInput("./src/main/java/year2024/Day2/input");

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        List<int[]> reports = parseInput(lines);
        int amountOfSafe = 0;
        for (int[] report : reports) {
            boolean safe = checkIfFlowIsSafe(report);
            if (safe) amountOfSafe++;
        }
        return amountOfSafe;
    }

    static Object solvePart2(List<String> lines) {
        List<int[]> reports = parseInput(lines);
        int amountOfSafe = 0;
        for (int[] report : reports) {
            amountOfSafe = getAmountOfSafe(amountOfSafe, report);
        }
        return amountOfSafe;
    }

    private static List<int[]> parseInput(List<String> lines) {
        List<int[]> input = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(" ");
            int[] card = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                card[i] = Integer.parseInt(split[i]);
            }
            input.add(card);
        }
        return input;
    }

    private static boolean getAscendingDescending(int[] report) {
        for (int i = 0; i < report.length; i++) {
            if (report[i] != report[i + 1]) {
                if (report[i] < report[i + 1]) return true;
                else if (report[i] > report[i + 1]) return false;
            }
        }
        throw new RuntimeException();
    }

    private static boolean checkUnsafe(boolean ascending, int previousLvl, int i) {
        if (ascending) {
            return i - previousLvl <= 0 || i - previousLvl > 3;
        } else {
            return previousLvl - i <= 0 || previousLvl - i > 3;
        }
    }

    private static boolean checkIfFlowIsSafe(int[] report) {
        boolean ascending = getAscendingDescending(report);
        int previousLvl = report[0];
        for (int i = 1; i < report.length; i++) {
            if (checkUnsafe(ascending, previousLvl, report[i])) {
                return false;
            }
            previousLvl = report[i];
        }
        return true;
    }

    private static int getAmountOfSafe(int amountOfSafe, int[] report) {
        boolean safe = checkIfFlowIsSafe(report);
        if (safe) amountOfSafe++;
        else {
            for (int j = 0; j < report.length; j++) {
                int[] newArray = removeElement(j, report);
                safe = checkIfFlowIsSafe(newArray);
                if (safe) {
                    amountOfSafe++;
                    break;
                }
            }
        }
        return amountOfSafe;
    }

    private static int[] removeElement(int j, int[] report) {
        var newList = new int[report.length - 1];
        for (int i = 0; i < report.length; i++) {
            if (i < j) {
                newList[i] = report[i];
            } else if (i > j) {
                newList[i - 1] = report[i];
            }
        }
        return newList;
    }

    static List<String> readInput(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }
}
