/**
 * @author chenkechao
 * @date 2019/10/19 10:45 上午
 */
public class MaxHeap<E extends Comparable> {

    private static int DEFAULT_CAPACITY = 10;

    private int count;
    private Comparable[] data;

    public MaxHeap(int capacity) {
        data = new Comparable[capacity + 1];
        count = 0;
    }

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    //root index = 1
    // left child = 2 * parent_index
    // right child = 2 * parent_index + 1

    public void add(Comparable e) {

    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
