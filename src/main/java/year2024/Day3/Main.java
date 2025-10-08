package year2024.Day3;

import utils.InputReader;
import utils.ParsingUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 3);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        String input = String.join("", lines);
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        while (matcher.find()) {
            String foundMatch = matcher.group();

            if (foundMatch.startsWith("mul")) {
                List<Integer> numbers = ParsingUtils.extractInts(foundMatch);
                sum += numbers.get(0) * numbers.get(1);
            }
        }
        return sum;
    }

    static Object solvePart2(List<String> lines) {
        String input = String.join("", lines);
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        boolean enabled = true;
        while (matcher.find()) {
            String foundMatch = matcher.group();

            if (foundMatch.equals("do()")) {
                enabled = true;
            } else if (foundMatch.equals("don't()")) {
                enabled = false;
            }

            if (enabled && foundMatch.startsWith("mul")) {
                List<Integer> numbers = ParsingUtils.extractInts(foundMatch);
                sum += numbers.get(0) * numbers.get(1);
            }
        }
        return sum;
    }
}
