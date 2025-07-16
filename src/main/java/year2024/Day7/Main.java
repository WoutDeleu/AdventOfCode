package year2024.Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day7/input");
    long score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    long score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  private static boolean isFirstCall(long tempSum) {
    return tempSum == 0;
  }

  public long solve_pt1(List<Equation> equations) {
    long result = 0;
    for (Equation equation : equations) {
      long desiredSum = equation.sum();
      var args = equation.args();
      long tempSum = 0;

      if (isPossible(desiredSum, args, tempSum, false)) {
        result += equation.sum();
      }
    }
    return result;
  }

  private boolean isPossible(long desiredSum, Queue<Long> args, long tempSum, boolean pt2) {
    // deep copy of args to avoid modifying the original queue
    var currentQueue = new LinkedList<>(args);

    // stop condition for recursion
    if (currentQueue.isEmpty()) {
      return tempSum == desiredSum;
    }

    // pruning condition
    if (tempSum > desiredSum) {
      return false;
    }

    long nextArg = currentQueue.poll();

    ///  ADDITION
    tempSum += nextArg;
    if (isPossible(desiredSum, currentQueue, tempSum, pt2)) {
      return true;
    }
    // recursion
    tempSum -= nextArg;

    // first element -> no need to try multiplication
    if (isFirstCall(tempSum)) {
      return false;
    }

    /// MULTIPLICATION
    tempSum *= nextArg;
    if (isPossible(desiredSum, currentQueue, tempSum, pt2)) {
      return true;
    }
    tempSum /= nextArg;

    // check if concatenation is allowed
    if (!pt2) {
      return false;
    }

    ///  CONCATENATION
    tempSum = concatenate(tempSum, nextArg);
    return isPossible(desiredSum, currentQueue, tempSum, pt2);
  }

  private long concatenate(long tempSum, long nextArg) {
    String tempSumStr = String.valueOf(tempSum);
    String nextArgStr = String.valueOf(nextArg);
    return Long.parseLong(tempSumStr + nextArgStr);
  }

  public long solve_pt2(List<Equation> equations) {
    long result = 0;
    for (Equation equation : equations) {
      long desiredSum = equation.sum();
      var args = equation.args();
      long tempSum = 0;

      if (isPossible(desiredSum, args, tempSum, true)) {
        result += equation.sum();
      }
    }
    return result;
  }

  public List<Equation> read(String s) throws FileNotFoundException {
    List<Equation> result = new ArrayList<>();
    var sc = new Scanner(new File(s));
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] parts = line.split(":");
      Long sum = Long.parseLong(parts[0]);
      var args =
          Arrays.stream(parts[1].split(" "))
              .filter(string -> !string.isBlank())
              .map(Long::parseLong)
              .toList();
      result.add(new Equation(sum, new LinkedList<>(args)));
    }

    return result;
  }

  public record Equation(Long sum, Queue<Long> args) {}
}
