package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 判断一棵二叉树是不是搜索二叉树
 * 搜索二叉树：
 * 对于任何一个二叉树及其子树，都满足，他的左子树所有节点的值 < 头节点 他的右子树所有节点的值 > 头节点
 * 注意：经典搜索二叉树中的值不能重复
 *
 */
public class _09_IsBinarySearchTree {

    /*
    方法1： 中序遍历一下，值一直在上升就是搜索二叉树
     */
    public static boolean isBST1 (TreeNode head) {

        if (head == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();

        // 执行中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = head;
        while (!stack.isEmpty() || curr != null) {

            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                list.add(curr.val);
                curr = curr.right;
            }
        }

        int step = list.get(0);
        for (int i=1; i<list.size(); i++) {
            if (list.get(i) <= step) {
                return false;
            }
            step = list.get(i);
        }

        return true;
    }

    // 使用二叉树的递归套路
    /*
      如果是二叉搜索树，对于以head为头的节点一定要满足：
      1. head的左树是搜索二叉树
      2. head的右树是搜索二叉树
      3. 左树的最大值 < head.val
      4. 右树的最小值 > head.val
     */
    public static boolean isBST2(TreeNode head) {
        return process(head).isBST;
    }

    public static TreeInfo process(TreeNode head) {

        // 当边界条件不方便构建info的时候直接返回Null
        if (head == null) {
            return null;
        }

        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);

        int max = head.val;
        if (head.left != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (head.right != null) {
            max = Math.max(max, rightInfo.max);
        }

        int min = head.val;
        if (head.left != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (head.right != null) {
            min = Math.min(min, rightInfo.min);
        }

        // 判断是否是BST
        boolean isBST = true;
        if (head.left != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (head.right != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (head.left != null && leftInfo.max >= head.val) {
            isBST = false;
        }
        if (head.right != null && rightInfo.min <= head.val) {
            isBST = false;
        }

        return new TreeInfo(isBST, max, min);
    }


    static class TreeInfo {
        boolean isBST;  // 是否是搜索二叉树
        int max; // 整棵树的最大值
        int min; // 整棵树的最小值
        public TreeInfo (boolean b, int max, int min) {
            this.isBST = b;
            this.max = max;
            this.min = min;
        }
    }

    // ------------------ test ---------------------

    public static void main(String[] args) {

        int maxLevel = 4;
        int maxValue = 100;
        int testTime = 100000;

        boolean isSuccess = true;
        for (int i=0; i<testTime; i++) {

            TreeNode head = createRandomTree(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                isSuccess = false;
            }
        }

        System.out.println(isSuccess ? "Success!" : "Failed!");

    }

    public static TreeNode createRandomTree(int maxLevel, int maxValue) {
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
