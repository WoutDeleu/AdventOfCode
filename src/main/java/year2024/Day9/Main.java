package year2024.Day9;

import static year2024.Day9.Utils.printLayout;

import utils.InputReader;
import utils.ParsingUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 9);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        int[] diskMap = parseInput(lines);
        char[] spaceLayout = createSpaceLayout(diskMap);
        int[] compactedLayout = compactSpaceLayout(spaceLayout);
        printLayout(compactedLayout);
        return checksum(compactedLayout);
    }

    static Object solvePart2(List<String> lines) {
        return 0;
    }

    private static int[] parseInput(List<String> lines) {
        String line = lines.get(0);
        return line.chars().map(c -> utils.ParsingUtils.charToDigit((char) c)).toArray();
    }

    private static int[] compactSpaceLayout(char[] spaceLayout) {
        int index = 0, reverseIndex = spaceLayout.length - 1;
        StringBuilder compactedLayout = new StringBuilder();

        while (index <= reverseIndex) {
            if (Character.isDigit(spaceLayout[index])) {
                compactedLayout.append(spaceLayout[index]);
                index++;
            } else {
                if (Character.isDigit(spaceLayout[reverseIndex])) {
                    compactedLayout.append(spaceLayout[reverseIndex]);
                    index++;
                }
                reverseIndex--;
            }
        }

        return getInts(compactedLayout);
    }

    private static int[] getInts(StringBuilder compactedLayout) {
        return compactedLayout.toString().chars().map(c -> utils.ParsingUtils.charToDigit((char) c)).toArray();
    }

    private static long checksum(int[] layout) {
        long checkSum = 0;
        for (int i = 0; i < layout.length; i++) {
            checkSum += (long) layout[i] * i;
        }
        return checkSum;
    }

    private static char[] createSpaceLayout(int[] input) {
        StringBuilder spaceLayout = new StringBuilder();

        boolean isFile = true, isFreeSpace = false;
        int index = 0;
        for (int i : input) {
            if (isFile) {
                spaceLayout.append(Integer.toString(index).repeat(i));
                index++;
            }
            if (isFreeSpace) {
                spaceLayout.append(".".repeat(i));
            }

            isFile = !isFile;
            isFreeSpace = !isFreeSpace;
        }

        return spaceLayout.toString().toCharArray();
    }
}

class Utils {
    public static void printLayout(char[] layout) {
        for (char c : layout) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void printLayout(int[] layout) {
        for (int c : layout) {
            System.out.print(c);
        }
        System.out.println();
    }
}
