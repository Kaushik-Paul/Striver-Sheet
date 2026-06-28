package com.example.linkedlist;

/**
 * A singly linked list node used across the linked list problems in
 * this package.
 *
 * Each node holds an integer value and a reference to the next node,
 * or {@code null} when it is the tail of the list.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
