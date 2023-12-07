package year2023.Day7;

import java.util.Arrays;
import java.util.HashMap;

public class Hand {
    private char[] cards;
    private int bid;
    public int handScore;

    public Hand(char[] cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        this.handScore = calculateHandScore();
    }

    // 1 : high card
    // 2 : pair
    // 3 : two pair
    // 4 : three of a kind
    // 5 : full House
    // 6 : four of a kind
    // 7 : five of a kind
    private int calculateHandScore() {
        HashMap<Character, Integer> cardCount = new HashMap<>();
        for(char card : cards) {
            if(cardCount.containsKey(card)) {
                cardCount.put(card, cardCount.get(card) + 1);
            } else {
                cardCount.put(card, 1);
            }
        }
        int currentBest = 0;
        for(int count : cardCount.values()) {
            if(count > currentBest) {
                currentBest = count;
            }
        }
        if(currentBest == 2) {
            int pairCount = 0;
            for(int count : cardCount.values()) {
                if(count == 2) {
                    if(pairCount == 1) {
                        currentBest++;
                        break;
                    }
                    pairCount++;
                }
            }
        }
        else if(currentBest == 3) {
             currentBest++;
            for(int count : cardCount.values()) {
                if(count == 2) {
                    currentBest++;
                }
            }
        }
        else if(currentBest > 3) currentBest += 2;
        return currentBest;
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
