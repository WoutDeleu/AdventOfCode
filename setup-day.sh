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
CHALLENGE_DIR="Challenges/$YEAR/Day$DAY"

echo -e "${YELLOW}Creating directories...${NC}"
mkdir -p "$SRC_DIR"
mkdir -p "$TEST_DIR"
mkdir -p "$CHALLENGE_DIR"

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
README_FILE="$CHALLENGE_DIR/README.md"

if [ "$FETCH_INPUT" == true ]; then
    echo -e "${YELLOW}Fetching from Advent of Code...${NC}"

    # Check if AOC_SESSION is set
    if [ -z "$AOC_SESSION" ]; then
        echo -e "${RED}Error: AOC_SESSION environment variable not set${NC}"
        echo ""

        # Check if setup-session.sh exists
        if [ -f "./setup-session.sh" ]; then
            echo -e "${YELLOW}Would you like to configure your session cookie now?${NC}"
            read -p "Run setup-session.sh? (y/n): " -n 1 -r
            echo ""

            if [[ $REPLY =~ ^[Yy]$ ]]; then
                # Run setup-session.sh
                ./setup-session.sh

                # Check if it succeeded by verifying AOC_SESSION is now set
                # Need to source the shell config to get the new variable
                if [ -f "$HOME/.zshrc" ]; then
                    source "$HOME/.zshrc"
                elif [ -f "$HOME/.bashrc" ]; then
                    source "$HOME/.bashrc"
                fi

                # Check again if AOC_SESSION is now set
                if [ -z "$AOC_SESSION" ]; then
                    echo -e "${RED}Session setup failed or was cancelled${NC}"
                    exit 1
                fi

                echo -e "${GREEN}Session configured! Continuing with setup...${NC}"
                echo ""
            else
                echo -e "${YELLOW}Setup cancelled. You can run ./setup-session.sh later.${NC}"
                exit 1
            fi
        else
            echo "Please set it with your Advent of Code session cookie:"
            echo "  export AOC_SESSION='your_session_cookie_here'"
            echo ""
            echo "To get your session cookie:"
            echo "  1. Log in to https://adventofcode.com"
            echo "  2. Open browser DevTools (F12)"
            echo "  3. Go to Application/Storage → Cookies"
            echo "  4. Copy the 'session' cookie value"
            echo ""
            echo -e "${YELLOW}Or run: ./setup-session.sh${NC}"
            exit 1
        fi
    fi

    # Fetch input using curl with retries
    echo -e "${YELLOW}  Fetching puzzle input...${NC}"

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
            if [ -s "$INPUT_FILE" ] && ! grep -q "Please log in" "$INPUT_FILE" 2>/dev/null; then
                echo -e "${GREEN}✓ Puzzle input fetched${NC}"
                SUCCESS=true
            else
                echo -e "${RED}✗ Invalid response${NC}"
                rm -f "$INPUT_FILE"
                ((RETRY_COUNT++))
            fi
        elif [ "$HTTP_CODE" -eq 404 ]; then
            echo -e "${RED}✗ Day $DAY not available yet (HTTP 404)${NC}"
            rm -f "$INPUT_FILE"
            break
        elif [ "$HTTP_CODE" -eq 400 ]; then
            echo -e "${RED}✗ Invalid session cookie (HTTP 400)${NC}"
            rm -f "$INPUT_FILE"

            # Offer to reconfigure session
            if [ -f "./setup-session.sh" ]; then
                echo ""
                echo -e "${YELLOW}Your session cookie appears to be invalid or expired.${NC}"
                echo -e "${YELLOW}Would you like to update it now?${NC}"
                read -p "Run setup-session.sh? (y/n): " -n 1 -r
                echo ""

                if [[ $REPLY =~ ^[Yy]$ ]]; then
                    ./setup-session.sh

                    # Reload session
                    if [ -f "$HOME/.zshrc" ]; then
                        source "$HOME/.zshrc"
                    elif [ -f "$HOME/.bashrc" ]; then
                        source "$HOME/.bashrc"
                    fi

                    if [ -z "$AOC_SESSION" ]; then
                        echo -e "${RED}Session setup failed or was cancelled${NC}"
                        break
                    fi

                    echo -e "${GREEN}Retrying with new session cookie...${NC}"
                    RETRY_COUNT=0  # Reset retry count to try again
                    continue
                fi
            fi
            break
        else
            echo -e "${RED}✗ Failed (HTTP $HTTP_CODE)${NC}"
            rm -f "$INPUT_FILE"
            ((RETRY_COUNT++))
        fi
    done

    if [ "$SUCCESS" == false ]; then
        echo -e "${RED}Failed to fetch puzzle input${NC}"
        touch "$INPUT_FILE"
    fi

    # Fetch puzzle description
    echo -e "${YELLOW}  Fetching puzzle description...${NC}"

    TEMP_HTML=$(mktemp)
    HTTP_CODE=$(curl -s -w "%{http_code}" \
        --connect-timeout 10 \
        --max-time 30 \
        -H "Cookie: session=$AOC_SESSION" \
        -H "User-Agent: github.com/woutdeleu/AdventOfCode via setup-day.sh" \
        -o "$TEMP_HTML" \
        "https://adventofcode.com/$YEAR/day/$DAY" 2>&1)

    if [ "$HTTP_CODE" -eq 200 ] && [ -s "$TEMP_HTML" ]; then
        # Extract the main article content and convert to markdown-ish format
        {
            echo "# Advent of Code $YEAR - Day $DAY"
            echo ""
            echo "**Source:** https://adventofcode.com/$YEAR/day/$DAY"
            echo ""
            echo "---"
            echo ""

            # Extract article content, clean up HTML, preserve code blocks
            sed -n '/<article/,/<\/article>/p' "$TEMP_HTML" | \
                sed -e 's/<article[^>]*>//g' -e 's/<\/article>//g' \
                    -e 's/<h2[^>]*>/## /g' -e 's/<\/h2>//g' \
                    -e 's/<p>/\n/g' -e 's/<\/p>/\n/g' \
                    -e 's/<pre><code>/\n```\n/g' -e 's/<\/code><\/pre>/\n```\n/g' \
                    -e 's/<code>/`/g' -e 's/<\/code>/`/g' \
                    -e 's/<em>\*/<strong>*/g' -e 's/\*<\/em>/*<\/strong>/g' \
                    -e 's/<em>/**/g' -e 's/<\/em>/**/g' \
                    -e 's/<strong>/**/g' -e 's/<\/strong>/**/g' \
                    -e 's/<ul>/\n/g' -e 's/<\/ul>/\n/g' \
                    -e 's/<li>/ - /g' -e 's/<\/li>//g' \
                    -e 's/<a[^>]*>//g' -e 's/<\/a>//g' \
                    -e 's/&lt;/</g' -e 's/&gt;/>/g' -e 's/&amp;/\&/g' \
                    -e 's/&quot;/"/g' -e 's/&#39;/'"'"'/g' \
                    -e 's/<[^>]*>//g' | \
                sed '/^[[:space:]]*$/d' | \
                sed 's/^[[:space:]]*//;s/[[:space:]]*$//'
        } > "$README_FILE"

        echo -e "${GREEN}✓ Puzzle description saved to $README_FILE${NC}"

        # Extract test input from first code block
        echo -e "${YELLOW}  Extracting test input...${NC}"

        TEST_INPUT=$(sed -n '/<pre><code>/,/<\/code><\/pre>/p' "$TEMP_HTML" | \
            head -1 | \
            sed -e 's/<pre><code>//g' -e 's/<\/code><\/pre>//g' \
                -e 's/&lt;/</g' -e 's/&gt;/>/g' -e 's/&amp;/\&/g' -e 's/&quot;/"/g' \
                -e 's/<em>//g' -e 's/<\/em>//g' | \
            sed 's/^[[:space:]]*//;s/[[:space:]]*$//')

        if [ -n "$TEST_INPUT" ] && [ ${#TEST_INPUT} -gt 10 ]; then
            echo "$TEST_INPUT" > "$TEST_INPUT_FILE"
            echo -e "${GREEN}✓ Test input extracted${NC}"
            echo -e "${YELLOW}  ⚠ Verify test input is correct!${NC}"
        else
            touch "$TEST_INPUT_FILE"
            echo -e "${YELLOW}⚠ Could not extract test input${NC}"
        fi
    else
        touch "$TEST_INPUT_FILE"
        echo -e "${YELLOW}⚠ Could not fetch puzzle description (HTTP $HTTP_CODE)${NC}"
    fi

    rm -f "$TEMP_HTML"

else
    # Create empty files
    if [ ! -f "$INPUT_FILE" ]; then
        touch "$INPUT_FILE"
        echo -e "${GREEN}✓ Created empty $INPUT_FILE${NC}"
        echo -e "${YELLOW}  Paste your puzzle input here${NC}"
    fi

    if [ ! -f "$TEST_INPUT_FILE" ]; then
        touch "$TEST_INPUT_FILE"
        echo -e "${GREEN}✓ Created empty $TEST_INPUT_FILE${NC}"
        echo -e "${YELLOW}  Add test input from examples${NC}"
    fi

    if [ ! -f "$README_FILE" ]; then
        echo "# Advent of Code $YEAR - Day $DAY" > "$README_FILE"
        echo "" >> "$README_FILE"
        echo "**Source:** https://adventofcode.com/$YEAR/day/$DAY" >> "$README_FILE"
        echo -e "${GREEN}✓ Created $README_FILE${NC}"
        echo -e "${YELLOW}  Run with --fetch to download puzzle description${NC}"
    fi
fi

echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Setup complete! 🎄${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "Files created:"
echo "  📝 README:   $README_FILE"
echo "  💻 Solution: $SRC_FILE"
echo "  🧪 Tests:    $TEST_FILE"
echo "  📥 Input:    $INPUT_FILE"
echo "  📋 Test In:  $TEST_INPUT_FILE"
echo ""
echo "Next steps:"
echo "1. Read puzzle:  cat $README_FILE"
echo "2. Verify test input in: $TEST_INPUT_FILE"
if [ "$FETCH_INPUT" == false ]; then
    echo "3. Add puzzle input to: $INPUT_FILE"
    echo "   Or run: ./setup-day.sh $YEAR $DAY --fetch"
fi
echo "3. Edit solution: $SRC_FILE"
echo "4. Run tests: mvn test -Dtest=year$YEAR.Day$DAY.MainTest"
echo "5. Run: mvn exec:java -Dexec.mainClass=\"year$YEAR.Day$DAY.Main\""
echo ""
