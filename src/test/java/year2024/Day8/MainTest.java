  package year2024.Day8;

  import org.junit.Test;

  import java.io.FileNotFoundException;

  import static year2024.Day8.ReadInput.read;


  public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = read("./src/test/java/year2024/Day8/input");
      int solution = main.solve_pt1(input);
      System.out.println("Solution: " + solution);
      assert solution == 14;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = read("./src/test/java/year2024/Day8/input");
      int solution = main.solve_pt2(input);
      System.out.println("Solution: " + solution);
      assert solution == 34;
    }
  }

