package year2024.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day5/input");
    int score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }


  @SuppressWarnings("unchecked")
  public int solve_pt1(Object[] input) {
    Map<Integer, List<Integer>> orderRules = (Map<Integer, List<Integer>>) input[0];
    List<int[]> updates = (List<int[]>) input[1];
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

  @SuppressWarnings("unchecked")
  public int solve_pt2(Object[] input) {
    Map<Integer, List<Integer>> orderRules = (Map<Integer, List<Integer>>) input[0];
    List<int[]> updates = (List<int[]>) input[1];
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

  public Object[] read(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    Map<Integer, List<Integer>> orderRules = new HashMap<>();
    String splitter = "\\|";
    while(sc.hasNextLine()) {
      String line = sc.nextLine();
      if (line.isBlank()) break;
      String[] parts = line.split(splitter);
      if(orderRules.containsKey(Integer.parseInt(parts[0]))) {
        orderRules.get(Integer.parseInt(parts[0])).add(Integer.parseInt(parts[1]));
      }
      else {
        orderRules.put(Integer.parseInt(parts[0]), new ArrayList<>(List.of(Integer.parseInt(parts[1]))));
      }
    }
    List<int[]> updates = new ArrayList<>();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] parts = line.split(",");
      int[] update = new int[parts.length];
      for (int i = 0; i < parts.length; i++) {
        update[i] = Integer.parseInt(parts[i]);
      }
      updates.add(update);
    }
    return new Object[]{orderRules, updates};
  }
}
