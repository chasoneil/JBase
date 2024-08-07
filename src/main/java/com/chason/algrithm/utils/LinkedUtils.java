package com.chason.algrithm.utils;

import com.chason.algrithm.class02.base.DNode;
import com.chason.algrithm.class02.base.Node;

public class LinkedUtils {


    public static Node buildRandomSingle(int maxValue, int maxSize) {

        if (maxSize < 0) {
            throw new RuntimeException("Max size can't be negative");
        }

        int randomSize = (int) (Math.random() * maxSize) + 1;

        if (randomSize == 0) {
            return null;
        }

        int rVal = ((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1);
        Node head = new Node(rVal);
        Node curr = head;
        for (int i=1; i<randomSize; i++) {
            Node node = new Node(((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1));
            curr.next = node;
            curr = curr.next;
        }
        return head;
    }

    public static DNode buildRandomDouble(int maxValue, int maxSize) {

        if (maxSize < 0) {
            throw new RuntimeException("Max size can't be negative");
        }

        int randomSize = (int) (Math.random() * maxSize) + 1;

        if (randomSize == 0) {
            return null;
        }

        int rVal = ((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1);
        DNode head = new DNode(rVal);
        head.prev = null;
        DNode curr = head;
        for (int i=1; i<randomSize; i++) {
            DNode node = new DNode(((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1));
            curr.next = node;
            node.prev = curr;
            curr = curr.next;
        }
        return head;

    }


    public static void print(Node head, DNode root) {

        StringBuilder sb = new StringBuilder();
        Node curr ;
        DNode curr1;
        if (head != null) {
            curr = head;
            while (curr != null) {
                sb.append(curr.value).append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }

        sb = new StringBuilder();
        if (root != null) {
            curr1 = root;
            while (curr1 != null) {
                sb.append(curr1.value).append(" <-> ");
                curr1 = curr1.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }


}
