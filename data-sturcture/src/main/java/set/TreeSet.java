package set;

import bst.BinarySearchTree;

/**
 * @author chenkechao
 * @date 2019-07-29 19:51
 */
public class TreeSet<E extends Comparable<E>> implements Set<E>{

    private BinarySearchTree<E> bst;

    public TreeSet() {
        this.bst = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
