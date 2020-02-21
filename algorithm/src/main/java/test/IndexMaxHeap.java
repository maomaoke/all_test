package test;

/**
 * @author chenkechao
 * @date 2019/10/24 8:23 下午
 */
public class IndexMaxHeap<Item extends Comparable> {

    private Item[] data;
    private int[] indexes;
    private int count;
    private int capacity;

    private IndexMaxHeap() {
    }

    public IndexMaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];

        count = 0;
        this.capacity = capacity;
    }

    public void insert(Item item) {
        data[++count] = item;
        shiftUp(count);
    }

    public void insert(int i, Item item) {
        i += 1;
        data[i] = item;
        indexes[++count] = i;
        shiftUp(count);
    }


    public void shiftUp(int k) {
        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {

            //swap
            swapIndex(k / 2, k);
            k /= 2;
        }
    }

    // 返回索引堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值, 表示索引堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    private void swapIndex(int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;
    }
}
