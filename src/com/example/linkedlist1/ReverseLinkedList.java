package com.example.linkedlist1;

import java.util.Arrays;

/**
 * Problem: Reverse a Linked List
 *
 * Given the head of a singly linked list, reverse the list and return
 * the head of the modified list.
 *
 * Example 1:
 *   Input:  head -> 1 -> 2 -> 3 -> 4 -> 5
 *   Output: head -> 5 -> 4 -> 3 -> 2 -> 1
 *   Explanation: All the links are reversed and the head now points to
 *   the last node of the original list.
 *
 * Example 2:
 *   Input:  head -> 6 -> 8
 *   Output: head -> 8 -> 6
 *   Explanation: All the links are reversed and the head now points to
 *   the last node of the original list. This can be seen like:
 *   6 <- 8 <- head.
 *
 * Constraints:
 *   0 <= number of nodes <= 5000
 *   -5000 <= node.val <= 5000
 */
public class ReverseLinkedList {

    /**
     * Reverses a singly linked list in place and returns the new head.
     *
     * Algorithm (iterative, three pointers):
     *   - Walk through the list, at each step pointing the current
     *     node's next back to the previous node.
     *   - prev tracks the node already reversed; temp walks forward.
     *   - When temp falls off the end, prev is the new head.
     *
     * Time complexity:  O(n)
     * Space complexity: O(1)
     */
    public ListNode reverseList(ListNode head) {

        // prev = node already reversed; temp = node being processed.
        ListNode prev = null;
        ListNode temp = head;

        while (temp != null) {
            // Save next before we overwrite temp.next.
            ListNode nextNode = temp.next;
            // Flip the link to point backwards.
            temp.next = prev;
            // Move both pointers one step forward.
            prev = temp;
            temp = nextNode;
        }

        // prev is the new head once temp falls off the tail.
        return prev;

    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement - 5 node list",
                new int[]{1, 2, 3, 4, 5},
                new int[]{5, 4, 3, 2, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two-node example from problem statement",
                new int[]{6, 8},
                new int[]{8, 6}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single node - list stays the same",
                new int[]{1},
                new int[]{1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Empty list - head stays null",
                new int[]{},
                new int[]{}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two nodes in ascending order",
                new int[]{1, 2},
                new int[]{2, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All nodes have the same value",
                new int[]{7, 7, 7},
                new int[]{7, 7, 7}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Longer odd-length ascending list",
                new int[]{1, 3, 5, 7, 9},
                new int[]{9, 7, 5, 3, 1}
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
            ReverseLinkedList solution,
            String testName,
            int[] input,
            int[] expected
    ) {
        try {
            ListNode inputList = buildList(input);
            ListNode expectedList = buildList(expected);

            ListNode actualList = solution.reverseList(inputList);

            String actualStr = listToString(actualList);
            String expectedStr = listToString(expectedList);

            if (actualStr.equals(expectedStr)) {
                System.out.println(
                        "[PASS] " + testName
                                + " | input=" + listToString(buildList(input))
                                + ", result=" + actualStr
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Input:    " + Arrays.toString(input));
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);
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
