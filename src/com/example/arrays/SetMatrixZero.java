package com.example.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * Problem: Set Matrix Zeroes
 *
 * Given an m x n integer matrix, if an element is 0, set its entire row
 * and column to 0.
 *
 * The matrix must be modified in place.
 *
 * Example:
 *
 * Input:
 * [
 *   [1, 1, 1],
 *   [1, 0, 1],
 *   [1, 1, 1]
 * ]
 *
 * Output:
 * [
 *   [1, 0, 1],
 *   [0, 0, 0],
 *   [1, 0, 1]
 * ]
 *
 * Constraints:
 * 1 <= matrix.length <= 200
 * 1 <= matrix[0].length <= 200
 * Integer.MIN_VALUE <= matrix[i][j] <= Integer.MAX_VALUE
 */
public class SetMatrixZero {

    /**
     * Solution 1
     *
     * Time complexity: O(m * n)
     * Extra space: O(m * n)
     */
    public void setZeroesSolution1(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        boolean[] rowZero = new boolean[row];
        boolean[] columnZero = new boolean[column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    columnZero[j] = true;
                }
            }
        }


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (rowZero[i] || columnZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * Solution 2
     *
     * Uses the first row and first column as marker storage.
     *
     * Time complexity: O(m * n)
     * Extra space: O(1)
     */
    public void setZeroesSolution2(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        // Check whether the first row originally contains a zero.
        for (int column = 0; column < columns; column++) {
            if (matrix[0][column] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check whether the first column originally contains a zero.
        for (int row = 0; row < rows; row++) {
            if (matrix[row][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        // Use the first row and first column as markers.
        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < columns; column++) {
                if (matrix[row][column] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][column] = 0;
                }
            }
        }

        // Set elements to zero using the markers.
        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < columns; column++) {
                if (matrix[row][0] == 0 || matrix[0][column] == 0) {
                    matrix[row][column] = 0;
                }
            }
        }

        // Set the first row to zero when required.
        if (firstRowZero) {
            for (int column = 0; column < columns; column++) {
                matrix[0][column] = 0;
            }
        }

        // Set the first column to zero when required.
        if (firstColumnZero) {
            for (int row = 0; row < rows; row++) {
                matrix[row][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixZero solution = new SetMatrixZero();

        List<TestCase> testCases = List.of(
                new TestCase(
                        "Zero in the middle",
                        new int[][]{
                                {1, 1, 1},
                                {1, 0, 1},
                                {1, 1, 1}
                        },
                        new int[][]{
                                {1, 0, 1},
                                {0, 0, 0},
                                {1, 0, 1}
                        }
                ),
                new TestCase(
                        "Multiple zeroes in first row",
                        new int[][]{
                                {0, 1, 2, 0},
                                {3, 4, 5, 2},
                                {1, 3, 1, 5}
                        },
                        new int[][]{
                                {0, 0, 0, 0},
                                {0, 4, 5, 0},
                                {0, 3, 1, 0}
                        }
                ),
                new TestCase(
                        "Zero in an inner row",
                        new int[][]{
                                {1, 2, 3, 4},
                                {5, 6, 0, 8},
                                {9, 10, 11, 12}
                        },
                        new int[][]{
                                {1, 2, 0, 4},
                                {0, 0, 0, 0},
                                {9, 10, 0, 12}
                        }
                ),
                new TestCase(
                        "No zeroes",
                        new int[][]{
                                {1, 2},
                                {3, 4}
                        },
                        new int[][]{
                                {1, 2},
                                {3, 4}
                        }
                ),
                new TestCase(
                        "All zeroes",
                        new int[][]{
                                {0, 0},
                                {0, 0}
                        },
                        new int[][]{
                                {0, 0},
                                {0, 0}
                        }
                ),
                new TestCase(
                        "Single zero element",
                        new int[][]{{0}},
                        new int[][]{{0}}
                ),
                new TestCase(
                        "Single non-zero element",
                        new int[][]{{7}},
                        new int[][]{{7}}
                ),
                new TestCase(
                        "Single row containing zero",
                        new int[][]{
                                {1, 2, 0, 4, 5}
                        },
                        new int[][]{
                                {0, 0, 0, 0, 0}
                        }
                ),
                new TestCase(
                        "Single column containing zero",
                        new int[][]{
                                {1},
                                {2},
                                {0},
                                {4}
                        },
                        new int[][]{
                                {0},
                                {0},
                                {0},
                                {0}
                        }
                ),
                new TestCase(
                        "Zero in top-left corner",
                        new int[][]{
                                {0, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9}
                        },
                        new int[][]{
                                {0, 0, 0},
                                {0, 5, 6},
                                {0, 8, 9}
                        }
                ),
                new TestCase(
                        "Zero in bottom-right corner",
                        new int[][]{
                                {1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 0}
                        },
                        new int[][]{
                                {1, 2, 0},
                                {4, 5, 0},
                                {0, 0, 0}
                        }
                ),
                new TestCase(
                        "Multiple independent zeroes",
                        new int[][]{
                                {1, 2, 0, 4},
                                {5, 6, 7, 8},
                                {9, 0, 11, 12},
                                {13, 14, 15, 16}
                        },
                        new int[][]{
                                {0, 0, 0, 0},
                                {5, 0, 0, 8},
                                {0, 0, 0, 0},
                                {13, 0, 0, 16}
                        }
                ),
                new TestCase(
                        "Negative values",
                        new int[][]{
                                {-1, -2, -3},
                                {-4, 0, -6},
                                {-7, -8, -9}
                        },
                        new int[][]{
                                {-1, 0, -3},
                                {0, 0, 0},
                                {-7, 0, -9}
                        }
                ),
                new TestCase(
                        "Zero in first column",
                        new int[][]{
                                {1, 2, 3},
                                {0, 5, 6},
                                {7, 8, 9}
                        },
                        new int[][]{
                                {0, 2, 3},
                                {0, 0, 0},
                                {0, 8, 9}
                        }
                ),
                new TestCase(
                        "Zeroes in first row and first column",
                        new int[][]{
                                {1, 0, 3},
                                {4, 5, 6},
                                {0, 8, 9}
                        },
                        new int[][]{
                                {0, 0, 0},
                                {0, 0, 6},
                                {0, 0, 0}
                        }
                ),
                new TestCase(
                        "Rectangular matrix with more columns",
                        new int[][]{
                                {1, 2, 3, 0, 5},
                                {6, 7, 8, 9, 10}
                        },
                        new int[][]{
                                {0, 0, 0, 0, 0},
                                {6, 7, 8, 0, 10}
                        }
                ),
                new TestCase(
                        "Rectangular matrix with more rows",
                        new int[][]{
                                {1, 2},
                                {3, 4},
                                {5, 0},
                                {7, 8}
                        },
                        new int[][]{
                                {1, 0},
                                {3, 0},
                                {0, 0},
                                {7, 0}
                        }
                ),
                new TestCase(
                        "Integer boundary values",
                        new int[][]{
                                {Integer.MIN_VALUE, 1, Integer.MAX_VALUE},
                                {2, 0, 3},
                                {Integer.MAX_VALUE, 4, Integer.MIN_VALUE}
                        },
                        new int[][]{
                                {Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                                {0, 0, 0},
                                {Integer.MAX_VALUE, 0, Integer.MIN_VALUE}
                        }
                )
        );

        runAllTests(
                "Solution 1",
                solution::setZeroesSolution1,
                testCases
        );

        runAllTests(
                "Solution 2",
                solution::setZeroesSolution2,
                testCases
        );
    }

    private static void runAllTests(
            String solutionName,
            MatrixZeroSolver solver,
            List<TestCase> testCases
    ) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("Testing " + solutionName);
        System.out.println("========================================");

        int passedTests = 0;

        for (int index = 0; index < testCases.size(); index++) {
            TestCase testCase = testCases.get(index);

            boolean passed = runTest(
                    solver,
                    index + 1,
                    testCase
            );

            if (passed) {
                passedTests++;
            }
        }

        System.out.println();
        System.out.println(
                solutionName + " result: "
                        + passedTests + "/" + testCases.size()
                        + " tests passed"
        );

        if (passedTests != testCases.size()) {
            System.out.println(
                    solutionName + " failed "
                            + (testCases.size() - passedTests)
                            + " test(s)."
            );
        }
    }

    private static boolean runTest(
            MatrixZeroSolver solver,
            int testNumber,
            TestCase testCase
    ) {
        int[][] actual = deepCopy(testCase.input());

        try {
            solver.setZeroes(actual);

            if (Arrays.deepEquals(actual, testCase.expected())) {
                System.out.println(
                        "[PASS] Test " + testNumber + ": " + testCase.name()
                );
                return true;
            }

            System.out.println(
                    "[FAIL] Test " + testNumber + ": " + testCase.name()
            );
            System.out.println(
                    "Input:    " + Arrays.deepToString(testCase.input())
            );
            System.out.println(
                    "Expected: " + Arrays.deepToString(testCase.expected())
            );
            System.out.println(
                    "Actual:   " + Arrays.deepToString(actual)
            );

            return false;
        } catch (Exception exception) {
            System.out.println(
                    "[ERROR] Test " + testNumber + ": " + testCase.name()
            );
            System.out.println(
                    "Exception: "
                            + exception.getClass().getSimpleName()
                            + ": "
                            + exception.getMessage()
            );

            return false;
        }
    }

    private static int[][] deepCopy(int[][] matrix) {
        int[][] copy = new int[matrix.length][];

        for (int row = 0; row < matrix.length; row++) {
            copy[row] = Arrays.copyOf(
                    matrix[row],
                    matrix[row].length
            );
        }

        return copy;
    }

    @FunctionalInterface
    private interface MatrixZeroSolver {
        void setZeroes(int[][] matrix);
    }

    private record TestCase(
            String name,
            int[][] input,
            int[][] expected
    ) {
    }
}
