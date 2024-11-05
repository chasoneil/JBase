package com.chason.algrithm.class05;

import java.util.HashMap;
import java.util.Map;

/**
 * 有一种特殊的链表：
 * 他有两个指针，除了单链表的next之外，他还有一个随机的指针，该指针可能指向任何其他节点(包括 null)
 * 实现一个方法，尝试复制这个链表的结构
 */
public class _04_RandomLinked {

    public static void main(String[] args) {

        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        head.rand = node2;
        node1.rand = node3;
        node2.rand = head;
        node3.rand = node1;

        Node newHead = copy(head);

        System.out.println(checkSameNode(head, newHead));
        // System.out.println(newHead);

    }

    /*
        方法1：使用hashMap
        key 老链表的节点  value 对应这个老节点我们新创建的节点
     */
    public static Node copy(Node head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {  // 单独的节点
            return new Node(head.val);
        }

        // 开始遍历节点 创建Map
        Node curr = head;
        Map<Node, Node> map = new HashMap<>();
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // map中已经包含了所有创建的节点，接下来就是要调整next 和 rand
        curr = head;
        Node newHead = map.get(curr);
        while (curr != null) {
            Node node = map.get(curr);
            Node next = map.get(curr.next);
            Node rand = map.get(curr.rand);
            node.next = next;
            node.rand = rand;
            curr = curr.next;
        }

        return newHead;
    }

    /*
        使用少数几个变量实现上述复制的功能
        遍历链表，在遍历到每个节点的时候，创建这个节点的复制节点Node'
        然后将这个复制节点就挂在这个节点的后面，插入该节点与他的next节点中间
        完成之后，再次遍历，这次遍历以组为单位，依次遍历两个节点去设置rand指针
        最后将克隆的节点独立出来，将原来的节点恢复
     */
    public static Node copy2(Node head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new Node(head.val);
        }

        // 遍历一遍，创建复制的节点
        Node curr = head;
        while (curr != null) {
            Node node = new Node(curr.val);
            Node next = curr.next;
            curr.next = node;
            node.next = next;
            curr = next;
        }

        // 以组的方式遍历，连好rand
        curr = head;
        while (curr != null) {
            curr.next.rand = curr.rand == null ? null : curr.rand.next;
            curr = curr.next.next;
        }

        // 将老链表和新链表分离，返回新链表的头
        curr = head;
        Node newHead = head.next;
        while (curr != null) {

            curr.next = curr.next.next;
            curr.next.next = curr.next.next.next;
            curr = curr.next;

        }

        return newHead;
    }


    /*
        验证两个链表是不是结构相同
     */
    public static boolean checkSameNode (Node head1, Node head2) {

        Node curr1 = head1;
        Node curr2 = head2;
        while (curr1 != null) {

            if (curr1.val != curr2.val) {
                return false;
            }

            if (curr1.rand.val != curr2.rand.val) {
                return false;
            }

            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return true;
    }


    static class Node {
        int val;
        Node next;
        Node rand;
        public Node(int val) {
            this.val = val;
        }
    }

}
