package aoc.utils;

import java.util.List;

/**
 * Common mathematical utilities for Advent of Code puzzles
 */
public class MathUtils {

    /**
     * Greatest Common Divisor using Euclidean algorithm
     */
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    /**
     * Least Common Multiple
     */
    public static long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    /**
     * LCM of multiple numbers
     */
    public static long lcm(long... numbers) {
        if (numbers.length == 0) return 0;
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }

    /**
     * LCM of a list of numbers
     */
    public static long lcm(List<Long> numbers) {
        if (numbers.isEmpty()) return 0;
        long result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }

    /**
     * Check if a number is prime
     */
    public static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculate factorial
     */
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial not defined for negative numbers");
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculate binomial coefficient (n choose k)
     */
    public static long binomial(int n, int k) {
        if (k > n || k < 0) return 0;
        if (k == 0 || k == n) return 1;

        // Use symmetry property
        if (k > n - k) {
            k = n - k;
        }

        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }

    /**
     * Calculate power with modulo
     */
    public static long modPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }

    /**
     * Clamp a value between min and max
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Clamp a value between min and max
     */
    public static long clamp(long value, long min, long max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Sign function (-1, 0, or 1)
     */
    public static int sign(int value) {
        return Integer.compare(value, 0);
    }

    /**
     * Sign function (-1, 0, or 1)
     */
    public static int sign(long value) {
        return Long.compare(value, 0);
    }

    /**
     * Sum of integers from 1 to n (Gauss formula)
     */
    public static long sumTo(long n) {
        return n * (n + 1) / 2;
    }

    /**
     * Sum of integers from a to b (inclusive)
     */
    public static long sumRange(long a, long b) {
        if (a > b) {
            long temp = a;
            a = b;
            b = temp;
        }
        return sumTo(b) - sumTo(a - 1);
    }

    /**
     * Check if ranges overlap
     */
    public static boolean rangesOverlap(long a1, long a2, long b1, long b2) {
        return Math.max(a1, b1) <= Math.min(a2, b2);
    }

    /**
     * Calculate Manhattan distance
     */
    public static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * Calculate Euclidean distance
     */
    public static double euclideanDistance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Modulo that works correctly with negative numbers
     */
    public static long mod(long a, long m) {
        return ((a % m) + m) % m;
    }

    /**
     * Find all divisors of a number
     */
    public static List<Long> divisors(long n) {
        List<Long> divisors = new java.util.ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        divisors.sort(Long::compareTo);
        return divisors;
    }
}
