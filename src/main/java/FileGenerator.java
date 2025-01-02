import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileGenerator {
  public static void main(String[] args) throws IOException {
    final String main = "Main.java";
    final String test = "MainTest.java";
    final String input = "input";
    final String year = "2024";

    for (int i = 3; i <= 25; i++) {
      String day = "Day" + i;
      String mainPath = "./src/main/java/year" + year + "/" + day + "/";
      String testPath = "./src/test/java/year" + year + "/" + day + "/";

      if (!Files.exists(Path.of(mainPath)))
        new File(mainPath).mkdir();
      if (!Files.exists(Path.of(testPath)))
        new File(testPath).mkdir();

      if (!Files.exists(Path.of(mainPath + main))) {
        new File(mainPath + main).createNewFile();
        String mainContent = """
          package year2024.Day%s;
          
          import java.io.File;
          import java.io.FileNotFoundException;
          import java.util.List;
          import java.util.Scanner;
          
          public class Main {
            public static void main(String[] args) throws FileNotFoundException {
              var input = new Main().read("./src/main/java/year2024/Day%s/input");
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
          """.formatted(Integer.toString(i), Integer.toString(i));

        BufferedWriter writer = new BufferedWriter(new FileWriter(mainPath + main));
        writer.write(mainContent);
        writer.close();
      }
      if (!Files.exists(Path.of(testPath + input))) {
        new File(testPath + input).createNewFile();
        String testContent = """
            package year2024.Day%s;
          
            import org.junit.Test;
          
            import java.io.FileNotFoundException;
          
          
            public class MainTest {
              @Test
              public void part1() throws FileNotFoundException {
                var main = new Main();
                var input = main.read("./src/test/java/year2024/Day%s/input");
                int solution = main.solve_pt1(input);
                System.out.println("Solution: " + solution);
                assert solution == 1;
              }
          
              @Test
              public void part2() throws FileNotFoundException {
                var main = new Main();
                var input = main.read("./src/test/java/year2024/Day%s/input");
                int solution = main.solve_pt2(input);
                System.out.println("Solution: " + solution);
                assert solution == 1;
              }
            }
          
          """.formatted(Integer.toString(i), Integer.toString(i), Integer.toString(i));
        BufferedWriter writer = new BufferedWriter(new FileWriter(testPath + test));
        writer.write(testContent);
        writer.close();

      }

      if (!Files.exists(Path.of(testPath + test)))
        new File(testPath + test).createNewFile();
      if (!Files.exists(Path.of(testPath + input)))
        new File(testPath + input).createNewFile();

    }
  }
}
