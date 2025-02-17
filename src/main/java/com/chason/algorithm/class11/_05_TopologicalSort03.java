package com.chason.algorithm.class11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 第三种拓扑排序的方式
 * 还是02的题
 * 使用最大深度的方式
 */
public class _05_TopologicalSort03 {

    /*
    对于任一节点 X Y
    如果X能走出的最大深度 >= Y的最大深度
    那么X的拓扑序在前面
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        if (graph == null) {
            return null;
        }

        HashMap<DirectedGraphNode, GraphInfo> cache = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node, cache);
        }

        ArrayList<GraphInfo> infos = new ArrayList<>();
        for (GraphInfo info :cache.values()) {
            infos.add(info);
        }

        infos.sort(new MyComparator());

        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        for (GraphInfo info : infos) {
            result.add(info.node);
        }

        return result;
    }

    public static class MyComparator implements Comparator<GraphInfo> {
        @Override
        public int compare(GraphInfo o1, GraphInfo o2) {
            return o2.deep - o1.deep;
        }
    }

    // 获取传入对应节点的最大深度
    public static GraphInfo process(DirectedGraphNode node, HashMap<DirectedGraphNode, GraphInfo> cache) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        int deep = 0;
        for (DirectedGraphNode dn: node.neighbors) {
            deep = Math.max(deep, process(dn, cache).deep);
        }
        GraphInfo info = new GraphInfo(node, deep + 1);
        cache.put(node, info);
        return info;
    }

    // 最大深度
    public static class GraphInfo {
        DirectedGraphNode node;
        int deep;
        public GraphInfo (DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
}
