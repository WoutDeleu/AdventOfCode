package year2023.Day3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static year2023.Day3.EngineFixer.*;
import static year2023.Day3.GearCollector.collectGears;
import static year2023.Day3.PartsCollector.collectParts;

public class EngineFixerTest {
    @Test
    public void collectPartsTest() throws FileNotFoundException {
        char[][] schematic = readSchematic("./src/test/java/year2023/Day3/input");
        assert collectParts(schematic) == 4361;
    }
    @Test
    public void collectGearsTest() throws FileNotFoundException {
        char[][] schematic = readSchematic("./src/test/java/year2023/Day3/input");
        System.out.println(collectGears(schematic));
        assert collectGears(schematic) == 467835;
    }
}
