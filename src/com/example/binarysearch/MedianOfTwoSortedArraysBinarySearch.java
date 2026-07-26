package com.example.binarysearch;

/**
 * Problem: Median of Two Sorted Arrays (Binary Search approach)
 *
 * Given two sorted arrays arr1 and arr2 of size m and n respectively,
 * return the median of the two sorted arrays.
 *
 * The median is the middle value of a sorted list. If the length is even,
 * the median is the average of the two middle elements.
 *
 * Example 1:
 *   Input:  arr1 = [2, 4, 6], arr2 = [1, 3, 5]
 *   Output: 3.5
 *   Explanation: Merged array is [1, 2, 3, 4, 5, 6]. Median = (3 + 4) / 2 = 3.5.
 *
 * Example 2:
 *   Input:  arr1 = [2, 4, 6], arr2 = [1, 3]
 *   Output: 3.0
 *   Explanation: Merged array is [1, 2, 3, 4, 6]. Median = 3.
 *
 * Constraints:
 *   0 <= m, n <= 10^6
 *   1 <= m + n <= 2 * 10^6
 */
public class MedianOfTwoSortedArraysBinarySearch {

    /**
     * Returns the median using binary search on partitions.
     *
     * We partition the smaller array so that the left half of the combined
     * array contains the correct number of elements. l1, r1 and l2, r2 are
     * the values immediately around the partition.
     *
     * Time complexity:  O(log(min(m, n)))
     * Space complexity: O(1)
     */
    public double median(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;

        // Ensure arr1 is the smaller array to keep binary search range minimal.
        if (n1 > n2) return median(arr2, arr1);

        int n = n1 + n2;

        // Total elements that must appear in the left half.
        int left = (n1 + n2 + 1) / 2;

        int low = 0, high = n1;
        while (low <= high) {

            // mid1 = elements taken from arr1 into the left half.
            int mid1 = (low + high) >>> 1;

            // mid2 = elements taken from arr2 into the left half.
            int mid2 = left - mid1;

            // Edge values around the partition (MIN_VALUE / MAX_VALUE when out of bounds).
            int l1 = (mid1 > 0) ? arr1[mid1 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? arr1[mid1] : Integer.MAX_VALUE;
            int l2 = (mid2 > 0) ? arr2[mid2 - 1] : Integer.MIN_VALUE;
            int r2 = (mid2 < n2) ? arr2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                // Correct partition found.
                if (n % 2 == 1) return Math.max(l1, l2);
                else return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2) {
                // Too many elements from arr1; move left.
                high = mid1 - 1;
            } else {
                // Too few elements from arr1; move right.
                low = mid1 + 1;
            }
        }

        // Control never reaches here for valid input.
        return 0;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArraysBinarySearch solution = new MedianOfTwoSortedArraysBinarySearch();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - even total length",
                new int[]{2, 4, 6},
                new int[]{1, 3, 5},
                3.5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - odd total length",
                new int[]{2, 4, 6},
                new int[]{1, 3},
                3.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "One array empty - median in the other array",
                new int[]{},
                new int[]{1, 2, 3, 4, 5},
                3.0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All elements of one array smaller than the other",
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                3.5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element in each array",
                new int[]{1},
                new int[]{2},
                1.5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Negative numbers and duplicates",
                new int[]{-5, -3, -1},
                new int[]{-1, 2, 4},
                -1.0
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
            MedianOfTwoSortedArraysBinarySearch solution,
            String testName,
            int[] arr1,
            int[] arr2,
            double expected
    ) {
        try {
            double actual = solution.median(arr1, arr2);

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
