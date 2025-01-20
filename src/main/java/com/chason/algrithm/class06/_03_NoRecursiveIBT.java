package com.chason.algrithm.class06;

import java.util.Stack;

/**
 * 非递归实现二叉树的各种遍历
 * 先序： 栈
 * 后序： 栈
 * 中序： 栈
 */
public class _03_NoRecursiveIBT {


    public static void main(String[] args) {
        TreeNode head = TreeNode.buildTestTreeNode();

        System.out.println("先序：");
        pre(head);
        System.out.println();

        System.out.println("中序：");
        mid(head);
        System.out.println();

        System.out.println("后序1：");
        after1(head);
        System.out.println();
    }

    /*
        1. 使用栈
        2. 先把head压入栈
        3. 然后开始弹出，弹出一个节点，就去检查他的子节点，有右先压入右，有左再压入左
        4. 直到栈中元素弹空
     */
    public static void pre(TreeNode head) {

        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);

        TreeNode curr = null;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            System.out.print(curr.val + " ");
            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    /*
        1. 使用栈
        2. 从头节点开始，压入一个节点，将这个节点的整条左边界全部进栈
        3. 从栈中弹出节点打印，压入当前弹出节点的右孩子
        4. 重复2 3
     */
    public static void mid(TreeNode head) {

        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = head;

        while (!stack.isEmpty() || curr != null) {  // 注意这里的条件
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
        }
    }

    /*
        方法1： 在先序遍历的基础上 先序 ： 头左右
        通过改变先序遍历中的方式（压栈的顺序），我们能轻松获得头右左这个顺序
        如果我再使用一个栈，将头右左压栈再弹出，顺序就是左右头，就是后序
        这个方法理解很简单，但是需要使用两个栈
     */
    public static void after1(TreeNode head) {

        if (head == null) {
            return;
        }

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(head);

        TreeNode tmp = null;
        while (!s1.isEmpty()) {
            tmp = s1.pop();
            s2.push(tmp);

            if (tmp.left != null) {
                s1.push(tmp.left);
            }

            if (tmp.right != null) {
                s1.push(tmp.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }
    }


}
