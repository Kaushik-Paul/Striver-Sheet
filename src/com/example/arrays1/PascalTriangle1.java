package com.example.arrays1;

/**
 * Problem: Pascal's Triangle I
 *
 * Given two integers r and c, return the value present at the r-th row
 * and c-th column of Pascal's Triangle.
 *
 * Both r and c use 1-based indexing.
 *
 * In Pascal's Triangle:
 * - The first row contains one element: 1.
 * - Every row starts and ends with 1.
 * - Each interior element is the sum of the two elements directly above it.
 *
 * The value at row r and column c can also be calculated using:
 *
 *     C(r - 1, c - 1)
 *
 * where C(n, k) is the binomial coefficient.
 *
 * Example:
 *
 * Input:
 * r = 4, c = 2
 *
 * Pascal's Triangle:
 *          1
 *         1 1
 *        1 2 1
 *       1 3 3 1
 *
 * Output:
 * 3
 */
public class PascalTriangle1 {

    /**
     * Returns the value at the given row and column of Pascal's Triangle.
     *
     * Time complexity: O(min(c - 1, r - c))
     * Space complexity: O(1)
     */
    public int pascalTriangleI(int r, int c) {
        return combimation(r - 1, c - 1);
    }

    /**
     * Calculates the binomial coefficient nCr.
     *
     * The method name "combimation" has been retained from the provided
     * solution.
     */
    public int combimation(int n, int r) {
        // Use the smaller value to reduce the number of iterations.
        if (r > (n - r)) {
            r = n - r;
        }

        // When r is 1, nC1 is n.
        if (r == 1) {
            return n;
        }

        int res = 1;

        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        PascalTriangle1 solution = new PascalTriangle1();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "First element of Pascal's Triangle",
                1,
                1,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Second row, first column",
                2,
                1,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Second row, last column",
                2,
                2,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example: fourth row, second column",
                4,
                2,
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Fourth row, third column",
                4,
                3,
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "First element of a larger row",
                5,
                1,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Last element of a larger row",
                5,
                5,
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Middle element of fifth row",
                5,
                3,
                6
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Sixth row, second column",
                6,
                2,
                5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Sixth row, third column",
                6,
                3,
                10
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Sixth row, fourth column",
                6,
                4,
                10
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Symmetric element in sixth row",
                6,
                5,
                5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Seventh row middle element",
                7,
                4,
                20
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Eighth row, third column",
                8,
                3,
                21
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Eighth row, sixth column",
                8,
                6,
                21
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Tenth row, second column",
                10,
                2,
                9
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Tenth row, third column",
                10,
                3,
                36
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Tenth row, fifth column",
                10,
                5,
                126
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Tenth row, sixth column",
                10,
                6,
                126
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Eleventh row middle element",
                11,
                6,
                252
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Fifteenth row, fourth column",
                15,
                4,
                364
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Fifteenth row, twelfth column",
                15,
                12,
                364
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Twentieth row, fourth column",
                20,
                4,
                969
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Twentieth row, seventeenth column",
                20,
                17,
                969
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Large valid result",
                30,
                15,
                77_558_760
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
            PascalTriangle1 solution,
            String testName,
            int row,
            int column,
            int expected
    ) {
        try {
            int actual = solution.pascalTriangleI(row, column);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | r=" + row
                                + ", c=" + column
                                + ", result=" + actual
                );

                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Row:      " + row);
            System.out.println("Column:   " + column);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();

            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Row:       " + row);
            System.out.println("Column:    " + column);
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
