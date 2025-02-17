package com.chason.algorithm.class11;

import com.chason.algorithm.class10.UnionSetMap;

import java.util.*;

/**
 * 最小生成树算法
 * 最小生成树算法应用在无向图上
 * 边一定是有权重的
 * 定义： 在不影响所有边连通的情况下，所有边加起来的最小值是多少
 * 最典型的应用：最短路径，收费最低路径
 */
public class _06_Kruskal {

    /*
    思路：考察所有的边
    所有的边从权值小的往权值大的考虑（贪心）
    如果当前的边不会形成环，要
    如果当前的边会形成环，不要
    判断会不会形成环，使用并查集！只有在一个集合中的才会形成环
     */
    public static Set<Edge> kruskal(Graph graph) {

        Collection<Node> nodes = graph.nodes.values();
        UnionMap<Node> unionMap = new UnionMap<>(nodes);

        // use edges weight to create comparator
        // Min-heap
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }

        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            Node from = edge.from;
            Node to = edge.to;
            if (!unionMap.isSameSet(from, to)) {
                result.add(edge);
                unionMap.union(from, to);
            }
        }

        return result;
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // This is the union set for the problem
    public static class UnionMap<T> {

        public HashMap<T, T> parents;
        public HashMap<T, Integer> sizeMap;

        public UnionMap (Collection<T> elements) {

            if (elements == null || elements.isEmpty()) {
                return;
            }

            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (T t : elements) {
                parents.put(t, t);
                sizeMap.put(t, 1);
            }
        }

        public void union(T a, T b) {

            T aHead = root(a);
            T bHead = root(b);

            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);

            if (aSize >= bSize) {
                parents.put(bHead, aHead);
                sizeMap.put(aHead, aSize + bSize);
            } else {
                parents.put(aHead, bHead);
                sizeMap.put(bHead, aSize + bSize);
            }
        }

        public boolean isSameSet(T a, T b) {
            return root(a) == root(b);
        }

        public T root(T ele) {

            T root = ele;
            Queue<T> queue = new LinkedList<>();

            while (root != parents.get(root)) {
                queue.add(root);
                root = parents.get(root);
            }

            while (!queue.isEmpty()) {
                parents.put(queue.poll(), root);
            }

            return root;
        }

    }

}
