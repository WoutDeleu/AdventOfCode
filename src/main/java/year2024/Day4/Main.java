package year2024.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  private final char[] XMAS = new char[] { 'X', 'M', 'A', 'S' };

  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day4/input");
    int score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  public int findSurroundingXMAS(int counter, int nextCharIndex, int currentRow, int currentCol, char[][] matrix) {
    char lookingFor = XMAS[nextCharIndex];
    for (int i = currentRow - 1; i <= currentRow + 1; i++) {
      for (int j = currentCol - 1; j <= currentCol + 1; j++) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[i].length) {
          continue;
        }
        if (matrix[i][j] == lookingFor) {
          counter = findDirectionalXMAS(counter , 1+nextCharIndex, i, j, i-currentRow, j-currentCol, matrix);
        }
      }
    }
    return counter;
  }

  private int findDirectionalXMAS(int counter, int nextCharIndex, int currentRow, int currentCol, int factorRow, int factorCol, char[][] matrix) {
    char lookingFor = XMAS[nextCharIndex];
    if (currentRow + factorRow >= 0 && currentCol + factorCol >= 0 && currentRow + factorRow < matrix.length && currentCol + factorCol < matrix[0].length) {
      if (matrix[currentRow + factorRow][currentCol + factorCol] == lookingFor) {
        if (nextCharIndex == 3) {
          return counter + 1;
        } else {
          return findDirectionalXMAS(counter, ++nextCharIndex, currentRow + factorRow, currentCol + factorCol, factorRow, factorCol, matrix);
        }
      }
    }
    return counter;
  }

  public int solve_pt1(char[][] input) {
    int count = 0;
    for(int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[row].length; col++) {
        if (input[row][col] == 'X') {
          count = findSurroundingXMAS(count, 1, row, col, input);
        }
      }
    }
    return count;
  }

  public int solve_pt2(char[][] input) {
    int count = 0;
    for(int row = 0; row < input.length; row++) {
      for (int col = 0; col < input[row].length; col++) {
        if(input[row][col] == 'A' && checkSurroundingCellAreValid(row, col, input) && surroundingCellsAreXMAS(row, col, input)) {
          count++;
        }
      }
    }
    return count;
  }

  private boolean surroundingCellsAreXMAS(int row, int col, char[][] input) {
    return ((input[row+1][col-1] == 'S' && input[row-1][col+1] == 'M') || (input[row+1][col-1] == 'M' && input[row-1][col+1] == 'S')) && ((input[row-1][col-1] == 'S' && input[row+1][col+1] == 'M') || (input[row-1][col-1] == 'M' && input[row+1][col+1] == 'S'));

  }

  private boolean checkSurroundingCellAreValid(int row, int col, char[][] input) {
    return row-1>=0 && row+1<input.length && col-1>=0 && col+1<input[0].length;
  }

  public char[][] read(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    int length = sc.nextLine().length();
    sc = new Scanner(new File(s));
    int depht = 0;
    while (sc.hasNextLine()) {
      depht++;
      sc.nextLine();
    }
    var matrix = new char[length][depht];
    int counter = 0;
    sc = new Scanner(new File(s));
    while (sc.hasNextLine()) {
      var line = sc.nextLine();
      matrix[counter] = line.toCharArray();
      counter++;
    }
    return matrix;
  }
}
