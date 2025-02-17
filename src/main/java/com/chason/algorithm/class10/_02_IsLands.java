package com.chason.algorithm.class10;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/number-of-islands/description/
 */
public class _02_IsLands {

    /*
        依次遍历整个数组，使用递归的方法感染一片，计算最终感染的次数
     */
    public static int numIslands1(char[][] grids) {

        if (grids == null || grids.length < 1) {
            return 0;
        }

        int row = grids.length;
        int col = grids[0].length;

        int lands = 0;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (grids[i][j] == '1') {
                    infect(grids, i, j);
                    lands++;
                }
            }
        }
        return lands;
    }

    private static void infect(char[][] grids, int i, int j) {

        int row = grids.length;
        int col = grids[0].length;

        if (i < 0 || i == row || j < 0 || j == col || grids[i][j] != '1') {
            return;
        }

        // 将沿途感染的节点都改成别的，不然会无法走出递归
        grids[i][j] = '2';

        // 将四个方向都感染一遍
        infect(grids, i-1, j);
        infect(grids, i+1, j);
        infect(grids, i, j-1);
        infect(grids, i, j+1);
    }

    // 使用并查集实现
    // 因为并查集中的元素要么是0 要么是1 无法分辨每个元素，所以需要做一层包装
    // 并查集使用map的方式实现
    public static int numIslands2(char[][] grids) {

        if (grids == null || grids.length < 1) {
            return 0;
        }

        List<Dot> lists = new ArrayList<>();
        int row = grids.length;
        int col = grids[0].length;
        Dot[][] dots = new Dot[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (grids[i][j] == '1') {
                    // 使用对象代替完全相同的'1' 从而做区分
                    dots[i][j] = new Dot();
                    lists.add(dots[i][j]);
                }
            }
        }

        UnionSetMap<Dot> unionSetMap = new UnionSetMap<>(lists);

        // 之所有先处理第一行和第一列，是因为判断完之后之后的所有元素就不用加边界判断了，节约了常数时间

        // 遍历的是 (1, 0) (2, 0) (3, 0) (4, 0)
        // 也就是第一列 省略(0, 0) 是因为(0, 0)既没有左也没有上
        for (int i=1; i<row; i++) {
            // 因为是第一列，所以只要考虑上就行了
            if (grids[i-1][0] == '1' && grids[i][0] == '1') {
                unionSetMap.union(dots[i-1][0], dots[i][0]);
            }
        }

        // (0, 1) (0, 2)...
        for (int j=1; j<col; j++) {
            if (grids[0][j-1] == '1' && grids[0][j] == '1') {
                unionSetMap.union(dots[0][j-1], dots[0][j]);
            }
        }

        // 其他的位置一定又有上也有左，不用判断边界问题
        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (grids[i-1][j] == '1' && grids[i][j] == '1') {
                    unionSetMap.union(dots[i-1][j], dots[i][j]);
                }

                if (grids[i][j-1] == '1' && grids[i][j] == '1') {
                    unionSetMap.union(dots[i][j-1], dots[i][j]);
                }
            }
        }

        return unionSetMap.sets;
    }

    // 使用并查集的包装类
    public static class Dot {}


    public static int numIslands3(char[][] grids) {

        return 0;
    }

}
