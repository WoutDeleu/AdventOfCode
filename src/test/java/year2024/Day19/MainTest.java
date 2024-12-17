  package year2024.Day19;

  import org.junit.Test;

  import java.io.FileNotFoundException;


  public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day19/input");
      int solution = main.solve_pt1(input);
      assert solution == 1;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day19/input");
      assert main.solve_pt2(input) == 1;
    }
  }

