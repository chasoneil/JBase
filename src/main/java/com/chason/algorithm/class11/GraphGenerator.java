package com.chason.algorithm.class11;

/**
 * 图转化器
 * 将各种各样的结构构建成我们自定义的图结构
 */
public class GraphGenerator {

    /*
        将常见的图表现结构 二维数组
        [5, 0, 7] 表示边的权重是5 从 0 -> 7
        [1, 3, 1] 表示边的权重是1 从 3 -> 1
        当然这个二维数组的一定是三列的数组
     */
    public static Graph generateGraphArray(int[][] matrix) {
        Graph graph = new Graph();
        int length = matrix.length;
        for (int i=0; i<length; i++) {
            int weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            // 如果这两个节点不存在，就建出来
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
               graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);

            // 补充点集的信息
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }


}
