package test;

/**
 * @author chenkechao
 * @date 2019/10/19 10:45 上午
 */
public class MaxHeap<E extends Comparable> {

    private static int DEFAULT_CAPACITY = 10;

    private volatile int count;
    private Comparable[] data;
    private volatile int capacity;

    public MaxHeap(int capacity) {
        data = new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MaxHeap(Comparable[] arr) {
        data = new Comparable[arr.length + 1];
        capacity = arr.length;
        for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }
        count = arr.length;
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    //root index = 1
    // left child = 2 * parent_index
    // right child = 2 * parent_index + 1

    public synchronized void insert(Comparable e) {
        if (capacity == count + 1) {
            //扩容
            resize(data.length * 2 - 1);
        }
        data[++count] = e;
        shiftUp(count);
    }

    public synchronized Comparable popMax() {
        if (count == 0) {
            throw new IllegalArgumentException("");
        }

        Comparable maxElement = data[1];
        data[1] = data[count];
        data[count] = null;
        count--;
        shiftDown(1);

        return maxElement;
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = k * 2;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Comparable tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * 扩容代码
     *
     * @param capacity
     */
    private void resize(int capacity) {
        Comparable[] tmpData = new Comparable[capacity];
        for (int i = 1; i < count; i++) {
            tmpData[i] = data[i];
        }
        data = tmpData;
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // 测试 test.MaxHeap
    public static void main(String[] args) {

        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for (int i = 0; i < N; i++) {
            maxHeap.insert(new Integer((int) (Math.random() * M)));
        }

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for (int i = 0; i < N; i++) {
            arr[i] = (Integer) maxHeap.popMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for (int i = 1; i < N; i++) {
            assert arr[i - 1] >= arr[i];
        }
    }
}
