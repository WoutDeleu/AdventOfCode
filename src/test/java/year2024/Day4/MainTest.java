  package year2024.Day4;

  import org.junit.Test;

  import java.io.FileNotFoundException;


  public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day4/input");
      int solution = main.solve_pt1(input);
      System.out.println(solution);
      assert solution == 18;
    }

    @Test
    public void part2() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day4/input2");
      System.out.println(main.solve_pt2(input));
      assert main.solve_pt2(input) == 9;
    }
  }

