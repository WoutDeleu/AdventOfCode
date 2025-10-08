package year2023.Day7;

import utils.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        var input = InputReader.readLines(2023, 7);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) throws Exception {
        List<Hand> hands = parseCards(lines, false);
        return calculateTotalRank(hands, false);
    }

    static Object solvePart2(List<String> lines) throws Exception {
        List<Hand> hands = parseCards(lines, true);
        return calculateTotalRank(hands, true);
    }

    static long calculateTotalRank(List<Hand> hands, boolean secondSolution) {
        long totalScore = 0;
        hands.sort(new HandComparator(secondSolution));
        for(int i = 1; i <= hands.size(); i++) {
            totalScore += (long) hands.get(i-1).getBid() * i;
        }
        return totalScore;
    }

    static List<Hand> parseCards(List<String> lines, boolean secondSolution) throws Exception {
        List<Hand> cards = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            cards.add(new Hand(parts[0].toCharArray(), Integer.parseInt(parts[1]), secondSolution));
        }
        return cards;
    }
}
