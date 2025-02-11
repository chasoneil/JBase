package com.chason.algorithm.class10;

/**
 * 使用arr实现并查集
 */
public class UnionSetArray<T> {

    // 等同于map中的parents： parent[i] = K表示i的父是K
    private int[] parent;

    // 等同于map中的sizeMap: size[i] = k表示，i作为代表节点的时候他的并查集的元素个数是k
    // 如果i不是代表节点，那么这个size[i]将毫无意义
    private int[] size;

    // 代替map中的栈，存放找代表节点过程中路过的所有元素
    private int[] help;

    // 这个并查集中有多少个集合
    private int sets;

    /*
     初始化： 给你的是一个数组，数组中的每个元素是独立的元素，将这个数组初始化成并查集
     数组中的元素不能重复
     */
    public UnionSetArray (int N) {

        parent = new int[N];
        size = new int[N];
        help = new int[N];
        for (int i=0; i<N; i++) {
            parent[i] = i;
        }

    }


}
