package com.chason.algorithm.class08;

import com.chason.algorithm.class06.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树的头节点 head
 * 给定其他的两个节点 a b
 * 返回 a 和 b的最低公共祖先
 */
public class _10_PublicParent {

    /*
    方法一： 遍历整颗树，使用一个Map，将这棵树的每个节点和他的父节点存下来
    然后以 a 或者 b 任意一个节点开始，从表中找到这个节点路过的所有的父节点放到一个Set中
    然后从另一个节点开始走同样的事儿，当set中出现的第一个重复节点，就是我们要找的节点
     */
    public static TreeNode getPublicRoot1(TreeNode head, TreeNode a, TreeNode b) {

        if (head == null) {
            return null;
        }

        Map<TreeNode, TreeNode> parents = new HashMap<>();
        fillMap(head, parents);
        Set<TreeNode> route = new HashSet<>();

        // a 开始
        TreeNode curr = a;
        route.add(curr);
        while (curr != null) {
            TreeNode p = parents.get(curr);
            route.add(p);
            curr = p;
        }

        curr = b;
        while (curr != null) {
            if (route.contains(curr)) {
                return curr;
            }
            curr = parents.get(curr);
        }

        return null;
    }

    public static void fillMap(TreeNode head, Map<TreeNode, TreeNode> parents) {

        if (head == null) {
            return;
        }

        fillMap(head.left, parents);
        fillMap(head.right, parents);

        if (head.left != null) {
            parents.put(head.left, head);
        }

        if (head.right != null) {
            parents.put(head.right, head);
        }
    }

    /*
    使用二叉树的递归套路：
    对于任意节点x
    和x相关：
    1. a 和 b 一个在左树一个在右树
    2. x 就是 a 或者 b 且他的左或者右数下面有另一个节点，这种情况答案就是x
    和x不相关
    1. 公共节点在左树
    2. 公共节点在右树

    总结： 能不能找到a; 能不能找到b, 答案节点Node
     */
    public static TreeNode getPublicRoot2(TreeNode head, TreeNode a, TreeNode b) {
        return process(head, a, b).ans;
    }

    public static TreeInfo process(TreeNode head, TreeNode a, TreeNode b) {

        if (head == null) {
            return new TreeInfo(false, false, null);
        }

        TreeInfo leftInfo = process(head.left, a, b);
        TreeInfo rightInfo = process(head.right, a, b);

        boolean findA = leftInfo.findA || rightInfo.findA || (head == a);
        boolean findB = leftInfo.findB || rightInfo.findB || (head == b);

        TreeNode ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if(rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            /*
             当左树右树都没有公共节点的时候
             1. 一个在左树，一个在右树 即找到了a 又找到了b
             2. x 本身就是 a 或者 b 且在下面找到了另一个节点
             */
            if (findA && findB) {
                ans = head;
            }
        }

        return new TreeInfo(findA, findB, ans);
    }

    public static class TreeInfo {
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public TreeInfo (boolean fa, boolean fb, TreeNode ans) {
            findA = fa;
            findB = fb;
            this.ans = ans;
        }
    }


    // ---------------------- 对数器 -------------------------
    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static TreeNode pickRandomOne(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            TreeNode o1 = pickRandomOne(head);
            TreeNode o2 = pickRandomOne(head);
            if (getPublicRoot2(head, o1, o2) != getPublicRoot1(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
