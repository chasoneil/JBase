package com.chason.algorithm.class11;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class _02_DFSGraph {

    /*
        深度优先遍历的思维是：
        1. 一条路如果没走完就一直往下走
        2. 如果走完了，就往回返回，再尝试走下一条路

        非递归的方式
     */
    public static void dfsGraph(Node start) {

        if (start == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.push(start);
        System.out.println(start.val);
        Node cur = start;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            for (Node node : cur.nexts) {
                if (!set.contains(node)) {
                    stack.push(cur);
                    stack.push(node);
                    System.out.println(node.val);
                    set.add(node);
                    break;
                }
            }
        }

    }

}
