package year2023.Day3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static year2023.Day3.EngineFixer.readSchematic;
import static year2023.Day3.EngineFixer.solveSchematic;

public class EngineFixerTest {
    @Test
    public void schematicSolverTest() throws FileNotFoundException {
        char[][] schematic = readSchematic("./src/test/java/year2023/Day3/input");
        assert solveSchematic(schematic) == 4361;
    }
}
