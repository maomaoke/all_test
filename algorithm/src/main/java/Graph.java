import java.util.Iterator;

/**
 * @author chenkechao
 * @date 2019/11/16 8:19 下午
 */
public interface Graph {

    int v();

    int e();

    boolean hasEdge(int n, int m);

    void addEdge(int n, int m);

    /**
     * 返回顶点n的所有临边
     * @param n
     * @return
     */
    Iterator<Integer> adj(int n);
}
