package com.example.arrays2;

import java.util.Arrays;

/**
 * Problem: Rotate matrix by 90 degrees
 *
 * Given an N * N 2D integer matrix, rotate the matrix by 90 degrees clockwise.
 * The rotation must be done in place, meaning the input 2D matrix must be
 * modified directly.
 *
 * Example 1:
 *   Input:  matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
 *   Output: matrix = [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
 *
 * Constraints:
 *   1 <= n <= 100
 */
public class RotateArraysBy90InPlace {

    /**
     * Transposes the matrix in place, then reverses each row to produce
     * the 90-degree clockwise rotation without using extra space.
     *
     * Time complexity:  O(n^2)
     * Space complexity: O(1)
     */
    public void rotateMatrix(int[][] matrix) {
        // Basic way to do this is transpose the matrix
        // And then reverse the columns
        int n = matrix.length;

        // Transpose the matrix
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse the columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        RotateArraysBy90InPlace solution = new RotateArraysBy90InPlace();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - 3x3 matrix",
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                },
                new int[][]{
                        {7, 4, 1},
                        {8, 5, 2},
                        {9, 6, 3}
                }
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single element matrix",
                new int[][]{
                        {42}
                },
                new int[][]{
                        {42}
                }
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "2x2 matrix",
                new int[][]{
                        {1, 2},
                        {3, 4}
                },
                new int[][]{
                        {3, 1},
                        {4, 2}
                }
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "4x4 matrix with repeated values",
                new int[][]{
                        {1, 1, 1, 1},
                        {2, 2, 2, 2},
                        {3, 3, 3, 3},
                        {4, 4, 4, 4}
                },
                new int[][]{
                        {4, 3, 2, 1},
                        {4, 3, 2, 1},
                        {4, 3, 2, 1},
                        {4, 3, 2, 1}
                }
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "3x3 matrix with negative numbers",
                new int[][]{
                        {-1, -2, -3},
                        {-4, -5, -6},
                        {-7, -8, -9}
                },
                new int[][]{
                        {-7, -4, -1},
                        {-8, -5, -2},
                        {-9, -6, -3}
                }
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "5x5 matrix",
                new int[][]{
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 25}
                },
                new int[][]{
                        {21, 16, 11, 6, 1},
                        {22, 17, 12, 7, 2},
                        {23, 18, 13, 8, 3},
                        {24, 19, 14, 9, 4},
                        {25, 20, 15, 10, 5}
                }
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
            RotateArraysBy90InPlace solution,
            String testName,
            int[][] input,
            int[][] expected
    ) {
        try {
            solution.rotateMatrix(input);

            if (Arrays.deepEquals(input, expected)) {
                System.out.println("[PASS] " + testName);
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Expected: " + Arrays.deepToString(expected));
            System.out.println("Actual:   " + Arrays.deepToString(input));
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
