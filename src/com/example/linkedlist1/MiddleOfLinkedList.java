package com.example.linkedlist1;

import java.util.Arrays;

/**
 * Problem: Find the Middle of a Linked List
 *
 * Given the head of a singly linked list, return the middle node of the
 * linked list. If the list has an even number of nodes, return the
 * second middle node.
 *
 * Example 1:
 *   Input:  head -> 3 -> 8 -> 7 -> 1 -> 3
 *   Output (value at returned node): 7
 *   Explanation: There are 5 nodes, so the middle node is the 3rd node
 *   with value 7.
 *
 * Example 2:
 *   Input:  head -> 2 -> 9 -> 1 -> 4 -> 0 -> 4
 *   Output (value at returned node): 4
 *   Explanation: There are 6 nodes, so both the 3rd and 4th nodes are
 *   middle. The 2nd middle (4th node) is returned, with value 4.
 *
 * Constraints:
 *   1 <= number of nodes <= 5000
 *   -5000 <= node.val <= 5000
 */
public class MiddleOfLinkedList {

    /**
     * Returns the middle node of a singly linked list. For an even-length
     * list, the second of the two middle nodes is returned.
     *
     * Algorithm (slow and fast pointers):
     *   - slow advances one node per step, fast advances two.
     *   - When fast reaches the tail, slow is at the middle.
     *   - Because fast starts at the same node as slow, on an even-length
     *     list slow lands on the second middle.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public ListNode middleOfLinkedList(ListNode head) {

        // slow lags by one, fast races ahead by two.
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // fast has reached (or jumped past) the tail, so slow is the middle.
        return slow;

    }

    public static void main(String[] args) {
        MiddleOfLinkedList solution = new MiddleOfLinkedList();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example - 5 node list, middle value 7",
                new int[]{3, 8, 7, 1, 3},
                7
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Even-length list - return second middle, value 4",
                new int[]{2, 9, 1, 4, 0, 4},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Now your turn - 5 node list, middle value 1",
                new int[]{3, 8, 1, 7, 0},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single node - that node is the middle",
                new int[]{5},
                5
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two nodes - second node is the second middle",
                new int[]{1, 2},
                2
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All nodes have the same value - middle is still that value",
                new int[]{7, 7, 7, 7},
                7
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Even-length ascending list - second middle is 30",
                new int[]{10, 20, 30, 40},
                30
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
            MiddleOfLinkedList solution,
            String testName,
            int[] input,
            int expected
    ) {
        try {
            ListNode inputList = buildList(input);
            ListNode actual = solution.middleOfLinkedList(inputList);

            if (actual != null && actual.val == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | input=" + listToString(inputList)
                                + ", middle=" + actual.val
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Input:    " + Arrays.toString(input));
            System.out.println(
                    "Expected middle: " + expected
            );
            System.out.println(
                    "Actual middle:   "
                            + (actual == null ? "null" : actual.val)
            );
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Input:     " + Arrays.toString(input));
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

    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    private static String listToString(ListNode head) {
        StringBuilder builder = new StringBuilder("head -> ");
        ListNode current = head;

        if (current == null) {
            builder.append("null");
            return builder.toString();
        }

        while (current != null) {
            builder.append(current.val);
            if (current.next != null) {
                builder.append(" -> ");
            }
            current = current.next;
        }
        builder.append(" -> null");

        return builder.toString();
    }
}
