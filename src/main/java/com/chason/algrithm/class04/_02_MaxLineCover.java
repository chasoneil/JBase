package com.chason.algrithm.class04;

/**
 * 最大线段重合
 * 给定很多线段  每个线段都有两个数 [start,end]表示线段开始和结束的位置，左右都是闭区间
 * 规定： 1. 线段开始和结束都是整数 2. 线段的重合区域长度一定大于1 （只有一个点不叫重合）
 * 返回 线段最多重合区域中，包含了几条线段
 */

/**
 * 常规思路 : 找到所有线段最左侧的点  找到所有线段最右侧的点 （最大最小边界）[min, max]
 * 从 min + 0.5 出发 比如 min 是 1 那么从1.5 开始
 * 看每个 .5 是几条线段重合 ，求最大的重合
 * 时间复杂度 O((max-min) * N)
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 解题步骤：
 * 1. 将所有的线段按照开始位置从小到大排序
 * 2. 建立一个小根堆，依次遍历线段
 * 3. 每遍历一个线段，从小根堆中弹出小于该线段开始值的值
 * 4. 将这个线段的结束位置加入小根堆，此时，小根堆中的数值(size)就是以该线段开始位置为头的重合的数值
 * 5. 找出这个数值最大的返回
 * 时间复杂度 O(N*logN)
 */
public class _02_MaxLineCover {

    // 线段
    static class Line {

        int start;
        int end;

        public Line (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {

        Line l1 = new Line(1, 10);
        Line l2 = new Line(2, 11);
        Line l3 = new Line(4, 8);
        Line l4 = new Line(4, 6);

        Line[] lines = new Line[]{l1, l2, l3, l4};
        //System.out.println(getMaxLineCover(lines));
        System.out.println(getMaxLineCover2(lines));
    }

    // 常规思路的办法
    public static int getMaxLineCover(Line[] lines) {

        if (lines == null || lines.length == 0) {
            return 0;
        }

        if (lines.length < 2) {
            return 1;
        }

        int min = lines[0].start;
        int max = lines[0].end;

        for (int i=0; i<lines.length; i++) {
            min = Math.min(lines[i].start, min);
            max = Math.max(lines[i].end, max);
        }

        float left = min + 0.5f;
        float right = max - 0.5f;

        int maxCount = 0;  // 最大重合线段的数量
        for (float i=left; i<=right; i++) {
            int count = 0;   // 当前位置重合线段的数量
            for (int j=0; j<lines.length; j++) {
                if (lines[j].start < i && lines[j].end > i) {
                    count++;
                }
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    public static int getMaxLineCover2(Line[] lines) {

        if (lines == null || lines.length == 0) {
            return 0;
        }

        if (lines.length < 2) {
            return 1;
        }

        // 将所有的线段按照 start 从小到大排序   时间复杂度 O(N * logN)
        Arrays.sort(lines, new LineComparator());

        PriorityQueue<Integer> heap = new PriorityQueue<>();    // 小根堆

        int maxCount = 0;
        for (int i=0; i< lines.length; i++) {

            // 从堆中弹出 <= 当前线段的开始位置的值
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }

            heap.add(lines[i].end);
            maxCount = Math.max(maxCount, heap.size());
        }

        return maxCount;
    }

    static class LineComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }


}
