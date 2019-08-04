package map;

import java.util.Objects;

/**
 * @author chenkechao
 * @date 2019-08-02 20:21
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private static class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> left, right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<K, V> root;
    private int size;

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以 node 为根节点的二分搜索树中插入元素`new Node<>(k, v)`
     *
     * @param node
     * @param k
     * @param v
     * @return 返回插入新节点后二分搜索树的根
     */
    private Node<K, V> add(Node<K, V> node, K k, V v) {
        //终止条件
        if (Objects.isNull(node)) {
            size++;
            return new Node<>(k, v);
        }
        //子问题
        if (k.compareTo(node.key) < 0) {
            //左子树
            node.left = add(node.left, k, v);
        } else if (k.compareTo(node.key) > 0) {
            //右子树
            node.left = add(node.right, k, v);
        } else {
            node.value = v;
        }
        return node;
    }

    /**
     * 返回以 node 为根节点的二分搜索树中,key所在的节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (Objects.isNull(node)) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public V remove(K key) {
        Node<K, V> node = getNode(root, key);
        if (Objects.nonNull(node)) {

        }
        return null;
    }

    /**
     * 删除以 node 为根节点的二分搜索树中 键为 key 的节点
     *
     * @param node
     * @param key
     * @return 返回删除节点后新的二分搜索树的根
     */
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (Objects.isNull(node)) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {

            if (Objects.isNull(node.left)) {
                size--;
                return node.right;
            }

            if (Objects.isNull(node.right)) {
                size --;
                return node.left;
            }

            //左,右子树均不为空
            //1. 找右子树中的最小值
            Node<K, V> successor = minimum(node.right);
            //2. 删除右子树的最小值
            //3. 引用左右子树
            successor.right = removeMinimum(node.right);
            successor.left = node.left;
            return successor;
        }
    }

    /**
     * 返回以 node 为根节点的二分搜索树的最小值所在的节点
     *
     * @param node
     * @return
     */
    private Node<K, V> minimum(Node<K, V> node) {
        if (Objects.isNull(node.left)) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除以 node 为根节点的二分搜索树中的最小值
     *
     * @return 返回删除节点后新的二分搜索树的根
     */
    private Node<K, V> removeMinimum(Node<K, V> node) {
        if (Objects.isNull(node.left)) {
            size--;
            return null;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return Objects.nonNull(getNode(root, key));
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return Objects.nonNull(node) ? node.value : null;
    }

    @Override
    public void set(K key, V newValue) {
        Node<K, V> node = getNode(root, key);
        if (Objects.isNull(node)) {
            throw new IllegalArgumentException(" the key doesn't exist");
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
