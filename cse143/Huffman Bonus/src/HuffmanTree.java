// Lefei (Sebastian) Liu
// 06/06/21
// CSE143
// TA: Randair Porter
// Homework 8 BONUS (HuffmanTree2)
//
// Class HuffmanTree can be used to compress text files through Huffman coding scheme

import java.util.*;
import java.io.*;

public class HuffmanTree {
    // a Huffman node used to keep tract of the overall root of the Huffman tree
    private HuffmanNode overallRoot;

    // post: constructs a Huffman tree with given array of frequencies where count[i] is
    //       the number of occurrences of the character with integer value i
    public HuffmanTree(int[] count) {
        overallRoot = buildTree(prepareLeafQueue(count));
    }

    // post: constructs a Huffman tree with given Scanner which contains a tree stored
    //       in standard format
    public HuffmanTree(Scanner input) {
        overallRoot = buildTree(input, overallRoot);
    }

    // post: writes current Huffman tree to the given output stream in standard format
    //       the code for the character appears in the order that any standard traversal
    //       of the tree would visit them
    public void write(PrintStream output) {
        write(output, overallRoot, "");
    }

    // post: reads individual bits from the given input stream
    //       writes the corresponding characters to the given output
    //       stops reading when a character with value equal to the given eof character
    //       is encountered
    //       the pseudo-eof character won't be written to the given output
    //       the given input stream is assumed to contain a legal encoding of characters for
    //       this tree's Huffman code
    public void decode(BitInputStream input, PrintStream output, int eof) {
        int currentBit = input.readBit();
        while (currentBit != -1) {
            currentBit = writeCharacter(input, output, eof,currentBit, overallRoot);
        }
    }

    // post: creates Huffman leaf nodes and a pseudo-eof node with a frequency of 1
    //       stores the leaf nodes in sorted order(from the lowest to the highest)
    //       base on their frequencies
    private Queue<HuffmanNode> prepareLeafQueue(int[] count) {
        Queue<HuffmanNode> leafQueue = new PriorityQueue<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                leafQueue.add(new HuffmanNode(i, count[i]));
            }
        }
        leafQueue.add(new HuffmanNode(count.length, 1));
        return leafQueue;
    }

    // post: builds a Huffman tree with given leaf queue
    //       the given queue is assumed to store all the leaf nodes in sorted order
    //       (from the lowest to the highest) base on their frequencies
    //       the given queue will be empty after this methods done executing
    private HuffmanNode buildTree(Queue<HuffmanNode> leafQueue) {
        while(leafQueue.size() - 1 != 0) {
            HuffmanNode leftLeaf = leafQueue.remove();
            HuffmanNode rightLeaf = leafQueue.remove();
            leafQueue.add(new HuffmanNode(leftLeaf.frequency + rightLeaf.frequency,
                    leftLeaf, rightLeaf));
        }
        return leafQueue.remove();
    }

    // post: builds a Huffman tree with given root and Scanner which contains a tree stored
    //       in standard format
    private HuffmanNode buildTree(Scanner input, HuffmanNode root) {
        while (input.hasNextLine()) {
            root = buildBranch(Integer.parseInt(input.nextLine()), input.nextLine(), root);
        }
        return root;
    }

    // post: builds a branch of the Huffman tree leading to a leaf node with given
    //       character, code, and root
    //       the given code is assumed to be in traversal order
    private HuffmanNode buildBranch(int character, String code, HuffmanNode root) {
        if (root == null) {
            root = new HuffmanNode();
        }
        if (code.length() == 1) {
            if (code.charAt(0) == '0') {
                root.left = new HuffmanNode(character);
            } else { // code.charAt(0) == "1"
                root.right = new HuffmanNode(character);
            }
        } else { // code.length() > 1
            if (code.charAt(0) == '0') {
                root.left = buildBranch(character, code.substring(1), root.left);
            } else { // code.charAt(0) == "1"
                root.right = buildBranch(character, code.substring(1), root.right);
            }
        }
        return root;
    }

    // post: writes current Huffman tree to the given output stream in standard format
    //       with given root of the tree
    //       the code for the character appears in traversal order
    //       the tree is assumed to only contain nodes that either have two nonempty subtrees
    //       or two empty subtrees(leaf nodes) and nothing in between
    private void write(PrintStream output, HuffmanNode root, String code) {
        if (root.left == null && root.right == null) {
            output.println(root.character);
            output.println(code);
        } else { // roo.left != null && root.right != null
            write(output, root.left, code + 0);
            write(output, root.right, code + 1);
        }
    }

    // post: writes a character to the given output with given code and tree root
    //       returns -1 if encounters a character with value equal to the eof parameter
    //       otherwise returns current bit of code(0 or 1)
    //       the tree is assumed to only contain nodes that either have two nonempty subtrees
    //       or two empty subtrees(leaf nodes) and nothing in between
    private int writeCharacter(BitInputStream inputStream, PrintStream output, int eof,
                               int currentBit, HuffmanNode root) {
        if (root.character == eof) {
            return  -1;
        } else if (root.left == null && root.right == null) {
            output.write(root.character);
            return currentBit;
        } else if (currentBit == 0) {
            return writeCharacter(inputStream, output, eof, inputStream.readBit(), root.left);
        } else { // root.character != eof && root.right != null && currentBit == 1
            return writeCharacter(inputStream, output, eof, inputStream.readBit(), root.right);
        }
    }
}
