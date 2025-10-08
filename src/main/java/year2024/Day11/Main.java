package year2024.Day11;

import utils.InputReader;
import utils.ParsingUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int BLINKING_COUNT = 25;

    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 11);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        List<Long> stones = parseInput(lines);
        BLINKING_COUNT = 25;
        for (int i = 0; i < BLINKING_COUNT; i++) {
            stones = changeStones(stones);
        }
        return stones.size();
    }

    static Object solvePart2(List<String> lines) {
        List<Long> stones = parseInput(lines);
        BLINKING_COUNT = 75;
        for (int i = 0; i < BLINKING_COUNT; i++) {
            stones = changeStones(stones);
        }
        return stones.size();
    }

    private static List<Long> parseInput(List<String> lines) {
        return ParsingUtils.parseLongs(lines.get(0));
    }

    private static boolean hasEvenNumberOfDigits(Long l) {
        return l.toString().length() % 2 == 0;
    }

    private static List<Long> changeStones(List<Long> stones) {
        List<Long> newStones = new ArrayList<>();
        for (Long stone : stones) {
            if (stone.equals(0L)) {
                newStones.add(1L);
            } else if (hasEvenNumberOfDigits(stone)) {
                Long[] split = splitLongDigitsInTwo(stone);
                newStones.add(split[0]);
                newStones.add(split[1]);
            } else {
                newStones.add(stone * 2024);
            }
        }
        return newStones;
    }

    private static Long[] splitLongDigitsInTwo(Long stone) {
        String stoneString = stone.toString();
        int mid = stoneString.length() / 2;
        String firstHalf = stoneString.substring(0, mid);
        String secondHalf = stoneString.substring(mid);
        return new Long[] {Long.parseLong(firstHalf), Long.parseLong(secondHalf)};
    }
}
