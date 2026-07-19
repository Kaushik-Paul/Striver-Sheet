package com.example.greedyalgorithm;

import java.util.Arrays;

/**
 * Problem: Fractional Knapsack
 *
 * Given weights and values of N items, put them into a knapsack of
 * capacity W so that the total value in the knapsack is maximised.
 * Unlike the 0/1 knapsack, items can be broken into fractions here,
 * i.e. you may take any fraction of an item rather than having to
 * take it whole.
 *
 * Return the maximum total value (a double) that can be stored in
 * the knapsack.
 *
 * Example 1:
 *   Input:  values = [60, 100, 120], weights = [10, 20, 30], W = 50
 *   Output: 240.0
 *   Explanation: Ratios are 6, 5, 4. Take item 1 whole (60, wt 10),
 *   item 2 whole (100, wt 20) and 2/3 of item 3 (80, wt 20) for a
 *   total value of 60 + 100 + 80 = 240.
 *
 * Example 2:
 *   Input:  values = [60, 100], weights = [10, 20], W = 50
 *   Output: 160.0
 *   Explanation: Both items fit entirely (total weight 30 <= 50),
 *   so the full value 60 + 100 = 160 is taken.
 *
 * Constraints:
 *   1 <= n = val.length = wt.length <= 10^5
 *   1 <= capacity <= 10^9
 *   1 <= val[i], wt[i] <= 10000
 */
public class FractionalKnapsack {

    /**
     * Returns the maximum value that can be stored in a knapsack of the
     * given capacity when items may be taken fractionally.
     *
     * Algorithm (greedy by value/weight ratio):
     *   - Compute the value-per-unit-weight ratio for every item.
     *   - Sort items in descending order of this ratio so the most
     *     valuable-per-weight item is considered first.
     *   - Greedily take as much of each item as the remaining capacity
     *     allows: the whole item if it fits, otherwise a fraction that
     *     exactly fills the remaining capacity.
     *
     * Time complexity:  O(N log N)  (dominated by sorting)
     * Space complexity: O(N)       (ratio table)
     */
    public double fractionalKnapsack(int[] val, int[] wt, long cap) {
        int numberOfItems = val.length;
        double[][] ratio = new double[numberOfItems][2];

        // ratio[i] = {value/weight ratio, original index of the item}
        for (int i = 0; i < numberOfItems; i++) {
            ratio[i] = new double[] {(double) val[i] / wt[i], i};
        }

        long capacity = cap;
        double maxProfit = 0;

        // Sort by maximum profit ratio so the densest item is picked first.
        Arrays.sort(ratio, (a, b) -> Double.compare(b[0], a[0]));

        for (double[] item: ratio) {
            int i = (int) item[1];
            if (wt[i] <= capacity) {
                // Whole item fits - take it entirely.
                maxProfit += val[i];
                capacity -= wt[i];
            } else {
                // Only a fraction fits - take enough to fill the knapsack.
                maxProfit += val[i] * ((double) capacity / wt[i]);
                capacity = 0;
            }
        }

        // Round to 6 decimal places to match the expected output format.
        return Math.round(maxProfit * 1e6) / 1e6;
    }

    public static void main(String[] args) {
        FractionalKnapsack solution = new FractionalKnapsack();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - classic 3-item case",
                new int[]{60, 100, 120},
                new int[]{10, 20, 30},
                50,
                240.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - both items fit entirely",
                new int[]{60, 100},
                new int[]{10, 20},
                50,
                160.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single item larger than capacity - take a fraction",
                new int[]{60},
                new int[]{10},
                4,
                24.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single item smaller than capacity - take the whole item",
                new int[]{60},
                new int[]{10},
                15,
                60.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Capacity exactly equals total weight - take every item whole",
                new int[]{10, 20, 30},
                new int[]{2, 4, 6},
                12,
                60.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All items have the same ratio - order does not matter",
                new int[]{10, 20, 30},
                new int[]{1, 2, 3},
                4,
                40.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Higher ratio item has larger weight - still picked first",
                new int[]{10, 60},
                new int[]{2, 10},
                5,
                30.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Zero capacity - nothing can be taken",
                new int[]{60, 100, 120},
                new int[]{10, 20, 30},
                0,
                0.0
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
            FractionalKnapsack solution,
            String testName,
            int[] val,
            int[] wt,
            long cap,
            double expected
    ) {
        try {
            double actual = solution.fractionalKnapsack(val, wt, cap);

            if (Math.abs(actual - expected) < 1e-6) {
                System.out.println(
                        "[PASS] " + testName
                                + " | val=" + arrayToString(val)
                                + ", wt=" + arrayToString(wt)
                                + ", cap=" + cap
                                + " -> result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Values:   " + arrayToString(val));
            System.out.println("Weights:  " + arrayToString(wt));
            System.out.println("Capacity: " + cap);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Values:   " + arrayToString(val));
            System.out.println("Weights:  " + arrayToString(wt));
            System.out.println("Capacity: " + cap);
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

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}