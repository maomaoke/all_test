package trie;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author chenkechao
 * @date 2019-08-09 20:23
 */
public class Trie {

    private static class Node {
        private boolean isWord;
        private Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Objects.isNull(cur.next.get(c))) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }

//        add(root, word, 0);
    }

    /**
     * recursion add
     * @param cur
     * @param word
     * @param index
     */
    private void add(Node cur, String word, int index) {
        //recursion
        //1.递归到底
        if (index == word.length() && !cur.isWord) {
            cur.isWord = true;
            size ++;
            return;
        }

        char c = word.charAt(index);
        if (Objects.isNull(cur.next.get(c))) {
            cur.next.put(c, new Node());
        }
        add(cur.next.get(c), word, index + 1);
    }

    /**
     * weather query contains the specified word in trie
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Objects.isNull(cur.next.get(c))) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;

        //recursion
//        contains(root, word, 0);
    }

    /**
     * recursion contains
     * @param node
     * @param word
     * @param index
     * @return
     */
    private boolean contains(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if (Objects.isNull(node.next.get(c))) {
            return false;
        }

        return contains(node.next.get(c), word, index + 1);
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (Objects.isNull(cur.next.get(c))) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public boolean remove(String word) {
        //todo 未实现
        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("cat");
        System.out.println(trie);
    }
}
