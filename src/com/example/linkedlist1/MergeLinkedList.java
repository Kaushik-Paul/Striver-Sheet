package com.example.linkedlist1;

import java.util.Arrays;

/**
 * Problem: Merge Two Sorted Linked Lists
 *
 * Given the heads of two sorted (ascending) singly linked lists, merge
 * them into a single sorted list and return its head. The result must
 * be made up of the original nodes by splicing their pointers, not by
 * copying values into new nodes.
 *
 * Example 1:
 *   Input:  list1 -> 1 -> 2 -> 4
 *           list2 -> 1 -> 3 -> 4
 *   Output: head -> 1 -> 1 -> 2 -> 3 -> 4 -> 4
 *   Explanation: The two sorted lists are merged into a single sorted
 *   list by walking both pointers and always picking the smaller head.
 *
 * Example 2:
 *   Input:  list1 -> null
 *           list2 -> 0
 *   Output: head -> 0
 *   Explanation: An empty list contributes nothing, so the other list
 *   is returned as-is.
 *
 * Constraints:
 *   0 <= number of nodes in each list <= 50
 *   -100 <= node.val <= 100
 *   Both lists are sorted in non-decreasing order.
 */
public class MergeLinkedList {

    /**
     * Merges two sorted linked lists into a single sorted list and
     * returns the head of the merged list.
     *
     * Algorithm (dummy head + two pointers):
     *   - A dummy node is used as a stable anchor so we never have to
     *     special-case the head of the result.
     *   - A tail pointer walks both input lists, attaching the smaller
     *     of the two current heads to the result each step.
     *   - When one list is exhausted, the remaining tail of the other
     *     is appended as-is (it is already sorted).
     *
     * Time complexity:  O(n + m)
     * Space complexity: O(1)  (only pointer rewiring, no extra nodes)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Dummy anchor so we can append without special-casing the head.
        ListNode dummyNode = new ListNode(-1);
        // Tail always points at the last node of the merged result so far.
        ListNode temp = dummyNode;

        while (list1 != null && list2 != null) {
            // Pick the smaller of the two current heads, then advance it.
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }

            temp = temp.next;
        }

        // One list is exhausted: splice in the rest of the other (already sorted).
        if (list1 != null) {
            temp.next = list1;
        } else if (list2 != null) {
            temp.next = list2;
        }

        // dummyNode.next is the real head; dummyNode itself is just a placeholder.
        return dummyNode.next;
    }

    public static void main(String[] args) {
        MergeLinkedList solution = new MergeLinkedList();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Basic example from problem statement",
                new int[]{1, 2, 4},
                new int[]{1, 3, 4},
                new int[]{1, 1, 2, 3, 4, 4}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "First list is empty - second list is returned as-is",
                new int[]{},
                new int[]{0},
                new int[]{0}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Second list is empty - first list is returned as-is",
                new int[]{5},
                new int[]{},
                new int[]{5}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Both lists are empty - merged result is null",
                new int[]{},
                new int[]{},
                new int[]{}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Lists of equal length with no overlapping values",
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{1, 2, 3, 4, 5, 6}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Lists of equal length with full overlap",
                new int[]{1, 2, 3},
                new int[]{1, 2, 3},
                new int[]{1, 1, 2, 2, 3, 3}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All values identical across both lists",
                new int[]{2, 2, 2},
                new int[]{2, 2},
                new int[]{2, 2, 2, 2, 2}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Negative values - merged list stays sorted",
                new int[]{-9, -3, 0},
                new int[]{-7, -1, 4},
                new int[]{-9, -7, -3, -1, 0, 4}
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
            MergeLinkedList solution,
            String testName,
            int[] list1Input,
            int[] list2Input,
            int[] expected
    ) {
        try {
            ListNode list1 = buildList(list1Input);
            ListNode list2 = buildList(list2Input);
            ListNode expectedList = buildList(expected);

            ListNode actualList = solution.mergeTwoLists(list1, list2);

            String actualStr = listToString(actualList);
            String expectedStr = listToString(expectedList);

            if (actualStr.equals(expectedStr)) {
                System.out.println(
                        "[PASS] " + testName
                                + " | list1=" + listToString(buildList(list1Input))
                                + ", list2=" + listToString(buildList(list2Input))
                                + " -> result=" + actualStr
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("List1:   " + Arrays.toString(list1Input));
            System.out.println("List2:   " + Arrays.toString(list2Input));
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("List1:    " + Arrays.toString(list1Input));
            System.out.println("List2:    " + Arrays.toString(list2Input));
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
