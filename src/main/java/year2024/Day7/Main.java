package year2024.Day7;

import utils.InputReader;
import utils.ParsingUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 7);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        List<Equation> equations = parseInput(lines);
        long result = 0;
        for (Equation equation : equations) {
            long desiredSum = equation.sum();
            var args = equation.args();
            long tempSum = 0;

            if (isPossible(desiredSum, args, tempSum, false)) {
                result += equation.sum();
            }
        }
        return result;
    }

    static Object solvePart2(List<String> lines) {
        List<Equation> equations = parseInput(lines);
        long result = 0;
        for (Equation equation : equations) {
            long desiredSum = equation.sum();
            var args = equation.args();
            long tempSum = 0;

            if (isPossible(desiredSum, args, tempSum, true)) {
                result += equation.sum();
            }
        }
        return result;
    }

    private static List<Equation> parseInput(List<String> lines) {
        List<Equation> result = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            Long sum = Long.parseLong(parts[0]);
            var args = ParsingUtils.parseLongs(parts[1]);
            result.add(new Equation(sum, new LinkedList<>(args)));
        }
        return result;
    }

    private static boolean isFirstCall(long tempSum) {
        return tempSum == 0;
    }

    private static boolean isPossible(long desiredSum, Queue<Long> args, long tempSum, boolean pt2) {
        // deep copy of args to avoid modifying the original queue
        var currentQueue = new LinkedList<>(args);

        // stop condition for recursion
        if (currentQueue.isEmpty()) {
            return tempSum == desiredSum;
        }

        // pruning condition
        if (tempSum > desiredSum) {
            return false;
        }

        long nextArg = currentQueue.poll();

        ///  ADDITION
        tempSum += nextArg;
        if (isPossible(desiredSum, currentQueue, tempSum, pt2)) {
            return true;
        }
        // recursion
        tempSum -= nextArg;

        // first element -> no need to try multiplication
        if (isFirstCall(tempSum)) {
            return false;
        }

        /// MULTIPLICATION
        tempSum *= nextArg;
        if (isPossible(desiredSum, currentQueue, tempSum, pt2)) {
            return true;
        }
        tempSum /= nextArg;

        // check if concatenation is allowed
        if (!pt2) {
            return false;
        }

        ///  CONCATENATION
        tempSum = concatenate(tempSum, nextArg);
        return isPossible(desiredSum, currentQueue, tempSum, pt2);
    }

    private static long concatenate(long tempSum, long nextArg) {
        String tempSumStr = String.valueOf(tempSum);
        String nextArgStr = String.valueOf(nextArg);
        return Long.parseLong(tempSumStr + nextArgStr);
    }

    public record Equation(Long sum, Queue<Long> args) {}
}
