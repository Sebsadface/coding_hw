package datastructures.dictionaries;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
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
            if (!currentNode.pointers.containsKey(character)) {
                currentNode.pointers.put(character, new HashTrieNode());
            }
            currentNode = currentNode.pointers.get(character);
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
            if (! currentNode.pointers.containsKey(character)) {
                return null;
            }
            currentNode = currentNode.pointers.get(character);
        }
        return currentNode.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        HashTrieNode currentNode = (HashTrieNode) this.root;
        for (A character: key) {
            if (!currentNode.pointers.containsKey(character)) {
                return false;
            }
            currentNode = currentNode.pointers.get(character);
        }

        return true;
    }

    @Override
    public void delete(K key) {
       if (key == null) {
           throw new IllegalArgumentException();
       }

       HashTrieNode currentNode = (HashTrieNode) this.root;
       HashTrieNode lastValidNode = (HashTrieNode) this.root;
       A lastValidCharacter = null;

       for (A character : key) {
           if (currentNode.pointers.size() > 1 || currentNode.value != null) {
               lastValidNode = currentNode;
               lastValidCharacter = character;
           }

           if (!currentNode.pointers.containsKey(character)) {
               return;
           }

           currentNode = currentNode.pointers.get(character);
       }

       if (currentNode.pointers.isEmpty()) {
           lastValidNode.pointers.remove(lastValidCharacter);
       }
        currentNode.value = null;

       this.size--;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = new HashTrieNode();
    }
}
