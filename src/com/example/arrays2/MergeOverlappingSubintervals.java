package com.example.arrays2;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Merge Overlapping Subintervals
 *
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge
 * all overlapping intervals and return an array of the non-overlapping
 * intervals that cover all the intervals in the input. The intervals may
 * be returned in any order.
 *
 * Example 1:
 *   Input:  intervals = [[1,5],[3,6],[8,10],[15,18]]
 *   Output: [[1,6],[8,10],[15,18]]
 *   Explanation: [1,5] and [3,6] overlap, so they are merged into [1,6].
 *
 * Example 2:
 *   Input:  intervals = [[5,7],[1,3],[4,6],[8,10]]
 *   Output: [[1,3],[4,7],[8,10]]
 *   Explanation: [4,6] and [5,7] overlap and are merged into [4,7].
 *
 * Constraints:
 *   1 &lt;= intervals.length &lt;= 10^5
 *   0 &lt;= start_i &lt;= end_i &lt;= 10^5
 */
public class MergeOverlappingSubintervals {

    /**
     * Sorts intervals by start time, then merges any that overlap or touch.
     *
     * Time complexity:  O(n log n)
     * Space complexity: O(n)
     */
    public List<List<Integer>> mergeOverlap(List<List<Integer>> intervals) {

        List<List<Integer>> result = new ArrayList<>();

        if (intervals.size() == 0) {
            return result;
        }

        // Sort array based on start time
        intervals.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));

        // Add the first intervals
        result.add(new ArrayList<>(intervals.get(0)));

        // Now compare through remaining items
        for (int i = 1; i < intervals.size(); i++) {
            List<Integer> currentInterval = intervals.get(i);
            List<Integer> lastInterval = result.get(result.size() - 1);

            // If overlapping
            if (currentInterval.get(0) <= lastInterval.get(1)) {
                lastInterval.set(1, Math.max(lastInterval.get(1), currentInterval.get(1)));
            } else {
                result.add(new ArrayList<>(currentInterval));
            }
        }


        return result;

    }

    public static void main(String[] args) {
        MergeOverlappingSubintervals solution = new MergeOverlappingSubintervals();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement",
                intervals(new int[][]{{1, 5}, {3, 6}, {8, 10}, {15, 18}}),
                intervals(new int[][]{{1, 6}, {8, 10}, {15, 18}})
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - unsorted input",
                intervals(new int[][]{{5, 7}, {1, 3}, {4, 6}, {8, 10}}),
                intervals(new int[][]{{1, 3}, {4, 7}, {8, 10}})
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single interval",
                intervals(new int[][]{{2, 4}}),
                intervals(new int[][]{{2, 4}})
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Empty input",
                intervals(new int[][]{}),
                intervals(new int[][]{})
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All intervals merge into one",
                intervals(new int[][]{{1, 4}, {2, 5}, {3, 6}}),
                intervals(new int[][]{{1, 6}})
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Touching intervals are merged (end equals next start)",
                intervals(new int[][]{{1, 4}, {4, 5}}),
                intervals(new int[][]{{1, 5}})
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Contained interval is absorbed",
                intervals(new int[][]{{1, 10}, {2, 3}, {4, 5}}),
                intervals(new int[][]{{1, 10}})
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
            MergeOverlappingSubintervals solution,
            String testName,
            List<List<Integer>> intervals,
            List<List<Integer>> expected
    ) {
        try {
            List<List<Integer>> actual = solution.mergeOverlap(intervals);

            if (expected.equals(actual)) {
                System.out.println("[PASS] " + testName);
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
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

    private static List<List<Integer>> intervals(int[][] values) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] interval : values) {
            List<Integer> pair = new ArrayList<>();
            for (int value : interval) {
                pair.add(value);
            }
            result.add(pair);
        }
        return result;
    }
}
