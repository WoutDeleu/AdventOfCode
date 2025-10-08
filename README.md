# Advent of Code - Java Solutions 🎄

My solutions for [Advent of Code](https://adventofcode.com/) puzzles, written in Java.

## 🎯 Progress

### 2024
**12 / 25 days completed** ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐

| Day | Status | Day | Status | Day | Status | Day | Status |
|-----|--------|-----|--------|-----|--------|-----|--------|
| [1](src/main/java/year2024/Day1) | ✅ | [7](src/main/java/year2024/Day7) | ✅ | 13 | ⬜ | 19 | ⬜ |
| [2](src/main/java/year2024/Day2) | ✅ | [8](src/main/java/year2024/Day8) | ✅ | 14 | ⬜ | 20 | ⬜ |
| [3](src/main/java/year2024/Day3) | ✅ | [9](src/main/java/year2024/Day9) | ✅ | 15 | ⬜ | 21 | ⬜ |
| [4](src/main/java/year2024/Day4) | ✅ | [10](src/main/java/year2024/Day10) | ✅ | 16 | ⬜ | 22 | ⬜ |
| [5](src/main/java/year2024/Day5) | ✅ | [11](src/main/java/year2024/Day11) | ✅ | 17 | ⬜ | 23 | ⬜ |
| [6](src/main/java/year2024/Day6) | ✅ | [12](src/main/java/year2024/Day12) | ✅ | 18 | ⬜ | 24 | ⬜ |
|     |        |     |        |     |        | 25 | ⬜ |

---

## 🚀 Quick Start

### Prerequisites
- Java 20+
- Maven

### Running a Solution
```bash
# Compile and run a specific day
cd src/main/java
javac year2024/Day1/Main.java
java year2024.Day1.Main

# Or use Maven
mvn compile exec:java -Dexec.mainClass="year2024.Day1.Main"

# Run tests
mvn test
```

---

## 📁 Project Structure

```
AdventOfCode/
├── src/main/java/
│   ├── year2023/          # 2023 solutions
│   ├── year2024/          # 2024 solutions
│   │   ├── Day1/
│   │   │   ├── Main.java
│   │   │   └── input
│   │   ├── Day2/
│   │   └── ...
│   └── FileGenerator.java
├── src/test/java/
│   └── year2024/          # Unit tests
│       ├── Day1/
│       │   ├── MainTest.java
│       │   └── input      # Test input
│       └── ...
├── Challenges/            # Problem descriptions
│   ├── 2023/
│   └── 2024/
├── pom.xml
└── README.md
```

Each day follows a consistent structure:
- **Main.java**: Contains `solve_pt1()` and `solve_pt2()` methods
- **input**: Your actual puzzle input
- **MainTest.java**: Unit tests with example inputs

---

## 💻 Solution Template

Each day's solution follows this pattern:

```java
package year2024.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var input = new Main().read("./src/main/java/year2024/Day1/input");
        int pt1 = new Main().solve_pt1(input);
        System.out.println("pt1: " + pt1);

        int pt2 = new Main().solve_pt2(input);
        System.out.println("pt2: " + pt2);
    }

    public int solve_pt1(String[] input) {
        // Your solution here
        return 0;
    }

    public int solve_pt2(String[] input) {
        // Your solution here
        return 0;
    }

    public String[] read(String path) throws FileNotFoundException {
        var sc = new Scanner(new File(path));
        // Read and parse input
        return null;
    }
}
```

---

## 🛠️ Development Tips

### Testing Your Solution
1. Add example input to `src/test/java/year2024/DayX/input`
2. Run the test: `mvn test -Dtest=DayXTest`
3. Once tests pass, run with actual input

### Common Patterns
- **Grid problems**: Use 2D arrays or custom Coordinate classes
- **Path finding**: BFS/DFS with visited sets
- **Parsing**: Scanner for input, split/regex for parsing
- **Performance**: Use HashMaps and HashSets for O(1) lookups

---

## 📚 About Advent of Code

[Advent of Code](https://adventofcode.com/) is an annual coding event with daily programming puzzles throughout December. Each puzzle has two parts, and solutions can be implemented in any programming language.

**Created by Eric Wastl** • [adventofcode.com](https://adventofcode.com/)
