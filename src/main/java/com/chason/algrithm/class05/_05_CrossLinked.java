package com.chason.algrithm.class05;

import java.util.HashSet;
import java.util.Set;

/**
 * 相交的链表问题
 * 给定两个可能有环也可能无环的单链表，头节点是head1 和 head2
 * 请实现一个函数，如果相交，请返回相交的第一个节点
 * 如果不相交，请返回null
 */
public class _05_CrossLinked {

    /*
        Step1: 针对每个单链表，如果有环，返回第一个入环的位置；如果没环，返回null


     */
    public static Node getFirstCross(Node head1, Node head2) {


        return null;
    }

    // step1: 方法1 使用hashSet 遍历链表，遍历一个就放进去set中一个，
    // 如果set某个时间发现set中出现了之前出现过的节点，那么这个节点就是第一个入环的节点
    public static Node getFirstCircle(Node head) {

        if (head == null || head.next == null) {
            return null;
        }

        Set<Node> set = new HashSet<>();
        Node curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return curr;
            }
            set.add(curr);
            curr = curr.next;
        }
        return null;
    }

    /*
       step1 方法2：
       使用两个指针 快慢指针，当快指针能走到null 意味着链表无环，否则快慢指针一定会在环中的某个节点相遇
       当两个节点相遇的时候，慢指针位置不动，快指针回到头位置，两个指针每次都移动1个节点
       两个指针再次相遇的地方一定是环的第一个交叉点
     */
    public static Node getFirstCircle2 (Node head) {

        if (head == null || head.next == null) {
            return null;
        }

        Node fast = head;
        Node slow = head;

        while (fast != null) {

            if (fast.next == null) {
                return null;
            }

            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

}
