package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;
import datastructures.worklists.ArrayStack;

import java.security.Key;

/**
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 * <p>
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 * instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 * children array or left and right fields in AVLNode.  This will
 * instead mask the super-class fields (i.e., the resulting node
 * would actually have multiple copies of the node fields, with
 * code accessing one pair or the other depending on the type of
 * the references used to access the instance).  Such masking will
 * lead to highly perplexing and erroneous behavior. Instead,
 * continue using the existing BSTNode children array.
 * 4. Ensure that the class does not have redundant methods
 * 5. Cast a BSTNode to an AVLNode whenever necessary in your AVLTree.
 * This will result a lot of casts, so we recommend you make private methods
 * that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 * 7. The internal structure of your AVLTree (from this.root to the leaves) must be correct
 */

public class AVLTree<K extends Comparable<? super K>, V> extends BinarySearchTree<K, V> {

    public AVLTree() {
        super();
    }

    private class AVLNode extends BSTNode{
        private int height;

        public AVLNode(K key, V value) {
            super(key, value);
            this.height = 0;
        }

    }


    @Override
    public V insert(K key, V value){
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        AVLNode current = locate(key, value);
        V oldValue = current.value;
        current.value = value;
        return oldValue;
    }

    private AVLNode locate(K key, V value) {
        ArrayStack<AVLNode> path = new ArrayStack<>();
        AVLNode prev = null;
        AVLNode current = (AVLNode) this.root;
        int child = -1;

        // locate the key
        while (current != null) {
            path.add(current);
            int direction = Integer.signum(key.compareTo(current.key));

            if (direction == 0) {
                //node found
                return current;
            } else {
                // move to the next node.
                child = Integer.signum(direction + 1);
                prev = current;
                current = (AVLNode) current.children[child];
            }

        }

        // key not found, inserting a new node
        if (value != null) {
            current = new AVLNode(key, null);
            if (this.root == null) {
                // this is an empty tree, no need to deal with parents
                this.root = current;
            } else {
                // child index is set in the loop above.
                assert(child >= 0);
                // connect the new child with its parents
                prev.children[child] = current;

                AVLNode problemNode = updateHeights(path);
                if (problemNode != null) {
                    path.next();
                    AVLNode parent = null;
                    if (path.hasWork()) {
                    parent = path.peek();
                    }

                    balanceTree(problemNode, parent, key);
                    updateHeights((AVLNode) this.root);
                }
            }
            this.size++;
        }
        return current;
    }

    private void balanceTree(AVLNode problemNode, AVLNode parent, K newKey) {
        AVLNode child;
        int step0,step1,step2;
        step0 = -1;

        if (parent != null) {
            step0 = Integer.signum(Integer.signum(newKey.compareTo(parent.key)) + 1);
        }

        step1 = Integer.signum(Integer.signum(newKey.compareTo(problemNode.key)) + 1);

        child = (AVLNode) problemNode.children[step1];
        step2 = Integer.signum(Integer.signum(newKey.compareTo(child.key)) + 1);

        if (step1 == step2) {
            // single rotation
            rotate(parent, problemNode, child, step0, step1);
        } else {
            // double rotation
            AVLNode grandchild = (AVLNode) child.children[step2];
            rotate(problemNode, child, grandchild, step1, step2);
            rotate(parent, problemNode, grandchild, step0, step1);
        }

    }

    private int getHeight (AVLNode parent, int child) {
        if (parent.children != null && parent.children[child] != null) {
            return ((AVLNode)parent.children[child]).height;
        } else {
            return -1;
        }
    }

    private void rotate (AVLNode prev, AVLNode current, AVLNode child, int step0, int step1) {
        if (prev == null) {
            this.root = child;
        } else {
            prev.children[step0] = child;
        }

        int inverse;

        if (step1 == 0) {
            inverse = 1;
        } else {
            inverse = 0;
        }

        current.children[step1] = child.children[inverse];
        child.children[inverse] = current;
    }

    private AVLNode updateHeights(ArrayStack<AVLNode> path) {
        int leftHeight;
        int rightHeight;

        while (path.hasWork()) {
            leftHeight = getHeight(path.peek(), 0);
            rightHeight = getHeight(path.peek(), 1);

            if (Math.abs(leftHeight - rightHeight) > 1){
                return path.peek();
            } else {
                path.peek().height = Math.max(leftHeight, rightHeight) + 1;
                path.next();
            }
        }
        return null;
    }

    private void updateHeights(AVLNode node) {
        heightsHelper(node);
    }

    private int heightsHelper(AVLNode subTree) {
        if (subTree == null){
            return -1;
        } else if (subTree.children[0] == null && subTree.children[1] == null) {
            subTree.height = 0;
        } else {
            subTree.height = Math.max(heightsHelper((AVLNode) subTree.children[0]),
                    heightsHelper((AVLNode) subTree.children[1])) +1;
        }
        return subTree.height;
    }

}
