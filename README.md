# Advent of Code - Java Solutions 🎄

Clean, utility-driven solutions for [Advent of Code](https://adventofcode.com/) puzzles.

---

## 📋 Table of Contents

- [Quick Start](#-quick-start)
- [Progress](#-progress)
- [Project Structure](#-project-structure)
- [Utility Classes](#-utility-classes)
- [Solution Template](#-solution-template)
- [Complete Workflow](#%EF%B8%8F-complete-workflow)
- [About Advent of Code](#-about-advent-of-code)

---

## 🚀 Quick Start

### Prerequisites
- Java 20+
- Maven
- AOC session cookie (optional, for auto-fetching inputs)

### Setting Up a New Day

Use the **`setup-day.sh`** script to create everything automatically:

```bash
# Option 1: Create structure with empty input files
./setup-day.sh 2024 13

# Option 2: Auto-fetch your puzzle input (recommended)
./setup-day.sh 2024 13 --fetch
```

This **automatically**:
- ✅ Creates directory structure (`year2024/Day13/`)
- ✅ Generates `Main.java` from template
- ✅ Generates `MainTest.java` from template
- ✅ Creates empty `input` files (or fetches your actual input with `--fetch`)

**Time:** ~30 seconds total! No more manual setup.

### One-Time Setup: Session Cookie

To use `--fetch`, configure your AOC session cookie once:

1. Log in to [adventofcode.com](https://adventofcode.com)
2. Open browser DevTools (F12)
3. Go to Application/Storage → Cookies → `https://adventofcode.com`
4. Copy the value of the `session` cookie
5. Add to your shell profile (~/.zshrc or ~/.bashrc):
   ```bash
   export AOC_SESSION='your_session_cookie_value'
   ```
6. Reload terminal: `source ~/.zshrc`

### Running a Solution
```bash
# Use Maven to compile and run
mvn compile exec:java -Dexec.mainClass="year2024.Day1.Main"

# Run tests
mvn test -Dtest=year2024.Day1.MainTest

# Run all tests
mvn test
```

---

## 🎯 Progress

### 2024 - **12 / 25 days** ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐

| Day | Status | Day | Status | Day | Status | Day | Status |
|-----|--------|-----|--------|-----|--------|-----|--------|
| [1](src/main/java/year2024/Day1) | ✅ | [7](src/main/java/year2024/Day7) | ✅ | 13 | ⬜ | 19 | ⬜ |
| [2](src/main/java/year2024/Day2) | ✅ | [8](src/main/java/year2024/Day8) | ✅ | 14 | ⬜ | 20 | ⬜ |
| [3](src/main/java/year2024/Day3) | ✅ | [9](src/main/java/year2024/Day9) | ✅ | 15 | ⬜ | 21 | ⬜ |
| [4](src/main/java/year2024/Day4) | ✅ | [10](src/main/java/year2024/Day10) | ✅ | 16 | ⬜ | 22 | ⬜ |
| [5](src/main/java/year2024/Day5) | ✅ | [11](src/main/java/year2024/Day11) | ✅ | 17 | ⬜ | 23 | ⬜ |
| [6](src/main/java/year2024/Day6) | ✅ | [12](src/main/java/year2024/Day12) | ✅ | 18 | ⬜ | 24 | ⬜ |
|     |        |     |        |     |        | 25 | ⬜ |

### 2023 - **9 / 25 days** ⭐⭐⭐⭐⭐⭐⭐⭐⭐

| Day | Status | Day | Status | Day | Status | Day | Status |
|-----|--------|-----|--------|-----|--------|-----|--------|
| [1](src/main/java/year2023/Day1) | ✅ | [7](src/main/java/year2023/Day7) | ✅ | 13 | ⬜ | 19 | ⬜ |
| [2](src/main/java/year2023/Day2) | ✅ | [8](src/main/java/year2023/Day8) | ✅ | 14 | ⬜ | 20 | ⬜ |
| [3](src/main/java/year2023/Day3) | ✅ | [9](src/main/java/year2023/Day9) | ✅ | 15 | ⬜ | 21 | ⬜ |
| [4](src/main/java/year2023/Day4) | ✅ | 10 | ⬜ | 16 | ⬜ | 22 | ⬜ |
| [5](src/main/java/year2023/Day5) | ✅ | 11 | ⬜ | 17 | ⬜ | 23 | ⬜ |
| [6](src/main/java/year2023/Day6) | ✅ | 12 | ⬜ | 18 | ⬜ | 24 | ⬜ |
|     |        |     |        |     |        | 25 | ⬜ |

### 2015 - **1 / 25 days** ⭐

| Day | Status | Day | Status | Day | Status | Day | Status |
|-----|--------|-----|--------|-----|--------|-----|--------|
| [1](src/main/java/year2015/Day1) | ✅ | 7 | ⬜ | 13 | ⬜ | 19 | ⬜ |
| 2 | ⬜ | 8 | ⬜ | 14 | ⬜ | 20 | ⬜ |
| 3 | ⬜ | 9 | ⬜ | 15 | ⬜ | 21 | ⬜ |
| 4 | ⬜ | 10 | ⬜ | 16 | ⬜ | 22 | ⬜ |
| 5 | ⬜ | 11 | ⬜ | 17 | ⬜ | 23 | ⬜ |
| 6 | ⬜ | 12 | ⬜ | 18 | ⬜ | 24 | ⬜ |
|     |        |     |        |     |        | 25 | ⬜ |

---

## 📁 Project Structure

```
AdventOfCode/
├── setup-day.sh           # Script to auto-setup new days
├── src/main/java/
│   ├── utils/             # Shared utility classes
│   │   ├── InputReader.java   # Centralized file I/O
│   │   ├── ParsingUtils.java  # Integer/grid parsing
│   │   ├── GridUtils.java     # Grid operations
│   │   └── MathUtils.java     # Math helpers
│   ├── year2015/          # 2015 solutions
│   ├── year2016/          # 2016 solutions
│   ├── year2023/          # 2023 solutions
│   └── year2024/          # 2024 solutions
│       ├── Day1/
│       │   ├── Main.java      # Solution code
│       │   └── input          # Your puzzle input
│       ├── Day2/
│       └── ...
├── src/test/java/
│   └── year2024/          # Unit tests
│       ├── Day1/
│       │   ├── MainTest.java  # JUnit tests
│       │   └── input          # Example/test input
│       └── ...
├── Challenges/            # Problem descriptions (markdown)
│   └── 2024/
│       ├── Day1/README.md
│       └── ...
├── templates/             # Code generation templates
│   ├── Day.java.template
│   └── DayTest.java.template
├── pom.xml               # Maven configuration
└── README.md
```

**Each day follows a consistent structure:**
- **Main.java**: Contains `solvePart1()` and `solvePart2()` methods
- **input**: Your actual puzzle input (fetched from AOC)
- **MainTest.java**: JUnit tests using example inputs
- **test/input**: Example input from puzzle description

---

## 🔧 Utility Classes

All solutions use centralized utilities to eliminate code duplication:

### InputReader
```java
// Read main input
var input = InputReader.readLines(2024, 1);

// Read test input
var testInput = InputReader.readLines(2024, 1, true);
```

### ParsingUtils
```java
// Parse space-separated integers
List<Integer> numbers = ParsingUtils.parseInts("1 2 3 4");

// Extract all integers from text (with regex)
List<Integer> nums = ParsingUtils.extractInts("mul(123,456)");

// Parse comma-separated integers
List<Integer> csv = ParsingUtils.parseCSVInts("1,2,3,4,5");

// Parse grids
char[][] grid = ParsingUtils.parseGrid(lines);
int[][] intGrid = ParsingUtils.parseIntGrid(lines);
```

### GridUtils
```java
// Bounds checking
if (GridUtils.inBounds(row, col, grid)) { ... }

// Find positions
int[] pos = GridUtils.findFirst(grid, 'S');
List<int[]> positions = GridUtils.findAll(grid, '#');

// Count occurrences
int count = GridUtils.countChar(grid, '#');

// Get neighbors (4-directional or 8-directional)
List<int[]> neighbors = GridUtils.getNeighbors4(row, col, grid);
List<int[]> neighbors8 = GridUtils.getNeighbors8(row, col, grid);

// Copy grid
char[][] copy = GridUtils.copyGrid(grid);
```

### MathUtils
```java
// GCD and LCM
long g = MathUtils.gcd(48, 18);
long l = MathUtils.lcm(12, 18);

// Manhattan distance
int dist = MathUtils.manhattanDistance(x1, y1, x2, y2);
```

---

## 💻 Solution Template

The generated template provides a clean, utility-driven structure:

```java
package year2024.Day1;

import utils.InputReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 1);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        // TODO: Implement part 1
        return 0;
    }

    static Object solvePart2(List<String> lines) {
        // TODO: Implement part 2
        return 0;
    }
}
```

**Key Features:**
- ✅ **Centralized I/O**: Uses `InputReader` utility - no custom file reading code
- ✅ **Flexible return types**: `Object` return type works with int, long, String, etc.
- ✅ **Utility-driven**: Leverage ParsingUtils, GridUtils, MathUtils for common operations
- ✅ **Static methods**: Testable without creating instances
- ✅ **Clean and minimal**: Focus on problem-solving, not boilerplate

**Common Usage Patterns:**

```java
// Parse integers with ParsingUtils
import utils.ParsingUtils;

static Object solvePart1(List<String> lines) {
    List<Integer> numbers = ParsingUtils.parseInts(lines.get(0));
    return numbers.stream().mapToInt(Integer::intValue).sum();
}

// Parse grid with GridUtils
import utils.ParsingUtils;
import utils.GridUtils;

static Object solvePart1(List<String> lines) {
    char[][] grid = ParsingUtils.parseGrid(lines);
    int count = GridUtils.countChar(grid, '#');
    return count;
}

// Extract all integers from text
import utils.ParsingUtils;

static Object solvePart1(List<String> lines) {
    List<Integer> allNumbers = ParsingUtils.extractInts(lines.get(0));
    return allNumbers.get(0) * allNumbers.get(1);
}
```

---

## 🛠️ Complete Workflow

Here's the recommended workflow when a new Advent of Code puzzle is released:

1. **Setup new day** (6:00 AM EST when puzzle unlocks)
   ```bash
   ./setup-day.sh 2024 13 --fetch
   ```
   This automatically:
   - Creates `src/main/java/year2024/Day13/` and `src/test/java/year2024/Day13/`
   - Downloads your **personal puzzle input** → `src/main/java/year2024/Day13/input`
   - Extracts **example input** from puzzle → `src/test/java/year2024/Day13/input`
   - Saves puzzle description → `Challenges/2024/Day13/README.md`
   - Generates `Main.java` and `MainTest.java` from templates

2. **Verify example input**
   - Check `src/test/java/year2024/Day13/input` has the correct example
   - Update if needed (some puzzles have multiple examples)

3. **Implement solution**
   - Edit `src/main/java/year2024/Day13/Main.java`
   - Implement `solvePart1()` with your solution logic
   - Use utility classes: `ParsingUtils`, `GridUtils`, `MathUtils`

4. **Test with example**
   ```bash
   mvn test -Dtest=year2024.Day13.MainTest
   ```

5. **Run with real input**
   ```bash
   mvn exec:java -Dexec.mainClass="year2024.Day13.Main"
   ```

6. **Submit answer and implement Part 2**
   - Implement `solvePart2()` method
   - Update test expectations in `MainTest.java`
   - Test and run again

### Development Tips

**Use the Utilities!**
- **Parsing**: `ParsingUtils.parseInts()`, `extractInts()`, `parseGrid()`
- **Grids**: `GridUtils.inBounds()`, `getNeighbors4()`, `countChar()`
- **Math**: `MathUtils.gcd()`, `lcm()`, `manhattanDistance()`
- **I/O**: `InputReader.readLines(year, day)` for all file reading

**Common Patterns**
- **Grid problems**: Use `ParsingUtils.parseGrid()` + `GridUtils` helpers
- **Path finding**: BFS/DFS with visited sets and `getNeighbors4/8()`
- **Number extraction**: Use `ParsingUtils.extractInts()` for regex extraction
- **Performance**: Use HashMaps and HashSets for O(1) lookups

**Testing Strategy**
1. Always start with example input from puzzle description
2. Add `System.out.println()` to debug intermediate steps
3. Test edge cases separately
4. Only run with real input once tests pass

**How Example Extraction Works**
- setup-day.sh fetches the puzzle description HTML
- Parses `<pre><code>` blocks to find example inputs
- Automatically handles HTML entities and formatting
- Selects the first substantial code block (usually the example)
- ⚠️ Note: Some puzzles have multiple examples - verify and update if needed!

---

## 📚 About Advent of Code

[Advent of Code](https://adventofcode.com/) is an annual coding event with daily programming puzzles throughout December. Each puzzle has two parts, and solutions can be implemented in any programming language.

**Created by Eric Wastl** • [adventofcode.com](https://adventofcode.com/)
