package com.example.arrays1;

import java.util.Arrays;

/**
 * Problem: Next Permutation
 *
 * A permutation of an array of integers is an arrangement of its members
 * into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations
 * of arr: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1].
 *
 * The next permutation of an array of integers is the next lexicographically
 * greater permutation of its integers. More formally, if all the permutations
 * of the array are sorted in one order, then the next permutation of that
 * array is the permutation that follows it in the sorted order.
 *
 * If such arrangement is not possible (i.e., the array is the last
 * permutation), then rearrange it to the lowest possible order (i.e.,
 * sorted in ascending order).
 *
 * The rearrangement must be done in-place using only constant extra memory.
 *
 * Example 1:
 *   Input:  nums = [1,2,3]
 *   Output: [1,3,2]
 *
 * Example 2:
 *   Input:  nums = [3,2,1]
 *   Output: [1,2,3]
 *
 * Constraints:
 *   1 <= nums.length <= 100
 *   0 <= nums[i] <= 100
 */
public class NextPermutation {

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    /**
     * Rearranges nums into its lexicographically next permutation in-place.
     *
     * Algorithm:
     *   1. Find the pivot: the rightmost index i where nums[i] < nums[i+1].
     *      If none exists, the array is the last permutation.
     *   2. Swap nums[pivot] with the smallest element to its right that is
     *      strictly greater than it.
     *   3. Reverse the suffix to the right of the pivot.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int pivot = -1;

        // Step 1: Find the rightmost index where nums[i] < nums[i+1].
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // No pivot means the array is fully descending - wrap to the first
        // permutation by reversing the whole array.
        if (pivot == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 2: Swap the pivot with the smallest element to its right
        // that is strictly greater than it.
        for (int i = n - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                swap(nums, pivot, i);
                break;
            }
        }

        // Step 3: Reverse the suffix after the pivot to get the smallest
        // possible tail, completing the next permutation.
        reverse(nums, pivot + 1, n - 1);
    }

    public static void main(String[] args) {
        NextPermutation solution = new NextPermutation();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement",
                new int[]{1, 2, 3},
                new int[]{1, 3, 2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Last permutation wraps to first",
                new int[]{3, 2, 1},
                new int[]{1, 2, 3}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Duplicate elements",
                new int[]{1, 1, 5},
                new int[]{1, 5, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Pivot in the middle",
                new int[]{1, 2, 5, 4, 3},
                new int[]{1, 3, 2, 4, 5}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element",
                new int[]{7},
                new int[]{7}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two elements in descending order",
                new int[]{2, 1},
                new int[]{1, 2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Larger array with duplicates and mid pivot",
                new int[]{2, 3, 1, 3, 3},
                new int[]{2, 3, 3, 1, 3}
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
            NextPermutation solution,
            String testName,
            int[] input,
            int[] expected
    ) {
        int[] actual = input.clone();

        try {
            solution.nextPermutation(actual);

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
