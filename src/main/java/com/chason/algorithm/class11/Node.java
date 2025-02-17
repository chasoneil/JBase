package com.chason.algorithm.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * 图结构中的点集合
 */
public class Node {

    // 节点的值
    public int val;
    // 指向这个节点的边的数量
    public int in;
    // 从这个节点指出的边的数量
    public int out;
    // 这个节点直接指向的下一个节点
    public List<Node> nexts;
    // 从当前节点出发的边的集合
    public List<Edge> edges;

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
