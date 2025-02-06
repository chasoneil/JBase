package com.chason.algorithm.class08;


import java.util.Stack;

/**
 * 返回某个二叉树的后继节点,二叉树的结构如代码所示
 * 后继节点： 一颗二叉树中序遍历中，一个节点的下一个节点就是这个节点的后继节点
 */
public class _01_NextNode {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;  // parent node
        public TreeNode(int _val) {
            val = _val;
        }
    }


    public static void main(String[] args) {
        buildTreeNodeAndTest();
    }

    /*
        常规方法，给定这个节点，先通过parent找到整个二叉树的头
        然后再中序遍历，找到这个节点的下一个节点
        时间复杂度O(N)
     */
    public static TreeNode getNextBase(TreeNode node) {

        if (node == null) {
            return null;
        }

        TreeNode head = node;

        while (head.parent != null) {
            head = head.parent;
        }

        // 中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = head;

        boolean flag = false; // 记录当前遍历的节点是不是后继节点
        while (!stack.isEmpty() || tmp != null) {

            if (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            } else {
                tmp = stack.pop();
                if (flag) {
                    return tmp;
                }
                if (tmp == node) {
                    flag = true;
                }
                tmp = tmp.right;
            }

        }
        return null;
    }

    /*
    时间复杂度O(K) K是该节点到root节点的距离
    思路： 中序遍历中，有两种情况：
    1. 对于任意一个节点node, 如果他有右树，那么他的后继节点一定是右树上的最左孩子
    解释： 因为在中序遍历中 左 node 右 如果他有右数，那么接下来打印的就是右树的最左节点
    2. 如果任意节点node, 如果他没有右树，那么就往上找父节点，如果他是父节点的右节点继续往上找
    直到找到一个父节点，他是父节点的左树，那么这个父节点就是他的后继，如果找到root还没有达到这个条件
    那么证明这个node是整棵树的最右节点，那他没有后继。
    解释：上述找法，其实就是找某个左树的最右，如果我是某个左树的最右，那么接下来就应该打印这棵树的头节点了
    只有这两种情况。
     */
    public static TreeNode getNext(TreeNode node) {

        if (node == null) {
            return null;
        }

        TreeNode res = node;
        if (res.right != null) {
            res = res.right;
            while (res.left != null) {
                res = res.left;
            }
        } else {  // 该节点没有右树
            res = node.parent;
            while (res != null && res.right == node) {
                res = res.parent;
            }
        }

        return res;
    }

    private static void buildTreeNodeAndTest() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node2.parent = node1;
        node3.left = node6;
        node3.right = node7;
        node3.parent = node1;
        node4.parent = node2;
        node5.parent = node2;
        node6.parent = node3;
        node7.parent = node3;

        //TreeNode node = getNextBase(node3);
        TreeNode node = getNext(node3);
        System.out.println(node.val);
    }

}
