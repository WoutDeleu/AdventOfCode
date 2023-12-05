import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileGenerator {
    public static void main(String[] args) throws IOException {
        final String main = "Main.java";
        final String test = "MainTest.java";
        final String input = "input";
        final String year = "2023";

        for(int i = 1; i <= 31; i++) {
            String day = "Day" + i;
            String mainPath = "./src/main/java/year" + year + "/" + day + "/";
            String testPath = "./src/test/java/year" + year + "/" + day + "/";

            if(!Files.exists(Path.of(mainPath)))
                new File(mainPath).mkdir();
            if (!Files.exists(Path.of(testPath)))
                new File(testPath).mkdir();

            if(!Files.exists(Path.of(mainPath + main))) {
                new File(mainPath + main).createNewFile();
                String mainContent = """
                        // package year2023.Day%s;
     
                        // import java.io.File;
                        // import java.io.FileNotFoundException;
                        // public class Main {
                        //     public static void main(String[] args) throws FileNotFoundException {
                        //         int[] cards = read("./src/main/java/year2023/Day%s/input");
                        //         int score = solve(cards);
                        //         System.out.println("score: " + score);
                        //     }
                        // }
                    """.formatted(Integer.toString(i), Integer.toString(i));

                BufferedWriter writer = new BufferedWriter(new FileWriter(mainPath+main));
                writer.write(mainContent);
                writer.close();
            }
            if(!Files.exists(Path.of(mainPath + input))) {
                new File(mainPath + input).createNewFile();


                String testContent = """
                        // package year2023.Day%s;
     
                        // import java.io.File;
                        // import java.io.FileNotFoundException;
                        // public class AlmanacFoodProductionSolverTest {
                        // 
                        //     @Test
                        //     public void testMain() throws FileNotFoundException {
                        //         int[] cards = read("./src/test/java/year2023/Day%s/input");
                        //         int score = solve(cards);
                        //         assert score == 35;
                        //     }
                        //     @Test
                        //     public void testMainV2() throws FileNotFoundException {
                        //         int[] cards = read("./src/test/java/year2023/Day%s/input");
                        //         int score = solve(cards);
                        //         assert score == 35;
                        //     }
                        // }

                    """.formatted(Integer.toString(i), Integer.toString(i), Integer.toString(i));
                BufferedWriter writer = new BufferedWriter(new FileWriter(testPath+test));
                writer.write(testContent);
                writer.close();

            }

            if(!Files.exists(Path.of(testPath + test)))
                new File(testPath + test).createNewFile();
            if(!Files.exists(Path.of(testPath + input)))
                new File(testPath + input).createNewFile();

        }
    }
}
