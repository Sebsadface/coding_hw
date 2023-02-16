package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;
import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ArrayStack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * 1. You must implement a generic chaining hashtable. You may not
 * restrict the size of the input domain (i.e., it must accept
 * any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 * shown in class!).
 * 5. HashTable should be able to resize its capacity to prime numbers for more
 * than 200,000 elements. After more than 200,000 elements, it should
 * continue to resize using some other mechanism.
 * 6. We suggest you hard code some prime numbers. You can use this
 * list: http://primes.utm.edu/lists/small/100000.txt
 * NOTE: Do NOT copy the whole list!
 * 7. When implementing your iterator, you should NOT copy every item to another
 * dictionary/list and return that dictionary/list's iterator.
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private static final int[] HARD_CODED_PRIMES = {89, 191, 383, 773, 1553, 3109, 6229, 12491, 24989, 49999, 10007,
                                                     20021, 40063, 80141, 160357, 320741};
    private static final double LOAD_FACTOR = 0.75;

    private final Supplier<Dictionary<K, V>> newChain;
    private Dictionary<K, V>[] table;
    private int capacity;

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        this.capacity = HARD_CODED_PRIMES[0];
        this.table = new Dictionary[capacity];
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        if (this.size >= this.capacity*LOAD_FACTOR) {
            this.table = rehash(this.table, getNextPrime(capacity));
        }

        int index = Math.abs(key.hashCode() % this.capacity);
        if (table[index] == null) {
            table[index] = this.newChain.get();
        }

        V oldVal = table[index].insert(key,value);
        if ( oldVal == null) {
            this.size++;
        }

        return oldVal;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int index = Math.abs(key.hashCode() % this.capacity);
        if (table[index] == null) {
            return null;
        } else {
            return this.table[Math.abs(key.hashCode() % this.capacity)].find(key);
        }
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new CHTIterator();
    }

    private class CHTIterator extends SimpleIterator<Item<K,V>> {
        private final WorkList<Item<K,V>> items = new ArrayStack<>();

        public CHTIterator() {
            for (Dictionary<K,V> dic : table) {
                if (dic != null && !dic.isEmpty()) {
                    for (Item<K,V> item : dic) {
                        items.add(item);
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return this.items.hasWork();
        }

        @Override
        public Item<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items.next();
        }
    }

    private static int getNextPrime (int currentPrime) {
        int nextPrime = 2 * currentPrime;
        boolean found = false;

        if (currentPrime < HARD_CODED_PRIMES[HARD_CODED_PRIMES.length -1]) {
            for (int hardCodedPrime : HARD_CODED_PRIMES) {
                if (currentPrime < hardCodedPrime) {
                    nextPrime = hardCodedPrime;
                }
            }
        } else {
            while (!found) {
                nextPrime++;
                if (isPrime(nextPrime)) {
                    found = true;
                }
            }
        }
        return nextPrime;
    }

    private static boolean isPrime (int n) {
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    private  Dictionary<K,V>[] rehash(Dictionary<K, V>[] oldTable, int newCapacity) {
        Dictionary<K,V>[] newTable = new Dictionary[newCapacity];

        for (int i = 0; i < this.capacity; i++) {
            if (oldTable[i] != null && !oldTable[i].isEmpty()) {
                for (Item<K,V> item : oldTable[i]) {
                    int index = Math.abs(item.key.hashCode() % newCapacity);
                    if (newTable[index] == null) {
                        newTable[index] = this.newChain.get();
                    }
                    newTable[index].insert(item.key, item.value);
                }
            }
        }

        this.capacity = newCapacity;
        return newTable;
    }
}
