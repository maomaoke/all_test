package bst;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-24-21:14
 */

import java.util.Objects;

/**
 * 不包含重复元素()
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node<E> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     *
     * @param node
     * @param e
     * @return 返回插入新节点后的二分搜索树的根
     */
    private Node<E> add(Node<E> node, E e) {
        if (Objects.isNull(node)) {
            return new Node<>(e);
        }

        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 以 node 为根的二分搜索树中是否包含元素 e, recursion
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node<E> node, E e) {
        //终止条件
        if (Objects.isNull(node)) {
            return false;
        }

        //子问题
        if (e.compareTo(node.data) == 0) {
            return true;
        } else if (e.compareTo(node.data) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }

        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }
}
