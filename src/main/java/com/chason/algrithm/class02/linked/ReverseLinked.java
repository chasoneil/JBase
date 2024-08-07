package com.chason.algrithm.class02.linked;

import com.chason.algrithm.class02.base.DNode;
import com.chason.algrithm.class02.base.Node;
import com.chason.algrithm.utils.LinkedUtils;

/**
 *  reverse linked list
 */
public class ReverseLinked {

    /**
     * reverse single linked list
     * @return
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


    public static void main(String[] args) {

//        Node head = LinkedUtils.buildRandomSingle(10, 10);
//        LinkedUtils.print(head, null);
//        Node newHead = reverseSingle(head);
//        LinkedUtils.print(newHead, null);
        DNode head = LinkedUtils.buildRandomDouble(10, 10);
        LinkedUtils.print(null, head);
        DNode newHead = reverseDouble(head);
        LinkedUtils.print(null, newHead);
    }

}
