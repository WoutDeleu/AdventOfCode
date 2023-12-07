package year2023.Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        solveFirst();
        solveSecond();
    }

    private static void solveSecond() throws Exception {
        List<Hand> hands = readCards("./src/main/java/year2023/Day7/input", true);
        long score = calculateTotalRank(hands, true);
        System.out.println("score: " + score);
    }

    private static void solveFirst() throws Exception {
        List<Hand> hands = readCards("./src/main/java/year2023/Day7/input", false);
        long score = calculateTotalRank(hands, false);
        System.out.println("score: " + score);
    }

    static long calculateTotalRank(List<Hand> hands, boolean secondSolution) {
        long totalScore = 0;
        hands.sort(new HandComparator(secondSolution));
        for(int i = 1; i <= hands.size(); i++) {
            totalScore += (long) hands.get(i-1).getBid() * i;
        }
        return totalScore;
    }

    static List<Hand> readCards(String path, boolean secondSolution) throws Exception {
        Scanner sc = new Scanner(new File(path));
        List<Hand> cards = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            cards.add(new Hand(line[0].toCharArray(), Integer.parseInt(line[1]), secondSolution));
        }
        return cards;
    }
}
