package test;

import java.util.Iterator;

/**
 * @author chenkechao
 * @date 2019/11/24 7:05 下午
 */
public interface WeightedGraph<Weight extends Number & Comparable<Number>> {
    public int v();

    public int e();

    public void addEdge(Edge<Weight> e);

    boolean hasEdge(int v, int w);

    void show();

    public Iterator<Edge<Weight>> adj(int v);
}
