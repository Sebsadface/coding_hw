package datastructures.worklists;

import java.util.NoSuchElementException;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {
    private E[] array;
    private int frontIndex;
    private int backIndex;
    private int size;

    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        clear();
    }

    @Override
    public void add(E work) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        array[backIndex] = work;
        size++;
        backIndex = (backIndex+1) % capacity();
    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        return array[frontIndex];
    }

    @Override
    public E peek(int i) {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[(frontIndex+i) % capacity()];
    }

    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }

        E next = array[frontIndex];
        size--;
        frontIndex = (frontIndex + 1) % capacity();
        return next;
    }

    @Override
    public void update(int i, E value) {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[(frontIndex+i)%capacity()] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[capacity()];
        backIndex = 0;
        frontIndex = 0;
        size = 0;
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            // Uncomment the line below for p2 when you implement equals
            // FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here
            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
