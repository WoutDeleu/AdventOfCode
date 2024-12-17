package year2024.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// todo - refactor
public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var cards = read("./src/main/java/year2024/Day1/input");
    int score = solve_pt1(cards);
    System.out.println("score: " + score);

    int score2 = solve_pt2(cards);
    System.out.println("score2: " + score2);
  }

  public static int solve_pt1(List<List<Integer>> cards) {
    var list1 = cards.get(0);
    var list2 = cards.get(1);

    Collections.sort(list1);
    Collections.sort(list2);

    int distance = 0;
    for (int i = 0; i < list1.size(); i++) {
      distance += Math.abs(list1.get(i) - list2.get(i));
    }
    return distance;
  }

  public static List<List<Integer>> read(String s) throws FileNotFoundException {
    var list1 = new ArrayList<Integer>();
    var list2 = new ArrayList<Integer>();

    Scanner scanner = new Scanner(new File(s));
    while (scanner.hasNextInt()) {
      list1.add(scanner.nextInt());
      list2.add(scanner.nextInt());
    }

    return List.of(list1, list2);
  }

  public static int solve_pt2(List<List<Integer>> cards) {
    var list1 = cards.get(0);
    var list2 = cards.get(1);

    int simularityScore = 0;
    for (int i = 0; i < list1.size(); i++) {
      int current = list1.get(i);
      int occurence = 0;
      for (int j = 0; j < list2.size(); j++) {
        if (current == list2.get(j)) {
          occurence++;
        }
      }
      simularityScore += occurence * current;
    }
    return simularityScore;
  }
}
