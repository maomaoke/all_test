package queue;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-13-13:08
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            //扩容
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail =  (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 1;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new RuntimeException("");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        stringBuilder.append("front [");
        for (int i = front; i != tail ; i = (i + 1) % data.length) {
            stringBuilder.append(data[i]);
            if ((i + 1) % data.length != tail) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return super.toString();
    }
}
