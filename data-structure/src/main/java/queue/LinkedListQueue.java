package queue;

import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-17-20:50
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node<E> {
        E e;
        Node<E> next;

        Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return Objects.nonNull(e) ? e.toString() : "null";
        }
    }

    private Node<E> dummyHead, tail;
    private int size;

    public LinkedListQueue() {
        dummyHead = tail = new Node<>();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        tail.next = new Node<>(e);
        tail = tail.next;
        size ++;
    }

    @Override
    public E dequeue() {
        //
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }

        Node<E> res = dummyHead.next;
        dummyHead.next = res.next;
        res.next = null;
        size --;
        if (res == tail) {
            tail = dummyHead;
        }

        return res.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        return dummyHead.next.e;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: front ");

//        Node<E> cur = dummyHead.next;
//        while (Objects.nonNull(cur)) {
//            stringBuilder.append(cur).append("->");
//            cur = cur.next;
//        }

        for (Node<E> cur = dummyHead.next; Objects.nonNull(cur) ; cur = cur.next) {
            stringBuilder.append(cur).append("->");
        }
        stringBuilder.append("NULL tail");
        return stringBuilder.toString();
    }
}
