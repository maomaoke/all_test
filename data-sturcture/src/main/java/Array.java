/**
 * @author chenkechao
 * @date 2019-07-06 10:39
 */
public class Array<Item> {

    private Item[] data;
    /**
     * size 变量实际上是指向数组下一个元素的下标
     */
    private int size;

    private static int DEFAULT_CAPACITY = 10;

    public Array(int capacity) {
        data = (Item[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, Item e) {

        if (size == getCapacity()) {
            throw new IllegalArgumentException("Add element failed because array is filled");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add element failed because index left than zero or greater than size");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addLast(Item e) {
        add(size, e);
    }

    public void addFirst(Item e) {
        add(0, e);
    }

    public Item get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("");
        }
        return data[index];
    }

    public void set(int index, Item e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("");
        }
        data[index] = e;
    }

    public boolean contains(Item e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }



    public int find(Item e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public Item remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("");
        }
        Item result = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        return result;
    }

    public Item removeFirst() {
        return remove(0);
    }

    public Item removeLast() {
        return remove(size - 1);
    }

    public void removeElement(Item e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }


    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
