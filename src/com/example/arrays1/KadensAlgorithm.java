package com.example.arrays1;

import java.util.Arrays;

/**
 * Problem: Kadane's Algorithm - Maximum Subarray Sum
 *
 * Given an integer array nums, find the subarray with the largest sum
 * and return the sum of the elements present in that subarray.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *   Input:  nums = [2, 3, 5, -2, 7, -4]
 *   Output: 15
 *   Explanation: The subarray from index 0 to index 4 has the largest sum = 15
 *
 * Example 2:
 *   Input:  nums = [-2, -3, -7, -2, -10, -4]
 *   Output: -2
 *   Explanation: The element on index 0 or index 3 make up the largest sum
 *   when taken as a subarray.
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 */
public class KadensAlgorithm {

    /**
     * Returns the maximum sum of any contiguous subarray of nums.
     *
     * Algorithm (Kadane's):
     *   - Maintain a running sum of the current subarray.
     *   - Track the best sum seen so far in maxSum.
     *   - Whenever the running sum drops below zero, reset it to zero
     *     because any extension of a negative-sum subarray would only
     *     reduce the total of a freshly started subarray.
     *   - Initialising maxSum to Integer.MIN_VALUE ensures correctness
     *     for arrays that contain only negative numbers.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i< nums.length; i++) {
            sum += nums[i];

            if (sum > maxSum) {
                maxSum = sum;
            }

            // A negative running sum only hurts, so reset and start fresh.
            if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;

    }

    public static void main(String[] args) {
        KadensAlgorithm solution = new KadensAlgorithm();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement",
                new int[]{2, 3, 5, -2, 7, -4},
                15
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All negative numbers - return the largest (least negative) element",
                new int[]{-2, -3, -7, -2, -10, -4},
                -2
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Now your turn example - subarray in the middle wins",
                new int[]{-1, 2, 3, -1, 2, -6, 5},
                6
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single positive element",
                new int[]{5},
                5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single negative element",
                new int[]{-7},
                -7
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All positive numbers - whole array is the answer",
                new int[]{1, 2, 3, 4, 5},
                15
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Mix of positive, negative and zero values",
                new int[]{1, -1, 0, 1, -1},
                1
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
            KadensAlgorithm solution,
            String testName,
            int[] input,
            int expected
    ) {
        try {
            int actual = solution.maxSubArray(input);

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
