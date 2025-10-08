package year2018.Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var input = readInput("./src/main/java/year2018/Day1/input");

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        // TODO: Implement part 1
        return 0;
    }

    static Object solvePart2(List<String> lines) {
        // TODO: Implement part 2
        return 0;
    }

    static List<String> readInput(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }
}
