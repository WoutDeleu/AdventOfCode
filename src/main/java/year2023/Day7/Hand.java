package year2023.Day7;

import java.util.Arrays;
import java.util.HashMap;

public class Hand {
    private char[] cards;
    private int bid;
    public int handScore;

    public Hand(char[] cards, int bid, boolean secondSolution) throws Exception {
        this.cards = cards;
        this.bid = bid;
        this.handScore = calculateHandScore(secondSolution);
    }

    // 1 : high card
    // 2 : pair
    // 3 : two pair
    // 4 : three of a kind
    // 5 : full House
    // 6 : four of a kind
    // 7 : five of a kind
    private int calculateHandScore(boolean secondSolution) throws Exception {
        HashMap<Character, Integer> cardCount = new HashMap<>();
        int currentBest;
        for (char card : cards) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }
        currentBest = convertCardCountToScore(cardCount);
        if (secondSolution) {
            // Fill in jokers
            int amountOfJokers = cardCount.getOrDefault('J', 0);
            if(amountOfJokers > 0) {
                int current = switch (currentBest) {
                    case 7,6,5 -> 7;
                    case 4 -> 6;
                    case 3 -> {
                        if(amountOfJokers == 1) {
                            yield 5;
                        }
                        else {
                            yield 6;
                        }
                    }
                    case 2 -> 4;
                    case 1 -> 2;
                    default -> throw new Exception();
                };
                if(current > currentBest) {
                    currentBest = current;
                }
            }
        }
        return currentBest;
    }

    private int convertCardCountToScore(HashMap<Character, Integer> cardCount) {
        // Check for different combinations and assign scores
        if (cardCount.containsValue(5)) {
            return 7; // Five of a kind
        } else if (cardCount.containsValue(4)) {
            return 6; // Four of a kind
        } else if (cardCount.containsValue(3) && cardCount.containsValue(2)) {
            return 5; // Full House
        } else if (cardCount.containsValue(3)) {
            return 4; // Three of a kind
        } else if (countPairs(cardCount) == 2) {
            return 3; // Two pair
        } else if (countPairs(cardCount) == 1) {
            return 2; // Pair
        } else {
            return 1; // High card
        }
    }

    private int countPairs(HashMap<Character, Integer> cardCount) {
        int pairCount = 0;
        for (int count : cardCount.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount;
    }


    public int getHandScore() {
        return handScore;
    }

    public char[] getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }
}
