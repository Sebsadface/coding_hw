package datastructures.worklists;

import java.util.NoSuchElementException;
import cse332.interfaces.worklists.FIFOWorkList;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private final Node<E> front;
    private final Node<E> back;
    private int size;
    public ListFIFOQueue() {
        front = new Node<>(null);
        back = new Node<>(null);
        clear();
    }

    @Override
    public void add(E work) {
        if (!hasWork()) {
            front.next = new Node<>(work, back, front);
            back.prev = front.next;
        } else {
            back.prev.next = new Node<>(work,back,back.prev);
            back.prev = back.prev.next;
        }
        size++;
    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        return front.next.data;
    }

    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }

        E next = front.next.data;
        if (size == 1) {
            front.next = back;
            back.prev = front;
        } else {
            front.next = front.next.next;
            front.next.next.prev = front;
        }
        size--;
        return next;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
       front.next = back;
       back.prev = front;
       size = 0;
    }

    private static class Node <E> {
        public E data;
        public Node<E> next;
        public Node<E> prev;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
