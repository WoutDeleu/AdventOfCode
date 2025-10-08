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

- **Solution**: `src/main/java/year2024/Day13/Main.java`
- **Test**: `src/test/java/year2024/Day13/MainTest.java`
- **Input**: `src/main/java/year2024/Day13/input`
- **Test Input**: `src/test/java/year2024/Day13/input`

## Run Solution

```bash
# From project root
mvn exec:java -Dexec.mainClass="year2024.Day13.Main"

# Or just run main() in your IDE
```

## Run Tests

```bash
mvn test -Dtest=year2024.Day13.MainTest
```

## Common Code Patterns

### Using Utilities

```java
import static aoc.utils.GridUtils.*;
import static aoc.utils.ParsingUtils.*;
import aoc.utils.Coordinate;

// Parse grid
char[][] grid = parseGrid(lines);

// Find positions
List<int[]> positions = findAll(grid, '#');

// Check bounds
if (inBounds(row, col, grid)) { ... }

// Count characters
int count = countChar(grid, 'X');
```

See [UTILITIES_GUIDE.md](UTILITIES_GUIDE.md) for complete examples.

### Solution Template

```java
static Object solvePart1(List<String> lines) {
    // Parse input
    // Solve
    return answer;
}

static Object solvePart2(List<String> lines) {
    // Parse input
    // Solve
    return answer;
}
```

## Workflow

1. `./setup-day.sh 2024 13 --fetch` ← Creates everything
2. Paste test input from problem into test `input` file
3. Code solution
4. Run tests with example data
5. Run with real input and solve!

Done! 🎄
