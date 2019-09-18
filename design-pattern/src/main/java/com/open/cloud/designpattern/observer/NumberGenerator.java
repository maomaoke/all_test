package com.open.cloud.designpattern.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-17-9:07 下午
 */
public abstract class NumberGenerator {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        Iterator<Observer> iterator = observers.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(this);
        }
    }

    public abstract int getNumber();

    public abstract void execute();
}
