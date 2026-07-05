package com.example.linkedlist1;

import java.util.Arrays;

/**
 * Problem: Delete Node in a Linked List
 *
 * Write a function to delete a node in a singly-linked list. You are
 * NOT given access to the head of the list - instead you are given a
 * direct reference to the node that has to be removed. The node to
 * delete is guaranteed to not be the tail, and the list is guaranteed
 * to contain at least two nodes. The list must be modified in place.
 *
 * Example 1:
 *   Input:  head -> 4 -> 5 -> 1 -> 9,  node (value 5)
 *   Output: head -> 4 -> 1 -> 9
 *   Explanation: The reference points to the node with value 5; after
 *   deletion the list skips that node and the result is 4 -> 1 -> 9.
 *
 * Example 2:
 *   Input:  head -> 1 -> 2 -> 3 -> 4,  node (value 3)
 *   Output: head -> 1 -> 2 -> 4
 *   Explanation: The reference points to the node with value 3; the
 *   list is updated in place and the result is 1 -> 2 -> 4.
 *
 * Constraints:
 *   2 <= number of nodes <= 1000
 *   -1000 <= node.val <= 1000
 *   The given node is guaranteed to be in the list and is not the tail.
 */
public class DeleteNodeInLinkedList {

    /**
     * Deletes the given node from its singly-linked list in place.
     * Only a reference to the target node is available; the head is
     * NOT provided.
     *
     * Algorithm (value-copy + bypass):
     *   - Since the previous node's pointer cannot be reached, the
     *     target itself cannot be unlinked directly. Instead, the
     *     value of the next node is copied into the target, and the
     *     target's next pointer is rewired to skip over the next node.
     *   - From the outside, the target now looks like it was removed,
     *     even though the actual node still exists in memory.
     *
     * Time complexity:  O(1)
     * Space complexity: O(1)
     */
    public void deleteNode(ListNode node) {

        // Copy the next node into the current one, then bypass the next node -
        // this is the only way to "remove" the target when we only have its reference.
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        DeleteNodeInLinkedList solution = new DeleteNodeInLinkedList();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - delete middle node 5 from [4,5,1,9]",
                new int[]{4, 5, 1, 9},
                5,
                new int[]{4, 1, 9}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - delete node 3 from [1,2,3,4]",
                new int[]{1, 2, 3, 4},
                3,
                new int[]{1, 2, 4}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Delete the head of a two-node list - list becomes a single node",
                new int[]{1, 2},
                1,
                new int[]{2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Delete the second-to-last (last valid) node of a 3-node list",
                new int[]{1, 2, 3},
                2,
                new int[]{1, 3}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two-node list, delete the first node - result is the second node only",
                new int[]{10, 20},
                10,
                new int[]{20}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Longer list, delete a middle node (value 4) from an 8-node list",
                new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                4,
                new int[]{1, 2, 3, 5, 6, 7, 8}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All nodes share the same value - first matching node is removed",
                new int[]{7, 7, 7, 7},
                7,
                new int[]{7, 7, 7}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Negative values - delete node 0 from [-5,-3,0,2,4]",
                new int[]{-5, -3, 0, 2, 4},
                0,
                new int[]{-5, -3, 2, 4}
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
            DeleteNodeInLinkedList solution,
            String testName,
            int[] input,
            int nodeValue,
            int[] expected
    ) {
        try {
            ListNode inputList = buildList(input);
            ListNode expectedList = buildList(expected);

            ListNode targetNode = findNodeByValue(inputList, nodeValue);
            if (targetNode == null) {
                System.out.println("[FAIL] " + testName);
                System.out.println(
                        "Input:  " + Arrays.toString(input)
                                + ", node value=" + nodeValue
                );
                System.out.println(
                        "No node with value " + nodeValue + " found in the list."
                );
                System.out.println();
                return 0;
            }

            solution.deleteNode(targetNode);

            String actualStr = listToString(inputList);
            String expectedStr = listToString(expectedList);

            if (actualStr.equals(expectedStr)) {
                System.out.println(
                        "[PASS] " + testName
                                + " | input=" + listToString(buildList(input))
                                + ", node=" + nodeValue
                                + " -> result=" + actualStr
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println(
                    "Input:    " + Arrays.toString(input)
                            + ", node value=" + nodeValue
            );
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println(
                    "Input:     " + Arrays.toString(input)
                            + ", node value=" + nodeValue
            );
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

    private static ListNode findNodeByValue(ListNode head, int value) {
        ListNode current = head;
        while (current != null) {
            if (current.val == value) {
                return current;
            }
            current = current.next;
        }
        return null;
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
