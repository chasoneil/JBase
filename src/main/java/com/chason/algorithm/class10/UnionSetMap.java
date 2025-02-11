package com.chason.algorithm.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集 使用Map实现
 * @param <T>
 */
public class UnionSetMap<T> {

    // 使用一张表代替指针key： 某个节点 value: 这个节点的父节点
    public HashMap<T, T> parents;

    // 只有代表节点才有size
    public HashMap<T, Integer> sizeMap;

    // 初始化并查集
    public UnionSetMap(List<T> values) {
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (T t : values) {
            parents.put(t, t);
            sizeMap.put(t, 1);
        }
    }

    // 给定任何一个节点，找到代表节点
    private T root(T t) {
        Stack<T> stack = new Stack<>();
        T root = t;
        while (root != parents.get(root)) {
            stack.add(root);
            root = parents.get(root);
        }

        // 将沿途的所有节点都设置成root
        while (!stack.isEmpty()) {
            parents.put(stack.pop(), root);
        }
        return root;
    }

    // 将a元素所在的集合和b元素所在的集合合并
    public void union(T a, T b) {
        T aHead = root(a);
        T bHead = root(b);

        // 说明不是一个集合可以合并
        if (aHead != bHead) {
            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);
            T bigHead = aSize > bSize ? aHead : bHead;
            T smallHead = aHead == bigHead ? bHead : aHead;
            // 小挂大
            parents.put(smallHead, bigHead);
            sizeMap.put(bigHead, aSize + bSize);
            sizeMap.remove(smallHead);
        }
    }

    // a 样本和 b 样本在不在一个集合
    // 判断两个代表节点是不是同一个
    public boolean isSameSet(T a, T b) {
        return root(a) == root(b);
    }
}
