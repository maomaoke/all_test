package queue;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-12-14:59
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    /**
     * 入队列
     * @param e
     */
    void enqueue(E e);

    /**
     * 出队列
     * @return
     */
    E dequeue();

    E getFront();
}
