package year2024.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static int BLINKING_COUNT = 25;

  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day11/input");
    int score = new Main().solve(input);
    System.out.println("pt1: " + score);

    BLINKING_COUNT = 75;
    int score2 = new Main().solve(input);
    System.out.println("pt2: " + score2);
  }

  public int solve(List<Long> stones) {
    for (int i = 0; i < BLINKING_COUNT; i++) {
      stones = changeStones(stones);
    }
    return stones.size();
  }


  private boolean hasEvenNumberOfDigits(Long l) {
    return l.toString().length() % 2 == 0;
  }

  private List<Long> changeStones(List<Long> stones) {
    List<Long> newStones = new ArrayList<>();
    for (Long stone : stones) {
      if (stone.equals(0L)) {
        newStones.add(1L);
      } else if (hasEvenNumberOfDigits(stone)) {
        Long[] split = splitLongDigitsInTwo(stone);
        newStones.add(split[0]);
        newStones.add(split[1]);
      } else {
        newStones.add(stone * 2024);
      }
    }
    return newStones;
  }

  private Long[] splitLongDigitsInTwo(Long stone) {
    String stoneString = stone.toString();
    int mid = stoneString.length() / 2;
    String firstHalf = stoneString.substring(0, mid);
    String secondHalf = stoneString.substring(mid);
    return new Long[] {Long.parseLong(firstHalf), Long.parseLong(secondHalf)};
  }


  public void print(List<Long> stones) {
    for (Long stone : stones) {
      System.out.print(stone + " ");
    }
    System.out.println();
  }


  public List<Long> read(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    List<Long> result = new ArrayList<>();
    while (sc.hasNextLong()) {
      result.add(sc.nextLong());
    }
    return result;
  }
}
