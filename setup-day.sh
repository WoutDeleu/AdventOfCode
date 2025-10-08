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

echo -e "${GREEN}Setting up Advent of Code $YEAR - Day $DAY${NC}"

# Create directory structure
SRC_DIR="src/main/java/year$YEAR/Day$DAY"
TEST_DIR="src/test/java/year$YEAR/Day$DAY"

echo -e "${YELLOW}Creating directories...${NC}"
mkdir -p "$SRC_DIR"
mkdir -p "$TEST_DIR"

# Generate source file from template
SRC_FILE="$SRC_DIR/Main.java"
if [ -f "$SRC_FILE" ]; then
    echo -e "${YELLOW}Warning: $SRC_FILE already exists, skipping...${NC}"
else
    echo -e "${YELLOW}Creating $SRC_FILE...${NC}"
    sed -e "s/{{YEAR}}/$YEAR/g" \
        -e "s/{{DAY}}/$DAY/g" \
        templates/Day.java.template > "$SRC_FILE"
    echo -e "${GREEN}✓ Created $SRC_FILE${NC}"
fi

# Generate test file from template
TEST_FILE="$TEST_DIR/MainTest.java"
if [ -f "$TEST_FILE" ]; then
    echo -e "${YELLOW}Warning: $TEST_FILE already exists, skipping...${NC}"
else
    echo -e "${YELLOW}Creating $TEST_FILE...${NC}"
    sed -e "s/{{YEAR}}/$YEAR/g" \
        -e "s/{{DAY}}/$DAY/g" \
        templates/DayTest.java.template > "$TEST_FILE"
    echo -e "${GREEN}✓ Created $TEST_FILE${NC}"
fi

# Handle input files
INPUT_FILE="$SRC_DIR/input"
TEST_INPUT_FILE="$TEST_DIR/input"

if [ "$FETCH_INPUT" == true ]; then
    echo -e "${YELLOW}Fetching input from Advent of Code...${NC}"

    # Check if AOC_SESSION is set
    if [ -z "$AOC_SESSION" ]; then
        echo -e "${RED}Error: AOC_SESSION environment variable not set${NC}"
        echo "Please set it with your Advent of Code session cookie:"
        echo "  export AOC_SESSION='your_session_cookie_here'"
        echo ""
        echo "To get your session cookie:"
        echo "  1. Log in to https://adventofcode.com"
        echo "  2. Open browser DevTools (F12)"
        echo "  3. Go to Application/Storage → Cookies"
        echo "  4. Copy the 'session' cookie value"
        exit 1
    fi

    # Fetch input using curl with retries
    echo -e "${YELLOW}  Fetching from: https://adventofcode.com/$YEAR/day/$DAY/input${NC}"

    MAX_RETRIES=3
    RETRY_COUNT=0
    SUCCESS=false

    while [ $RETRY_COUNT -lt $MAX_RETRIES ] && [ "$SUCCESS" == false ]; do
        if [ $RETRY_COUNT -gt 0 ]; then
            echo -e "${YELLOW}  Retry attempt $RETRY_COUNT/$MAX_RETRIES...${NC}"
            sleep 2
        fi

        HTTP_CODE=$(curl -s -w "%{http_code}" \
            --connect-timeout 10 \
            --max-time 30 \
            -H "Cookie: session=$AOC_SESSION" \
            -H "User-Agent: github.com/woutdeleu/AdventOfCode via setup-day.sh" \
            -o "$INPUT_FILE" \
            "https://adventofcode.com/$YEAR/day/$DAY/input" 2>&1)

        if [ "$HTTP_CODE" -eq 200 ]; then
            # Verify file is not empty and doesn't contain error message
            if [ -s "$INPUT_FILE" ] && ! grep -q "Please log in" "$INPUT_FILE" 2>/dev/null; then
                echo -e "${GREEN}✓ Input fetched successfully to $INPUT_FILE${NC}"
                SUCCESS=true
            else
                echo -e "${RED}✗ Received invalid response (empty or error page)${NC}"
                rm -f "$INPUT_FILE"
                ((RETRY_COUNT++))
            fi
        elif [ "$HTTP_CODE" -eq 404 ]; then
            echo -e "${RED}✗ Day $DAY not yet available for year $YEAR (HTTP 404)${NC}"
            rm -f "$INPUT_FILE"
            break
        elif [ "$HTTP_CODE" -eq 400 ]; then
            echo -e "${RED}✗ Invalid session cookie (HTTP 400)${NC}"
            echo "Please check your AOC_SESSION value"
            rm -f "$INPUT_FILE"
            break
        else
            echo -e "${RED}✗ Failed to fetch input (HTTP $HTTP_CODE)${NC}"
            rm -f "$INPUT_FILE"
            ((RETRY_COUNT++))
        fi
    done

    if [ "$SUCCESS" == false ]; then
        echo -e "${RED}Failed to fetch input after $MAX_RETRIES attempts${NC}"
        echo "Creating empty input file instead..."
        touch "$INPUT_FILE"
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

# Always create empty test input file (must be filled manually)
if [ ! -f "$TEST_INPUT_FILE" ]; then
    touch "$TEST_INPUT_FILE"
    echo -e "${GREEN}✓ Created empty $TEST_INPUT_FILE${NC}"
    echo -e "${YELLOW}  Add test input from puzzle examples manually${NC}"
else
    echo -e "${YELLOW}Test input file $TEST_INPUT_FILE already exists${NC}"
fi

echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Setup complete! 🎄${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "Next steps:"
echo "1. Add test input from puzzle examples to: $TEST_INPUT_FILE"
if [ "$FETCH_INPUT" == false ]; then
    echo "2. Add puzzle input to: $INPUT_FILE"
    echo "   Or run: ./setup-day.sh $YEAR $DAY --fetch"
fi
echo "3. Edit solution in: $SRC_FILE"
echo "4. Run tests: mvn test -Dtest=year$YEAR.Day$DAY.MainTest"
echo "5. Run solution: mvn exec:java -Dexec.mainClass=\"year$YEAR.Day$DAY.Main\""
echo ""
