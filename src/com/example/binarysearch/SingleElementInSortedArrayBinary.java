package com.example.binarysearch;

/**
 * Problem: Single Element in a Sorted Array (Binary Search approach)
 *
 * You are given a sorted array consisting of only integers where every element
 * appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 *
 * This implementation uses binary search for O(log n) performance.
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
public class SingleElementInSortedArrayBinary {

    /**
     * Returns the unique element that appears only once.
     *
     * Algorithm (binary search on pair symmetry):
     *   - If the array has one element, it is the answer.
     *   - Check if the first or last element is unique (no neighbor).
     *   - Binary-search the interior [1, n-2].
     *   - If nums[mid] is unique, return it.
     *   - In a fully paired array, pairs start at even indices (0, 2, 4, ...).
     *     When the single element is on the right, the symmetry breaks:
     *     mid lands on the second element of a pair when mid is odd,
     *     or on the first element of a pair when mid is even.
     *     In that case, the single element is to the right -> low = mid + 1.
     *     Otherwise, it is to the left -> high = mid - 1.
     *
     * Time complexity:  O(log n)
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

        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Found the lone element.
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            // Eliminate the half that remains properly paired.
            // When mid is odd and matches the left neighbor,
            // or mid is even and matches the right neighbor,
            // the left side is still intact, so the single element must be on the right.
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) ||
                    (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        SingleElementInSortedArrayBinary solution = new SingleElementInSortedArrayBinary();

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
            SingleElementInSortedArrayBinary solution,
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
