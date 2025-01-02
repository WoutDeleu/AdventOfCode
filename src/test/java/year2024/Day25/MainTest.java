  package year2024.Day25;

  import org.junit.Test;

  import java.io.FileNotFoundException;


  public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day25/input");
      int solution = main.solve_pt1(input);
      System.out.println("Solution: " + solution);
      assert solution == 1;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day25/input");
      int solution = main.solve_pt2(input);
      System.out.println("Solution: " + solution);
      assert solution == 1;
    }
  }

