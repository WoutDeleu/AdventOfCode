package year2023.Day3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static year2023.Day3.EngineFixer.*;

public class EngineFixerTest {
    @Test
    public void collectPartsTest() throws FileNotFoundException {
        char[][] schematic = readSchematic("./src/test/java/year2023/Day3/input");
        assert collectParts(schematic) == 4361;
    }
    // @Test
    // public void collectBearsTest() throws FileNotFoundException {
    //     char[][] schematic = readSchematic("./src/test/java/year2023/Day3/input");
    //     assert collectGears(schematic) == 467835;
    // }
}
