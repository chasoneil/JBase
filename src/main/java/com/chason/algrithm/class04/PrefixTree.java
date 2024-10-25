package com.chason.algrithm.class04;

import com.chason.algrithm.class02.base.Node;

/**
 * 前缀树  官方的名字是 TrieTree
 * 前缀树是一个多叉树
 *
 * 有一个字符串数组，里面存放着各种字符串，当然也可以是数字，例如 ["abc", "abd", "bcd", "abcd", "amd" ...]
 * 现在建立一个树，存放该字符串
 * 前缀树建立的过程： 创建一个根节点 ，遍历字符串数组，取出字符串，遍历字符串的字符
 * 从根节点出发，有没有通往第一个字符的路径，没有就创建一个节点，路径标记为该字符。
 * 例如 从root 出发 ,第一个字符串是abc,没有通往a的路，那么就创建第二个节点 node1 root -a-> node1
 * 将路径标记为a.
 * 前缀树中都是路径表示具体的字符，而不是节点表示，当出现相同的路径的时候，节点的 pass++
 * 当一个字符串结束的时候，节点的 end++
 */
public class PrefixTree {

    public static void main(String[] args) {

        String[] strs = new String[] {"abc", "abcd", "acd", "abcd", "abck"};

        Trie1 trie1 = new Trie1();
        for (int i=0; i<strs.length; i++) {
            trie1.add(strs[i]);
        }

        System.out.println("以ab为头的字符串数量为：" + trie1.prefix("ab"));
        System.out.println("数组中abc出现的次数为：" + trie1.search("abc"));
        trie1.delete("abck");
        System.out.println("-------------------");
        System.out.println("以ab为头的字符串数量为：" + trie1.prefix("ab"));
        System.out.println("数组中abc出现的次数为：" + trie1.search("abc"));
        System.out.println("数组中acd出现的次数为：" + trie1.search("acd"));
    }

    static class Node1 {
        int pass;
        int end;
        Node1[] nexts;

        public Node1 () {
            this.pass = 0;
            this.end = 0;

            // 假如我们的字符串不区分大小写，那么最多的路径就是26条
            // 使用底层节点是否为null标记路是否存在
            nexts = new Node1[26];
        }
    }

    static class Trie1 {

        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        // 将字符串加入前缀树中
        public void add(String str) {

            if (str == null || "".equals(str)) {
                return;
            }

            Node1 node = root;
            node.pass++;
            for (int i=0; i<str.length(); i++) {
                // 路径不存在，创建该路径
                int road = str.charAt(i) - 'a';
                if (node.nexts[road] == null) {
                    node.nexts[road] = new Node1();
                }
                node = node.nexts[road];
                node.pass++;
            }
            node.end++;
        }

        // 单次删除一个字符串
        public void delete(String str) {
            // 字符串存在的时候才删除
            if (search(str) != 0) {

                Node1 node = root;
                node.pass--;

                for (int i=0; i<str.length(); i++) {
                    int road = str.charAt(i) - 'a';
                    // 下面的节点一定存在
                    if (--node.nexts[road].pass == 0) { // 后面的节点已经不用看了
                        node.nexts[road] = null;
                        return;
                    } else {
                        node = node.nexts[road];
                    }
                }

                node.end--;
            }
        }

        // str在整个过程中出现过几回
        public int search(String str) {

            if (str == null || "".equals(str)) {
                return 0;
            }

            Node1 node = root;
            for (int i=0; i<str.length(); i++) {
                int road = str.charAt(i) - 'a';
                if (node.nexts[road] == null) {
                    return 0;
                } else {
                    node = node.nexts[road];
                }
            }

            return node.end;
        }

        // 以 pre 为前缀的字符串出现过几回
        public int prefix(String pre) {

            if (pre == null || "".equals(pre)) {
                return 0;
            }

            Node1 node = root;
            for (int i=0; i<pre.length(); i++) {
                int road = pre.charAt(i) - 'a';
                if (node.nexts[road] == null) {
                    return 0;
                }
                node = node.nexts[road];
            }

            return node.pass;
        }

    }



}
