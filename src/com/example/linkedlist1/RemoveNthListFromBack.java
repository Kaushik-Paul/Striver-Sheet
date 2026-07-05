package com.example.linkedlist1;

import java.util.Arrays;

/**
 * Problem: Remove Nth Node From End of List
 *
 * Given the head of a singly linked list, remove the n-th node from
 * the end of the list and return the head of the modified list. The
 * list nodes are manipulated by rewiring their next pointers, not by
 * shifting values.
 *
 * Example 1:
 *   Input:  head -> 1 -> 2 -> 3 -> 4 -> 5, n = 2
 *   Output: head -> 1 -> 2 -> 3 -> 5
 *   Explanation: The 2nd node from the end is the node with value 4,
 *   so it is removed by skipping it from the node before it.
 *
 * Example 2:
 *   Input:  head -> 1, n = 1
 *   Output: null
 *   Explanation: The only node is also the 1st from the end, so the
 *   result is an empty list.
 *
 * Example 3:
 *   Input:  head -> 1 -> 2, n = 1
 *   Output: head -> 1
 *   Explanation: The 1st node from the end is the node with value 2,
 *   so it is removed and the new tail is the node with value 1.
 *
 * Constraints:
 *   1 <= number of nodes <= 30
 *   1 <= n <= number of nodes
 *   1 <= node.val <= 100
 */
public class RemoveNthListFromBack {

    /**
     * Removes the n-th node from the end of a singly linked list and
     * returns the head of the modified list.
     *
     * Algorithm (length + index walk):
     *   - First pass: walk the whole list once to count its length L.
     *   - The target is at position (L - n) from the head (0-indexed).
     *   - Second pass: walk (L - n) steps so that the iterator lands on
     *     the node right before the target, then rewire its next past
     *     the target.
     *   - When (L - n) == 0, the head itself is the target, so the
     *     new head is just head.next.
     *
     * Time complexity:  O(n)   (two linear passes)
     * Space complexity: O(1)   (no extra nodes or containers)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // First pass: measure the list length.
        int linkedListLength = 0;
        ListNode temp = head;

        while (temp != null) {
            linkedListLength++;
            temp = temp.next;
        }

        // Convert "n from the end" into "k from the start" (0-indexed).
        int requiredTraverse = linkedListLength - n;

        // requiredTraverse == 0 means the head itself is the target.
        if (requiredTraverse == 0) {
            return head.next;
        }

        // Second pass: walk so that temp stops just before the target.
        int currentTraverse = 1;
        temp = head;

        while (currentTraverse < requiredTraverse) {
            temp = temp.next;
            currentTraverse++;
        }

        // Bypass the target node by pointing temp.next at the one after it.
        temp.next = temp.next.next;

        return head;
    }

    public static void main(String[] args) {
        RemoveNthListFromBack solution = new RemoveNthListFromBack();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement - remove 2nd from end of 5-node list",
                new int[]{1, 2, 3, 4, 5},
                2,
                new int[]{1, 2, 3, 5}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single-node list - removing that node yields an empty list",
                new int[]{1},
                1,
                new int[]{}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two-node list - removing the 1st from end (last) leaves only the head",
                new int[]{1, 2},
                1,
                new int[]{1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Removing the last from the end (i.e. the head) of a 2-node list",
                new int[]{1, 2},
                2,
                new int[]{2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Removing the head of a 5-node list - head must be reattached",
                new int[]{1, 2, 3, 4, 5},
                5,
                new int[]{2, 3, 4, 5}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All nodes share the same value - value is irrelevant, just walk the positions",
                new int[]{7, 7, 7, 7, 7},
                3,
                new int[]{7, 7, 7, 7}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Longer list - removing the 2nd from end of 7 nodes",
                new int[]{10, 20, 30, 40, 50, 60, 70},
                2,
                new int[]{10, 20, 30, 40, 50, 70}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Removing the middle of a 4-node list (n=2)",
                new int[]{5, 10, 15, 20},
                2,
                new int[]{5, 10, 20}
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
            RemoveNthListFromBack solution,
            String testName,
            int[] input,
            int n,
            int[] expected
    ) {
        try {
            ListNode inputList = buildList(input);
            ListNode expectedList = buildList(expected);

            ListNode actualList = solution.removeNthFromEnd(inputList, n);

            String actualStr = listToString(actualList);
            String expectedStr = listToString(expectedList);

            if (actualStr.equals(expectedStr)) {
                System.out.println(
                        "[PASS] " + testName
                                + " | input=" + listToString(buildList(input))
                                + ", n=" + n
                                + " -> result=" + actualStr
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Input:    " + Arrays.toString(input) + ", n=" + n);
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Input:     " + Arrays.toString(input) + ", n=" + n);
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
