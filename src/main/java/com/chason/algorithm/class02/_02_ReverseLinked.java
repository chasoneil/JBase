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
            push(stack, head);
            Node newHead = reverseSingle(head);

            if (!checkReverse(stack, newHead)) {
                suc = false;
                break;
            }
        }
        System.out.println(suc ? "Passed" : "Failed");
    }

    private static void push(Stack<Node> stack, Node head) {

        if (head == null) {
            return;
        }

        Node tmp = head;
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }
    }

    // 反转链表之后，原链表的头已经无效了，所以只能用新链表的头做测试
    private static boolean checkReverse(Stack<Node> stack, Node head) {

        if (head == null) {
            return true;
        }

        while (!stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
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
