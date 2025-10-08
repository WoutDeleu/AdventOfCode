# Project Structure

This repository contains solutions for Advent of Code puzzles in Java.

## 📂 Directory Layout

```
AdventOfCode/
├── src/main/java/
│   ├── aoc/
│   │   └── utils/                    # Shared utility classes
│   │       ├── InputReader.java      # Resource-based input reading
│   │       ├── GridUtils.java        # Grid/matrix operations
│   │       ├── Coordinate.java       # 2D coordinate class
│   │       ├── MathUtils.java        # Mathematical functions
│   │       └── ParsingUtils.java     # Input parsing helpers
│   │
│   ├── year2024/                     # 2024 solutions
│   │   ├── Day1/
│   │   │   ├── Main.java
│   │   │   └── input
│   │   ├── Day2/
│   │   │   ├── Main.java
│   │   │   └── input
│   │   └── ...
│   │
│   ├── year2023/                     # 2023 solutions
│   │   └── Day*/
│   │
│   └── year2015/                     # 2015 solutions
│       └── Day*/
│
├── src/test/java/
│   ├── year2024/
│   │   ├── Day1/MainTest.java
│   │   └── ...
│   │
│   ├── year2023/
│   │
│   └── year2015/
│
├── templates/                        # Templates for new days
│   ├── Day.java.template             # Main.java template
│   └── DayTest.java.template         # MainTest.java template
│
├── setup-day.sh                      # Automated day setup script
├── README.md                         # Main documentation
├── QUICKSTART.md                     # Quick reference guide
├── UTILITIES_GUIDE.md                # Utility classes usage guide
└── pom.xml                           # Maven configuration
```

## 📝 Solution Structure

Each day follows this pattern:

```
src/main/java/yearXXXX/DayX/
  ├── Main.java          # Solution with solvePart1() and solvePart2()
  ├── input              # Puzzle input (gitignored)
  └── [other classes]    # Helper classes if needed

src/test/java/yearXXXX/DayX/
  ├── MainTest.java      # Unit tests
  └── input              # Test input from examples
```

### Main.java Pattern

```java
package year2024.Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var input = readInput("./src/main/java/year2024/Day1/input");

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        // Solution logic
        return 0;
    }

    static Object solvePart2(List<String> lines) {
        // Solution logic
        return 0;
    }

    static List<String> readInput(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }
}
```

## 🚀 Adding New Days

Use the automated setup script:

```bash
./setup-day.sh 2024 14
```

Or with automatic input fetching:

```bash
./setup-day.sh 2024 14 --fetch
```

This creates:
- `src/main/java/year2024/Day14/Main.java` (from template)
- `src/test/java/year2024/Day14/MainTest.java` (from template)
- `src/main/java/year2024/Day14/input` (empty or fetched)
- `src/test/java/year2024/Day14/input` (empty)

**Note:** To use `--fetch`, you need to set your AOC session cookie:
```bash
export AOC_SESSION='your_session_cookie_here'
```

## 🔧 Using Utilities

Import shared utilities to reduce code duplication:

```java
import static aoc.utils.GridUtils.*;
import static aoc.utils.ParsingUtils.*;
import aoc.utils.Coordinate;
```

See [UTILITIES_GUIDE.md](UTILITIES_GUIDE.md) for detailed usage examples.

## 🔍 Running Solutions

### Run a solution:
```bash
mvn exec:java -Dexec.mainClass="year2024.Day1.Main"
```

### Run tests:
```bash
mvn test -Dtest=year2024.Day1.MainTest
```

### Run all tests for a year:
```bash
mvn test -Dtest="year2024.*"
```

## 📊 Current Progress

### 2024
- Days 1-12 completed
- Utilities integrated into Days 8, 10, 12

### 2023
- Various days completed

### 2015
- Day 1 completed
