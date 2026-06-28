package com.example.arrays1;

import java.util.Arrays;

/**
 * Problem: Sort an array of 0's, 1's and 2's
 *
 * Given an array nums consisting of only 0, 1, or 2, sort the array in
 * non-decreasing order. The sorting must be done in-place, without
 * making a copy of the original array.
 *
 * Example 1:
 *   Input:  nums = [1, 0, 2, 1, 0]
 *   Output: [0, 0, 1, 1, 2]
 *
 * Example 2:
 *   Input:  nums = [0, 0, 1, 1, 1]
 *   Output: [0, 0, 1, 1, 1]
 *
 * Constraints:
 *   1 <= nums.length <= 10^6
 *   nums[i] is 0, 1, or 2.
 */
public class SortArrays0s1s2s {

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * Sorts the array in-place using the Dutch National Flag algorithm.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public void sortZeroOneTwo(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                // Don't advance mid after swapping in nums[high] - it hasn't
                // been inspected yet.
                swap(nums, mid, high);
                high--;
            }
        }
    }

    public static void main(String[] args) {
        SortArrays0s1s2s solution = new SortArrays0s1s2s();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement",
                new int[]{1, 0, 2, 1, 0},
                new int[]{0, 0, 1, 1, 2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Already sorted array",
                new int[]{0, 0, 1, 1, 1},
                new int[]{0, 0, 1, 1, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Reverse sorted array",
                new int[]{2, 2, 1, 1, 0},
                new int[]{0, 1, 1, 2, 2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All elements are the same (all 1's)",
                new int[]{1, 1, 1},
                new int[]{1, 1, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element (0)",
                new int[]{0},
                new int[]{0}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two elements out of order",
                new int[]{2, 0},
                new int[]{0, 2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Mix with duplicates (turn-it-yourself case)",
                new int[]{1, 1, 2, 2, 1},
                new int[]{1, 1, 1, 2, 2}
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
            SortArrays0s1s2s solution,
            String testName,
            int[] input,
            int[] expected
    ) {
        int[] actual = input.clone();

        try {
            solution.sortZeroOneTwo(actual);

            if (Arrays.equals(actual, expected)) {
                System.out.println(
                        "[PASS] " + testName
                                + " | input=" + Arrays.toString(input)
                                + ", result=" + Arrays.toString(actual)
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Input:    " + Arrays.toString(input));
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(actual));
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
