package year2023.Day7;

import java.util.Comparator;

public class HandComparator implements Comparator<Hand> {
    private boolean secondSolution = false;

    public HandComparator(boolean secondSolution) {
        this.secondSolution = secondSolution;
    }

    // alphabet : a b c d e f g h i j k l m n o p q r s t u v w x y z
    @Override
    public int compare(Hand h1, Hand h2) {
        if(h1.getHandScore() > h2.getHandScore()) {
            return 1;
        }
        if(h1.getHandScore() < h2.getHandScore()) {
            return -1;
        }
        for(int i = 0; i < h1.getCards().length; i++) {
            if(Character.isDigit(h1.getCards()[i]) && Character.isDigit(h2.getCards()[i])) {
                if (h1.getCards()[i] > h2.getCards()[i]) {
                    return 1;
                }
                if (h1.getCards()[i] < h2.getCards()[i]) {
                    return -1;
                }
            }
            else if(!Character.isDigit(h1.getCards()[i]) && !Character.isDigit(h2.getCards()[i])) {
                switch (h1.getCards()[i]) {
                    case 'A':
                        if(h2.getCards()[i] != 'A') {
                            return 1;
                        }
                        break;
                    case 'K':
                        if(h2.getCards()[i] != 'A' && h2.getCards()[i] != 'K') {
                            return 1;
                        }
                        else if(h2.getCards()[i] == 'A') {
                            return -1;
                        }
                        break;
                    case 'Q':
                        if(h2.getCards()[i] != 'A' && h2.getCards()[i] != 'K' && h2.getCards()[i] != 'Q') {
                            return 1;
                        }
                        else if(h2.getCards()[i] == 'A' || h2.getCards()[i] == 'K') {
                            return -1;
                        }
                        break;
                    case 'J':
                        if(!secondSolution) {
                            if(h2.getCards()[i] != 'A' && h2.getCards()[i] != 'K' && h2.getCards()[i] != 'Q' && h2.getCards()[i] != 'J') {
                                return 1;
                            }
                            else if(h2.getCards()[i] == 'A' || h2.getCards()[i] == 'K' || h2.getCards()[i] == 'Q') {
                                return -1;
                            }
                        }
                        else {
                            if(h2.getCards()[i] != 'J') return -1;
                            break;
                        }
                        break;
                    case 'T':
                        if(h2.getCards()[i] != 'A' && h2.getCards()[i] != 'K' && h2.getCards()[i] != 'Q' && h2.getCards()[i] != 'J' && h2.getCards()[i] != 'T') {
                            return 1;
                        }
                        else if(h2.getCards()[i] == 'A' || h2.getCards()[i] == 'K' || h2.getCards()[i] == 'Q' || h2.getCards()[i] == 'J') {
                            return -1;
                        }
                        break;
                    default:
                        break;
                }
            }
            else {
                if(Character.isDigit(h1.getCards()[i])) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
        return 0;
    }
}
