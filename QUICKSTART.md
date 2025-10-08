# Quick Start Guide 🚀

## Setup New Day (30 seconds!)

```bash
# Option 1: Manual input
./setup-day.sh 2024 13

# Option 2: Auto-fetch (requires AOC_SESSION env var)
./setup-day.sh 2024 13 --fetch
```

## Setup Session Cookie (One-time)

1. Login to https://adventofcode.com
2. Open DevTools (F12) → Application → Cookies
3. Copy `session` cookie value
4. Add to `~/.zshrc` or `~/.bashrc`:

```bash
export AOC_SESSION='your_session_cookie_here'
```

## File Locations

After running setup script:

- **Solution**: `src/main/java/aoc/year2024/Day13.java`
- **Test**: `src/test/java/aoc/year2024/Day13Test.java`
- **Input**: `src/main/resources/inputs/2024/day13.txt`
- **Test Input**: `src/main/resources/inputs/2024/day13_test.txt`

## Run Solution

```bash
# From project root
mvn compile exec:java -Dexec.mainClass="aoc.year2024.Day13"

# Or just run main() in your IDE
```

## Common Code Patterns

### Read Input
```java
// In your solve methods, input is already a List<String>
// No need to read manually!

// But if you need custom reading:
import aoc.utils.InputReader;

List<String> lines = InputReader.readLines(2024, 13);
char[][] grid = InputReader.readAsGrid(2024, 13);
String raw = InputReader.readAsString(2024, 13);
```

### Solution Template
```java
@Override
public Object solvePart1(List<String> input) {
    // Parse input
    // Solve
    return answer;
}
```

### Test with Example Input
```java
public static void main(String[] args) {
    Day13 solution = new Day13();
    solution.solveTest();  // Uses day13_test.txt
    // solution.solve();    // Uses day13.txt
}
```

## Workflow

1. `./setup-day.sh 2024 13 --fetch` ← Creates everything
2. Paste test input from problem into `day13_test.txt`
3. Code solution
4. Run and verify with test input
5. Switch to real input and solve!

Done! 🎄
