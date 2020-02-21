package test;

/**
 * @author chenkechao
 * @date 2019/11/24 6:51 下午
 */
public class Edge<Weight extends Number & Comparable<Number>> implements Comparable<Edge> {

    private int v, w;
    private Weight weight;

    public int other(int x) {
        assert x == v || x == w;
        return x == v ? w : v;
    }

    public int v() {
        return v;
    }

    public int w() {
        return w;
    }

    public Weight getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight.compareTo(o.getWeight());
    }
}
