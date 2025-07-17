package year2024.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day10/input");
    long score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    long score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  public long solve_pt1(int[][] map) {
    List<TrailHead> trailheads = getAllTrailHeads(map);
    for (TrailHead trailHead : trailheads) {
      trailHead.calculateAccessibleTops(map);
    }
    return calculateTotalAccessibleTops(trailheads);
  }

  public long solve_pt2(int[][] map) {
    List<TrailHead> trailheads = getAllTrailHeads(map);
    for (TrailHead trailHead : trailheads) {
      trailHead.calculateAllPaths(map);
    }
    return calculateTotalPaths(trailheads);
  }

  private long calculateTotalPaths(List<TrailHead> trailheads) {
    long score = 0;
    for (TrailHead trailHead : trailheads) {
      score += trailHead.getPathsCount();
    }
    return score;
  }


  private long calculateTotalAccessibleTops(List<TrailHead> trailheads) {
    long score = 0;
    for (TrailHead trailHead : trailheads) {
      score += trailHead.getAccessibleTops();
    }
    return score;
  }

  private List<TrailHead> getAllTrailHeads(int[][] input) {
    List<TrailHead> trailheads = new ArrayList<>();
    for (int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[row].length; col++) {
        if (input[row][col] == 0) { // Assuming 1 indicates a trail head
          trailheads.add(new TrailHead(row, col));
        }
      }
    }
    return trailheads;
  }

  public int[][] read(String s) throws FileNotFoundException {
    int[][] map = getEmptyMap(s);
    var sc = new Scanner(new File(s));
    int row = 0;
    while (sc.hasNextLine()) {
      int[] line = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
      map[row] = line;
      row++;
    }
    Utils.printMap(map);
    return map;
  }

  private int[][] getEmptyMap(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    int rows = 1;
    var line = sc.nextLine();
    while (sc.hasNextLine()) {
      sc.nextLine();
      rows++;
    }
    int width = line.length();
    sc.close();
    return new int[rows][width];
  }
}

class Utils {
  public static void printMap(int[][] map) {
    for (int[] row : map) {
      for (int cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
}
