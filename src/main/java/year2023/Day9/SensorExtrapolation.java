package year2023.Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SensorExtrapolation {
    public static void main(String[] args) throws FileNotFoundException {
        List<SensorReading> readings = readHistoryInput("./src/main/java/year2023/Day9/input");
        Long sum = readings.stream().mapToLong(x-> {x.extrapolate(); return x.predictNextValue();}).reduce(Long::sum).orElseThrow();
        System.out.println("sum: " + sum);
    }

    static List<SensorReading> readHistoryInput(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        List<SensorReading> readings = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            readings.add(new SensorReading(Arrays.stream(parts).mapToLong(Long::parseLong).boxed().toArray(Long[]::new)));
        }
        return readings;
    }
}
