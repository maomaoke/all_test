package set;

/**
 * @author chenkechao
 * @date 2019-07-29 19:40
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
