package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的按层遍历 （宽度优先遍历）
 * 宽度优先遍历使用队列
 *
 */
public class _01_ByLevelBT {

    public static void main(String[] args) {

        TreeNode head = TreeNode.buildTestTreeNode();
        System.out.println("宽度(按层):");
        width(head);
        System.out.println();

    }

    /*
        1. 准备一个队列
        2. 将头节点入队列
        3. 弹出即打印，然后如果弹出的节点有左，则先入左，有右则再入右
     */
    public static void width(TreeNode head) {

        if (head == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(head);

        TreeNode tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            System.out.print(tmp.val + " ");
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }

            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
    }



}
