package com.chason.algorithm.class09;

import javax.sound.sampled.Port;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入正整数组 costs[] 正整数组 profits[] 正整数K, 正整数M
 * costs[i] 表示i项目需要的花费
 * profits[i] 表示纯利润
 * K 表示你能做的最大的项目数
 * M 表示你的启动资金
 * 每做完一个项目，马上获得收益，不能并行做项目，求最大收益
 */
public class _04_MaxProjectEarn {

    /*

    组织一个小根堆，以花费为排列顺序
    再组织一个大根堆，以利润为排序，一开始为空

    开始遍历小根堆，从小根堆中依次弹出你能做的项目（从堆顶弹出即可）放到大根堆里去
    直到出现你的初始资金做不了的，暂停。
    从大根堆的堆顶弹出一个项目，选择做这个项目，初始自信变化
    继续上面的流程

     */
    public static int maxEarn1(int[] costs, int[] profits, int K, int M) {
        if (costs == null || profits== null || costs.length < 1 || profits.length < 1) {
            return 0;
        }

        if (costs.length != profits.length) {
            return 0;
        }

        int doneProject = 0;
        PriorityQueue<Project> cQ = new PriorityQueue<>(new CostComparator());
        PriorityQueue<Project> pQ = new PriorityQueue<>(new ProfitComparator());
        for (int i=0; i<costs.length; i++) {
            cQ.offer(new Project(costs[i], profits[i]));
        }

        while (doneProject > K) {
            while (!cQ.isEmpty() && cQ.peek().cost < M) {
                pQ.add(cQ.poll());
            }

            if (!pQ.isEmpty()) {
                Project p = pQ.poll();
                M += p.profit;
                doneProject++;
            } else {
                return M;
            }
        }

        return M;
    }

    public static class CostComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class ProfitComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    }

    private static class Project {
        int cost;
        int profit;
        public Project (int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

}
