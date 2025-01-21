package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode No.431
 * 将一个多叉树序列化成二叉树
 * 然后可以将这个二叉树反序列化回多叉树
 *
 * 思路： 对于任何一颗多叉树的某个节点x
 * 我们规定，如果x有子节点，那么转化为二叉树的时候，他的子节点全部变成他二叉树中的左子树的右边界
 * 例如 有一颗多叉树
 *         a
 *       /| |\
 *      b c d e
 *     /     /|\
 *    i     f g h
 *   那么转化为二叉树结构是
 *        a   a有子节点，所有的子节点转为了左子树的右边界
 *       /
 *      b     b有子节点，所有的子节点转为了左子树的右边界
 *     / \
 *    i   c
 *         \
 *          d
 *           \
 *            e  e有子节点，所有的子节点转为了左子树的右边界
 *           /
 *          f
 *           \
 *            g
 *             \
 *              h
 */
public class _03_NaryTreeToBinaryTree {

    // 系统提供的原Node(多叉树)
    public static class Node {

        int val;

        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static void main(String[] args) {

    }

    /*
    将一颗多叉树转化成二叉树
     */
    public static TreeNode encode (Node root) {

        if (root == null) {
            return null;
        }

        // 多叉树的root 当然也是二叉树的head
        TreeNode head = new TreeNode(root.val);
        // 多叉树的所有子节点往二叉树的左节点上挂，如果有多个子节点，那就是左节点的右边界
        head.left = en(root.children);
        return head;
    }

    // 把多叉树的所有子节点变成二叉树的右边界，返回子树头节点
    public static TreeNode en(List<Node> children) {

        TreeNode head = null;
        TreeNode curr = null;

        for (Node child : children) {
            TreeNode node = new TreeNode(child.val);
            // 如果有边界的第一个节点（其实就是父节点左节点的第一个）为空
            if (head == null) {
                head = node;
            } else {
                curr.right = node;
            }
            curr = node;
            curr.left = en(child.children);
        }
        return head;
    }

    /*
    将一颗二叉树反序列化成原来的多叉树
     */
    public static Node decode(TreeNode root) {

        if (root == null) {
            return null;
        }

        return new Node(root.val, de(root.left));
    }

    // 传给的是二叉树的左树的头节点，从这个节点开始，所有的右边界都是上一个节点的子节点
    public static List<Node> de(TreeNode root) {

        if (root == null) {
            return null;
        }

        List<Node> children = new ArrayList<>();

        while (root != null) {
            Node node = new Node(root.val, de(root.left));
            children.add(node);
            root = root.right;
        }
        return children;
    }
}
