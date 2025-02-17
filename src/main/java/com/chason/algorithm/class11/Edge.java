package com.chason.algorithm.class11;

/**
 * 图结构中的边
 */
public class Edge {

    // 边的值
    public int weight;
    public Node from;
    public Node to;

    public Edge (int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
