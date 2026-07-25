package com.example.binarysearch;

/**
 * Problem: Single Element in a Sorted Array
 *
 * You are given a sorted array consisting of only integers where every element
 * appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 *
 * Example 1:
 *   Input:  nums = [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
 *   Output: 4
 *   Explanation: Only the number 4 appears once in the array.
 *
 * Example 2:
 *   Input:  nums = [1, 1, 3, 5, 5]
 *   Output: 3
 *   Explanation: Only the number 3 appears once in the array.
 *
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   0 <= nums[i] <= 10^4
 *   nums is sorted in non-decreasing order.
 */
public class SingleElementInSortedArray {

    /**
     * Returns the unique element that appears only once.
     *
     * Algorithm (linear scan with edge checks):
     *   - If the array has one element, it is the answer.
     *   - Check if the first or last element is unique (no neighbor).
     *   - Scan the interior, returning the element whose neighbors differ.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public int singleNonDuplicate(int[] nums) {

        int n = nums.length;

        // Single element case.
        if (n == 1) {
            return nums[0];
        }

        // First element is unique.
        if (nums[0] != nums[1]) {
            return nums[0];
        }

        // Last element is unique.
        if (nums[n - 1] != nums[n - 2]) {
            return nums[n - 1];
        }

        // Scan interior elements for the lone value.
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        SingleElementInSortedArray solution = new SingleElementInSortedArray();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - single element in the middle",
                new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - single element in the middle",
                new int[]{1, 1, 3, 5, 5},
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Interactive quiz - single element at the end",
                new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7},
                7
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element array - only one element",
                new int[]{42},
                42
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element at the start",
                new int[]{1, 2, 2, 3, 3},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element at the end",
                new int[]{1, 1, 2, 2, 3},
                3
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
            SingleElementInSortedArray solution,
            String testName,
            int[] nums,
            int expected
    ) {
        try {
            int actual = solution.singleNonDuplicate(nums);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " -> result=" + actual
                );
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
