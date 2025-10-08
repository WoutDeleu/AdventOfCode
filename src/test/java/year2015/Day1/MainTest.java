package year2015.Day1;

import org.junit.Test;

public class MainTest {

    @Test
    public void testPart1() {
        Main main = new Main();
        assert main.solve_pt1("(())") == 0;
        assert main.solve_pt1("()()") == 0;
        assert main.solve_pt1("(((") == 3;
        assert main.solve_pt1("(()(()(") == 3;
        assert main.solve_pt1("))(((((") == 3;
        assert main.solve_pt1("())") == -1;
        assert main.solve_pt1("))(") == -1;
        assert main.solve_pt1(")))") == -3;
        assert main.solve_pt1(")())())") == -3;
    }

    @Test
    public void testPart2() {
        Main main = new Main();
        assert main.solve_pt2(")") == 1;
        assert main.solve_pt2("()())") == 5;
    }
}
