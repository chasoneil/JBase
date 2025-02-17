package com.chason.algorithm.class11;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 所谓拓扑排序，就是一种依次递进的关系
 * 例如：
 * 生产A 需要 B C
 * 生产C 需要 D E F
 * 生产B 需要 D H
 *
 * 那么这张图的拓扑排序的顺序是A B C D E F H ... 获取其他相关的
 * 意思是 需要先有DH才能有B 需要先有DEF才能有C 需要先有BC才能有A
 * 他们之间是有顺序的，但是同一层没有顺序
 *
 */
public class _03_TopologicalSort01 {

    /*
        排序法：
        1. 找到所有入度为零的节点，这些节点就是头，也就是排序的开始
        2. 找到的入度为零的节点加入一个队列
        3. 弹出一个节点，消除这个节点所有的影响（删除从这个节点出发的边和下一个节点的入度）
        4. 压入新的入度为0的节点
        重复上述过程
     */
    public static List<Node> sortGraph(Graph graph) {

        if (graph == null) {
            return null;
        }

        // 节点的入度Map key： 对应的节点 value: 这个节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInNode = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInNode.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zeroInNode.isEmpty()) {
            Node temp = zeroInNode.poll();
            result.add(temp);

            // 消除这个节点的所有影响
            for (Node node : temp.nexts) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) == 0) {
                    zeroInNode.add(node);
                }
            }
        }

        return result;
    }


}
