package year2024.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day6/input");
    var copyInput = copyMatrix(input);
    int score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    input = copyMatrix(copyInput);
    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  private int countX(char[][] input) {
    int count = 0;
    for (char[] row : input) {
      for (char c : row) {
        if (c == 'X') {
          count++;
        }
      }
    }
    return count;
  }

  private int[] findStartingCords(char[][] input) {
    int[] cords = new int[2];
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (input[i][j] == '^' || input[i][j] == 'v' || input[i][j] == '<' || input[i][j] == '>') {
          cords[0] = i;
          cords[1] = j;
          return cords;
        }
      }
    }
    return cords;
  }

  private int[] getDirection(char c) {
    int[] direction = new int[2];
    return switch (c) {
      case '^' -> {
        direction[0] = -1;
        yield direction;
      }
      case 'v' -> {
        direction[0] = 1;
        yield direction;
      }
      case '<' -> {
        direction[1] = -1;
        yield direction;
      }
      case '>' -> {
        direction[1] = 1;
        yield direction;
      }
      default -> direction;
    };
  }

  public int solve_pt1(char[][] input) {
    int[] cors = findStartingCords(input);
    int i = cors[0];
    int j = cors[1];

    int previousI = i;
    int previousJ = j;
    do {
      i += getDirection(input[previousI][previousJ])[0];
      j += getDirection(input[previousI][previousJ])[1];
      if(i >= 0 && i < input.length && j >= 0 && j < input[i].length) {
        if (input[i][j] == 'X' || input[i][j] == '.') {
          input[i][j] = input[previousI][previousJ];
          input[previousI][previousJ] = 'X';
          previousI = i;
          previousJ = j;
        } else {
          input[previousI][previousJ] = rotateInput(input[previousI][previousJ]);
          i = previousI;
          j = previousJ;
        }
      }
    } while (i >= 0 && i < input.length && j >= 0 && j < input[i].length);
    input[previousI][previousJ] = 'X';
    return countX(input);
  }

  private char rotateInput(char c) {
    return switch (c) {
      case '^' -> '>';
      case '>' -> 'v';
      case 'v' -> '<';
      case '<' -> '^';
      default -> c;
    };
  }

  public int solve_pt2(char[][] input) {
    var copyInput = copyMatrix(input);
    int[] cors = findStartingCords(input);

    solve_pt1(input);
    List<int[]> originalPath = getAllXsesPath(input);

    int count = 0;
    for (int[] entry : originalPath) {
      int q = entry[0];
      int w = entry[1];

      int i = cors[0];
      int j = cors[1];
      int previousI = i;
      int previousJ = j;
      input = copyMatrix(copyInput);
      if (input[q][w] == '#' || input[q][w] == '^' || input[q][w] == 'v' || input[q][w] == '<' || input[q][w] == '>') {
        continue;
      }
      else {
        input[q][w] = '#';
      }
      List<int[]> path = new ArrayList<>();
      List<Character> directions = new ArrayList<>();
      int steps = 0;
      do {
        i += getDirection(input[previousI][previousJ])[0];
        j += getDirection(input[previousI][previousJ])[1];
        if(i >= 0 && i < input.length && j >= 0 && j < input[i].length) {
          if (input[i][j] == 'X' || input[i][j] == '.') {
            input[i][j] = input[previousI][previousJ];
            path.add(new int[]{previousI, previousJ});
            directions.add(input[previousI][previousJ]);
            input[previousI][previousJ] = 'X';
            previousI = i;
            previousJ = j;
          } else {
            input[previousI][previousJ] = rotateInput(input[previousI][previousJ]);
            i = previousI;
            j = previousJ;
          }
        }
        steps++;
        // todo - fix
        // if (containsLoop(path, directions)) {
        if (steps > 10000) {
          count++;
          break;
        }
      } while (i >= 0 && i < input.length && j >= 0 && j < input[i].length);
      input[previousI][previousJ] = 'X';
      input[q][w] = '#';
    }
    return count;
  }

  private List<int[]> getAllXsesPath(char[][] input) {
    List<int[]> result = new ArrayList<>();
    for(int i = 0; i<input.length; i++) {
      for(int j = 0; j<input[i].length; j++) {
        if(input[i][j] == 'X') result.add(new int[]{i, j});
      }
    }
    return result;
  }

  private boolean containsLoop(List<int[]> path, List<Character> directions) {
    for(int i = 0; i < path.size(); i++) {
      for(int j = i+1; j < path.size(); j++) {
        if (path.get(i)[0] == path.get(j)[0] && path.get(i)[1] == path.get(j)[1] && directions.get(i) == directions.get(j)) {
          return true;
        }
      }
    }
    return false;
  }

  private static char[][] copyMatrix(char[][] input) {
    var copyInput = new char[input.length][input[0].length];
    for(int i = 0; i< input.length; i++) {
      for (int j = 0; j< input[0].length; j++) {
        copyInput[i][j] = input[i][j];
      }
    }
    return copyInput;
  }

  public char[][] read(String s) throws FileNotFoundException {
    // get dimensions
    var sc = new Scanner(new File(s));
    int rows = 0;
    while(sc.hasNextLine()) {
      sc.nextLine();
      rows++;
    }
    sc.close();
    sc = new Scanner(new File(s));
    int cols = sc.nextLine().length();

    // read input
    sc = new Scanner(new File(s));
    char[][] grid = new char[rows][cols];
    int i = 0, j;
    while(sc.hasNextLine()) {
      j = 0;
      var arr = sc.nextLine().toCharArray();
      for (char c : arr) {
        grid[i][j] = c;
        j++;
      }
      i++;
    }
    return grid;
  }
}
