package unionfind;

/**
 * @author chenkechao
 * @date 2019-08-10 22:19
 */
public interface UnionFind {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
