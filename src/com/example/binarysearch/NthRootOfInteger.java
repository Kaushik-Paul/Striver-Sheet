package com.example.binarysearch;

/**
 * Problem: Find Nth root of a number
 *
 * Given two positive integers N and M, find the Nth root of M, i.e. an
 * integer R such that R^N = M. If no such integer exists, return -1.
 *
 * Example 1:
 *   Input:  N = 3, M = 27
 *   Output: 3
 *   Explanation: 3^3 = 27, so the cube root of 27 is 3.
 *
 * Example 2:
 *   Input:  N = 4, M = 69
 *   Output: -1
 *   Explanation: No integer R satisfies R^4 = 69 (1^4 = 1, 2^4 = 16,
 *   3^4 = 81), so return -1.
 *
 * Constraints:
 *   1 <= N <= 30
 *   1 <= M <= 10^9
 */
public class NthRootOfInteger {

    /**
     * Computes b^exponent using binary exponentiation.
     *
     * Time complexity:  O(log exponent)
     * Space complexity: O(1)
     */
    private long Pow(int b, int exponent) {
        long answer = 1;
        long base = b;

        while (exponent > 0) {
            // Odd exponent: multiply one factor of base into the answer.
            if (exponent % 2 == 1) {
                exponent--;
                answer *= base;
            } else {
                // Even exponent: square the base and halve the exponent.
                exponent /= 2;
                base *= base;
            }
        }

        return answer;
    }

    /**
     * Returns the Nth root of M if it is an integer, otherwise -1.
     *
     * Algorithm (linear search):
     *   - Iterate candidate roots from 1 upward.
     *   - If candidate^N equals M, return the candidate.
     *   - If candidate^N exceeds M, no larger candidate can match, so stop.
     *
     * Time complexity:  O(M * log N)  (M candidates, each raised in O(log N))
     * Space complexity: O(1)
     */
    public int NthRoot(int N, int M) {
        for (int i = 1; i <= M; i++) {
            long val = Pow(i, N);

            if (val == M) {
                return i;
            } else if (val > M) {
                // Exceeded M - further candidates only grow, so stop early.
                break;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        NthRootOfInteger solution = new NthRootOfInteger();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - cube root of 27",
                3, 27, 3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - 4th root of 69 does not exist",
                4, 69, -1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Interactive quiz - 4th root of 81 is 3",
                4, 81, 3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Nth root of 1 is always 1",
                10, 1, 1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Square root of a perfect square - 2nd root of 64",
                2, 64, 8
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Root larger than any integer - 2nd root of 63 does not exist",
                2, 63, -1
        );

        System.out.println();
        System.out.println("========================================");
        System.out.println(
                "Test summary: " + passedTests + "/" + totalTests + " passed"
        );
        System.out.println("========================================");

        if (passedTests != totalTests) {
            throw new AssertionError(
                    (totalTests - passedTests) + " test(s) failed."
            );
        }
    }

    private static int runTest(
            NthRootOfInteger solution,
            String testName,
            int N,
            int M,
            int expected
    ) {
        try {
            int actual = solution.NthRoot(N, M);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | N=" + N + ", M=" + M
                                + " -> result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("N:        " + N);
            System.out.println("M:        " + M);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("N:        " + N);
            System.out.println("M:        " + M);
            System.out.println(
                    "Exception: "
                            + exception.getClass().getSimpleName()
                            + ": "
                            + exception.getMessage()
            );
            System.out.println();
            return 0;
        }
    }
}
