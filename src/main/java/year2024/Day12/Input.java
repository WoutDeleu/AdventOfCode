package year2024.Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
  public static char[][] read(String s) throws FileNotFoundException {
    char[][] empty_field = readEmptyField(s);
    var sc = new Scanner(new File(s));
    int i = 0, j = 0;
    while (sc.hasNextLine()) {
      empty_field[i] = sc.nextLine().toCharArray();
      i++;
    }
    return empty_field;
  }

  private static char[][] readEmptyField(String s) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(s));
    int rows = 0;
    int cols = 0;
    String line;
    while (sc.hasNextLine()) {
      line = sc.nextLine();
      cols = line.length();
      rows++;
    }
    return new char[rows][cols];
  }
}
