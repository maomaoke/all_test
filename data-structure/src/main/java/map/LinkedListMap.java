package map;

import java.util.Objects;

/**
 * @author chenkechao
 * @date 2019-08-01 20:20
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node<K, V> {

        public K key;
        public V value;
        public Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node<K, V> dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node<>();
        size = 0;
    }

    private Node<K, V> getNode(K key) {

        Node<K, V> node = dummyHead.next;
        while (Objects.nonNull(node)) {
            if (key.equals(node.key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node<K, V> node = getNode(key);
        if (Objects.isNull(node)) {
            dummyHead.next = new Node<>(key, value, dummyHead.next);
            size ++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node<K, V> pre = this.dummyHead;
        while (Objects.nonNull(pre.next)) {
            if (key.equals(pre.next.key)) {
                break;
            }
            pre = pre.next;
        }

        if (Objects.nonNull(pre.next)) {
            Node<K, V> deleteNode = pre.next;
            pre.next = pre.next.next;
            deleteNode.next = null;
            return deleteNode.value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return Objects.nonNull(getNode(key));
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        if (Objects.nonNull(node)) {
            return node.value;
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node<K, V> node = getNode(key);
        if (Objects.isNull(node)) {
            throw new IllegalArgumentException("key doesn't exist");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
