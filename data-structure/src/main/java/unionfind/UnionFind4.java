package unionfind;

/**
 * @author chenkechao
 * @date 2019-08-11 13:10
 * rank 优化
 */
public class UnionFind4 implements UnionFind {

    private int[] parent;
    /**
     * rank[i]表示以 i为根的集合所表示的树的层数
     */
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {

//        return findRecursion(p) == findRecursion(q);
        return find(p) == find(q);
    }


    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    private int findR(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        if (parent[p] == p) {
            return p;
        }
        return findR(parent[p]);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] == rank[qRoot]) {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        } else {
            parent[qRoot] = pRoot;
        }
    }
}
