package year2024.Day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day22/input");
    int score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }


  public int solve_pt1(int[] input) {
    return 0;
  }

  public int solve_pt2(int[] input) {
    return 0;
  }

  public int[] read(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    return null;
  }
}
