package com.chason.algrithm.class05;

import java.util.Stack;

/**
 * 给定一个单链表的头结点
 * 判断该链表是不是一个回文链表
 *
 * eg:
 * 1 -> 2 -> 3 -> 2 -> 1  是回文结构
 * 1 -> 2 -> 2 -> 1 是回文结构
 * 1 -> 2 -> 3 不是回文结构
 *
 */
public class _02_HuiwenLink {

    public static void main(String[] args) {

        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(2);
        root.next.next.next.next = new Node(1);

        //System.out.println(isHuiwenBase(root));
        System.out.println(isHuiwen(root));

        System.out.println(root);

    }

    /**
     * 使用快慢指针，如果是偶数个，找上中点，如果是奇数个，找中点
     * 将链表的后半部分的指针调整方向，往前指
     * 再分别从开头和结尾出发，比对两个值，当两个指针其中有一个指向null的时候结束
     * 判断完成之后，再把链表调整回来
     *
     * @param head
     * @return
     */
    public static boolean isHuiwen(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        Node fast = head;
        Node slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 当前的slow已经是我们想要的位置了,从slow出发，对后半部分逆序，新的头节点 prev
        Node prev = null;
        Node curr = slow;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }


        boolean result = true;
        Node n1 = prev;
        Node n2 = head;
        // 此时有两个头节点 prev head
        while (n1 != null && n2 != null) {
            if (n1.val != n2.val) {
                result = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        // 不管怎么样，都要把链表的后半部分还原 从prev出发逆序后半部分
        // head 和 prev 都是头
        Node tmp = null;  // 新头不用保留
        curr = prev;
        while (curr != null) {
            Node next = curr.next;
            curr.next = tmp;
            tmp = curr;
            curr = next;
        }

        return result;
    }


    /**
     * 使用容器是最简单的办法
     * 将链表依次遍历压栈
     * 再出栈，依次比对元素的值是不是相等
     * @return
     */
    public static boolean isHuiwenBase(Node root) {

        if (root == null) {
            return true;
        }

        if (root.next == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        curr = root;
        while (!stack.isEmpty()) {
            if (stack.pop().val != curr.val) {
                return false;
            }
            curr = curr.next;
        }

        return true;
    }


    static class Node {
        int val;
        Node next;
        public Node (int val) {
            this.val = val;
        }
    }
}
