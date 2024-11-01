package com.chason.algrithm.class05;

/**
 * 请将单链表按照某个值划分成左边小 中间相等 右边大的形式
 */
public class _03_linkedSeperate {

    public static void main(String[] args) {

        Node head = new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(4);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(7);

        Node node = partition2(head, 4);
        System.out.println(node);

    }

    static class Node {

        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 将链表放到一个数组中，在数组中做partition
     * 然后再把这个链表串起来
     * @param head
     * @return
     */
    public static Node partition1(Node head, int target) {

        if (head == null || head.next == null) {
            return head;
        }

        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        int[] arr = new int[count];
        curr = head;
        int index = 0;
        while (curr != null) {
            arr[index++] = curr.val;
            curr = curr.next;
        }

        // 接下来对数组做partition
        int left = -1;
        int right = count;

        index = 0;
        while (index < right) {
            if (arr[index] < target) {
                swap(arr, ++left, index++);
            } else if (arr[index] == target) {
                index++;
            } else {
                swap(arr, index, --right);
            }
        }

        // partition 结束之后重构链表
        Node head1 = new Node(arr[0]);
        curr = head1;
        for (int i=1; i<arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }

        return head1;
    }

    public static void swap (int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 申请六个指针，分别是小于区域的头尾
     * 等于区域的头尾
     * 大于区域的头尾
     * 遍历链表，将链表中的元素使用上述六个变量连接
     * 最后将小尾串中头，中尾串大头
     * @param head
     * @param target
     * @return
     */
    public static Node partition2(Node head, int target) {

        if (head == null || head.next == null) {
            return head;
        }

        Node smallHead = null;
        Node smallTail = null;
        Node midHead = null;
        Node midTail = null;
        Node bigHead = null;
        Node bigTail = null;

        while (head != null) {

            if (head.val < target) {
                if (smallHead == null) {
                    smallHead = smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            } else if (head.val == target) {
                if (midHead == null) {
                    midHead = midTail = head;
                } else {
                    midTail.next = head;
                    midTail = midTail.next;
                }
            } else {
                if (bigHead == null) {
                    bigHead = bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = bigTail.next;
                }
            }

            head = head.next;
        }

        // 结束之后将 小于区域的尾部串上等于区域的头 等于区域的尾部串上大于区域的头
        smallTail.next = midHead;
        midTail.next = bigHead;
        return smallHead;
    }


}
