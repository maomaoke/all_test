package stack;

import linkedlist.LinkedList;

/**
 * @author chenkechao
 * @date 2019-07-16 20:30
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> data;

    public LinkedListStack() {
        data = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        data.addFirst(e);
    }

    @Override
    public void pop() {
        data.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public E peek() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("top: ").append(data);
        return res.toString();
    }
}
