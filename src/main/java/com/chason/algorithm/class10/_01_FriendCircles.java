package com.chason.algorithm.class10;

/**
 * 并查集练习1：
 * <a href="https://leetcode.cn/problems/number-of-provinces/description/">leetcode</a>
 */
public class _01_FriendCircles {

    public static int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionSet unionSet = new UnionSet(N);

        for (int i=0; i<N; i++) {
           for (int j=i+1; j<N; j++) {
               if (isConnected[i][j] == 1) {
                   unionSet.union(i, j);
               }
           }
        }
        return unionSet.sets;
    }

    public static class UnionSet {

        // 等同于map中的parents： parent[i] = K表示i的父是K
        private int[] parent;

        // 等同于map中的sizeMap: size[i] = k表示，i作为代表节点的时候他的并查集的元素个数是k
        // 如果i不是代表节点，那么这个size[i]将毫无意义
        private int[] size;

        // 代替map中的栈，存放找代表节点过程中路过的所有元素
        private int[] help;

        // 这个并查集中有多少个集合
        public int sets;

        /*
         初始化： 给你的是一个数组，数组中的每个元素是独立的元素，将这个数组初始化成并查集
         数组中的元素不能重复
         */
        public UnionSet (int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i=0; i<N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 找到元素n的代表节点
        public int root(int n) {
            int index = 0;
            while (parent[n] != n) {
                help[index++] = n;
                n = parent[n];
            }

            // 将途径的每个节点都挂到代表节点下（扁平化）
            for (int i=0; i<help.length; i++) {
                parent[help[i]] = n;
            }
            return n;
        }

        public void union(int a, int b) {
            int aRoot = root(a);
            int bRoot = root(b);
            if (aRoot != bRoot) {

                // b 要挂到 a 上
                if (size[aRoot] >= size[bRoot]) {
                    size[aRoot] += size[bRoot];
                    parent[bRoot] = aRoot;
                    size[bRoot] = 0;
                } else {
                    // a 挂到 b 上
                    size[bRoot] += size[aRoot];
                    parent[aRoot] = bRoot;
                    size[aRoot] = 0;
                }
                sets--;
            }
        }

        public boolean isSameSet(int a, int b) {
            return root(a) == root(b);
        }

    }

}
