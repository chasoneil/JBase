package com.chason.algorithm.class07;

import com.chason.algorithm.class06.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点
 *
 * 宽度优先遍历? 重点是获取每层的结尾的位置
 *
 */
public class _04_MaxWidthNodes {

    public static void main(String[] args) {


    }

    /*
    思路： 通过有限几个变量额外空间复杂度O(1)，时间复杂度O(N)的方法
    1. 基于宽度优先遍历，已经可以通过队列实现按层级别的遍历
    2. 设置几个变量 当前层的最后一个节点 currEnd; 下一层的最后一个节点 nextEnd; 当前层的节点数 currNode; 层最大节点 maxNode;
    3. 从根节点出发的时候，currEnd 你肯定知道，那么在后续的遍历中，每遍历一个节点，看看他有没有子节点，从左往右依次更新nextEnd
    4. 根据上面的节点，保证每次我在遍历第N层的时候，不管到了哪个节点，nextEnd一定是这个节点能接触到的N+1层的最右节点
    5. 如果遍历到了当前层结束的节点(有currEnd)，证明本层结束，可以结算，同时，nextEnd一定是正确的。
     */
    public static int maxWidthNodes(TreeNode head) {

        if (head == null) {
            return 0;
        }

        TreeNode currEnd;
        TreeNode nextEnd;
        int currNode = 0;
        int maxNode = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        currEnd = head;
        nextEnd = null;

        while (!queue.isEmpty()) {

            TreeNode tmp = queue.poll();

            if (tmp.left != null) {
                queue.offer(tmp.left);
                nextEnd = tmp.left;
            }

            if (tmp.right != null) {
                queue.offer(tmp.right);
                nextEnd = tmp.right;
            }
            currNode++;

            if (tmp == currEnd) {
                maxNode = Math.max(maxNode, currNode);
                currNode = 0;
                currEnd = nextEnd;
            }
        }

        return maxNode;
    }

    public static int maxWidthByMap() {
        return 0;
    }


}
