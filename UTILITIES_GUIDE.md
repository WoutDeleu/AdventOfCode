# Utilities Usage Guide

This guide shows how to use the AOC utility classes in your solutions.

## 📦 Available Utilities

Located in `src/main/java/aoc/utils/`:
- **GridUtils** - Grid/matrix operations
- **Coordinate** - 2D coordinate with direction support
- **MathUtils** - Mathematical functions
- **ParsingUtils** - Input parsing helpers
- **InputReader** - Resource-based input loading (existing)
- **AOCClient** - Auto-fetch inputs (existing)

## 🎯 Quick Start

### Import Utilities

```java
// Static imports for convenience
import static aoc.utils.GridUtils.*;
import static aoc.utils.ParsingUtils.*;
import static aoc.utils.MathUtils.*;

// Regular imports
import aoc.utils.Coordinate;
import aoc.utils.Coordinate.Direction;
```

## 📖 Common Patterns

### Pattern 1: Grid-based Puzzles

**Before:**
```java
// Manual grid parsing
char[][] grid = new char[lines.size()][];
for (int i = 0; i < lines.size(); i++) {
    grid[i] = lines.get(i).toCharArray();
}

// Manual bounds checking
if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
    // do something
}

// Manual grid printing
for (char[] row : grid) {
    for (char c : row) {
        System.out.print(c);
    }
    System.out.println();
}
```

**After:**
```java
import static aoc.utils.GridUtils.*;
import static aoc.utils.ParsingUtils.*;

// Parse grid in one line
char[][] grid = parseGrid(lines);

// Simple bounds checking
if (inBounds(row, col, grid)) {
    // do something
}

// Simple printing
printGrid(grid);
```

### Pattern 2: Coordinate/Position Tracking

**Before:**
```java
// Custom coordinate class
public record Coordinate(int row, int col) {}

// Manual neighbor calculation
int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
List<int[]> neighbors = new ArrayList<>();
for (int[] dir : directions) {
    int newRow = row + dir[0];
    int newCol = col + dir[1];
    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
        neighbors.add(new int[]{newRow, newCol});
    }
}
```

**After:**
```java
import aoc.utils.Coordinate;
import aoc.utils.Coordinate.Direction;

// Use built-in Coordinate
Coordinate pos = new Coordinate(row, col);

// Get neighbors easily
Coordinate[] neighbors = pos.neighbors4();

// Move in directions
Coordinate newPos = pos.move(Direction.UP);
Coordinate next = pos.move(Direction.RIGHT, 3); // Move 3 steps

// Calculate distance
int dist = pos.manhattanDistance(otherPos);
```

### Pattern 3: Parsing Input

**Before:**
```java
// Manual int parsing
List<Integer> numbers = new ArrayList<>();
for (String part : line.split("\\s+")) {
    numbers.add(Integer.parseInt(part));
}

// Manual regex extraction
Pattern p = Pattern.compile("-?\\d+");
Matcher m = p.matcher(text);
while (m.find()) {
    numbers.add(Integer.parseInt(m.group()));
}
```

**After:**
```java
import static aoc.utils.ParsingUtils.*;

// Parse space-separated ints
List<Integer> numbers = parseInts(line);

// Extract all integers from text
List<Integer> extracted = extractInts(text);

// Parse CSV
List<Integer> values = parseCSVInts("1,2,3,4,5");

// Parse grid
char[][] grid = parseGrid(lines);
```

### Pattern 4: Mathematical Operations

**Before:**
```java
// Manual GCD/LCM
public long gcd(long a, long b) {
    while (b != 0) {
        long temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

public long lcm(long a, long b) {
    return (a * b) / gcd(a, b);
}
```

**After:**
```java
import static aoc.utils.MathUtils.*;

// Use built-in
long result = lcm(12, 18, 24); // Multiple numbers
long g = gcd(48, 18);

// Other useful functions
boolean prime = isPrime(17);
long sum = sumRange(1, 100);
List<Long> divs = divisors(24);
```

