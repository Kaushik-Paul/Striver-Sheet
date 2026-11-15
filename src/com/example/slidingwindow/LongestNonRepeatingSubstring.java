package com.example.slidingwindow;

import java.util.Arrays;

/**
 * Problem: Longest Substring Without Repeating Characters
 *
 * Given a string s, find the length of the longest substring without
 * repeating characters.
 *
 * Example 1:
 *   Input:  s = "abcddabac"
 *   Output: 4
 *   Explanation: The answer is "abcd", with a length of 4.
 *
 * Example 2:
 *   Input:  s = "aaabbbccc"
 *   Output: 2
 *   Explanation: The answers are "ab", "bc". Both have maximum length 2.
 *
 * Constraints:
 *   0 <= s.length()
 *   s consists of printable ASCII characters.
 */
public class LongestNonRepeatingSubstring {

    /**
     * Sliding window over the string, remembering the last index of every
     * character so the left edge can jump past a repeat in O(1).
     *
     * Time complexity:  O(n)
     * Space complexity: O(1), the table is fixed at 256 entries
     */
    public int longestNonRepeatingSubstring(String s) {
        int n = s.length();
        int HashLen = 256;

        int[] lastSeen = new int[HashLen];

        // Fill every element with -1
        Arrays.fill(lastSeen, -1);
        int leftPointer = 0;
        int rightPointer = 0;
        int maxSum = 0;

        while(rightPointer < n) {
            if (lastSeen[s.charAt(rightPointer)] != -1) {
                // Never move the left edge backwards; the repeat may already
                // lie outside the current window.
                leftPointer = Math.max(leftPointer, lastSeen[s.charAt(rightPointer)] + 1);
            }

            int currentLength = rightPointer - leftPointer + 1;
            maxSum = Math.max(maxSum, currentLength);

            lastSeen[s.charAt(rightPointer)] = rightPointer;

            rightPointer++;
        }


        return maxSum;

    }

    public static void main(String[] args) {
        LongestNonRepeatingSubstring solution = new LongestNonRepeatingSubstring();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 - repeating characters at the end",
                "abcddabac",
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 - grouped identical characters",
                "aaabbbccc",
                2
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All same characters",
                "aaaa",
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Empty string",
                "",
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single character",
                "a",
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Window must shrink by more than one step",
                "dvdf",
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Repeat whose previous index is behind the window start",
                "abba",
                2
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All distinct characters",
                "abcdefghijklmnopqrstuvwxyz",
                26
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
            LongestNonRepeatingSubstring solution,
            String testName,
            String s,
            int expected
    ) {
        try {
            int actual = solution.longestNonRepeatingSubstring(s);

            if (actual == expected) {
                System.out.println("[PASS] " + testName);
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
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