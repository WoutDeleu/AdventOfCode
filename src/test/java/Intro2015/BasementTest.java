package Intro2015;

import org.junit.jupiter.api.Test;

public class BasementTest {
    @Test
    public void baseScenariosTest() {
        assert FloorCounter.findBasementCharacter(")") == 1;
        assert FloorCounter.findBasementCharacter("()())") == 5;
    }

}
