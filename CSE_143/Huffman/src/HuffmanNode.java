// Lefei (Sebastian) Liu
// 06/04/21
// CSE143
// TA: Randair Porter
// Homework 8 (Huffman)
//
// Class HuffmanNode can be used to store a single node of a Huffman tree
// which can be used in a HuffmanTree class

public class HuffmanNode implements Comparable<HuffmanNode> {
    public int character; // an integer stores the character
    public int frequency; // an integer stores the number of times the character appeared
    public HuffmanNode left; // a reference to the left subtree
    public HuffmanNode right; // a reference to the right subtree

    // post: constructs a empty Huffman node
    public HuffmanNode() {
        this(-1, -1, null, null);
    }

    // post: constructs a Huffman leaf node with given character
    public HuffmanNode(int character) {
        this(character, -1, null, null);
    }

    // post: constructs a huffman leaf node with given character and frequency
    public HuffmanNode(int character, int frequency) {
        this(character, frequency, null, null);
    }

    // post: constructs a huffman node with given frequency and references to
    //       left and right subtrees
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this(-1, frequency, left, right);
    }

    // post: constructs a huffman node with given character, frequency, and references to
    //       left and right subtrees
    public HuffmanNode(int character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // post: compares two Huffman nodes by their frequencies
    //       returns the difference of the frequencies
    public int compareTo(HuffmanNode other) {
        return frequency - other.frequency;
    }
}
