package year2024.Day8;

import static aoc.utils.GridUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Util {

  public static boolean inRange(int[] antinode, char[][] grid) {
    return inBounds(antinode[0], antinode[1], grid);
  }

  public static int countAntinodes(char[][] grid) {
    return countChar(grid, '#');
  }

  public static Stack<int[]> deepCopy(Stack<int[]> ints) {
    Stack<int[]> copy = new Stack<>();
    for (int[] arr : ints) {
      copy.push(new int[] {arr[0], arr[1]});
    }
    return copy;
  }

  public static void addCharToMap(
    Map<Character, Stack<int[]>> availableChars, char ch, int row, int col) {
    if (!availableChars.containsKey(ch)) {
      Stack<int[]> stack = new Stack<>();
      stack.push(new int[] {row, col});
      availableChars.put(ch, stack);
    } else {
      availableChars.get(ch).add(new int[] {row, col});
    }
  }



  public static Map<Character, Stack<int[]>> availableChars(char[][] input) {
    Map<Character, Stack<int[]>> availableChars = new HashMap<>();
    int r = 0;
    for (char[] row : input) {
      int col = 0;
      for (char c : row) {
        if (c != '.') {
          addCharToMap(availableChars, c, r, col);
        }
        col++;
      }
      r++;
    }
    return availableChars;
  }
}
