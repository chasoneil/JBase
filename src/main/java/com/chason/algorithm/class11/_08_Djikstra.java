package com.chason.algorithm.class11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 迪锐克斯拉算法
 *
 * 在一个有向图中，边有权重且一定为证
 * 从一个规定的点出发，能抵达的点最小权重
 * 如果是到不了的点，距离是正无穷
 *
 */
public class _08_Djikstra {

    public static Map<Node, Integer> djikstra1(Node from) {

        HashMap<Node, Integer> result = new HashMap<>();
        result.put(from, 0); // 到自己的距离是0

        HashSet<Node> selectedNode = new HashSet<>();
        Node minDisNode = getMinButNotSelectedNode(result, selectedNode);

        // 还有没遍历完的点
        while (minDisNode != null) {

            int distance = result.get(minDisNode);
            for (Edge edge : minDisNode.edges) {  // 从当前节点出发的所有边
                Node toNode = edge.to;
                if (!selectedNode.contains(toNode)) {  // 这个点从来没去过，那么一开始的最大距离是无穷大
                    result.put(toNode, edge.weight + distance);
                } else {    // 这个点已经去过了
                    int oriDistance = result.get(toNode);
                    result.put(toNode, Math.min(oriDistance, edge.weight + distance));
                }
            }
            selectedNode.add(minDisNode);
            minDisNode = getMinButNotSelectedNode(result, selectedNode);
        }

        return result;
    }

    // 从距离的map中选中一个没有被选过的，但是距离最小的点
    public static Node getMinButNotSelectedNode(Map<Node, Integer> map, HashSet<Node> selectedNode) {
        Node result = null;
        for (Node node : map.keySet()) {
            if (result == null) {
                result = node;
                continue;
            }

            if (!selectedNode.contains(node) && map.get(node) < map.get(result)) {
                result = node;
            }
        }
        return result;
    }

}
