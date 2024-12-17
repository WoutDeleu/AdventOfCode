package year2024.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// todo - refactor
public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = read("./src/main/java/year2024/Day1/input");
    int score = solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  public static int solve_pt1(List<int[]> input) {
    int amountOfSafe = 0;
    boolean safe = true;
    boolean ascending = true;
    for (int[] report : input) {
      for (int i = 0; i < report.length; i++) {
        if (report[i] != report[i + 1]) {
          if (report[i] < report[i + 1]) ascending = true;
          else if (report[i] > report[i + 1]) ascending = false;
          break;
        }
        safe = false;
      }
      int previousLvl = report[0];
      for (int i = 1; i < report.length; i++) {
        if (!safe || checkUnsafe(ascending, previousLvl, report[i])) {
          safe = false;
          break;
        }
        previousLvl = report[i];
      }
      if (safe)
        amountOfSafe++;
      safe = true;
    }
    return amountOfSafe;
  }

  private static boolean checkUnsafe(boolean ascending, int previousLvl, int i) {
    if (ascending) {
      return i - previousLvl <= 0 || i - previousLvl > 3;
    } else {
      return previousLvl - i <= 0 || previousLvl - i > 3;
    }
  }


  public static List<int[]> read(String s) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(s));
    List<int[]> input = new ArrayList<>();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] split = line.split(" ");
      int[] card = new int[split.length];
      for (int i = 0; i < split.length; i++) {
        card[i] = Integer.parseInt(split[i]);
      }
      input.add(card);
    }
    return input;
  }

  public static int solve_pt2(List<int[]> input) {
    return 0;

  }
}
