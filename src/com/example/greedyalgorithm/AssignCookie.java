package com.example.greedyalgorithm;

import java.util.Arrays;

/**
 * Problem: Assign Cookies
 *
 * Given two integer arrays `Student` and `Cookie` where `Student[i]` is the
 * greed factor of the i-th child (the minimum size of a cookie that the
 * child will be content with) and `Cookie[j]` is the size of the j-th
 * cookie. Each child can receive at most one cookie, and each cookie can
 * be assigned to at most one child.
 *
 * Return the maximum number of children that can be content.
 *
 * Example 1:
 *   Input:  Student = [1, 2, 3], Cookie = [1, 1]
 *   Output: 1
 *   Explanation: You have 3 children and 2 cookies. Only one child with
 *   greed factor 1 can be content with a cookie of size 1.
 *
 * Example 2:
 *   Input:  Student = [1, 2], Cookie = [1, 2, 3]
 *   Output: 2
 *   Explanation: Both children can be content - child 1 gets cookie 1
 *   (size 1) and child 2 gets cookie 2 (size 2).
 *
 * Constraints:
 *   1 <= Student.length, Cookie.length <= 10^5
 *   1 <= Student[i], Cookie[j] <= 2^31 - 1
 */
public class AssignCookie {

    /**
     * Returns the maximum number of children that can be content with
     * the given cookies.
     *
     * Algorithm (greedy + two pointers):
     *   - Sort both arrays so the least greedy child and the smallest
     *     cookie are considered first.
     *   - Walk both arrays with two pointers. If the current cookie is
     *     large enough for the current child, assign it and advance both
     *     pointers. Otherwise the cookie is too small for this child (and
     *     every later, greedier child), so discard it and advance only
     *     the cookie pointer.
     *   - This greedy choice is optimal: giving the smallest sufficient
     *     cookie to the least greedy child leaves larger cookies free
     *     for greedier children.
     *
     * Time complexity:  O(N log N + M log M)  (dominated by sorting)
     * Space complexity: O(1)                  (in-place sort, two pointers)
     */
    public int findMaximumCookieStudents(int[] Student, int[] Cookie) {
        // Sort so the least greedy child and the smallest cookie line up first.
        Arrays.sort(Student);
        Arrays.sort(Cookie);

        int studentLength = Student.length;
        int cookieLength = Cookie.length;

        int studentPointer = 0;
        int cookiePointer = 0;

        int satisfiedStudents = 0;

        while (studentPointer < studentLength && cookiePointer < cookieLength) {
            // Current cookie satisfies the current child - assign and advance both.
            if (Student[studentPointer] <= Cookie[cookiePointer]) {
                satisfiedStudents++;
                studentPointer++;
            }

            // Always move the cookie regardless used or not
            cookiePointer++;
        }

        return satisfiedStudents;
    }

    public static void main(String[] args) {
        AssignCookie solution = new AssignCookie();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - 3 children, 2 cookies",
                new int[]{1, 2, 3},
                new int[]{1, 1},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - both children content",
                new int[]{1, 2},
                new int[]{1, 2, 3},
                2
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "More cookies than children - every child can be content",
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4, 5},
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "No cookie is large enough - zero children content",
                new int[]{5, 6, 7},
                new int[]{1, 2, 3},
                0
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single child with a single matching cookie",
                new int[]{3},
                new int[]{3},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Duplicate greed factors and cookie sizes",
                new int[]{1, 1, 2, 2},
                new int[]{1, 1, 2, 2, 2},
                4
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
            AssignCookie solution,
            String testName,
            int[] studentInput,
            int[] cookieInput,
            int expected
    ) {
        try {
            int actual = solution.findMaximumCookieStudents(studentInput, cookieInput);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | student=" + Arrays.toString(studentInput)
                                + ", cookie=" + Arrays.toString(cookieInput)
                                + " -> result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Student:  " + Arrays.toString(studentInput));
            System.out.println("Cookie:   " + Arrays.toString(cookieInput));
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Student:  " + Arrays.toString(studentInput));
            System.out.println("Cookie:   " + Arrays.toString(cookieInput));
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
