package year2024.Day8;

import static year2023.Day1.InputGenerator.input;
import static year2024.Day8.ReadInput.printGrid;
import static year2024.Day8.ReadInput.read;
import static year2024.Day8.Util.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = read("./src/main/java/year2024/Day8/input");
    int score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  public int solve_pt1(char[][] grid) {
    Map<Character, Stack<int[]>> charMap = availableChars(grid);
    for (var c : charMap.keySet()) {
      fillAntiNodes(grid, c, charMap, false);
      printGrid(grid);
      System.out.println();
    }
    return countAntinodes(grid);
  }

  private void fillAntiNodes(
      char[][] grid, Character c, Map<Character, Stack<int[]>> charMap, boolean pt2) {
    Stack<int[]> stack = deepCopy(charMap.get(c));
    while (!stack.isEmpty()) {
      int[] currentCoordinates = stack.pop();
      List<int[]> restingCoordinates = stack.stream().toList();
      for (int[] otherCoordinates : restingCoordinates) {
        DistanceVector distanceVector = new DistanceVector(currentCoordinates, otherCoordinates);
        List<int[]> antinodes = antisemetricPoints(distanceVector, grid, pt2);
        for (int[] antinode : antinodes) {
          grid[antinode[0]][antinode[1]] = '#';
        }
      }
    }
  }

  private List<int[]> antisemetricPoints(
      DistanceVector distanceVector, char[][] grid, boolean pt2) {
    if (!pt2) {
      return distanceVector.antisymmetricPoints(grid);
    } else {
      return distanceVector.allAntisymmetricPoints(grid);
    }
  }

  public int solve_pt2(char[][] grid) {
    Map<Character, Stack<int[]>> charMap = availableChars(grid);
    for (var c : charMap.keySet()) {
      fillAntiNodes(grid, c, charMap, true);
      // printGrid(grid);
      // System.out.println();
    }
    return countAntinodes(grid);
  }
}
