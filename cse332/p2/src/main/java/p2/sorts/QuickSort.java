package p2.sorts;

import cse332.exceptions.NotYetImplementedException;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, Comparable::compareTo);
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        quickSort(array, comparator, 0, array.length - 1);
    }

    private static <E> void quickSort(E[] array, Comparator<E> comparator, int start, int end) {
        if (start < end) {
            int pivot = partition(array, comparator, start, end);
            quickSort(array, comparator, start, pivot - 1);
            quickSort(array, comparator, pivot + 1, end);
        }
    }

    private static <E> int partition (E[] array, Comparator<E> comparator, int start, int end) {
        swap(array, end, getMid(array, comparator, start, end));

        E pivot = array[end];
        int i = start - 1;
        for (int j = start; j <= end; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    private static <E> int getMid(E[] array, Comparator<E> comparator, int start, int end) {
        int mid = (start + end) / 2;
        if (comparator.compare(array[start], array[mid]) < 0) {
            if (comparator.compare(array[mid], array[end]) < 0) {
                return mid;
            } else if (comparator.compare(array[start], array[end]) < 0) {
                return end;
            } else {
                return start;
            }
        } else {
            if (comparator.compare(array[start], array[end]) < 0) {
                return start;
            } else if (comparator.compare(array[mid], array[end]) < 0) {
                return end;
            } else {
                return mid;
            }
        }
    }
}
