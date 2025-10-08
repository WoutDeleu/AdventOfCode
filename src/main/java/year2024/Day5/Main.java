package year2024.Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        var input = readInput("./src/main/java/year2024/Day5/input");

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        Object[] parsed = parseInput(lines);
        @SuppressWarnings("unchecked")
        Map<Integer, List<Integer>> orderRules = (Map<Integer, List<Integer>>) parsed[0];
        @SuppressWarnings("unchecked")
        List<int[]> updates = (List<int[]>) parsed[1];
        List<int[]> correctUpdates = new ArrayList<>();

        for(int[] update : updates) {
            boolean correct = true;
            for(int i=0; i<update.length; i++) {
                if(orderRules.containsKey(update[i])) {
                    List<Integer> before = orderRules.get(update[i]);
                    for(int j=0; j<i; j++) {
                        if(before.contains(update[j])) {
                            correct = false;
                            break;
                        }
                    }
                }
            }
            if(correct) {
                correctUpdates.add(update);
            }
        }
        int score = 0;
        for (int[] update : correctUpdates) {
            score += update[(update.length - 1)/2];
        }
        return score;
    }

    static Object solvePart2(List<String> lines) {
        Object[] parsed = parseInput(lines);
        @SuppressWarnings("unchecked")
        Map<Integer, List<Integer>> orderRules = (Map<Integer, List<Integer>>) parsed[0];
        @SuppressWarnings("unchecked")
        List<int[]> updates = (List<int[]>) parsed[1];
        List<int[]> incorrectUpdate = new ArrayList<>();

        for(int[] update : updates) {
            boolean correct = true;
            int[] alternated = new int[update.length];
            System.arraycopy(update, 0, alternated, 0, update.length);
            for(int i=0; i<update.length; i++) {
                if(orderRules.containsKey(alternated[i])) {
                    List<Integer> before = orderRules.get(alternated[i]);
                    for(int j=0; j<i; j++) {
                        if(before.contains(alternated[j])) {
                            correct = false;
                            int temp = alternated[i];
                            alternated[i] = alternated[j];
                            alternated[j] = temp;
                            i = 0;
                        }
                    }
                }
            }
            if (!correct) incorrectUpdate.add(alternated);
        }
        int score = 0;
        for (int[] update : incorrectUpdate) {
            score += update[(update.length - 1)/2];
        }
        return score;
    }

    private static Object[] parseInput(List<String> lines) {
        Map<Integer, List<Integer>> orderRules = new HashMap<>();
        List<int[]> updates = new ArrayList<>();
        boolean parsingRules = true;

        for (String line : lines) {
            if (line.isBlank()) {
                parsingRules = false;
                continue;
            }

            if (parsingRules) {
                String[] parts = line.split("\\|");
                int key = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);

                if(orderRules.containsKey(key)) {
                    orderRules.get(key).add(value);
                } else {
                    orderRules.put(key, new ArrayList<>(List.of(value)));
                }
            } else {
                String[] parts = line.split(",");
                int[] update = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    update[i] = Integer.parseInt(parts[i]);
                }
                updates.add(update);
            }
        }
        return new Object[]{orderRules, updates};
    }

    static List<String> readInput(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }
}