package com.example.greedyalgorithm;

/**
 * Problem: Minimum Coins (Coin Change)
 *
 * Given an integer array of coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * Return the fewest number of coins needed to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins,
 * return -1. There are infinite numbers of coins of each type.
 *
 * Example 1:
 *   Input:  coins = [1, 2, 5], amount = 11
 *   Output: 3
 *   Explanation: 11 = 5 + 5 + 1. We need 3 coins to make up the amount 11.
 *
 * Example 2:
 *   Input:  coins = [2, 5], amount = 3
 *   Output: -1
 *   Explanation: It is not possible to make amount 3 with coins 2 and 5.
 *
 * Constraints:
 *   1 <= coins.length <= 12
 *   1 <= coins[i] <= 2^31 - 1
 *   0 <= amount <= 10^4
 */
public class MiniMumCoins {

    /**
     * Returns the minimum number of coins needed to make the given amount.
     *
     * This is a brute-force recursive approach exploring all combinations.
     * For each coin, we either take it (and stay at the same index because
     * coins are unlimited) or skip it and move to the next coin.
     *
     * Time complexity:  O(2^N) in the worst case (exponential)
     * Space complexity: O(amount) call-stack depth
     */
    public int MinimumCoins(int[] coins, int amount) {
        int n = coins.length;

        int answer = findSmallest(coins, n - 1, amount);

        if (answer >= Integer.MAX_VALUE) {
            return -1;
        }

        return answer;
    }

    private int findSmallest(int[] coins, int index, int remainingAmount) {
        if (index == 0) {
            if (remainingAmount % coins[index] == 0) {
                return remainingAmount / coins[index];
            } else {
                return Integer.MAX_VALUE;
            }
        }

        // Option 1: do not use coins[index] at all.
        int notTaken = findSmallest(coins, index - 1, remainingAmount);

        // Option 2: use coins[index] once (can be reused in deeper calls).
        int taken = Integer.MAX_VALUE;

        if (coins[index] <= remainingAmount) {
            taken = 1 + findSmallest(coins, index, remainingAmount - coins[index]);
        }

        return Math.min(notTaken, taken);

    }

    public static void main(String[] args) {
        MiniMumCoins solution = new MiniMumCoins();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - coins [1,2,5] amount 11",
                new int[]{1, 2, 5},
                11,
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - impossible amount",
                new int[]{2, 5},
                3,
                -1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Interactive quiz - coin larger than amount",
                new int[]{10},
                5,
                -1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single exact coin match",
                new int[]{5},
                5,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Multiple coins of same denomination",
                new int[]{2},
                8,
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Optimal uses non-greedy combination",
                new int[]{1, 3, 4},
                6,
                2
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
            MiniMumCoins solution,
            String testName,
            int[] coins,
            int amount,
            int expected
    ) {
        try {
            int actual = solution.MinimumCoins(coins, amount);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " -> result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Coins:    " + java.util.Arrays.toString(coins));
            System.out.println("Amount:   " + amount);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Coins:    " + java.util.Arrays.toString(coins));
            System.out.println("Amount:   " + amount);
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
