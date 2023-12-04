package year2023.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static year2023.Day3.GearCollector.collectGears;
import static year2023.Day3.PartsCollector.collectParts;

public class EngineFixer {

    public static void main(String[] args) throws FileNotFoundException {
        char[][] schematic = readSchematic("./src/main/java/year2023/Day3/input");
        int sum = collectParts(schematic);
        System.out.println("parts: " + sum);

        sum = collectGears(schematic);
        System.out.println("gears: " + sum);
    }



    static char[][] readSchematic(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        // get dimensions of matrix
        int rowSize = 0, colSize = 0;
        while (sc.hasNextLine()) {
            colSize = sc.nextLine().length();
            rowSize++;
        }
        sc = new Scanner(new File(path));
        char[][] schematic = new char[rowSize][colSize];
        int row = 0;
        while (sc.hasNextLine()) {
            int col = 0;
            String line = sc.nextLine();
            for(char c : line.toCharArray()) {
                schematic[row][col] = c;
                col++;
            }
            row++;
        }
        return schematic;
    }


}
