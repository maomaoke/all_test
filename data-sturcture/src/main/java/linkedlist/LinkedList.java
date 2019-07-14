package linkedlist;

/**
 * @author chenkechao
 * @date 2019-07-14 18:51
 */
public class LinkedList<E> {

    private class Node<E> {
        public E e;
        public Node<E> next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node<E> head;
    private int size;

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    /**
     * 向表头添加元素
     * @param e
     */
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }
        if (index == 0) {
            addFirst(e);
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1; i++) {
                //找到 index 的上一个元素所以是 (index - 1)
                prev = prev.next;
            }
            prev.next = new Node<>(e, prev.next);
            size++;
        }
    }

    public void addLast(E e) {
        add(size, e);
    }
}
