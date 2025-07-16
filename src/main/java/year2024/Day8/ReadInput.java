package year2024.Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadInput {
  public static void printGrid(char[][] grid) {
    System.out.println();
    for (char[] row : grid) {
      for (char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
    System.out.println();
  }

  public static char[][] read(String s) throws FileNotFoundException {
    var grid = createGrid(s);
    var sc = new Scanner(new File(s));
    int row = 0;
    while (sc.hasNextLine()) {
      grid[row] = sc.nextLine().toCharArray();
      row++;
    }
    printGrid(grid);
    return grid;
  }

  private static char[][] createGrid(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    int rows = 0;
    while (sc.hasNextLine()) {
      sc.nextLine();
      rows++;
    }
    sc.close();
    return new char[rows][];
  }
}
