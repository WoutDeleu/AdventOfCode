package year2024.Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static year2024.Day9.Utils.printLayout;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day9/input");
    var score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  public long solve_pt1(int[] input) {
    char[] spaceLayout = createSpaceLayout(input);
    int[] compactedLayout = compactSpaceLayout(spaceLayout);
    printLayout(compactedLayout);
    return checksum(compactedLayout);
  }

  public int solve_pt2(int[] input) {
    return 0;
  }

  private int[] compactSpaceLayout(char[] spaceLayout) {
    int index = 0, reverseIndex = spaceLayout.length - 1;
    StringBuilder compactedLayout = new StringBuilder();

    while (index <= reverseIndex) {
      if (Character.isDigit(spaceLayout[index])) {
        compactedLayout.append(spaceLayout[index]);
        index++;
      } else {
        if (Character.isDigit(spaceLayout[reverseIndex])) {
          compactedLayout.append(spaceLayout[reverseIndex]);
          index++;
        }
        reverseIndex--;
      }
    }

    return getInts(compactedLayout);
  }

  private int[] getInts(StringBuilder compactedLayout) {
    return compactedLayout.toString().chars().map(c -> charToInt((char) c)).toArray();
  }

  private long checksum(int[] layout) {
    long checkSum = 0;
    for (int i = 0; i < layout.length; i++) {
      checkSum += (long) layout[i] * i;
    }
    return checkSum;
  }

  private int charToInt(char c) {
    return c - '0';
  }

  private char[] createSpaceLayout(int[] input) {
    StringBuilder spaceLayout = new StringBuilder();

    boolean isFile = true, isFreeSpace = false;
    int index = 0;
    for (int i : input) {
      if (isFile) {
        spaceLayout.append(Integer.toString(index).repeat(i));
        index++;
      }
      if (isFreeSpace) {
        spaceLayout.append(".".repeat(i));
      }

      isFile = !isFile;
      isFreeSpace = !isFreeSpace;
    }

    return spaceLayout.toString().toCharArray();
  }

  public int[] read(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    return sc.nextLine().chars().map(c -> charToInt((char) c)).toArray();
  }
}

class Utils {
  public static void printLayout(char[] layout) {
    for (char c : layout) {
      System.out.print(c);
    }
    System.out.println();
  }

  public static void printLayout(int[] layout) {
    for (int c : layout) {
      System.out.print(c);
    }
    System.out.println();
  }
}
