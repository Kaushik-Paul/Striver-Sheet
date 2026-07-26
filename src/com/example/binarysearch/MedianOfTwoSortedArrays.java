package com.example.binarysearch;

/**
 * Problem: Median of Two Sorted Arrays
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
public class MedianOfTwoSortedArrays {

    /**
     * Returns the median of two sorted arrays using a two-pointer merge.
     *
     * Only merges until the median indices are reached.
     *
     * Time complexity:  O(m + n) in the worst case
     * Space complexity: O(1)
     */
    public double median(int[] arr1, int[] arr2) {

        int totalLength = arr1.length + arr2.length;
        int firstIndex = totalLength / 2;
        int secondIndex = firstIndex - 1;

        int firstPointer = 0;
        int secondPointer = 0;
        int counter = 0;

        int requiredIndex1 = -1;
        int requiredIndex2 = -1;

        while (firstPointer < arr1.length && secondPointer < arr2.length) {
            if (arr1[firstPointer] <= arr2[secondPointer]) {
                if (counter == firstIndex) {
                    requiredIndex1 = arr1[firstPointer];
                }

                if (counter == secondIndex) {
                    requiredIndex2 = arr1[firstPointer];
                }

                firstPointer++;
            } else {
                if (counter == firstIndex) {
                    requiredIndex1 = arr2[secondPointer];
                }

                if (counter == secondIndex) {
                    requiredIndex2 = arr2[secondPointer];
                }

                secondPointer++;
            }

            counter++;
        }

        // Process any remaining elements in arr1.
        while (firstPointer < arr1.length) {
            if (counter == firstIndex) {
                requiredIndex1 = arr1[firstPointer];
            }

            if (counter == secondIndex) {
                requiredIndex2 = arr1[firstPointer];
            }

            firstPointer++;
            counter++;
        }

        // Process any remaining elements in arr2.
        while (secondPointer < arr2.length) {
            if (counter == firstIndex) {
                requiredIndex1 = arr2[secondPointer];
            }

            if (counter == secondIndex) {
                requiredIndex2 = arr2[secondPointer];
            }

            secondPointer++;
            counter++;
        }

        // Odd length: return the single middle element.
        if (totalLength % 2 == 1) {
            return requiredIndex1;
        }

        // Even length: average the two middle elements.
        return (double) (requiredIndex1 + requiredIndex2) / 2;

    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();

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
            MedianOfTwoSortedArrays solution,
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
