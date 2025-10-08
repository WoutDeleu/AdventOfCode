package year2024.Day1;

import aoc.utils.ParsingUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var input = readInput("./src/main/java/year2024/Day1/input");

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();

        for (String line : lines) {
            List<Integer> parts = ParsingUtils.parseInts(line);
            list1.add(parts.get(0));
            list2.add(parts.get(1));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        int distance = 0;
        for (int i = 0; i < list1.size(); i++) {
            distance += Math.abs(list1.get(i) - list2.get(i));
        }
        return distance;
    }

    static Object solvePart2(List<String> lines) {
        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();

        for (String line : lines) {
            List<Integer> parts = ParsingUtils.parseInts(line);
            list1.add(parts.get(0));
            list2.add(parts.get(1));
        }

        int simularityScore = 0;
        for (int i = 0; i < list1.size(); i++) {
            int current = list1.get(i);
            int occurence = 0;
            for (int j = 0; j < list2.size(); j++) {
                if (current == list2.get(j)) {
                    occurence++;
                }
            }
            simularityScore += occurence * current;
        }
        return simularityScore;
    }

    static List<String> readInput(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }
}
