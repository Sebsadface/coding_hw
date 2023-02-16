package datastructures.dictionaries;

import com.sun.net.httpserver.Filter;
import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;
import cse332.interfaces.trie.TrieMap;
import cse332.interfaces.worklists.WorkList;
import cse332.types.BString;
import datastructures.worklists.ArrayStack;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<ChainingHashTable<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new ChainingHashTable<>(AVLTree::new);
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            ArrayStack<Entry<A,HashTrieNode>> stack = new ArrayStack<>();
            for (Item<A, HashTrieNode> item : this.pointers) {
                stack.add(new AbstractMap.SimpleEntry<>(item.key, item.value));
            }
            return stack.iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        if (this.root == null){
            this.root = new HashTrieNode();
        }

        V returnValue = null;
        HashTrieNode currentNode = (HashTrieNode) this.root;
        for (A character : key) {
            if (currentNode.pointers.find(character) == null) {
                currentNode.pointers.insert(character, new HashTrieNode());
            }
            currentNode = currentNode.pointers.find(character);
        }
        returnValue = currentNode.value;
        currentNode.value = value;

        if (returnValue == null) {
            this.size++;
        }
        return returnValue;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        HashTrieNode currentNode = (HashTrieNode) this.root;
        for (A character : key) {
            if (currentNode.pointers.find(character) == null) {
                return null;
            }
            currentNode = currentNode.pointers.find(character);
        }
        return currentNode.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (this.size == 0) {
            return false;
        }

        HashTrieNode currentNode = (HashTrieNode) this.root;

        for (A character: key) {
            if (currentNode.pointers.find(character) == null) {
                return false;
            }
            currentNode = currentNode.pointers.find(character);
        }

        return true;
    }

    @Override
    public void delete(K key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

}
