package aoc.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Client for fetching Advent of Code inputs
 * Requires AOC_SESSION environment variable to be set with your session cookie
 */
public class AOCClient {
    private static final String BASE_URL = "https://adventofcode.com";
    private final String sessionCookie;

    public AOCClient(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

    public AOCClient() {
        this.sessionCookie = System.getenv("AOC_SESSION");
        if (this.sessionCookie == null || this.sessionCookie.isEmpty()) {
            throw new RuntimeException("AOC_SESSION environment variable not set. Please set it to your Advent of Code session cookie.");
        }
    }

    /**
     * Fetches input for a specific day and year
     * @param year The year
     * @param day The day (1-25)
     * @return The input as a string
     */
    public String fetchInput(int year, int day) throws IOException, InterruptedException {
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

    /**
     * Fetches and saves input to the resources directory
     * @param year The year
     * @param day The day (1-25)
     */
    public void fetchAndSaveInput(int year, int day) throws IOException, InterruptedException {
        String input = fetchInput(year, day);

        Path inputDir = Paths.get("src/main/resources/inputs/" + year);
        Files.createDirectories(inputDir);

        Path inputFile = inputDir.resolve(String.format("day%02d.txt", day));
        Files.writeString(inputFile, input);

        System.out.println("Input saved to: " + inputFile);

        // Create empty test file if it doesn't exist
        Path testFile = inputDir.resolve(String.format("day%02d_test.txt", day));
        if (!Files.exists(testFile)) {
            Files.writeString(testFile, "");
            System.out.println("Test file created: " + testFile);
        }
    }
}
