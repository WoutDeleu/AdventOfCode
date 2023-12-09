package year2023.Day9;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static year2023.Day9.SensorExtrapolation.readHistoryInput;

public class sensorReadingTest {

    @Test
    public void testExtrapolation() throws FileNotFoundException {
        List<SensorReading> readings = readHistoryInput("./src/test/java/year2023/Day9/input");
        Long sum = readings
                .stream()
                .mapToLong(x-> { x.extrapolate(); return x.predictNextValue(); })
                .reduce(Long::sum).orElseThrow();
        assert sum == 114;
    }
    @Test
    public void testExtrapolationBackwards() throws FileNotFoundException {
        List<SensorReading> readings = readHistoryInput("./src/test/java/year2023/Day9/input");
        Long sum = readings
                .stream()
                .mapToLong(x-> { x.extrapolate(); return x.predictBackwards();} )
                .reduce(Long::sum).orElseThrow();
        assert sum == 2;

    }
}

