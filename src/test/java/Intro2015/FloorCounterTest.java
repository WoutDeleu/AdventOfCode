package Intro2015;

import org.junit.jupiter.api.Test;

public class FloorCounterTest {
    @Test
    public void goldenStandardTest() {
        assert FloorCounter.countFloors("(())") == 0;
        assert FloorCounter.countFloors("()()") == 0;
        assert FloorCounter.countFloors("(((") == 3;
        assert FloorCounter.countFloors("(()(()(") == 3;
        assert FloorCounter.countFloors("))(((((") == 3;
        assert FloorCounter.countFloors("())") == -1;
        assert FloorCounter.countFloors("))(") == -1;
        assert FloorCounter.countFloors(")))") == -3;
        assert FloorCounter.countFloors(")())())") == -3;
    }
}
