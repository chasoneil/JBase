package com.chason.algorithm.class11;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 * 使用一个队列和set
 */
public class _01_BFSGraph {

    // 图的宽度优先遍历
    // 宽度优先遍历一定要有开始的节点
    public static void bfsGraph(Node start) {

        if (start == null) {
            return;
        }

        HashSet<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        set.add(start);

        Node cur = start;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.val);
            for (Node node : cur.nexts) {
                if (!set.contains(node)) {
                    queue.add(node);
                    set.add(node);
                }
            }
        }
    }
}
