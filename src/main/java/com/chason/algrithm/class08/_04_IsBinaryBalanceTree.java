package com.chason.algrithm.class08;

import com.chason.algrithm.class06.TreeNode;

/**
 * 给定一个二叉树的头节点 head，判断这棵树是不是平衡二叉树
 * 平衡二叉树：
 * 在一个二叉树中，左右子树相差的高度的绝对值 < 1 每颗子树都如此
 */
public class _04_IsBinaryBalanceTree {

    /**
     * 思路： 根据平衡二叉树的概念
     * 如果一颗树的左树是平衡 右树是平衡，且左树的高度 - 右树的高度的绝对值 < 2他就是平衡二叉树
     * 那么需要的条件是 左树是不是平衡 且左树的高度 右树是不是平衡且高度
     * @param head
     * @return
     */
    public static boolean isBalanceTree(TreeNode head) {

        if (head == null) {
            return true;
        }

        return treeBalanceProcess(head).isBalance;
    }

    /*
        给定任意一颗树的头节点， 返回这棵树的TreeInfo信息
     */
    public static TreeInfo treeBalanceProcess(TreeNode head) {

        if (head == null) {
            return new TreeInfo(true, 0);
        }

        TreeInfo leftInfo = treeBalanceProcess(head.left);
        TreeInfo rightInfo = treeBalanceProcess(head.right);

        // 以 head 为头节点的树的高度  左右子树中较大的那个 + 1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isBalance = true;
        if (!leftInfo.isBalance) {
            isBalance = false;
        }

        if (!rightInfo.isBalance) {
            isBalance = false;
        }

        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalance = false;
        }

        return new TreeInfo(isBalance, height);
    }

    /*
    使用一个类用来存放我们需要的信息
     */
    static class TreeInfo {
        public boolean isBalance;
        public int height;
        public TreeInfo(boolean b, int h) {
            isBalance = b;
            height = h;
        }
    }

    // 对数器
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTime = 100000;
    }

    public static TreeNode createRandomBT(int maxLevel, int maxValue) {
        return createTree(1, maxLevel, maxValue);
    }

    private static TreeNode createTree(int level, int maxLevel, int maxValue) {
        if (level > maxLevel) {
            return null;
        }
        TreeNode head = new TreeNode((int)(Math.random() * maxValue));
        head.left = createTree(level + 1, maxLevel, maxValue);
        head.right = createTree(level + 1, maxLevel, maxValue);
        return head;
    }

}
