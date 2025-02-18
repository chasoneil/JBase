package com.chason.algorithm.class02;

/**
 * delete target node from single linked list
 */
public class _01_DeleteNode {

    // 删除节点涉及删除头
    public static Node delNode(Node head, int target) {

        if (head == null) {
            return head;
        }

        // 来到第一个head 不是target的位置
        Node newHead = head;
        while (newHead != null) {

            if (newHead.value != target) {
                break;
            }
            newHead = newHead.next;
        }

        Node curr = newHead;
        Node prev = newHead;
        while (curr != null) {
            if (curr.value == target) {
                prev.next = curr.next;   // 保证prev.next在移动
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return newHead;
    }


    public static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    /*
        给定一个单链表的头节点和一个target
        返回删除这个target之后的链表长度
     */
    private static int getOtherNodeLength (Node head, int target) {

        if (head == null) {
            return 0;
        }

        int length = 0;
        Node curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        int targetCount = 0;
        curr = head;
        while (curr != null) {
            if (curr.value == target) {
                targetCount++;
            }
            curr = curr.next;
        }

        return length - targetCount;
    }

    private static int size(Node head) {
        if (head == null) {
            return 0;
        }

        int size = 0;
        Node curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    private static Node generateLinkedList(int maxLength, int maxValue) {
        int rLength = (int) (Math.random() * maxLength);
        Node head = null;
        Node prev = head;
        for (int i=0; i<rLength; i++) {
            int rValue = (int)(Math.random() * maxValue) - (int)(Math.random() * maxValue);
            Node node = new Node(rValue);
            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
        }
        return head;
    }


    // ========= 对数器 ============
    public static void main(String[] args) {

        int maxLength = 10;
        int maxValue = 100;
        int testTime = 100000;
        boolean suc = true;

        for (int i=0; i<testTime; i++) {
            Node head = generateLinkedList(maxLength, maxValue);
            int target = (int) (Math.random() * maxValue);
            int removeLength = getOtherNodeLength(head, target);
            Node newHead = delNode(head, target);
            if (newHead != null  && newHead.value == target) {
                suc = false;
                break;
            }
            if (size(newHead) != removeLength) {
                suc = false;
                break;
            }
        }
        System.out.println(suc ? "Passed" : "Failed");
    }

}
