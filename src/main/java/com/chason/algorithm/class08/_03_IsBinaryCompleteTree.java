package com.chason.algorithm.class08;

import com.chason.algorithm.class06.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如何判断一棵树是不是完全二叉树
 * 完全二叉树：
 * 每层都是满的，或者处于从左往右依次变满的状态
 */
public class _03_IsBinaryCompleteTree {

    /**
     * 思路： 按照二叉树的按层遍历
     * 1. 如果一个节点他只有右孩子，没有左孩子，他肯定不是完全二叉树
     * 2. 如果当你遍历到一个节点的时候，他的子孩子不完整（没有子，或者只有一个左）那么他后面的节点都是叶子节点
     * 叶子（不能有子节点了）
     * 3. 如果满足上述要求遍历完成，则是完全二叉树
     */
    public static boolean isCompleteBT1(TreeNode head) {

        if (head == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);

        TreeNode tmp;
        boolean leaf = false;
        while (!queue.isEmpty()) {

            tmp = queue.poll();

            // 发现了有不完整的子节点的节点，那么后面都要是叶子节点
            if (leaf) {
                if (tmp.left != null || tmp.right != null) {
                    return false;
                }
            }

            // 命中条件1
            if (tmp.right != null && tmp.left == null) {
                return false;
            }

            // 命中 2 后面的所有节点都只能是叶子节点
            if (!(tmp.left != null && tmp.right != null)) {
                leaf = true;
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
    public static boolean isCompleteBT2(TreeNode head) {
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

    public static class TreeInfo {
        public int height;
        public boolean isFull;
        public boolean isComplete;

        public TreeInfo (int h, boolean f, boolean c) {
            height = h;
            isFull = f;
            isComplete = c;
        }
    }

    // ---------- 对数器 --------------
    public static void main(String[] args) {

        int maxLevel = 5;
        int maxValue = 100;
        int testTime = 100000;
        boolean suc = true;

        for (int i=0; i<testTime; i++) {
            TreeNode head = createRTree(maxLevel, maxValue);
            if (isCompleteBT1(head) != isCompleteBT2(head)) {
                suc = false;
                break;
            }
        }

        System.out.println(suc ? "Pass" : "Failed");
    }

    public static TreeNode createRTree(int maxLevel, int maxValue) {
        return createTree(1, maxLevel, maxValue);
    }

    public static TreeNode createTree(int cl, int ml, int mv) {

        // Math.random < 0.3 是构建空树
        if (cl > ml || Math.random() < 0.3) {
            return null;
        }

        TreeNode head = new TreeNode((int)(Math.random() * mv));
        head.left = createTree(cl+1, ml, mv);
        head.right = createTree(cl+1, ml, mv);
        return head;
    }



}
