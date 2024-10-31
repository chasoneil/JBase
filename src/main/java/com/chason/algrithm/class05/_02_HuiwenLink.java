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
        root.next.next = new Node(2);
        root.next.next.next = new Node(1);

        System.out.println(isHuiwenBase(root));
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
