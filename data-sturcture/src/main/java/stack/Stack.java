package stack;

/**
 * @author chenkechao
 * @date 2019-07-09 20:47
 */
public interface Stack<E> {

    void push(E e);

    void pop();

    boolean isEmpty();

    int getSize();

    E peek();
}
