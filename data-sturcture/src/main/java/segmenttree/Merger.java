package segmenttree;

/**
 * @author chenkechao
 * @date 2019-08-06 23:37
 */
@FunctionalInterface
public interface Merger<E> {
    /**
     * 线段树合并
     * @param a
     * @param b
     * @return
     */
    E merge(E a, E b);
}
