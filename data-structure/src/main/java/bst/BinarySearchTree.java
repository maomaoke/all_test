package bst;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-24-21:14
 */

import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 不包含重复元素()
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private static class Node<E> {
        @NotNull
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
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
            size ++;
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

    public void levelOrder() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.remove();
            System.out.println(node);
            if (Objects.nonNull(node.left)) {
                queue.add(node.left);
            }
            if (Objects.nonNull(node.right)) {
                queue.add(node.right);
            }
        }
    }

    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("current size is zero");
        }
        return minimum(root).data;
    }

    private Node<E> minimum(Node<E> node) {
        if (Objects.isNull(node.left)) {
            return node;
        }
        return minimum(node.left);
    }

    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("current size is zero");
        }
        return maximum(root).data;
    }

    /**
     * 返回以 node 节点为根的二分搜索树的最大值所在的节点
     * @return
     */
    private Node<E> maximum(Node<E> node) {
        if (Objects.isNull(node.right)) {
            return node;
        }
        return maximum(node.right);
    }

    public E removeMin() {
        E minimum = minimum();

        root = removeMin(root);

        return minimum;
    }

    /**
     * 删除掉以 node 为根的二分搜索树中的最小节点
     * @param node
     * @return 返回删除节点后新的二分搜索树的根
     */
    private Node<E> removeMin(Node<E> node) {
        if (Objects.isNull(node.left)) {
            size --;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E maximum = maximum();
        root = removeMax(root);
        return maximum;
    }

    private Node<E> removeMax(Node<E> node) {
        if (Objects.isNull(node.right)) {
            size --;
            return node.left;
        }
        node.right = removeMax(node);
        return node;
    }

    public void remove(E e) {
        if (size == 0) {
            throw new IllegalArgumentException("current size is zero");
        }
        root = remove(root, e);
    }

    /**
     *
     * @param node
     * @param e
     * @return
     */
    private Node<E> remove(Node<E> node, E e) {
        // 终止条件
        if (Objects.isNull(node)) {
            return null;
        }

        // 子问题
        if (e.compareTo(node.data) < 0) {
            // recursion left sub-tree
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            // recursion right sub-tree
            node.right = remove(node.right, e);
            return node;
        } else {
            // equal
            if (Objects.isNull(node.left)) {
                size --;
                return node.right;
            }
            if (Objects.isNull(node.right)) {
                size --;
                return node.left;
            }

            // 右子树的最小节点
            Node<E> successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            return successor;
        }
    }
}
