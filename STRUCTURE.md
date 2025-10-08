# Project Structure

This repository contains solutions for Advent of Code puzzles in Java, using **two different structures**:

## 📂 Directory Layout

```
AdventOfCode/
├── src/main/java/
│   ├── aoc/                          # NEW structure (Day 13+)
│   │   ├── Solution.java             # Base class for all solutions
│   │   ├── utils/
│   │   │   ├── InputReader.java      # Resource-based input reading
│   │   │   └── AOCClient.java        # Auto-fetch inputs from AOC
│   │   └── year2024/
│   │       └── Day13.java            # New format solutions
│   │
│   ├── year2024/                     # OLD structure (Days 1-12)
│   │   ├── Day1/
│   │   │   ├── Main.java             # Old format
│   │   │   └── input                 # Input in same directory
│   │   ├── Day2/
│   │   └── ...                       # Days 1-12 use this format
│   │
│   └── year2023/                     # 2023 solutions (old structure)
│
├── src/main/resources/
│   └── inputs/                       # NEW structure inputs
│       └── 2024/
│           ├── day13.txt             # Actual inputs (gitignored)
│           └── day13_test.txt        # Test inputs (committed)
│
├── src/test/java/
│   ├── aoc/year2024/                 # NEW structure tests
│   │   └── Day13Test.java
│   │
│   ├── year2024/                     # OLD structure tests
│   │   ├── Day1/MainTest.java
│   │   └── ...
│   │
│   └── year2023/                     # 2023 tests
│
├── templates/                        # Templates for new days
│   ├── Day.java.template
│   └── DayTest.java.template
│
├── setup-day.sh                      # Setup script for new days
├── README.md                         # Main documentation
├── QUICKSTART.md                     # Quick reference guide
└── pom.xml                           # Maven configuration
```

## 🔄 Structure Evolution

### Old Structure (Days 1-12, Year 2023)
- Each day in `yearXXXX/DayX/` folder
- `Main.java` with custom read methods
- Input files in same directory as code
- Manual file creation required

**Example:**
```
src/main/java/year2024/Day1/
  ├── Main.java
  └── input
```

### New Structure (Day 13+)
- Solutions in `aoc.yearXXXX` package
- Extends `Solution` base class
- Inputs in `resources/inputs/YYYY/`
- Auto-generated via `./setup-day.sh`

**Example:**
```
src/main/java/aoc/year2024/Day13.java
src/main/resources/inputs/2024/day13.txt
src/main/resources/inputs/2024/day13_test.txt
```

## 🎯 Why Two Structures?

**Old structure (Days 1-12):** Working solutions from before the refactoring. Keeping them as-is to avoid breaking existing code.

**New structure (Day 13+):** Improved workflow that eliminates 10 minutes of setup per day:
- Resource-based inputs (cleaner)
- Base class eliminates boilerplate
- Auto-generation via script
- Consistent package structure

## 📝 Adding New Days

Always use the new structure:

```bash
./setup-day.sh 2024 14
# or
./setup-day.sh 2024 14 --fetch
```

This creates:
- `src/main/java/aoc/year2024/Day14.java`
- `src/test/java/aoc/year2024/Day14Test.java`
- `src/main/resources/inputs/2024/day14.txt`
- `src/main/resources/inputs/2024/day14_test.txt`

## 🔍 Finding Solutions

### Old structure (Days 1-12, 2023):
```bash
# Run
mvn exec:java -Dexec.mainClass="year2024.Day1.Main"

# Test
mvn test -Dtest=year2024.Day1.MainTest
```

### New structure (Day 13+):
```bash
# Run
mvn exec:java -Dexec.mainClass="aoc.year2024.Day13"

# Test
mvn test -Dtest=Day13Test
```

## 📊 Current Progress

### 2024
- **Days 1-12**: Completed using old structure
- **Day 13+**: Use new structure going forward

### 2023
- Various days completed using old structure

## 🚀 Migration Note

Old solutions (Days 1-12) remain in their original structure for stability. All new solutions use the improved structure. Both structures are fully supported and functional.
