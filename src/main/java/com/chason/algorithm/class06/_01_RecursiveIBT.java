package com.chason.algorithm.class06;

/**
 * 递归实现二叉树的先序中序后序的遍历
 * 通过递归的方式实现，本质上是递归序
 */
public class _01_RecursiveIBT {

    public static void main(String[] args) {

        TreeNode node1 = TreeNode.buildTestTreeNode();

        pre(node1);
        System.out.println();
        mid(node1);
        System.out.println();
        after(node1);

    }

    // 先序遍历  顺序：头左右
    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }

        System.out.print(head.val + " ");
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历 左头右
    public static void mid(TreeNode head) {

        if (head == null) {
            return;
        }

        mid(head.left);
        System.out.print(head.val + " ");
        mid(head.right);
    }

    // 后序遍历 左右头
    public static void after (TreeNode head) {

        if (head == null) {
            return;
        }

        after(head.left);
        after(head.right);
        System.out.print(head.val + " ");
    }
}
