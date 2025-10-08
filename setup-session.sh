#!/bin/bash

# Advent of Code Session Cookie Setup Script
# Simplifies the process of configuring your AOC session cookie

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}╔════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║  Advent of Code - Session Cookie Setup        ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════╝${NC}"
echo ""

# Check if session cookie is already set
if [ -n "$AOC_SESSION" ]; then
    echo -e "${YELLOW}⚠ AOC_SESSION is already set in your current session${NC}"
    echo -e "${YELLOW}Current value: ${AOC_SESSION:0:10}...${NC}"
    echo ""
    read -p "Do you want to update it? (y/n): " -n 1 -r
    echo ""
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        echo -e "${GREEN}Setup cancelled. Keeping existing session cookie.${NC}"
        exit 0
    fi
fi

# Instructions
echo -e "${BLUE}📋 How to get your session cookie:${NC}"
echo ""
echo "1. Open https://adventofcode.com in your browser"
echo "2. Log in to your account"
echo "3. Open Developer Tools (F12 or Cmd+Option+I)"
echo "4. Go to: Application/Storage → Cookies → https://adventofcode.com"
echo "5. Find the 'session' cookie and copy its VALUE"
echo ""
echo -e "${YELLOW}Note: Your session cookie is like a password - keep it private!${NC}"
echo ""

# Prompt for session cookie
read -p "Paste your session cookie here: " SESSION_COOKIE

# Validate cookie format (basic check)
if [ -z "$SESSION_COOKIE" ]; then
    echo -e "${RED}✗ Error: Session cookie cannot be empty${NC}"
    exit 1
fi

# Remove quotes if user accidentally included them
SESSION_COOKIE=$(echo "$SESSION_COOKIE" | tr -d '"' | tr -d "'")

# Basic validation - AOC session cookies are typically 128 hex characters
if [ ${#SESSION_COOKIE} -lt 96 ]; then
    echo -e "${YELLOW}⚠ Warning: Session cookie seems short (${#SESSION_COOKIE} chars)${NC}"
    echo -e "${YELLOW}AOC session cookies are usually 128 characters${NC}"
    read -p "Continue anyway? (y/n): " -n 1 -r
    echo ""
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        echo -e "${RED}Setup cancelled.${NC}"
        exit 1
    fi
fi

# Detect shell configuration file
SHELL_CONFIG=""
if [ -n "$ZSH_VERSION" ] || [ -f "$HOME/.zshrc" ]; then
    SHELL_CONFIG="$HOME/.zshrc"
elif [ -n "$BASH_VERSION" ] || [ -f "$HOME/.bashrc" ]; then
    SHELL_CONFIG="$HOME/.bashrc"
elif [ -f "$HOME/.bash_profile" ]; then
    SHELL_CONFIG="$HOME/.bash_profile"
else
    echo -e "${YELLOW}⚠ Could not detect shell configuration file${NC}"
    read -p "Enter path to your shell config file (e.g., ~/.zshrc): " SHELL_CONFIG
    SHELL_CONFIG="${SHELL_CONFIG/#\~/$HOME}"
fi

echo ""
echo -e "${BLUE}Detected shell config: ${SHELL_CONFIG}${NC}"

# Check if AOC_SESSION already exists in the file
if grep -q "export AOC_SESSION=" "$SHELL_CONFIG" 2>/dev/null; then
    echo -e "${YELLOW}⚠ Found existing AOC_SESSION in ${SHELL_CONFIG}${NC}"

    # Create backup
    BACKUP_FILE="${SHELL_CONFIG}.backup.$(date +%Y%m%d_%H%M%S)"
    cp "$SHELL_CONFIG" "$BACKUP_FILE"
    echo -e "${GREEN}✓ Created backup: ${BACKUP_FILE}${NC}"

    # Remove old AOC_SESSION lines
    if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        sed -i '' '/export AOC_SESSION=/d' "$SHELL_CONFIG"
    else
        # Linux
        sed -i '/export AOC_SESSION=/d' "$SHELL_CONFIG"
    fi
    echo -e "${GREEN}✓ Removed old AOC_SESSION${NC}"
fi

# Add new session cookie to config file
echo "" >> "$SHELL_CONFIG"
echo "# Advent of Code session cookie (added by setup-session.sh)" >> "$SHELL_CONFIG"
echo "export AOC_SESSION='$SESSION_COOKIE'" >> "$SHELL_CONFIG"

echo -e "${GREEN}✓ Added AOC_SESSION to ${SHELL_CONFIG}${NC}"

# Export for current session
export AOC_SESSION="$SESSION_COOKIE"
echo -e "${GREEN}✓ Exported AOC_SESSION for current session${NC}"

# Test the session cookie
echo ""
echo -e "${BLUE}Testing session cookie...${NC}"

TEST_RESPONSE=$(curl -s -w "%{http_code}" -o /dev/null \
    -H "Cookie: session=$AOC_SESSION" \
    -H "User-Agent: github.com/woutdeleu/AdventOfCode via setup-session.sh" \
    "https://adventofcode.com/2024/day/1/input")

if [ "$TEST_RESPONSE" -eq 200 ]; then
    echo -e "${GREEN}✓ Session cookie is valid!${NC}"
elif [ "$TEST_RESPONSE" -eq 400 ]; then
    echo -e "${RED}✗ Invalid session cookie (HTTP 400)${NC}"
    echo -e "${YELLOW}Please check that you copied the correct value${NC}"
    exit 1
elif [ "$TEST_RESPONSE" -eq 404 ]; then
    echo -e "${YELLOW}⚠ Cannot verify (HTTP 404 - Day 1 may not be available)${NC}"
    echo -e "${YELLOW}But your session cookie format looks correct${NC}"
else
    echo -e "${YELLOW}⚠ Unexpected response (HTTP $TEST_RESPONSE)${NC}"
    echo -e "${YELLOW}Your session cookie may still work${NC}"
fi

echo ""
echo -e "${GREEN}╔════════════════════════════════════════════════╗${NC}"
echo -e "${GREEN}║  ✓ Setup Complete!                            ║${NC}"
echo -e "${GREEN}╚════════════════════════════════════════════════╝${NC}"
echo ""
echo -e "${BLUE}Next steps:${NC}"
echo "1. Open a new terminal (or run: source $SHELL_CONFIG)"
echo "2. Use setup-day.sh with --fetch flag:"
echo "   ${GREEN}./setup-day.sh 2024 13 --fetch${NC}"
echo ""
echo -e "${YELLOW}Security reminder: Never commit your session cookie to git!${NC}"
