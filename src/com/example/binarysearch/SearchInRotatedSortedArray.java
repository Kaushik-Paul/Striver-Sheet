package com.example.binarysearch;

/**
 * Problem: Search in Rotated Sorted Array
 *
 * Given an integer array nums sorted in ascending order (with distinct values)
 * and a target value k. The array is rotated at some unknown pivot point.
 * Find the index at which k is present, or return -1 if it is not present.
 *
 * Example 1:
 *   Input:  nums = [4, 5, 6, 7, 0, 1, 2], k = 0
 *   Output: 4
 *   Explanation: 0 is present at index 4.
 *
 * Example 2:
 *   Input:  nums = [4, 5, 6, 7, 0, 1, 2], k = 3
 *   Output: -1
 *   Explanation: 3 is not present in the array.
 *
 * Constraints:
 *   1 <= nums.length <= 5000
 *   -10^4 <= nums[i] <= 10^4
 *   All values of nums are distinct.
 *   nums is sorted in ascending order and then rotated.
 */
public class SearchInRotatedSortedArray {

    /**
     * Returns the index of target in the rotated sorted array, or -1.
     *
     * Algorithm (modified binary search):
     *   - At each step, identify which half (left or right of mid) is sorted.
     *   - If the left half [low … mid] is sorted, check whether target lies
     *     inside that half (nums[low] <= target < nums[mid]). If yes, search
     *     left; otherwise search right.
     *   - If the right half [mid … high] is sorted, check whether target lies
     *     inside that half (nums[mid] < target <= nums[high]). If yes, search
     *     right; otherwise search left.
     *
     * Time complexity:  O(log n)
     * Space complexity: O(1)
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                // Left half [low … mid] is sorted.
                if (target >= nums[low] && target < nums[mid]) {
                    // Target lies inside the sorted left half.
                    high = mid - 1;
                } else {
                    // Target is outside the left half, search right.
                    low = mid + 1;
                }
            } else {
                // Right half [mid … high] is sorted.
                if (target > nums[mid] && target <= nums[high]) {
                    // Target lies inside the sorted right half.
                    low = mid + 1;
                } else {
                    // Target is outside the right half, search left.
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 - target 0 in rotated array [4,5,6,7,0,1,2]",
                new int[]{4, 5, 6, 7, 0, 1, 2},
                0,
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 - target 3 not present",
                new int[]{4, 5, 6, 7, 0, 1, 2},
                3,
                -1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Interactive quiz - target 5 present at index 1",
                new int[]{4, 5, 6, 7, 0, 1, 2},
                5,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element array - target present",
                new int[]{1},
                1,
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element array - target not present",
                new int[]{1},
                0,
                -1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Not rotated array - binary search still works",
                new int[]{1, 2, 3, 4, 5, 6},
                4,
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Target at pivot boundary (last element)",
                new int[]{4, 5, 6, 7, 0, 1, 2},
                2,
                6
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Target at pivot boundary (first element)",
                new int[]{4, 5, 6, 7, 0, 1, 2},
                4,
                0
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
            SearchInRotatedSortedArray solution,
            String testName,
            int[] nums,
            int target,
            int expected
    ) {
        try {
            int actual = solution.search(nums, target);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " -> index=" + actual
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
