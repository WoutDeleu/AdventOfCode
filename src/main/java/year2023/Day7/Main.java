package year2023.Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Hand> hands = readCards("./src/main/java/year2023/Day7/input");
        long score = calculateTotalRank(hands);
        System.out.println("score: " + score);
    }

    static long calculateTotalRank(List<Hand> hands) {
        long totalScore = 0;
        hands.sort(new HandComparator());
        for(int i = 1; i <= hands.size(); i++) {
            totalScore += (long) hands.get(i-1).getBid() * i;
        }
        return totalScore;
    }

    static List<Hand> readCards(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        List<Hand> cards = new ArrayList<Hand>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            cards.add(new Hand(line[0].toCharArray(), Integer.parseInt(line[1])));
        }
        return cards;
    }
}
