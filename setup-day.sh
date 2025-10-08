#!/bin/bash

# Advent of Code Day Setup Script
# Usage: ./setup-day.sh <year> <day> [--fetch]

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Parse arguments
if [ $# -lt 2 ]; then
    echo -e "${RED}Usage: $0 <year> <day> [--fetch]${NC}"
    echo "Example: $0 2024 12"
    echo "Example: $0 2024 12 --fetch (to auto-fetch input)"
    exit 1
fi

YEAR=$1
DAY=$2
FETCH_INPUT=false

# Check for --fetch flag
if [ $# -eq 3 ] && [ "$3" == "--fetch" ]; then
    FETCH_INPUT=true
fi

# Pad day with leading zero
DAY_PADDED=$(printf "%02d" $DAY)

echo -e "${GREEN}Setting up Advent of Code $YEAR - Day $DAY${NC}"

# Create directory structure
SRC_DIR="src/main/java/aoc/year$YEAR"
TEST_DIR="src/test/java/aoc/year$YEAR"
RESOURCE_DIR="src/main/resources/inputs/$YEAR"

echo -e "${YELLOW}Creating directories...${NC}"
mkdir -p "$SRC_DIR"
mkdir -p "$TEST_DIR"
mkdir -p "$RESOURCE_DIR"

# Generate source file from template
SRC_FILE="$SRC_DIR/Day$DAY_PADDED.java"
if [ -f "$SRC_FILE" ]; then
    echo -e "${YELLOW}Warning: $SRC_FILE already exists, skipping...${NC}"
else
    echo -e "${YELLOW}Creating $SRC_FILE...${NC}"
    sed -e "s/{{YEAR}}/$YEAR/g" \
        -e "s/{{DAY}}/$DAY/g" \
        -e "s/{{DAY_PADDED}}/$DAY_PADDED/g" \
        templates/Day.java.template > "$SRC_FILE"
    echo -e "${GREEN}✓ Created $SRC_FILE${NC}"
fi

# Generate test file from template
TEST_FILE="$TEST_DIR/Day${DAY_PADDED}Test.java"
if [ -f "$TEST_FILE" ]; then
    echo -e "${YELLOW}Warning: $TEST_FILE already exists, skipping...${NC}"
else
    echo -e "${YELLOW}Creating $TEST_FILE...${NC}"
    sed -e "s/{{YEAR}}/$YEAR/g" \
        -e "s/{{DAY}}/$DAY/g" \
        -e "s/{{DAY_PADDED}}/$DAY_PADDED/g" \
        templates/DayTest.java.template > "$TEST_FILE"
    echo -e "${GREEN}✓ Created $TEST_FILE${NC}"
fi

# Handle input files
INPUT_FILE="$RESOURCE_DIR/day$DAY_PADDED.txt"
TEST_INPUT_FILE="$RESOURCE_DIR/day${DAY_PADDED}_test.txt"

if [ "$FETCH_INPUT" == true ]; then
    echo -e "${YELLOW}Fetching input from Advent of Code...${NC}"

    # Check if AOC_SESSION is set
    if [ -z "$AOC_SESSION" ]; then
        echo -e "${RED}Error: AOC_SESSION environment variable not set${NC}"
        echo "Please set it with your Advent of Code session cookie:"
        echo "export AOC_SESSION='your_session_cookie_here'"
        exit 1
    fi

    # Fetch input using curl
    HTTP_CODE=$(curl -s -w "%{http_code}" \
        -H "Cookie: session=$AOC_SESSION" \
        -H "User-Agent: github.com/woutdeleu/AdventOfCode" \
        -o "$INPUT_FILE" \
        "https://adventofcode.com/$YEAR/day/$DAY/input")

    if [ "$HTTP_CODE" -eq 200 ]; then
        echo -e "${GREEN}✓ Input fetched successfully to $INPUT_FILE${NC}"
    else
        echo -e "${RED}✗ Failed to fetch input (HTTP $HTTP_CODE)${NC}"
        rm -f "$INPUT_FILE"
        exit 1
    fi
else
    # Create empty input file if it doesn't exist
    if [ ! -f "$INPUT_FILE" ]; then
        touch "$INPUT_FILE"
        echo -e "${GREEN}✓ Created empty $INPUT_FILE${NC}"
        echo -e "${YELLOW}  Remember to paste your puzzle input!${NC}"
    else
        echo -e "${YELLOW}Input file $INPUT_FILE already exists${NC}"
    fi
fi

# Create empty test input file if it doesn't exist
if [ ! -f "$TEST_INPUT_FILE" ]; then
    touch "$TEST_INPUT_FILE"
    echo -e "${GREEN}✓ Created empty $TEST_INPUT_FILE${NC}"
    echo -e "${YELLOW}  Remember to paste your test input!${NC}"
else
    echo -e "${YELLOW}Test input file $TEST_INPUT_FILE already exists${NC}"
fi

echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Setup complete! 🎄${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "Next steps:"
echo "1. Add test input to: $TEST_INPUT_FILE"
if [ "$FETCH_INPUT" == false ]; then
    echo "2. Add puzzle input to: $INPUT_FILE"
    echo "   Or run: ./setup-day.sh $YEAR $DAY --fetch"
fi
echo "3. Edit solution in: $SRC_FILE"
echo "4. Run with: cd src/main/java && java aoc.year$YEAR.Day$DAY_PADDED"
echo ""
