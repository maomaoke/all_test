package avl;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenkechao
 * @date 2019-08-12 22:18
 */
public class AvlTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node<K, V> root;

    private int size;

    public AvlTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 获取节点 node 的高度
     *
     * @param node
     * @return
     */
    private int getHeight(Node<K, V> node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获得节点 node 的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node<K, V> node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private boolean isBst() {
        ArrayList<K> list = Lists.newArrayList();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }


    private void inOrder(Node<K, V> node, List<K> list) {
        if (Objects.isNull(node)) {
            return;
        }

        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }

    /**
     * 判断该二叉树是否是一颗平衡二叉树
     *
     * @return
     */
    private boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 判断以 node为根的二叉树是否是一颗平衡二叉树, 递归算法
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node<K, V> node) {
        if (Objects.isNull(node)) {
            return true;
        }
        int balance = getBalanceFactor(node);
        if (Math.abs(balance) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void set(K key, V newValue) {
        Node<K, V> node = getNode(root, key);
        if (Objects.isNull(node)) {
            throw new IllegalArgumentException(" the key doesn't exist");
        }
        node.value = newValue;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以 node节点为根节点的二分搜索树中添加节点
     *
     * @param node
     * @param key
     * @param value
     * @return 返回添加新节点后的二分搜索树的根
     */
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (Objects.isNull(node)) {
            size++;
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

        //更新 height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //左子树 高于 右子树 并且 左子树的左子树 大于等于 左子树的右子树
            /*
                getBalanceFactor(node.left) >= 0 代表着是向左子树的左边添加了元素.
                原来的平衡状态下, getBalanceFactor(node.left) = -1, 0,
                如果想左子树的左边插入元素, balance 会等于 0 or 1.
             */
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node<K, V> rightRotate(Node<K, V> y) {
        Node<K, V> x = y.left;
        Node<K, V> t3 = x.right;

        x.right = y;
        y.left = t3;

        //更新 height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node<K, V> leftRotate(Node<K, V> y) {

        Node<K, V> x = y.right;
        Node<K, V> t2 = x.left;

        x.left = y;
        y.right = t2;

        //更新 height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
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

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (Objects.isNull(node)) {
            return null;
        }

        Node<K, V> returnNode;
        if (key.compareTo(node.key) < 0) {
            //左子树
            node.left = remove(node.left, key);
            returnNode = node;
        } else if (key.compareTo(node.key) > 0) {
            //右子树
            node.right = remove(node.right, key);
            returnNode = node;
        }
        if (Objects.isNull(node.left)) {
            size--;
            returnNode = node.right;
        } else if (Objects.isNull(node.right)) {
            size--;
            returnNode = node.left;
        } else {

            //左,右子树均不为空
            //1. 找右子树中的最小值
            Node<K, V> successor = minimum(node.right);
            //2. 删除右子树的最小值
            //3. 引用左右子树
            successor.right = remove(node.right, successor.key);
            successor.left = node.left;
            returnNode = successor;
        }

        if (Objects.isNull(returnNode)) {
            return null;
        }

        //更新 height
        returnNode.height = 1 + Math.max(getHeight(returnNode.left), getHeight(returnNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(returnNode);

        //平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(returnNode.left) >= 0) {
            //左子树 高于 右子树 并且 左子树的左子树 大于等于 左子树的右子树
            /*
                getBalanceFactor(node.left) >= 0 代表着是向左子树的左边添加了元素.
                原来的平衡状态下, getBalanceFactor(node.left) = -1, 0,
                如果想左子树的左边插入元素, balance 会等于 0 or 1.
             */
            return rightRotate(returnNode);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(returnNode.right) <= 0) {
            return leftRotate(returnNode);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(returnNode.left) < 0) {
            returnNode.left = leftRotate(returnNode.left);
            return rightRotate(returnNode);
        }

        if (balanceFactor < -1 && getBalanceFactor(returnNode.right) > 0) {
            returnNode.right = rightRotate(returnNode.right);
            return leftRotate(returnNode);
        }

        return returnNode;
    }

}
