package com.chason.algorithm.class11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 一种图结构的数据表达方式，他由点和边组成
 */
public class Graph {

    public Map<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph () {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
