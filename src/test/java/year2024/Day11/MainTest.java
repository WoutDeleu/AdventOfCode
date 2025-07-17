  package year2024.Day11;

  import org.junit.Test;

  import java.io.FileNotFoundException;


  public class MainTest {
    @Test
    public void part1() throws FileNotFoundException {
      var main = new Main();
      var input = main.read("./src/test/java/year2024/Day11/input");
      int solution = main.solve(input);
      System.out.println("Solution: " + solution);
      assert solution == 55312;
    }

  }

