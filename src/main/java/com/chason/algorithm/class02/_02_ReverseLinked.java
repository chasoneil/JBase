package com.chason.algorithm.class02;

import com.chason.algorithm.class02.base.DNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 *  reverse linked list
 */
public class _02_ReverseLinked {

    /**
     * reverse single linked list
     * @return newHead
     */
    public static Node reverseSingle(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static DNode reverseDouble(DNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        DNode prev = null;
        DNode curr = head;

        while (curr != null) {

            DNode next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }

        return prev;
    }


    public static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    // ===== 对数器 ====
    public static void main(String[] args) {

        int maxLength = 10;
        int maxValue = 100;
        int testTime = 100000;
        boolean suc = true;

        for (int i=0; i<testTime; i++) {
            Node head = generateLinkedList(maxLength, maxValue);
            Stack<Node> stack = new Stack<>();
            Node curr = head;
            while (curr != null) {
                stack.push(curr);
                curr = curr.next;
            }

            Node newHead = reverseSingle(head);
            curr = newHead;
            while (curr != null) {
                if (curr.value != stack.pop().value) {
                    suc = false;
                    break;
                }
            }
        }
        System.out.println(suc ? "Passed" : "Failed");
    }



    //  传入两个链表的头，判断两个链表是否是反转链表
    private static boolean sucReverse(Node head1, Node head2) {

        Stack<Integer> stack = new Stack<>();
        while (head1 != null) {
            stack.push(head1.value);
            head1 = head1.next;
        }

        ArrayList<Integer> list1 = new ArrayList<>();
        while (head2 != null) {
            list1.add(head2.value);
            head2 = head2.next;
        }

        for (int i=0; i<list1.size(); i++) {
            if (list1.get(i) != stack.pop().intValue()) {
                return false;
            }
        }

        return true;
    }

    private static Node generateLinkedList(int maxLength, int maxValue) {
        int rLength = (int) (Math.random() * maxLength);
        Node head = null;
        Node prev = head;
        for (int i=0; i<rLength; i++) {
            int rValue = (int)(Math.random() * maxValue) - (int)(Math.random() * maxValue);
            Node node = new Node(rValue);
            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
        }
        return head;
    }

}
