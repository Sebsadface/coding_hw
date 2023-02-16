package p2.sorts;

import cse332.exceptions.NotYetImplementedException;
import datastructures.worklists.MinFourHeap;

import java.util.Comparator;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, Comparable::compareTo);
    }

    /**
     * Behaviour is undefined when k > array.length
     */
    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<>(comparator);
        for (E item : array) {
            heap.add(item);
        }
        int j = 0;
       for(int i = array.length - 1; i >= 0; i--) {
           if (heap.size() > k) {
               heap.next();
               array[i] = null;
           } else {
               array[j] = heap.next();
               j++;
           }
       }
    }
}
