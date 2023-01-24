package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.SimpleIterator;

import java.util.Iterator;

/**
 * 1. The list is typically not sorted.
 * 2. Add new items to the front of the list.
 * 3. Whenever find or insert is called on an existing key, move it
 * to the front of the list. This means you remove the node from its
 * current position and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 * elements to the front.  The iterator should return elements in
 * the order they are stored in the list, starting with the first
 * element in the list. When implementing your iterator, you should
 * NOT copy every item to another dictionary/list and return that
 * dictionary/list's iterator.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    private Node front;

    public MoveToFrontList() {
        front = null;
        size = 0;
    }
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        V returnValue = find(key);
        if (returnValue != null) {
            front.value = value;
        } else {
            front = new Node(key, value, front);
            size++;
        }

        return returnValue;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        V returnValue = null;
        Node current = front;

        if (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }

            while (current.next != null && !current.next.key.equals(key)) {
                current = current.next;
            }

            if (current.next != null) {
                returnValue = current.next.value;
                Node temp = current.next;
                current.next = current.next.next;
                temp.next = front;
                front = temp;
            }
        }
        return  returnValue;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new MTFLIterator();
    }

    private class MTFLIterator extends SimpleIterator<Item<K, V>> {
       private Node current;

       public MTFLIterator() {
           current = front;
       }

       public boolean hasNext() {
           return current != null;
       }

       public Item<K,V> next() {
           Item<K,V> next = current;
           current = current.next;
           return next;
        }
    }

    private class Node extends Item<K,V>{
        private Node next;

        public Node (K key, V value) {
            this(key,value,null);
        }
        public Node (K key, V value, Node next) {
            super(key,value);
            this.next = next;
        }
    }

}
