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
    for (int[] report : input) {
      boolean safe = checkIfFlowIsSafe(report);
      if (safe) amountOfSafe++;
    }
    return amountOfSafe;
  }

  private static boolean getAscendingDescending(int[] report) {
    for (int i = 0; i < report.length; i++) {
      if (report[i] != report[i + 1]) {
        if (report[i] < report[i + 1]) return true;
        else if (report[i] > report[i + 1]) return false;
      }
    }
    throw new RuntimeException();
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
    int amountOfSafe = 0;
    for (int[] report : input) {
      amountOfSafe = getAmountOfSafe(amountOfSafe, report);
    }
    return amountOfSafe;
  }

  private static boolean checkIfFlowIsSafe(int[] report) {
    boolean ascending = getAscendingDescending(report);
    int previousLvl = report[0];
    for (int i = 1; i < report.length; i++) {
      if (checkUnsafe(ascending, previousLvl, report[i])) {
        return false;
      }
      previousLvl = report[i];
    }
    return true;
  }

  private static int getAmountOfSafe(int amountOfSafe, int[] report) {
    boolean safe = checkIfFlowIsSafe(report);
    if (safe) amountOfSafe++;
    else {
      for (int j = 0; j < report.length; j++) {
        int[] newArray = removeElement(j, report);
        safe = checkIfFlowIsSafe(newArray);
        if (safe) {
          amountOfSafe++;
          break;
        }
      }
    }
    return amountOfSafe;
  }

  private static int[] removeElement(int j, int[] report) {
    var newList = new int[report.length - 1];
    for (int i = 0; i < report.length; i++) {
      if (i < j) {
        newList[i] = report[i];
      } else if (i > j) {
        newList[i - 1] = report[i];
      }
    }
    return newList;
  }
}
