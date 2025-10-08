package year2016.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var input = new Main().read("./src/main/java/year2016/Day1/input");
        int score = new Main().solve_pt1(input);
        System.out.println("pt1: " + score);

        int score2 = new Main().solve_pt2(input);
        System.out.println("pt2: " + score2);
    }

    public int solve_pt1(String[] input) {
        return 0;
    }

    public int solve_pt2(String[] input) {
        return 0;
    }

    public String[] read(String path) throws FileNotFoundException {
        var sc = new Scanner(new File(path));
        // TODO: Parse input
        return null;
    }
}
