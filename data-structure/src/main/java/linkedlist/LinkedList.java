package linkedlist;

import java.util.Objects;

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


    private Node<E> dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }

        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            //找到 index 的上一个元素所以是 (index - 1)
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);
        size++;
    }

    /**
     * 向表头添加元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }


    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
//        Node<E> cur = dummyHead;
//        while (Objects.nonNull(cur.next)) {
//            cur = cur.next;
//            if (e.equals(cur.e)) {
//                return true;
//            }
//        }

        for (Node<E> cur = dummyHead; Objects.nonNull(cur.next); cur = cur.next) {
            if (e.equals(cur.e)) {
                return true;
            }
        }

        return false;
    }

    public void removeElement(E e) {
        Node<E> pre = dummyHead;
        while (Objects.nonNull(pre.next)) {
            if (e.equals(pre.next)) {
                break;
            }
            pre = pre.next;
        }
        // 要删除的是 pre.next

        if (Objects.nonNull(pre.next)) {
            pre.next = pre.next.next;
        }
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("");
        }
        //find e which index
        Node<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        //remove
        Node<E> tmp = pre.next;
        pre.next = tmp.next;
        tmp.next = null;
        size--;
        return tmp.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<E> cur = dummyHead.next;
        while (Objects.nonNull(cur)) {
            stringBuilder.append(cur).append("->");
            cur = cur.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }
}
