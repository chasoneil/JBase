package com.chason.algorithm.class11;

import java.util.*;

/**
 * lintcode原题链接： https://www.lintcode.com/problem/127/
 * 给定特定的图结构，进行拓扑排序
 *
 */
public class _04_TopologicalSort02 {

    /*
    思路： 在图的拓扑排序中，对于任意一个节点x和y
    如果x下面走完包含的点是m个
    如果y下面走完包含的点是n个
    如果m > n, 那么x的拓扑序一定比y小
    原因： 如果x的拓扑序比y大，那么x是挂载y下面的，那么x的子节点不可能比y多
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        HashMap<DirectedGraphNode, GraphInfo> cache = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node, cache); // 建立缓存体系
        }

        ArrayList<GraphInfo> infos = new ArrayList<>();
        for (GraphInfo info : cache.values()) {
            infos.add(info);
        }
        infos.sort(new MyComparator());
        ArrayList<DirectedGraphNode> resultList = new ArrayList<>();

        for (GraphInfo gi : infos) {
            resultList.add(gi.node);
        }

        return resultList;
    }

    public static class MyComparator implements Comparator<GraphInfo> {

        // 因为比较的类型是long 所以需要经过一次转化
        @Override
        public int compare(GraphInfo o1, GraphInfo o2) {
            return o1.nodes - o2.nodes > 0 ? -1 : 1;
        }
    }

    /**
     *
     * @param node 进来的对应的图中的点
     * @param cache 缓存结构
     * @return
     */
    public static GraphInfo process(DirectedGraphNode node, HashMap<DirectedGraphNode, GraphInfo> cache) {

        // 缓存中已经命中，那就不用再算了
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        long deep = 0;
        // 缓存没有命中
        for (DirectedGraphNode dn : node.neighbors) {
            GraphInfo info = process(dn, cache);
            deep += info.nodes;
        }

        // 最后要加上自己
        GraphInfo info = new GraphInfo(node, deep + 1);
        // 缓存补充
        cache.put(node, info);
        return info;
    }

    public static class GraphInfo {
        // 这个节点
        DirectedGraphNode node;
        // 这个节点遍历下面的所有节点，走过的点数
        long nodes;
        public GraphInfo (DirectedGraphNode node, long deep) {
            this.node = node;
            this.nodes = deep;
        }
    }

    // 规定的图结构
    public static class DirectedGraphNode {
        // value
        int label;
        // 一个点的直接邻居
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

}
