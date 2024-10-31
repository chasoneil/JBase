package com.chason.algrithm.class05;

/**
 * 给你一个单链表的头结点 root
 * 如果链表是奇数个的，返回中点
 * 1. 偶数个节点返回上中点
 * 2. 偶数个返回下中点
 *
 * 奇数：返回中点的前一个节点
 * 偶数：
 * 1. 返回上中点的前一个节点
 * 2. 返回下中点的前一个
 *
 * 使用快慢指针的思路本质上其实就是起始出发点不同实现不同的效果
 */
public class _01_GetMidOfLink {

    public static void main(String[] args) {

        Node root = new Node(5);
        root.next = new Node(4);
        root.next.next = new Node(3);
        root.next.next.next = new Node(2);
        //root.next.next.next.next = new Node(1);

        Node target = getMidNodeNext(root);
        System.out.println(target.val);

    }


    static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 偶数上中点
     * 奇数中点
     */
    public static Node getMidNode(Node root) {

        if (root == null) {
            return null;
        }

        if (root.next == null) {
            return root;
        }

        Node f = root;
        Node s = root;

        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    /**
     * 奇数 中点
     * 偶数 下中点
     */
    public static Node getMidNodeNext(Node root) {

        if (root == null) {
            return null;
        }

        if (root.next == null) {
            return root;
        }

        Node f = root.next;
        Node s = root.next;

        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    /**
     * 奇数 返回中点的前一个
     * 偶数 返回上中点的前一个
     */
    public static Node getMidNodeBefore(Node root) {

        if (root == null) {
            return null;
        }

        if (root.next == null) {
            return root;
        }

        if (root.next.next == null) {
            return root;
        }

        Node f = root.next.next;
        Node s = root;

        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    /**
     * 奇数 返回中点的前一个
     * 偶数 返回下中点的前一个
     */
    public static Node getMidNodeAfter(Node root) {

        if (root == null) {
            return null;
        }

        if (root.next == null) {
            return root;
        }

        Node f = root.next;
        Node s = root;

        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    /**
     * 获取中点最基础的方法，全部走一遍
     * 奇数 中点
     * 偶数 上中点
     * @return
     */
    public static Node getMidBase(Node root) {
        if (root == null) {
            return null;
        }

        if (root.next == null) {
            return root;
        }

        int count = 0;
        Node curr = root;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        int midCount  = count / 2;
        curr = root;
        while (midCount > 0) {
            curr = curr.next;
            midCount--;
        }

        return curr;
    }

}