## 💡 Real-World Examples

### Example: Grid Search

```java
import static aoc.utils.GridUtils.*;
import static aoc.utils.ParsingUtils.*;

public class DayX {
    public int solve_pt1(List<String> input) {
        // Parse grid
        char[][] grid = parseGrid(input);

        // Find starting position
        int[] start = findFirst(grid, 'S');

        // Count occurrences
        int targetCount = countChar(grid, '#');

        // Get all positions of a character
        List<int[]> walls = findAll(grid, '#');

        // Print for debugging
        printGrid(grid);

        return targetCount;
    }
}
```

### Example: Path Finding with Coordinates

```java
import aoc.utils.Coordinate;
import aoc.utils.Coordinate.Direction;
import static aoc.utils.GridUtils.*;

public class DayY {
    public int solve_pt1(List<String> input) {
        char[][] grid = parseGrid(input);

        // Start at top-left
        Coordinate pos = new Coordinate(0, 0);
        Direction facing = Direction.RIGHT;

        // Move around
        while (pos.inBounds(grid.length, grid[0].length)) {
            // Check what's ahead
            Coordinate next = pos.move(facing);

            if (!next.inBounds(grid.length, grid[0].length)) {
                break;
            }

            if (grid[next.row][next.col] == '#') {
                // Turn right if blocked
                facing = facing.turnRight();
            } else {
                // Move forward
                pos = next;
            }
        }

        return 0;
    }
}
```

### Example: Complex Parsing

```java
import static aoc.utils.ParsingUtils.*;

public class DayZ {
    public int solve_pt1(List<String> input) {
        // Split into blocks by blank lines
        List<List<String>> blocks = splitBlocks(input);

        int total = 0;
        for (List<String> block : blocks) {
            // Extract numbers from text
            String line = block.get(0);
            List<Integer> nums = extractInts(line);

            // Parse coordinates
            if (line.contains(",")) {
                int[] coord = parseCoordinate("5,10");
            }

            // Parse ranges
            if (line.contains("-")) {
                int[] range = parseRange("1-100");
            }

            total += nums.stream().mapToInt(Integer::intValue).sum();
        }

        return total;
    }
}
```

## 🔄 Migration Guide

### Migrating Custom Coordinate Classes

If you have:
```java
public record Coordinate(int row, int col) {
    // custom methods
}
```

Replace with:
```java
import aoc.utils.Coordinate;
// Use directly - it's feature-complete!
```

### Migrating Custom Utils

If you have utility methods in your solution:
```java
public class Utils {
    public static boolean inBounds(int[] pos, char[][] grid) { ... }
    public static void printGrid(char[][] grid) { ... }
}
```

Replace with:
```java
import static aoc.utils.GridUtils.*;
// Use the shared utilities
```

## 🎨 Best Practices

1. **Use static imports** for frequently-used methods:
   ```java
   import static aoc.utils.GridUtils.*;
   import static aoc.utils.ParsingUtils.*;
   ```

2. **Combine utilities** for powerful one-liners:
   ```java
   char[][] grid = parseGrid(InputReader.readLines(2024, 12));
   printGrid(grid);
   ```

3. **Leverage Coordinate methods** instead of manual array math:
   ```java
   // Instead of: new int[]{row+1, col}
   // Use: coord.move(Direction.DOWN)
   ```

4. **Use ParsingUtils** instead of manual regex/splitting:
   ```java
   // Instead of: line.split(",").map(Integer::parseInt)
   // Use: parseCSVInts(line)
   ```

## 📚 Full API Reference

See the individual utility files for complete documentation:
- `src/main/java/aoc/utils/GridUtils.java`
- `src/main/java/aoc/utils/Coordinate.java`
- `src/main/java/aoc/utils/MathUtils.java`
- `src/main/java/aoc/utils/ParsingUtils.java`

Each method is documented with JavaDoc comments explaining parameters and return values.
