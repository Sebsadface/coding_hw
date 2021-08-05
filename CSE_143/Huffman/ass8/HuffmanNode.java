public class HuffmanNode implements Comparable<HuffmanNode> {
    public int character;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode() {
        this(-1, -1, null, null);
    }

    public HuffmanNode(int character) {
        this(character, -1, null, null);
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this(-1, frequency, left, right);
    }
    public HuffmanNode(int character, int frequency) {
        this(character, frequency, null, null);
    }

    public HuffmanNode(int character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public int compareTo(HuffmanNode other) {
        return frequency - other.frequency;
    }
}
