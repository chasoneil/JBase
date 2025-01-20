package com.chason.algrithm.class07;

import com.chason.algrithm.class06.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现二叉树的序列化和反序列化
 */
public class _02_serializeIBT {

    private static int index = 0;

    public static void main(String[] args) {
        TreeNode head = TreeNode.buildSerializeTreeNode();
        List<String> list = preSerial(head);
        System.out.println(list);
    }

    // 序列化
    public static List<String> preSerial(TreeNode head) {
        List<String> list = new ArrayList<>();
        pre(head, list);  // 将二叉树序列化之后放到list中
        return list;
    }

    public static TreeNode buildByList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return preBuildTreeNode(list);
    }

    public static void pre(TreeNode head, List<String> list) {
        if (head == null) {
            list.add("#");    // 这里虽然没有return 但是没有继续调用递归也是结束
        } else {
            list.add(String.valueOf(head.val));
            pre(head.left, list);
            pre(head.right, list);
        }
    }

    public static TreeNode preBuildTreeNode(List<String> list) {
        String val = list.get(index++);
        TreeNode head = null;
        if (val.equals("#")) {
            return head;
        } else {
            head = new TreeNode(Integer.parseInt(val));
            head.left = preBuildTreeNode(list);
            head.right = preBuildTreeNode(list);
        }
        return head;
    }


}
