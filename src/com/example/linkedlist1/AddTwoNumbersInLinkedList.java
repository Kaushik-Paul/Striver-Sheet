package com.example.linkedlist1;

import java.util.Arrays;

/**
 * Problem: Add Two Numbers in Linked List
 *
 * Given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains
 * a single digit. Add the two numbers and return the sum as a linked list.
 *
 * Example 1:
 *   Input:  linkedList1 = [5, 4], linkedList2 = [4]
 *   Output: [9, 4]
 *   Explanation: 45 + 4 = 49.
 *
 * Example 2:
 *   Input:  linkedList1 = [4, 5, 6], linkedList2 = [1, 2, 3]
 *   Output: [5, 7, 9]
 *   Explanation: 654 + 321 = 975.
 *
 * Constraints:
 *   The number of nodes in each linked list is in the range [1, 100].
 *   0 <= Node.val <= 9
 *   It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbersInLinkedList {

    /**
     * Adds two numbers represented by linked lists where digits are
     * stored in reverse order.
     *
     * Algorithm:
     *   - Initialize a dummy head and a carry variable to 0.
     *   - Traverse both lists simultaneously, adding corresponding digits
     *     along with any carry.
     *   - If one list is exhausted, continue traversal with the remaining
     *     list and carry.
     *   - After both lists are exhausted, if carry > 0, append a new node
     *     with the carry value.
     *
     * Time complexity:  O(max(N, M))
     * Space complexity: O(max(N, M))  (a new node is created for every digit of the sum)
     */
    public ListNode addTwoNumbers(ListNode list1, ListNode list2) {
        // Dummy anchor so we can append without special-casing the head.
        ListNode dummyHead = new ListNode(-1);
        int carryOver = 0;
        ListNode temp = dummyHead;

        // Traverse both lists while both have nodes remaining.
        while (list1 != null && list2 != null) {
            int sum = list1.val + list2.val + carryOver;
            carryOver = 0;

            // If the sum overflows a single digit, keep the unit place and carry the rest.
            if (sum >= 10) {
                carryOver = sum / 10;
                sum = sum % 10;
            }

            ListNode newNode = new ListNode(sum);
            temp.next = newNode;
            temp = temp.next;
            list1 = list1.next;
            list2 = list2.next;
        }

        // Process remaining nodes in list1, still propagating any carry.
        while (list1 != null) {
            int sum = list1.val + carryOver;
            carryOver = 0;

            if (sum >= 10) {
                carryOver = sum / 10;
                sum = sum % 10;
            }

            ListNode newNode = new ListNode(sum);
            temp.next = newNode;
            temp = temp.next;
            list1 = list1.next;
        }

        // Process remaining nodes in list2, still propagating any carry.
        while (list2 != null) {
            int sum = list2.val + carryOver;
            carryOver = 0;

            if (sum >= 10) {
                carryOver = sum / 10;
                sum = sum % 10;
            }

            ListNode newNode = new ListNode(sum);
            temp.next = newNode;
            temp = temp.next;
            list2 = list2.next;
        }

        // If a carry is still left after both lists are exhausted, it becomes a new digit.
        if (carryOver != 0) {
            ListNode newNode = new ListNode(carryOver);
            temp.next = newNode;
            temp = temp.next;
        }

        // dummyHead.next is the real head; dummyHead itself is just a placeholder.
        return dummyHead.next;
    }

    public static void main(String[] args) {
        AddTwoNumbersInLinkedList solution = new AddTwoNumbersInLinkedList();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - [5, 4] + [4] -> [9, 4]",
                new int[]{5, 4},
                new int[]{4},
                new int[]{9, 4}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - [4, 5, 6] + [1, 2, 3] -> [5, 7, 9]",
                new int[]{4, 5, 6},
                new int[]{1, 2, 3},
                new int[]{5, 7, 9}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Different lengths with carry propagation - [9, 9] + [1] -> [0, 0, 1]",
                new int[]{9, 9},
                new int[]{1},
                new int[]{0, 0, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single digit addition resulting in carry - [5] + [5] -> [0, 1]",
                new int[]{5},
                new int[]{5},
                new int[]{0, 1}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Adding zero - [0] + [7, 3] -> [7, 3]",
                new int[]{0},
                new int[]{7, 3},
                new int[]{7, 3}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Multiple carries across multiple digits - [9, 9, 9] + [9, 9] -> [8, 9, 0, 1]",
                new int[]{9, 9, 9},
                new int[]{9, 9},
                new int[]{8, 9, 0, 1}
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
            AddTwoNumbersInLinkedList solution,
            String testName,
            int[] list1Input,
            int[] list2Input,
            int[] expected
    ) {
        try {
            ListNode list1 = buildList(list1Input);
            ListNode list2 = buildList(list2Input);
            ListNode expectedList = buildList(expected);

            ListNode actualList = solution.addTwoNumbers(list1, list2);

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
            System.out.println("List1:    " + Arrays.toString(list1Input));
            System.out.println("List2:    " + Arrays.toString(list2Input));
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("List1:     " + Arrays.toString(list1Input));
            System.out.println("List2:     " + Arrays.toString(list2Input));
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
