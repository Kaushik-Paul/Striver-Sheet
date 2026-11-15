package com.example.slidingwindow;

/**
 * Problem: Max Consecutive Ones III
 *
 * Given a binary array nums and an integer k, flip at most k 0's and return
 * the maximum number of consecutive 1's in the resulting array.
 *
 * Example 1:
 *   Input:  nums = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], k = 3
 *   Output: 10
 *   Explanation: Flipping the 0's at indices 3, 4 and 5 gives
 *                [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0], so the longest run is 10.
 *
 * Example 2:
 *   Input:  nums = [0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1], k = 3
 *   Output: 9
 *   Explanation: Flipping three 0's gives a run of 9 consecutive 1's.
 *
 * Constraints:
 *   0 <= nums.length
 *   nums[i] is 0 or 1.
 *   0 <= k
 */
public class MaxConsequencesOnesIII {

    /**
     * Sliding window that is allowed to contain at most k zeroes; whenever it
     * holds more, the left edge advances until the window is valid again.
     *
     * Time complexity:  O(n), each pointer moves forward at most n times
     * Space complexity: O(1)
     */
    public int longestOnes(int[] nums, int k) {
        //your code goes here
        int leftPointer = 0;
        int rightPointer = 0;
        int maxLen = 0;
        int zeroes = 0;
        int n = nums.length;

        while (rightPointer < n) {
            if (nums[rightPointer] == 0) {
                zeroes++;
            }

            // Shrink from the left until at most k zeroes remain in the window.
            while(zeroes > k) {
                if(nums[leftPointer] == 0) {
                    zeroes--;
                }

                leftPointer++;
            }

            int currentLength = rightPointer - leftPointer + 1;
            maxLen = Math.max(maxLen, currentLength);
            rightPointer++;
        }


        return maxLen;
    }

    public static void main(String[] args) {
        MaxConsequencesOnesIII solution = new MaxConsequencesOnesIII();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 - flip three middle zeroes",
                new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0},
                3,
                10
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 array - best window starts after the leading zeroes",
                new int[]{0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
                3,
                // Indices 2..13 hold exactly three zeroes, so the run is 12.
                12
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "k larger than the number of zeroes",
                new int[]{1, 1, 0, 0, 1},
                3,
                5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All zeroes with no flips allowed",
                new int[]{0, 0, 0, 0, 0},
                0,
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All ones",
                new int[]{1, 1, 1, 1},
                2,
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single zero with one flip",
                new int[]{0},
                1,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Empty array",
                new int[]{},
                5,
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Zeroes separated by a single one",
                new int[]{0, 1, 0, 1, 0},
                1,
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
            MaxConsequencesOnesIII solution,
            String testName,
            int[] nums,
            int k,
            int expected
    ) {
        try {
            int actual = solution.longestOnes(nums, k);

            if (actual == expected) {
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
}