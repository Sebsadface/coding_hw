// Lefei (Sebastian) Liu
// 06/06/21
// CSE143
// TA: Randair Porter
// Homework 8 BONUS (HuffmanTree2)
//
// Class HuffmanTree2 can be used to compress text files through Huffman coding scheme

import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanTree2 {
    // a Huffman node used to keep tract of the overall root of the Huffman tree
    private HuffmanNode overallRoot;

    // post: constructs a Huffman tree with given array of frequencies where count[i] is
    //       the number of occurrences of the character with integer value i
    public HuffmanTree2(int[] count) {
        overallRoot = buildTree(prepareLeafQueue(count));
    }

    // post: constructs a Huffman tree with given Scanner which contains a tree stored
    //       in standard format
    public HuffmanTree2(Scanner input) {
        overallRoot = buildTree(input, overallRoot);
    }

    // post: constructs a Huffman tree with the given input stream
    //       assumes that the standard bit representation has been
    //       used for the tree
    public HuffmanTree2(BitInputStream input) {
        int currentBit = input.readBit();
        overallRoot = buildTree(input, currentBit, overallRoot);
    }

    // post: assigns codes for each character of the tree to the given array
    //       assumes the array has null values before the method is called
    public void assign(String[] codes) {
        assign(codes, overallRoot, "");
    }

    // post: writes the current tree to the output stream using the standard
    //       bit representation
    public void writeHeader(BitOutputStream output) {
        writeHeader(output, overallRoot);
    }

    // post: builds a Huffman tree with the given input stream
    //       assumes that the standard bit representation has been
    //       used for the tree
    private HuffmanNode buildTree(BitInputStream input, int currentBit, HuffmanNode root) {
        if (root == null) {
            root = new HuffmanNode();
        }
        if (currentBit == 0) {
            root.left = buildTree(input, input.readBit(), root.left);
            root.right = buildTree(input, input.readBit(), root.right);
        } else if (currentBit == 1) {
            root.character = read9(input);
        }
        return root;
    }

    // post: assigns codes for each character of the tree to the given array
    //       assumes the array has null values before the method is called
    //       assumes that the Huffman tree only contain nodes that either have
    //       two nonempty subtrees or two empty subtrees(leaf nodes) and nothing in between
    private void assign(String[] codes, HuffmanNode root, String code) {
        if (root.left == null && root.right == null) {
            codes[root.character] = code;
        } else { // root.left != null && root.right != null
            assign(codes, root.left, code + 0);
            assign(codes, root.right, code + 1);
        }
    }

    // post: writes the current tree to the output stream using the standard
    //       bit representation
    //       assumes that the Huffman tree only contain nodes that either have
    //       two nonempty subtrees or two empty subtrees(leaf nodes) and nothing in between
    private void writeHeader(BitOutputStream output, HuffmanNode root) {
        if (root.left == null && root.right == null) {
            output.writeBit(1);
            write9(output, root.character);
        } else { // root.left != null && root.right != null
            output.writeBit(0);
            writeHeader(output, root.left);
            writeHeader(output, root.right);
        }
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

    // pre : an integer n has been encoded using write9 or its equivalent
    // post: reads 9 bits to reconstruct the original integer
    private int read9(BitInputStream input) {
        int multiplier = 1;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += multiplier * input.readBit();
            multiplier = multiplier * 2;
        }
        return sum;
    }

    // pre : 0 <= n < 512
    // post: writes a 9-bit representation of n to the given output stream
    private void write9(BitOutputStream output, int n) {
        for (int i = 0; i < 9; i++) {
            output.writeBit(n % 2);
            n = n / 2;
        }
    }
}
