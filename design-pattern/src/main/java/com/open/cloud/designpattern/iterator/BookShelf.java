package com.open.cloud.designpattern.iterator;

/**
 * @author chenkechao
 * @date 2019/9/23 9:13 下午
 */
public class BookShelf implements Aggregate {

    private Book[] books;
    private int last = 0;

    public BookShelf(int capacity) {
        books = new Book[capacity];
    }

    public Book getBookAt(int index) {
        if (index < 0 || index > books.length - 1) {
            throw new IllegalArgumentException();
        }
        return books[index];
    }

    public void appendBook(Book book) {
        books[last] = book;
        last ++;
    }

    public int getLength() {
        return books.length;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
