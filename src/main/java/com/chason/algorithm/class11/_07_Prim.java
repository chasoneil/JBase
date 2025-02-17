package com.chason.algorithm.class11;

import java.util.*;

/**
 * Prim算法也是做最小生成树的
 * 1. start from any node
 * 2. when one node was added in the collection, then the edges from this node was unlocked.
 * 3. select the min edge from unlocked edges, if there was a circle drop it, no circle choose it
 * 4. when all nodes were chose, it was finished.
 */
public class _07_Prim {

    public static List<Edge> prim (Graph graph) {

        if (graph == null) {
            return null;
        }

        // unlocked edges
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());

        // reached(unlocked) nodes
        HashSet<Node> nodeSet = new HashSet<>();

        List<Edge> result = new ArrayList<>();

        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    Edge e = queue.poll();
                    Node toNode = e.to;
                    if (!nodeSet.contains(toNode)) {
                        result.add(e);
                        nodeSet.add(node);
                        // unlock new edges
                        for (Edge nextEdge :toNode.edges) {
                            queue.add(nextEdge);
                        }
                    }
                }
            }
            // break;
        }
        return result;
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


}
