import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenkechao
 * @date 2019/11/16 8:05 下午
 */
public class DenseGraph implements Graph {

    private int v;

    private int e;

    private boolean directed;

    private boolean[][] g;

    public DenseGraph(int v, boolean directed) {
        this.v = v;
        this.e = 0;
        this.directed = directed;
        g = new boolean[v][v];
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
    public void addEdge(int n, int m) {
        g[n][m] = true;
        if (!directed) {
            g[m][n] = true;
        }
        e++;
    }

    @Override
    public boolean hasEdge(int n, int m) {
        return g[n][m];
    }

    @Override
    public Iterator<Integer> adj(int n) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            if (g[n][i]) {
                list.add(i);
            }
        }
        return list.iterator();
    }
}
