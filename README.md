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
- AOC session cookie (optional, for auto-fetching inputs)

### Setting Up a New Day

**Option 1: Auto-fetch from Advent of Code (Recommended)**
```bash
# Set your session cookie (one-time setup)
export AOC_SESSION='your_session_cookie_here'

# Fetch everything automatically!
java FetchInput.java 2024 13
```

This **automatically**:
- ✅ Downloads your **personal puzzle input**
- ✅ Extracts **example input** from puzzle description
- ✅ Creates directory structure
- ✅ Generates `Main.java` and `MainTest.java` from templates

**Option 2: Manual setup**
```bash
# Create directories
mkdir -p src/main/java/year2024/Day13
mkdir -p src/test/java/year2024/Day13

# Create files and paste input manually
touch src/main/java/year2024/Day13/{Main.java,input}
touch src/test/java/year2024/Day13/{MainTest.java,input}
```

### Getting Your Session Cookie
1. Log in to [adventofcode.com](https://adventofcode.com)
2. Open browser DevTools (F12)
3. Go to Application/Storage → Cookies → `https://adventofcode.com`
4. Copy the value of the `session` cookie
5. Add to your shell profile (~/.zshrc or ~/.bashrc):
   ```bash
   export AOC_SESSION='your_cookie_value'
   ```

### Running a Solution
```bash
# Compile and run a specific day
cd src/main/java
javac year2024/Day1/Main.java
java year2024.Day1.Main

# Or use Maven
mvn compile exec:java -Dexec.mainClass="year2024.Day1.Main"

# Run tests
mvn test -Dtest=Day1Test
```

---

## 📁 Project Structure

```
AdventOfCode/
├── FetchInput.java        # Utility to auto-fetch inputs from AOC
├── src/main/java/
│   ├── year2016/          # 2016 solutions
│   ├── year2023/          # 2023 solutions
│   ├── year2024/          # 2024 solutions
│   │   ├── Day1/
│   │   │   ├── Main.java     # Solution code
│   │   │   └── input         # Your puzzle input
│   │   ├── Day2/
│   │   └── ...
│   └── aoc/               # Utility classes (newer structure)
│       ├── Solution.java
│       └── utils/
├── src/test/java/
│   └── year2024/          # Unit tests
│       ├── Day1/
│       │   ├── MainTest.java # JUnit tests
│       │   └── input         # Example/test input
│       └── ...
├── Challenges/            # Problem descriptions (markdown)
│   ├── 2023/
│   └── 2024/
├── pom.xml               # Maven configuration
└── README.md
```

**Each day follows a consistent structure:**
- **Main.java**: Contains `solve_pt1()` and `solve_pt2()` methods
- **input**: Your actual puzzle input (fetched from AOC)
- **MainTest.java**: JUnit tests using example inputs
- **test/input**: Example input from puzzle description

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

## 🛠️ Complete Workflow

Here's the recommended workflow when a new Advent of Code puzzle is released:

1. **Fetch everything automatically** (6:00 AM EST when puzzle unlocks)
   ```bash
   java FetchInput.java 2024 13
   ```
   This automatically:
   - Creates `src/main/java/year2024/Day13/` and `src/test/java/year2024/Day13/`
   - Downloads your **personal puzzle input** → `src/main/java/year2024/Day13/input`
   - Extracts **example input** from puzzle → `src/test/java/year2024/Day13/input`
   - Generates `Main.java` and `MainTest.java` from templates

2. **Verify example input**
   - Check `src/test/java/year2024/Day13/input` has the correct example
   - Update if needed (some puzzles have multiple examples)

3. **Implement solution**
   - Edit `src/main/java/year2024/Day13/Main.java`
   - Implement `read()` method to parse your input
   - Implement `solve_pt1()` with your solution logic

4. **Test with example**
   ```bash
   mvn test -Dtest=Day13Test
   ```

5. **Run with real input**
   ```bash
   cd src/main/java
   java year2024.Day13.Main
   ```

6. **Submit answer and implement Part 2**
   - Implement `solve_pt2()` method
   - Update test expectations
   - Test and run again

### Development Tips

**Common Patterns**
- **Grid problems**: Use 2D arrays or custom Coordinate classes (see Day10, Day12)
- **Path finding**: BFS/DFS with visited sets
- **Parsing**: Scanner for input, split/regex for parsing
- **Performance**: Use HashMaps and HashSets for O(1) lookups

**Testing Strategy**
1. Always start with example input from puzzle description
2. Add `System.out.println()` to debug intermediate steps
3. Test edge cases separately
4. Only run with real input once tests pass

**How Example Extraction Works**
- FetchInput.java fetches the puzzle description HTML
- Parses `<pre><code>` blocks to find example inputs
- Automatically handles HTML entities and formatting
- Selects the first substantial code block (usually the example)
- ⚠️ Note: Some puzzles have multiple examples - verify and update if needed!

---

## 📚 About Advent of Code

[Advent of Code](https://adventofcode.com/) is an annual coding event with daily programming puzzles throughout December. Each puzzle has two parts, and solutions can be implemented in any programming language.

**Created by Eric Wastl** • [adventofcode.com](https://adventofcode.com/)
