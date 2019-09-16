package set;

import avl.AvlTree;

/**
 * @author chenkechao
 * @date 2019-08-20 20:50
 */
public class AvlSet<E extends Comparable<E>> implements Set<E> {

    private AvlTree<E, ?> avl;

    public AvlSet() {
        avl = new AvlTree<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
