package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如何判断一棵树是不是完全二叉树
 * 完全二叉树：
 * 每层都是满的，或者处于从左往右依次变满的状态
 */
public class _07_IsBinaryCompleteTree {

    public static void main(String[] args) {

        TreeNode head = buildTestTree();
        System.out.println(isFullBT(head));

    }

    /**
     * 思路： 按照二叉树的按层遍历
     * 1. 如果一个节点他只有右孩子，没有左孩子，他肯定不是完全二叉树
     * 2. 如果当你遍历到一个节点的时候，他的子孩子不完整（没有子，或者只有一个左）那么他后面的节点都是叶子节点
     * 叶子（不能有子节点了）
     * 3. 如果满足上述要求遍历完成，则是完全二叉树
     *
     * @return
     */
    public static boolean isFullBT(TreeNode head) {

        if (head == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);

        TreeNode tmp = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {

            tmp = queue.poll();

            if (leaf) {
                if (tmp.left != null || tmp.right != null) {
                    return false;
                }
            }

            if (tmp.right != null && tmp.left == null) { // 命中 1
                return false;
            }

            if (!(tmp.left != null && tmp.right != null)) {
                leaf = true;    // 命中 2 后面的所有节点都只能是叶子节点
            }

            if (tmp.left != null) {
                queue.offer(tmp.left);
            }

            if(tmp.right != null) {
                queue.offer(tmp.right);
            }
        }

        return true;
    }

    /*
     1. 以x为头节点的树，如果左树满，右树满 且高度相同 他是完全二叉树 (满树)
     2. 以x为头节点的树，如果左树是完全，右树是满，左树比右树高度 > 1
     3. 左树满，右树满 左树高度 = 右树高度 + 1
     4. 左树满，右树是完全二叉树， 高度相同
     需要：
          高度
          满没满
          是不是完全
     */
    public static boolean isCompleteTree(TreeNode head) {
        if (head == null) {
            return true;
        }

        return process(head).isComplete;
    }

    public static TreeInfo process(TreeNode head) {

        if (head == null) {
            return new TreeInfo(0, true, true);
        }

        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);

        int height = 0;
        boolean isFull = false;
        boolean isComplete = false;

        height = Math.max(leftInfo.height, rightInfo.height) + 1;

        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isFull = true;
            isComplete = true;
        }

        if (leftInfo.isComplete && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isComplete = true;
        }

        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isComplete = true;
        }

        if (leftInfo.isFull && rightInfo.isComplete && leftInfo.height == rightInfo.height) {
            isComplete = true;
        }

        return new TreeInfo(height, isFull, isComplete);
    }

    static class TreeInfo {
        public int height;
        public boolean isFull;
        public boolean isComplete;

        public TreeInfo (int h, boolean f, boolean c) {
            height = h;
            isFull = f;
            isComplete = c;
        }
    }


    private static TreeNode buildTestTree() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node2.right = node5;

        return node1;
    }


}
