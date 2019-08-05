package heap;

import array.Array;

/**
 * @author chenkechao
 * @date 2019-08-03 19:12
 */
public class MaxHeap<E extends Comparable<E>>  {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * heapify
     * @param array
     */
    public MaxHeap(E[] array) {
        data = new Array<>(array);
        for (int i = parent(array.length - 1); i <= 0; i--) {
            shiftDown(i);
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中, index 索引所表示的元素的父节点的索引
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中, index 索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中, index 索引所表示的元素的优孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

    private void shiftUp(int index) {
        while (index > 0
                && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }

        /* recursion
        if (index < 0
                || data.get(parent(index)).compareTo(data.get(index)) > 0) {
            return;
        }
        data.swap(index, parent(index));
        shiftUp(parent(index));
         */
    }

    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("can not findMax when heap is empty");
        }
        return data.get(0);
    }

    public E extractMax() {
        E max = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();

        shiftDown(0);

        return max;
    }

    private void shiftDown(int index) {

        while (leftChild(index) < data.getSize()) {
            int j = leftChild(index);
            if (j + 1 < data.getSize()
                    && data.get(j + 1).compareTo(data.get(j)) > 0) {
                //条件 1:是否有右孩子, 2:右孩子是否比左孩子值大
                //右孩子 l = rightChild(index);
                j ++;
            }
            //此时 data[j] 是 leftChild(index) 和 rightChild(index) 中的最大值
            if (data.get(index).compareTo(data.get(j)) > 0) {
                break;
            }

            data.swap(index, j);
            index = j;
        }

        /* recursion
        if (leftChild(index) > data.getSize()) {
            return;
        }

        int j = leftChild(index);
        if (j + 1 < data.getSize()
                && data.get(j + 1).compareTo(data.get(j)) > 0) {
            //条件 1:是否有右孩子, 2:右孩子是否比左孩子值大
            //右孩子 l = rightChild(index);
            j ++;
        }

        if (data.get(index).compareTo(data.get(j)) > 0) {
            return;
        }

        data.swap(index, j);
        shiftDown(j);

         */
    }

    /**
     * 取出 heap 中的最大元素, 并替换成元素 e
     * @param e
     */
    public E replace(E e) {
        if (data.getSize() == 0) {
            add(e);
            return null;
        }
        E result = findMax();
        data.set(0, e);
        shiftDown(0);
        return result;
    }
}
