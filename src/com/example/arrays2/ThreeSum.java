package com.example.arrays2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: 3 Sum
 *
 * Given an integer array nums, return all unique triplets whose sum is zero.
 * Each triplet must use three distinct indices. Both the triplets and the
 * values within a triplet may be returned in any order.
 *
 * Example 1:
 *   Input:  nums = [2, -2, 0, 3, -3, 5]
 *   Output: [[-2, 0, 2], [-3, -2, 5], [-3, 0, 3]]
 *   Explanation: Each triplet sums to zero and uses distinct array indices.
 *
 * Example 2:
 *   Input:  nums = [2, -1, -1, 3, -1]
 *   Output: [[-1, -1, 2]]
 *
 * Constraints:
 *   The three indices in each triplet must be distinct.
 *   The result must not contain duplicate triplets.
 */
public class ThreeSum {

    /**
     * Sorts nums in place, then uses two pointers for each distinct first value.
     *
     * Time complexity:  O(n^2)
     * Space complexity: O(log n) auxiliary sorting stack, plus O(k) output
     *                   for k unique triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array
        Arrays.sort(nums);

        List<List<Integer>> requiredSum = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            // Remove duplicate from first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int leftPointer = i + 1;
            int rightPointer = nums.length - 1;

            while (leftPointer < rightPointer) {
                int sum = nums[i] + nums[leftPointer] + nums[rightPointer];

                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[leftPointer]);
                    temp.add(nums[rightPointer]);
                    requiredSum.add(temp);

                    leftPointer++;
                    rightPointer--;

                    // Remove duplicates if any from 2nd and 3rd element
                    while (leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer - 1]) {
                        leftPointer++;
                    }

                    while (leftPointer < rightPointer && nums[rightPointer] == nums[rightPointer + 1]) {
                        rightPointer--;
                    }

                } else if (sum > 0) {
                    rightPointer--;
                } else {
                    leftPointer++;
                }
            }
        }

        return requiredSum;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 - multiple triplets",
                new int[]{2, -2, 0, 3, -3, 5},
                Arrays.asList(
                        Arrays.asList(-2, 0, 2),
                        Arrays.asList(-3, -2, 5),
                        Arrays.asList(-3, 0, 3)
                )
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 - duplicate values produce one triplet",
                new int[]{2, -1, -1, 3, -1},
                Arrays.asList(Arrays.asList(-1, -1, 2))
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Large all-zero input produces one triplet",
                new int[3000],
                Arrays.asList(Arrays.asList(0, 0, 0))
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Empty input",
                new int[]{},
                new ArrayList<>()
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element cannot form a triplet",
                new int[]{0},
                new ArrayList<>()
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All-positive input has no zero-sum triplet",
                new int[]{1, 2, 3, 4, 5},
                new ArrayList<>()
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
            ThreeSum solution,
            String testName,
            int[] nums,
            List<List<Integer>> expected
    ) {
        try {
            List<List<Integer>> actual = solution.threeSum(nums);

            if (normalizeTriplets(expected).equals(normalizeTriplets(actual))) {
                System.out.println("[PASS] " + testName);
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

    private static List<String> normalizeTriplets(List<List<Integer>> triplets) {
        List<String> normalized = new ArrayList<>();
        for (List<Integer> triplet : triplets) {
            List<Integer> sorted = new ArrayList<>(triplet);
            sorted.sort(Integer::compareTo);
            normalized.add(sorted.toString());
        }
        // Sorting a list preserves duplicates so duplicate triplets fail the comparison.
        normalized.sort(String::compareTo);
        return normalized;
    }
}
