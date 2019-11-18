import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author chenkechao
 * @date 2019/11/16 8:05 下午
 */
public class SparseGraph implements Graph {

    private int v;

    private int e;

    private boolean directed;

    private ArrayList<Integer>[] g;

    @SuppressWarnings("unchecked")
    public SparseGraph(int v, boolean directed) {
        this.v = v;
        this.directed = directed;
        g = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            g[i] = new ArrayList<>();
        }
    }

    @Override
    public int v() {
        return v;
    }

    @Override
    public int e() {
        return e;
    }

    @Override
    public boolean hasEdge(int n, int m) {
        for (int i = 0; i < g[n].size(); i++) {
            if (g[n].indexOf(i) == m) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(int n, int m) {
        g[n].add(m);
        if (n != m && !directed) {
            g[m].add(n);
        }
        e++;
    }

    @Override
    public Iterator<Integer> adj(int n) {
        return g[n].iterator();
    }
}
