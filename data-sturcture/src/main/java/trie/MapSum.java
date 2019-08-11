package trie;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author chenkechao
 * @date 2019-08-10 12:16
 */
public class MapSum {

    private static class Node {
        private int value;
        private TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int value) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Objects.isNull(cur.next.get(c))) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = value;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (Objects.isNull(cur.next.get(c))) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node) {
//        if (node.next.isEmpty()) {
//            return node.value;
//        }

        int result = node.value;
        for (Map.Entry<Character, Node> entry : node.next.entrySet()) {
            result += sum(entry.getValue());
        }
        return  result;
    }


}
