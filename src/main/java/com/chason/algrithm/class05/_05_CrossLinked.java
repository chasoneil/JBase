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


    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        Node in = head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        Node last = head.next.next.next.next.next = new Node(6);
        last.next = in;

        Node node = getFirstCircle2(head);
        System.out.println(node.val);
    }

    /*
        Step1: 针对每个单链表，如果有环，返回第一个入环的位置；如果没环，返回null
        Step2: 讨论可能出现的情况：
        1. 两个链表都是无环的 使用容器的方法
     */
    public static Node getFirstCross(Node head1, Node head2) {

        Node loop1 = getFirstCircle2(head1);
        Node loop2 = getFirstCircle2(head2);

        Set<Node> set = new HashSet<>();
        if (loop1 == null && loop2 == null) {

            Node curr = head1;


        }

        return null;
    }

    // 确定两个链表无环，找第一个相交节点
    /*
        1. 首先遍历一次链表1 记录长度 a 和 链表的最末尾节点
        2. 遍历一次链表2 记录长度 b 和链表的最末尾节点
        3. 如果他们的末尾节点是同一个，则两链表相交，否则不相交
        4. 如果两链表相交 求长度差 c = a -b; 让长的那个先走c步，然后再两个同时出发，两个链表的同一个节点就是相交的点
     */
    public static Node noLoop(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return null;
        }

        int length1 = 0;
        Node end1;
        Node curr = head1;
        while (curr != null) {
            length1++;
            curr = curr.next;
        }
        end1 = curr;

        int length2 = 0;
        Node end2;
        curr = head2;
        while (curr != null) {
            length2++;
            curr = curr.next;
        }
        end2 = curr;

        if (end1 != end2) { // 不相交
            return null;
        }

        int step = Math.abs(length1 - length2);
        Node start = length1 >= length2 ? head1 : head2;
        Node other = length1 < length2 ? head1 : head2;

        curr = start;
        while (step > 0) {
            curr = curr.next;
            step--;
        }

        while (curr != null) {
            if (curr == other) {
                return curr;
            }

            curr = curr.next;
            other = other.next;
        }

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

        if (head == null || head.next == null || head.next.next == null) {
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
