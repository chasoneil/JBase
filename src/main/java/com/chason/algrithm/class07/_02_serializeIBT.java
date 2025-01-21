package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现二叉树的序列化和反序列化
 * 用数组会比较麻烦，用队列比较简单
 * 二叉树的序列化的方式支持先序和后序，一般我们只讨论先序，但是中序是不可能进行序列化的，有歧义
 *
 *
 */
public class _02_serializeIBT {

    private static int index = 0;

    public static void main(String[] args) {
        TreeNode head = TreeNode.buildSerializeTreeNode();
        // Queue<String> queue = preSerial(head);
        Queue<String> queue = levelSerial(head);
        System.out.println(queue);
    }

    // 序列化（先序）
    public static Queue<String> preSerial(TreeNode head) {
        Queue<String> queue = new LinkedList<>();
        pre(head, queue);  // 将二叉树序列化之后放到list中
        return queue;
    }

    // 反序列化（先序）
    public static TreeNode buildByQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preBuildTreeNode(queue);
    }

    public static Queue<String> levelSerial(TreeNode head) {

        Queue<String> queue = new LinkedList<>();
        if (head == null) {
            return queue;
        }

        queue.add(String.valueOf(head.val));
        Queue<TreeNode> help = new LinkedList<>(); // 这个队列用于按层方式进行遍历节点用
        help.offer(head);

        TreeNode tmp = null;
        while (!help.isEmpty()) {

            tmp = help.poll();

            /*
            一个节点的序列化，是他的父节点帮他完成的
            对于某个节点X的子节点，如果为空则只序列化，如果不为空则既压入队列又序列化
             */
            if (tmp.left != null) {
                queue.offer(String.valueOf(tmp.left.val));
                help.offer(tmp.left);
            } else{
                queue.offer("#");
            }

            if (tmp.right != null) {
                queue.offer(String.valueOf(tmp.right.val));
                help.offer(tmp.right);
            } else {
                queue.offer("#");
            }
        }

        return queue;
    }



    // 递归序列化二叉树
    public static void pre(TreeNode head, Queue<String> queue) {
        if (head == null) {
            queue.add("#");    // 这里虽然没有return 但是没有继续调用递归也是结束
        } else {
            queue.add(String.valueOf(head.val));
            pre(head.left, queue);
            pre(head.right, queue);
        }
    }

    public static TreeNode preBuildTreeNode(Queue<String> queue) {
        String val = queue.poll();
        if (val == null || val.equals("#")) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(val));
        head.left = preBuildTreeNode(queue);
        head.right = preBuildTreeNode(queue);
        return head;
    }

    // 按层的方式进行反序列化
    public static TreeNode buildByLevel(Queue<String> queue) {

        if (queue == null || queue.isEmpty()) {
            return null;
        }

        // 先构建头节点
        TreeNode head = buildTreeNodeOrNull(queue.poll());

        Queue<TreeNode> help = new LinkedList<>(); // 用来按层方式反序列化的帮助队列
        help.offer(head);

        TreeNode tmp = null;
        // 序列化的时候是父帮子序列化，反序列化一样
        while (!help.isEmpty()) {
            tmp = help.poll();  // 他就是头

            // 父帮子进行反序列化
            tmp.left = buildTreeNodeOrNull(queue.poll());
            tmp.right = buildTreeNodeOrNull(queue.poll());

            if (tmp.left != null) {
                help.offer(tmp.left);
            }

            if (tmp.right != null) {
                help.offer(tmp.right);
            }
        }
        return head;
    }

    private static TreeNode buildTreeNodeOrNull(String val) {

        if (val == null || val.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }


}
