package com.chason.algrithm.class02.linked;

import com.chason.algrithm.class02.base.Node;
import com.chason.algrithm.utils.LinkedUtils;

/**
 * delete target node from single linked list
 */
public class DeleteNode {


    public static Node deleteNode(Node head, int target) {

        if (head == null) {
            return null;
        }

        Node newHead = head;

        while ((Integer) newHead.value == target) {
            newHead = newHead.next;
        }

        Node prev = newHead;
        Node curr = newHead;

        while (curr != null) {
            if ((Integer)curr.value == target) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return newHead;
    }

    public static void main(String[] args) {

        Node head = LinkedUtils.buildRandomSingle(10, 10);
        int target = -5;

        LinkedUtils.print(head, null);

        head = deleteNode(head, target);
        LinkedUtils.print(head, null);

    }

}
