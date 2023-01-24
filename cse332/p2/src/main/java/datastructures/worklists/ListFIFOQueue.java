package datastructures.worklists;

import java.util.NoSuchElementException;
import cse332.interfaces.worklists.FIFOWorkList;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private Node<E> front;
    private Node<E> back;
    private int size;
    public ListFIFOQueue() {
        this.front = new Node<>(null);
        this.back = new Node<>(null);
        clear();
    }

    @Override
    public void add(E work) {
        if (!this.hasWork()) {
            this.front = new Node<>(work);
            this.back = this.front;
        } else {
            this.back.next = new Node<>(work);
            this.back = this.back.next;
        }
        this.size++;
    }

    @Override
    public E peek() {
        if (!this.hasWork()) {
            throw new NoSuchElementException();
        }
        return this.front.data;
    }

    @Override
    public E next() {
        if (!this.hasWork()) {
            throw new NoSuchElementException();
        }

        E next = this.front.data;
        if (this.size == 1) {
            this.back = this.front = null;
        } else {
            this.front = this.front.next;
        }
        this.size--;
        return next;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
       this.front = null;
       this.back = null;
       size = 0;
    }

    private static class Node <E> {
        public E data;
        public Node<E> next;

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
