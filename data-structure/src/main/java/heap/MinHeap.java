package heap;

import array.Array;
import com.sun.corba.se.spi.ior.iiop.IIOPFactories;

/**
 * @author chenkechao
 * @date 2019-08-05 20:29
 */
public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap() {
        data = new Array<>();
    }

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * heapify
     * @param array
     */
    public MinHeap(E[] array) {
        data = new Array<>(array.length);
        int i = parent(array.length - 1);
        while (i <= 0) {
            shiftDown(i);
            i--;
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void add(E e) {
        data.addLast(e);

        shiftUp(data.getSize() - 1);
    }

    private void shiftUp(int index) {
        while (index > 0
                && data.get(index).compareTo(data.get(parent(index))) < 0) {
            data.swap(index, parent(index));
        }


        /*
            recursion
        if (index < 0 || data.get(index).compareTo(data.get(parent(index))) > 0) {
            return;
        }

        data.swap(index, parent(index));

        shiftUp(parent(index));

         */
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public E findMin() {
        if (getSize() == 0) {
            throw new IllegalArgumentException("can not findMin when heap is empty");
        }
        return data.getFirst();
    }
    public E extractMin() {
        E result = findMin();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        shiftDown(0);
        return result;
    }

    private void shiftDown(int index) {
        while (leftChild(index) < getSize()) {
            int j = leftChild(index);

            if (j + 1 < getSize()
                    && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j ++;
            }

            if (data.get(index).compareTo(data.get(j)) <= 0) {
                break;
            }

            data.swap(index, j);

            index = j;
        }
    }
}
