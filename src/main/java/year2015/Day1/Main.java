package year2015.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var input = new Main().read("./src/main/java/year2015/Day1/input");
        int pt1 = new Main().solve_pt1(input);
        System.out.println("pt1: " + pt1);

        int pt2 = new Main().solve_pt2(input);
        System.out.println("pt2: " + pt2);
    }

    public int solve_pt1(String input) {
        int floor = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') floor++;
            else floor--;
        }
        return floor;
    }

    public int solve_pt2(String input) {
        int floor = 0;
        int count = 0;
        for (char c : input.toCharArray()) {
            count++;
            if (c == '(') floor++;
            else floor--;
            if (floor < 0) break;
        }
        return count;
    }

    public String read(String path) throws FileNotFoundException {
        var sc = new Scanner(new File(path));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        sc.close();
        return sb.toString();
    }
}
