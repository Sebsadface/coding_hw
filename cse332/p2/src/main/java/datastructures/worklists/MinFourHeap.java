package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int capacity;
    private int size;
    private final Comparator<E> comparator;
    private final int CHILD_NUM = 4;

    public MinFourHeap(Comparator<E> c) {
        this.comparator = c;
        clear();
    }

    @Override
    public boolean hasWork() {
        return (size > 0);
    }

    @Override
    public void add(E work) {
        if (size == capacity) {
            resize();
        }
        size++;
        data[percolateUp(size-1, work)] = work;
    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }

        return data[0];
    }

    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        E next = data[0];
        data[percolateDown(0, data[size-1])] = data[size - 1];
        size--;
        return next;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        capacity = 10;
        size = 0;
        data = (E[]) new Object[capacity];
    }

    private int percolateUp(int hole, E work) {
        while (hole > 0 && (this.comparator.compare(work, data[getParent(hole)]) < 0)){
            data[hole] = data[getParent(hole)];
            hole = getParent(hole);
        }
        return hole;
    }

    private void resize() {
        E[] newData = (E[]) new Object[capacity*2];
        if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
        capacity *= 2;
        data = newData;
    }

    private int percolateDown (int hole, E work) {

        while ((hole * CHILD_NUM + 1 ) <= (size - 1)) {
            if (hole == getTarget(hole, work)) {
                break;
            } else {
                data[hole] = data[getTarget(hole, work)];
                hole = getTarget(hole, work);
            }

        }
        return hole;
    }

    private int getParent (int index) {
        return (int) Math.floor((double) (index - 1) / (double) CHILD_NUM);
    }

    private int getTarget (int hole, E work) {
        int target;
        int[] child = new int[CHILD_NUM];
        int i;

        for (i = 0; i < CHILD_NUM; i++) {
            child[i] = getChild(hole, i);
        }
        target = child[0];
        for (i = 0; i < CHILD_NUM; i++) {
            if (child[i] >= size -1) {
                break;
            }

            if (this.comparator.compare(data[target], data[child[i]]) > 0) {
                target = child[i];
            }
        }

        if (this.comparator.compare(data[target],work) > 0) {
            target = hole;
        }
        return target;
    }

    private int getChild (int index, int childRank) {
        return (index * CHILD_NUM) + childRank + 1;
    }
}
