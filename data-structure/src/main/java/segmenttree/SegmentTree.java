package segmenttree;

/**
 * @author chenkechao
 * @date 2019-08-06 21:12
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] array, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }

        tree = (E[]) new Object[4 * array.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在 treeIndex的位置创建表示区间 [l...r] 的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        //1.终止条件
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = this.merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树中的数组表示中,一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index is illegal");
        }
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index is illegal");
        }
        return 2 * index + 2;
    }

    public E query(int queryL, int queryR) {
        if (queryL <0 || queryL >= data.length
                || queryR < 0 || queryR >data.length || queryR < queryL) {
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以 treeIndex 为根的线段树中[l...r]的范围里,搜索区间[queryL...queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryLeft
     * @param queryRight
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryLeft, int queryRight) {

        //1.递归到底
        if (l == queryLeft && r == queryRight) {
            return tree[treeIndex];
        }

        //2.用更小规模的解来构建原问题的解
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);


        if (queryRight <= mid) {
            //全部都集中在 左子树上
            return query(leftTreeIndex, l, mid, queryLeft, queryRight);
        } else if (queryLeft >= mid + 1) {
            //全部都集中在 右子树上
            return query(rightTreeIndex, mid + 1, r, queryLeft, queryRight);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryLeft, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryRight);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = e;

    }

    /**
     * 在以 treeIndex为根的线段树中更新index 的值为 e
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        //1.递归到底
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index <= mid) {
            set(leftTreeIndex, l, mid, index, e);
        } else {
            set(rightTreeIndex, mid + 1, r, index, e);
        }
        //2.用更小规模的解来构建原问题的解
        tree[treeIndex] = this.merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public static void main(String[] args) {
        Integer[] arr = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, Integer::sum);
        Integer sum = segmentTree.query(1, 3);

        System.out.println(sum);
    }
}
