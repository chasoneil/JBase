package com.chason.algorithm.class02;

import com.chason.algorithm.class02.base.Node;
import com.chason.algorithm.utils.LinkedUtils;

/**
 * delete target node from single linked list
 */
public class _01_DeleteNode {

    public static Node<Integer> deleteNode(Node<Integer> head, int target) {

        if (head == null) {
            return null;
        }

        Node<Integer> newHead = head;

        while (newHead.value == target) {
            newHead = newHead.next;
        }

        Node<Integer> prev = newHead;
        Node<Integer> curr = newHead;

        while (curr != null) {
            if (curr.value == target) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return newHead;
    }

    public static void main(String[] args) {

        Node<Integer> head = LinkedUtils.buildRandomSingle(10, 10);
        int target = -5;

        LinkedUtils.print(head, null);

        head = deleteNode(head, target);
        LinkedUtils.print(head, null);

    }

}
