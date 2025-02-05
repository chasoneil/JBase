package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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
        return  (1 << info.height) - 1 == info.nodes;
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

    /*
    常规的方法：
    如果一颗树是满二叉树，按层遍历二叉树的节点(宽度优先遍历)
    记录二叉树的高度，记录二叉树的节点数，然后验证高度和节点数的关系
     */
    public static boolean isFullBase(TreeNode head) {

        if (head == null) {
            return true;
        }

        // 队列宽度优先遍历
        TreeNode currEnd = head;
        TreeNode nextEnd = null;

        int height = 1;
        int nodes = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);

        TreeNode tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            nodes++;

            if (tmp.left != null) {
                queue.offer(tmp.left);
                nextEnd = tmp.left;
            }

            if (tmp.right != null) {
                queue.offer(tmp.right);
                nextEnd = tmp.right;
            }

            if (tmp == currEnd) {
                currEnd = nextEnd;
                height++;
            }
        }

        height--; // 因为每次是上层结束就结算，那么会多结算一层
        return (1 << height) - 1 == nodes;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTime = 100000;

        boolean isSuccess = true;
        for (int i=0; i<testTime; i++) {
            TreeNode head = createRandomTree(maxLevel, maxValue);
            if (isFull(head) != isFullBase(head)) {
                isSuccess = false;
                break;
            }
        }

        System.out.println(isSuccess ? "Pass!" : "Failed!");
    }

    public static TreeNode createRandomTree(int maxLevel, int maxValue) {

        if (maxLevel < 1) {
            return null;
        }

        int randomLevel = (int) (Math.random() * (maxLevel + 1));
        return createTree(1, randomLevel, maxValue);
    }

    public static TreeNode createTree(int level, int randomLevel, int maxValue) {

        if (level > randomLevel) {
            return null;
        }

        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = createTree(level + 1, randomLevel, maxValue);
        head.right = createTree(level + 1, randomLevel, maxValue);
        return head;
    }


    private static TreeNode buildFullTree() {

        TreeNode head = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        head.left = node2;
        head.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        return head;
    }
}
