package com.chason.algorithm.class08;

import com.chason.algorithm.class06.TreeNode;

/**
 * 给定一个二叉树的头结点
 * 每两个节点之间都有距离 距离为1
 * 求整个二叉树的最大距离(沿途的距离)
 *
 * 例如 二叉树
 *
 *          1
 *         / \
 *        2   3
 *      / \    \
 *     4   5    6
 *
 * 从节点6 到节点2的距离就是 6 > 3 > 1 > 2 那么距离就是4
 */
public class _06_BinaryTreeMaxDistance {

    /*
    分析： 最大距离分为和Node有关或者和Node无关
    1. 和node有关，那么最大距离是左树到Node的最大距离 + 右树到Node的最大距离 + 1
    左树到Node的最大距离其实就是高度，右树同理
    2. 和node无关， 那么要么就是左树的最大距离就是结果，或者右树的最大距离就是结果
    基于上述分析，我们发现只要最大距离和树的高度即可。
     */
    public static int getMaxDistance(TreeNode head) {
        TreeInfo info = process(head);
        return info.maxDistance;
    }

    public static TreeInfo process (TreeNode head) {

        if (head == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);

        // 最大距离是要么左树的最大距离，要么右树的最大距离，要么就是左树height + 右树height + 1 三个中求最大的
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new TreeInfo(maxDistance, height);
    }

    static class TreeInfo {
        int maxDistance;
        int height;
        public TreeInfo (int m, int h) {
            maxDistance = m;
            height = h;
        }
    }

    public static void main(String[] args) {

    }

    public static void runTest() {
        int maxLevel = 5;
        int maxValue = 100;
        int testTime = 100000;

        for (int i=0; i<testTime; i++) {
            TreeNode head = createRandomTreeNode(maxLevel, maxValue);


        }

    }

    public static TreeNode createRandomTreeNode(int maxLevel, int maxValue) {

        if (maxLevel < 1) {
            return null;
        }

        return createTree(1, maxLevel, maxValue);
    }

    public static TreeNode createTree(int currentLevel, int maxLevel, int maxValue) {

        if (currentLevel > maxLevel) {
            return null;
        }

        TreeNode node = new TreeNode((int) (Math.random() * maxValue));
        node.left = createTree(currentLevel + 1, maxLevel, maxValue);
        node.right = createTree(currentLevel + 1, maxLevel, maxValue);
        return node;
    }
}
