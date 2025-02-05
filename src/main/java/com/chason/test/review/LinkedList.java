package com.chason.test.review;

public class LinkedList {

    static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    public static Node reverseNode(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node next = head.next;
        while (next != null) {
            head.next = prev;
            prev = head;
            head = next;
            next = head.next;
        }

        return prev;
    }

}
