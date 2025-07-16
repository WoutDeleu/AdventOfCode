  package year2024.Day7;

  import org.junit.Test;

  import java.io.FileNotFoundException;


  public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day7/input");
      var solution = main.solve_pt1(input);
      System.out.println("Solution: " + solution);
      assert solution == 3749;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day7/input");
      var solution = main.solve_pt2(input);
      System.out.println("Solution: " + solution);
      assert solution == 11387;
    }
  }

