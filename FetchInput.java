import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility to fetch Advent of Code inputs from the official website
 * Usage: java FetchInput.java <year> <day>
 * Example: java FetchInput.java 2016 1
 *
 * Requires AOC_SESSION environment variable to be set with your session cookie
 */
public class FetchInput {
    private static final String BASE_URL = "https://adventofcode.com";

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            System.err.println("Usage: java FetchInput.java <year> <day>");
            System.err.println("Example: java FetchInput.java 2016 1");
            System.exit(1);
        }

        int year = Integer.parseInt(args[0]);
        int day = Integer.parseInt(args[1]);

        String sessionCookie = System.getenv("AOC_SESSION");
        if (sessionCookie == null || sessionCookie.isEmpty()) {
            System.err.println("Error: AOC_SESSION environment variable not set");
            System.err.println("Please set it with your Advent of Code session cookie:");
            System.err.println("  export AOC_SESSION='your_session_cookie_here'");
            System.exit(1);
        }

        // Create directory structure
        String dayPadded = String.format("%d", day);
        String mainDir = String.format("./src/main/java/year%d/Day%d/", year, day);
        String testDir = String.format("./src/test/java/year%d/Day%d/", year, day);

        new File(mainDir).mkdirs();
        new File(testDir).mkdirs();

        // Fetch input
        System.out.printf("Fetching input for %d Day %d...%n", year, day);
        String input = fetchInput(year, day, sessionCookie);

        // Save main input
        Path mainInputFile = Path.of(mainDir + "input");
        Files.writeString(mainInputFile, input);
        System.out.println("✓ Input saved to: " + mainInputFile);

        // Create empty test input
        Path testInputFile = Path.of(testDir + "input");
        if (!Files.exists(testInputFile)) {
            Files.writeString(testInputFile, "");
            System.out.println("✓ Test input created: " + testInputFile);
        }

        // Create Main.java from template
        createMainFile(year, day, mainDir);

        // Create MainTest.java from template
        createTestFile(year, day, testDir);

        System.out.println("\n✓ Setup complete!");
        System.out.println("\nNext steps:");
        System.out.println("1. Add test input to: " + testInputFile);
        System.out.println("2. Edit solution in: " + mainDir + "Main.java");
        System.out.println("3. Run with: cd src/main/java && java year" + year + ".Day" + day + ".Main");
    }

    private static String fetchInput(int year, int day, String sessionCookie)
            throws IOException, InterruptedException {
        String url = String.format("%s/%d/day/%d/input", BASE_URL, year, day);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + sessionCookie)
                .header("User-Agent", "github.com/woutdeleu/AdventOfCode")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch input: HTTP " + response.statusCode());
        }

        return response.body();
    }

    private static void createMainFile(int year, int day, String dir) throws IOException {
        String content = String.format("""
            package year%d.Day%d;

            import java.io.File;
            import java.io.FileNotFoundException;
            import java.util.Scanner;

            public class Main {
                public static void main(String[] args) throws FileNotFoundException {
                    var input = new Main().read("./src/main/java/year%d/Day%d/input");
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
            """, year, day, year, day);

        Path mainFile = Path.of(dir + "Main.java");
        Files.writeString(mainFile, content);
        System.out.println("✓ Created: " + mainFile);
    }

    private static void createTestFile(int year, int day, String dir) throws IOException {
        String content = String.format("""
            package year%d.Day%d;

            import org.junit.Test;

            import java.io.FileNotFoundException;

            public class MainTest {
                @Test
                public void part1() throws FileNotFoundException {
                    var main = new Main();
                    var input = main.read("./src/test/java/year%d/Day%d/input");
                    var solution = main.solve_pt1(input);
                    System.out.println("Solution: " + solution);
                    assert solution == 1;
                }

                @Test
                public void part2() throws FileNotFoundException {
                    var main = new Main();
                    var input = main.read("./src/test/java/year%d/Day%d/input");
                    var solution = main.solve_pt2(input);
                    System.out.println("Solution: " + solution);
                    assert solution == 1;
                }
            }
            """, year, day, year, day, year, day);

        Path testFile = Path.of(dir + "MainTest.java");
        Files.writeString(testFile, content);
        System.out.println("✓ Created: " + testFile);
    }
}
