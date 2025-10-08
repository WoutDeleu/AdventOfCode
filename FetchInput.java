import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to fetch Advent of Code inputs from the official website
 * Usage: java FetchInput.java <year> <day>
 * Example: java FetchInput.java 2016 1
 *
 * Automatically fetches:
 * - Your personal puzzle input
 * - Example inputs from the puzzle description
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

        // Fetch puzzle input
        System.out.printf("Fetching puzzle input for %d Day %d...%n", year, day);
        String input = fetchInput(year, day, sessionCookie);

        // Save main input
        Path mainInputFile = Path.of(mainDir + "input");
        Files.writeString(mainInputFile, input);
        System.out.println("✓ Puzzle input saved to: " + mainInputFile);

        // Fetch example input from puzzle description
        System.out.printf("Fetching example input from puzzle description...%n");
        String exampleInput = fetchExampleInput(year, day, sessionCookie);

        Path testInputFile = Path.of(testDir + "input");
        if (exampleInput != null && !exampleInput.isEmpty()) {
            Files.writeString(testInputFile, exampleInput);
            System.out.println("✓ Example input saved to: " + testInputFile);
        } else {
            Files.writeString(testInputFile, "");
            System.out.println("⚠ No example input found, created empty file: " + testInputFile);
        }

        // Create Main.java from template
        createMainFile(year, day, mainDir);

        // Create MainTest.java from template
        createTestFile(year, day, testDir);

        System.out.println("\n✓ Setup complete!");
        System.out.println("\nNext steps:");
        if (exampleInput == null || exampleInput.isEmpty()) {
            System.out.println("1. Add example input to: " + testInputFile);
            System.out.println("2. Edit solution in: " + mainDir + "Main.java");
            System.out.println("3. Run with: cd src/main/java && java year" + year + ".Day" + day + ".Main");
        } else {
            System.out.println("1. Verify example input in: " + testInputFile);
            System.out.println("2. Edit solution in: " + mainDir + "Main.java");
            System.out.println("3. Run tests with: mvn test -Dtest=Day" + day + "Test");
            System.out.println("4. Run with: cd src/main/java && java year" + year + ".Day" + day + ".Main");
        }
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

    private static String fetchExampleInput(int year, int day, String sessionCookie)
            throws IOException, InterruptedException {
        String url = String.format("%s/%d/day/%d", BASE_URL, year, day);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + sessionCookie)
                .header("User-Agent", "github.com/woutdeleu/AdventOfCode")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("Warning: Failed to fetch puzzle description: HTTP " + response.statusCode());
            return null;
        }

        return extractExampleInput(response.body());
    }

    private static String extractExampleInput(String html) {
        // AOC puzzle examples are typically in <pre><code> tags
        // We'll extract the first substantial code block that looks like input data
        Pattern codePattern = Pattern.compile("<pre><code>(.*?)</code></pre>", Pattern.DOTALL);
        Matcher matcher = codePattern.matcher(html);

        List<String> codeBlocks = new ArrayList<>();
        while (matcher.find()) {
            String code = matcher.group(1)
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&")
                .replaceAll("&quot;", "\"")
                .replaceAll("<em>", "")
                .replaceAll("</em>", "")
                .trim();

            // Filter out empty blocks and very short snippets
            if (!code.isEmpty() && code.length() > 5) {
                codeBlocks.add(code);
            }
        }

        // Return the first substantial code block (usually the example input)
        // Most AOC puzzles have the example input in the first or second code block
        for (String block : codeBlocks) {
            // Skip blocks that look like output/results (typically numbers or short text)
            // Example inputs usually have multiple lines or are longer
            if (block.contains("\n") || block.length() > 20) {
                return block;
            }
        }

        // If no multi-line block found, return the first block if it exists
        return codeBlocks.isEmpty() ? null : codeBlocks.get(0);
    }

    private static void createMainFile(int year, int day, String dir) throws IOException {
        String content = String.format("""
            package year%d.Day%d;

            import java.io.IOException;
            import java.nio.file.Files;
            import java.nio.file.Path;
            import java.util.List;

            public class Main {
                public static void main(String[] args) throws IOException {
                    var input = readInput("./src/main/java/year%d/Day%d/input");

                    var part1 = solvePart1(input);
                    System.out.println("Part 1: " + part1);

                    var part2 = solvePart2(input);
                    System.out.println("Part 2: " + part2);
                }

                static Object solvePart1(List<String> lines) {
                    // TODO: Implement solution for part 1
                    return null;
                }

                static Object solvePart2(List<String> lines) {
                    // TODO: Implement solution for part 2
                    return null;
                }

                static List<String> readInput(String path) throws IOException {
                    return Files.readAllLines(Path.of(path));
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
            import java.io.IOException;
            import java.util.List;

            import static org.junit.Assert.*;

            public class MainTest {
                @Test
                public void testPart1() throws IOException {
                    var input = Main.readInput("./src/test/java/year%d/Day%d/input");
                    var result = Main.solvePart1(input);

                    System.out.println("Part 1 result: " + result);
                    // TODO: Update with expected result from puzzle
                    // assertEquals(expectedValue, result);
                    assertNotNull(result);
                }

                @Test
                public void testPart2() throws IOException {
                    var input = Main.readInput("./src/test/java/year%d/Day%d/input");
                    var result = Main.solvePart2(input);

                    System.out.println("Part 2 result: " + result);
                    // TODO: Update with expected result from puzzle
                    // assertEquals(expectedValue, result);
                    assertNotNull(result);
                }
            }
            """, year, day, year, day, year, day);

        Path testFile = Path.of(dir + "MainTest.java");
        Files.writeString(testFile, content);
        System.out.println("✓ Created: " + testFile);
    }
}
