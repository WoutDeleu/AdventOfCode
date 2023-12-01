package year2023.Day1;

import java.util.*;

public class TransCoder {
    private final static List<String> numbers = Arrays.asList(new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"});

    public static void main(String[] args) {
        int sumCallibration = multiLineExtract(InputGenerator.input, false);
        System.out.println("Part 1: " + sumCallibration);
        int sumNumbersAllowed = multiLineExtract(InputGenerator.input, true);
        System.out.println("Part 2: " + sumNumbersAllowed);
    }
    public static int multiLineExtract(String input, boolean numbersAllowed) {
        String[] lines = input.split("\n");
        int sum = 0;
        for(String line : lines) {
            sum += extractDigits(line, numbersAllowed);
        }
        return sum;
    }

    public static int extractDigits(String input, boolean numbersAllowed) {
        StringBuilder sb = new StringBuilder();
        boolean firstTwo = true;
        // <Number, current index of thata number>
        Map<Integer, Integer> numbersInProgress = new HashMap<>();
        for(char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                firstTwo = parseDigit(sb, c, firstTwo);
                if (numbersAllowed) numbersInProgress.clear();
            }
            else if(numbersAllowed) {
                updateNumbersInprogress(c, numbersInProgress);
                // check of het het laatste char van een nummer is, en return het nummer!
                int number = getFinalNumbers(numbersInProgress);
                if(number != 0) {
                    numbersInProgress.remove(number-1);
                    firstTwo = parseDigit(sb, (char)('0'+number) , firstTwo);
                }
            }
        }
        if (sb.length() == 1) sb.append(sb.charAt(0));
        return Integer.parseInt(sb.toString());
    }

    private static void updateNumbersInprogress(char c, Map<Integer, Integer> numbersInProgress) {
        List<Integer> keysToRemove = new ArrayList<>();
        List<Integer> keysToAdd = new ArrayList<>();
        List<Integer> valuesToAdd = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry: numbersInProgress.entrySet()) {
            String number  = numbers.get(entry.getKey());
            if(c == number.charAt(entry.getValue())) {
                keysToAdd.add(entry.getKey());
                valuesToAdd.add(entry.getValue()+1);
            }
            else keysToRemove.add(entry.getKey());
        }
        for(int i : keysToRemove) numbersInProgress.remove(i);
        for(int i = 0; i<keysToAdd.size(); i++) numbersInProgress.put(keysToAdd.get(i), valuesToAdd.get(i));

        for(int i =0; i<numbers.size(); i++) {
           if(!numbersInProgress.containsKey(i) && numbers.get(i).charAt(0) == c) {
               numbersInProgress.put(i, 1);
           }
        }
    }

    private static int getFinalNumbers(Map<Integer, Integer> numbersInProgress) {
        for(Map.Entry<Integer, Integer> entry: numbersInProgress.entrySet()) {
            String number = numbers.get(entry.getKey());
            if(number.length() == entry.getValue()) return entry.getKey()+1;
        }
        return 0;
    }

    private static boolean parseDigit(StringBuilder sb, char c, boolean firstTwo) {
        if (firstTwo) {
            sb.append(c);
            if (sb.length() == 2) firstTwo = false;
        } else {
            sb.replace(1, 2, Character.toString(c));
        }
        return firstTwo;
    }
}