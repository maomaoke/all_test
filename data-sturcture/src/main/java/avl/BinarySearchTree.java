package avl;

import java.util.Objects;

/**
 * @author chenkechao
 * @date 2019-08-12 22:18
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<K, V> root;

    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以 node节点为根节点的二分搜索树中添加节点
     * @param node
     * @param key
     * @param value
     * @return 返回添加新节点后的二分搜索树的根
     */
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (Objects.isNull(node)) {
            size ++;
            return new Node<>(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            //左子树
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            //右子树
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public boolean contains(K key) {
        return Objects.nonNull(getNode(root, key));
    }

    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return Objects.nonNull(node) ? node.value : null;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (Objects.isNull(node)) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            //左子树
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            //右子树
            return getNode(node.right, key);
        }
        return node;
    }

    private Node<K, V> minimum(Node<K, V> node) {
        //递归左子树
        if (Objects.isNull(node.left)) {
            return node;
        }
        return minimum(node.left);
    }

    public V remove(K key) {
        Node<K, V> node = getNode(root, key);
        if (Objects.isNull(node)) {
            return null;
        }
        root = remove(root, key);
        return node.value;
    }

    private Node<K ,V> remove(Node<K, V> node, K key) {
        if (Objects.isNull(node)) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            //左子树
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            //右子树
            node.right = remove(node.right, key);
            return node;
        }
        if (Objects.isNull(node.left)) {
            size --;
            return node.right;
        } else if (Objects.isNull(node.right)) {
            size --;
            return node.left;
        } else  {

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
}
