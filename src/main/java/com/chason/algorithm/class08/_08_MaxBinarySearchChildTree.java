package com.chason.algorithm.class08;

import com.chason.algorithm.class06.TreeNode;

/**
 * 找到一颗二叉树的规模最大的搜索二叉树的子树，并返回头结点
 *
 * 暂无对数器
 */
public class _08_MaxBinarySearchChildTree {

    public static int getMaxBinarySearchChildTreeSize (TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxChildBST;
    }

    /*
       以x为头节点分为两种情况：
       1. 这棵树和x相关 （意思整棵树都是搜索二叉树）
          满足：
          （1）左树是不是搜索二叉树
          （2）右树是不是索索二叉树
          （3）左树的最大值 < x.val
          （4）右树的最小值是不是 > x.val
          （5）整颗数的size 【需要左树的size ,需要右树的size】
       2. 这棵树和x不相关 (要么就是左树中的搜索二叉树，要么就是右树中的搜索二叉树)
          （1）左树是不是搜索二叉树
          （2）右树是不是搜索二叉树
          （3）以x为头节点的话，左树的size
          （4）以x为头节点的话，右树的size

       整理info : 是不是搜索二叉树 ; max ; min ; 最大搜索二叉子树的size ; 自己的size
       如果 size == 最大搜索二叉子树的size 则是搜索二叉树
     */
    public static TreeInfo process (TreeNode head) {

        if (head == null) {
            return null;
        }

        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);

        int max = head.val;
        int min = head.val;
        int nodes = 1;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            nodes += leftInfo.nodes;
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            nodes += rightInfo.nodes;
        }

        // 计算maxChildBST
        /*
        1. 判断左树是不是二叉搜索子树
         */
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxChildBST == leftInfo.nodes);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxChildBST == rightInfo.nodes);

        int size1 = -1;
        if (leftBST) {
            size1 = leftInfo == null ? 0 : leftInfo.maxChildBST;
        }

        int size2 = -1;
        if (rightBST) {
            size2 = rightInfo == null ? 0 : rightInfo.maxChildBST;
        }

        boolean leftFlag = false;
        boolean rightFlag = false;
        if (leftBST && rightBST) {  // 接下来要判断满不满足 加上本节点是不是搜索二叉树
            if ((leftInfo != null && leftInfo.max < head.val) || leftInfo == null) {
                leftFlag = true;
            }

            if ((rightInfo != null && rightInfo.min > head.val) || rightInfo == null) {
                rightFlag = true;
            }
        }

        int size3 = -1;
        if (leftFlag && rightFlag) {
            size3 = leftInfo.nodes + rightInfo.nodes + 1;
        }

        return new TreeInfo(nodes, max, min, Math.max(size1, Math.max(size2, size3)));
    }

    static class TreeInfo {
        int nodes;  // 整颗树的节点数
        int max;
        int min;
        int maxChildBST; // 整颗树的最大二叉搜索子树的大小
        public TreeInfo (int n, int max, int min,int maxChildBST) {
            this.nodes = n;
            this.max = max;
            this.min = min;
            this.maxChildBST = maxChildBST;
        }
    }

}
