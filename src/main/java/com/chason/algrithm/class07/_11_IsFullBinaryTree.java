package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

/**
 * 判断一颗树是不是满二叉树
 */
public class _11_IsFullBinaryTree {

    /*
    判断一颗树是不是满二叉树：
    如果一颗N层的满二叉树的节点个数一定是 2^N - 1
    所以info 需要 层数 节点数
     */
    public static boolean isFull(TreeNode head) {
        if (head == null) {
            return true;
        }

        TreeInfo info = process(head);
        return  1 << info.height - 1 == info.nodes;
    }

    public static TreeInfo process(TreeNode head) {
        if (head == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new TreeInfo(height, nodes);
    }

    static class TreeInfo {
        int height;
        int nodes;
        public TreeInfo (int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static void main(String[] args) {

    }
}
