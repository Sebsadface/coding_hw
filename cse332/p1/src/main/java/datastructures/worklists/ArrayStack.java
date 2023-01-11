package datastructures.worklists;

import java.util.NoSuchElementException;
import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private E[] array;
    private int size;
    private int capacity;
    public ArrayStack() {
        clear();
    }

    @Override
    public void add(E work) {
        if (size == capacity) {
            capacity = 2 * size;
            E[] newArray = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size] = work;
        size++;
    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        return array[size - 1];
    }

    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        size--;
        return array[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        capacity = 10;
        size = 0;
        array = (E[]) new Object[capacity];
    }
}
