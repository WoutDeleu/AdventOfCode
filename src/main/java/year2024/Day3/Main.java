package year2024.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = new Main().read("./src/main/java/year2024/Day3/input");
    int score = new Main().solve_pt1(input);
    System.out.println("pt1: " + score);

    int score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }


  public int solve_pt2(String input) {
    Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
    Matcher matcher = pattern.matcher(input);

    int sum = 0;
    boolean enabled = true;
    while (matcher.find()) {
      String foundMatch = matcher.group();

      if (foundMatch.equals("do()")) {
        enabled = true;
      } else if (foundMatch.equals("don't()")) {
        enabled = false;
      }

      if (enabled && foundMatch.startsWith("mul")) {
        // Replaces everything that is not a digit or a comma
        String[] numbers = foundMatch.replaceAll("[^\\d+,]", "").split(",");
        int num1 = Integer.parseInt(numbers[0]);
        int num2 = Integer.parseInt(numbers[1]);
        sum += num1 * num2;
      }
    }
    return sum;
  }

  public int solve_pt1(String input) {
    Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
    Matcher matcher = pattern.matcher(input);

    int sum = 0;
    while (matcher.find()) {
      String foundMatch = matcher.group();

      if (foundMatch.startsWith("mul")) {
        // Replaces everything that is not a digit or a comma
        String[] numbers = foundMatch.replaceAll("[^\\d+,]", "").split(",");
        int num1 = Integer.parseInt(numbers[0]);
        int num2 = Integer.parseInt(numbers[1]);
        sum += num1 * num2;
      }
    }
    return sum;
  }

  public String read(String s) throws FileNotFoundException {
    var sc = new Scanner(new File(s));
    StringBuilder line = new StringBuilder();
    while (sc.hasNextLine()) {
      line.append(sc.nextLine());
    }
    return line.toString();
  }
}