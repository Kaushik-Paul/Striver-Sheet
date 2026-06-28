package com.example.arrays;

import java.util.Arrays;

/**
 * Problem: Best Time to Buy and Sell Stock
 *
 * Given an array arr of n integers, where arr[i] represents the price of
 * the stock on the ith day, determine the maximum profit achievable by
 * buying and selling the stock at most once.
 *
 * The stock must be purchased before it is sold, and both actions cannot
 * occur on the same day. If no profitable transaction exists, the maximum
 * profit is 0 (i.e. do nothing).
 *
 * Example 1:
 *   Input:  arr = [10, 7, 5, 8, 11, 9]
 *   Output: 6
 *   Explanation: Buy on day 3 (price = 5) and sell on day 5 (price = 11),
 *   profit = 11 - 5 = 6.
 *
 * Example 2:
 *   Input:  arr = [5, 4, 3, 2, 1]
 *   Output: 0
 *   Explanation: In this case, no transactions are made. Therefore, the
 *   maximum profit remains 0.
 *
 * Constraints:
 *   1 <= n <= 10^5
 *   0 <= arr[i] <= 10^4
 */
public class BuyAndSellStock {

    /**
     * Returns the maximum profit achievable from a single buy/sell
     * transaction, or 0 if no transaction yields a positive profit.
     *
     * Algorithm (single pass):
     *   - Track the lowest price seen so far in minBuy.
     *   - For each subsequent day, compute the profit of selling today
     *     after buying at minBuy and update the best profit.
     *   - Update minBuy whenever a lower price appears.
     *   - Initialising maxProfit to 0 reflects the "do nothing" option,
     *     so a strictly decreasing price series correctly returns 0.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public int stockBuySell(int[] arr, int n) {

        int minBuy = arr[0];
        int maxProfit = 0;

        for (int i = 1; i < n; i++) {
            int currentProfit = arr[i] - minBuy;

            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }

            if (arr[i] < minBuy) {
                minBuy = arr[i];
            }
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        BuyAndSellStock solution = new BuyAndSellStock();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement - profit 6",
                new int[]{10, 7, 5, 8, 11, 9},
                6
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Strictly decreasing prices - no transaction, profit 0",
                new int[]{5, 4, 3, 2, 1},
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Now your turn example - buy at 1, sell at 6, profit 5",
                new int[]{3, 8, 1, 4, 6, 2},
                5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element - no sell possible, profit 0",
                new int[]{5},
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two elements with profit",
                new int[]{1, 5},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All equal prices - no profit possible, profit 0",
                new int[]{5, 5, 5, 5},
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Mixed ups and downs - buy at 1, sell at 7, profit 6",
                new int[]{5, 3, 6, 4, 1, 7},
                6
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
            BuyAndSellStock solution,
            String testName,
            int[] input,
            int expected
    ) {
        try {
            int actual = solution.stockBuySell(input, input.length);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | input=" + Arrays.toString(input)
                                + ", result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Input:    " + Arrays.toString(input));
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Input:     " + Arrays.toString(input));
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
