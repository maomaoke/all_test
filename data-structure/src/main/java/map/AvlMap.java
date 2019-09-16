package map;

import avl.AvlTree;

import java.util.Objects;

/**
 * @author chenkechao
 * @date 2019-08-20 20:41
 */
public class AvlMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AvlTree<K, V> avlTree;

    public AvlMap() {
        avlTree = new AvlTree<>();
    }

    @Override
    public void add(K key, V value) {
        avlTree.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avlTree.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return avlTree.contains(key);
    }

    @Override
    public void set(K key, V newValue) {
        avlTree.set(key, newValue);
    }

    @Override
    public V get(K key) {
        return avlTree.get(key);
    }



    @Override
    public int getSize() {
        return avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }
}
