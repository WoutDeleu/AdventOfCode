package year2023.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScratchCardSolver {
    public static void main(String[] args) throws FileNotFoundException {
        List<List<int[]>> cards = readScratchCard("./src/main/java/year2023/Day4/input");
        int score = calculateTotalScore(cards);
        System.out.println("score: " + score);

        int totalScratchCards = calculateTotalCardScoreExponential(cards);
        System.out.println("totalAmount: " + totalScratchCards);

    }

    static int calculateTotalCardScoreExponential(List<List<int[]>> cards) {
        Map<Integer, Integer> amountOfCards = new HashMap<>();
        int i = 1;
        for(List<int[]> card : cards) {
            amountOfCards.put(i, 1);
            i++;
        }

        i = 1;
        for(List<int[]> card : cards) {
            multiplyCards(i, amountOfCards, card.get(0), card.get(1));
            i++;
        }

        int score = 0;
        for(int v : amountOfCards.values()) {
            score += v;
        }
        return score;
    }

    private static void multiplyCards(int id, Map<Integer, Integer> amountOfCards, int[] correctNumbers, int[] personalNumbers) {
        int matchingNumbers = 0;
        for(int n : personalNumbers) {
            if (arrayContainsNumber(n, correctNumbers)) {
                matchingNumbers++;
            }
        }
        int amountOfCopies = amountOfCards.get(id);
        for(int i = id+1; i <= id+matchingNumbers; i++) {
            amountOfCards.put(i, amountOfCards.get(i)+amountOfCopies);
        }
    }

    static int calculateTotalScore(List<List<int[]>> cards) {
        int totalScore = 0;
        for(List<int[]> card : cards) {
            totalScore += calculateCardScore(card.get(0), card.get(1));
        }
        return totalScore;
    }

    private static int calculateCardScore(int[] correctNumbers, int[] personalNumbers) {
        int score = 0;
        for(int n : personalNumbers) {
            if(arrayContainsNumber(n, correctNumbers)) {
                if(score == 0) score = 1;
                else score *= 2;
            }
        }
        return score;
    }
    private static boolean arrayContainsNumber(int n, int[] arr) {
        for(int i : arr) {
            if (i == n) return true;
        }
        return false;
    }

    static List<List<int[]>> readScratchCard(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        List<List<int[]>> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().split(": ")[1];
            String[] winning = line.split("\\|")[0].trim().split("\\s+");
            int[] winningNumbers = Arrays.stream(winning).mapToInt(Integer::valueOf).toArray();
            String[] personal = line.split("\\|")[1].trim().split("\\s+");
            int[] personalNumbers = Arrays.stream(personal).mapToInt(Integer::valueOf).toArray();
            input.add(List.of(winningNumbers, personalNumbers));
        }
        return input;
    }
}
