package year2015.Day1;

import utils.InputReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2015, 1);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        String input = String.join("", lines);
        int floor = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') floor++;
            else floor--;
        }
        return floor;
    }

    static Object solvePart2(List<String> lines) {
        String input = String.join("", lines);
        int floor = 0;
        int count = 0;
        for (char c : input.toCharArray()) {
            count++;
            if (c == '(') floor++;
            else floor--;
            if (floor < 0) break;
        }
        return count;
    }
}
