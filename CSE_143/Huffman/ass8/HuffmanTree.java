import java.util.*;
import java.io.*;

public class HuffmanTree {
    private HuffmanNode overallRoot;

    public HuffmanTree(int[] count) {
        buildTree(prepareLeafQueue(count));
    }

    public HuffmanTree(Scanner input) {
        overallRoot = buildTree(input, overallRoot);
    }

    public void write(PrintStream output) {
        write(output, overallRoot, "");
    }

    public void decode(BitInputStream input, PrintStream output, int eof) {
        int currentBit = input.readBit();
        while (currentBit != -1) {
            currentBit = writeCharacter(input, output, eof,currentBit, overallRoot);
        }
    }

    private int writeCharacter(BitInputStream inputStream, PrintStream output, int eof,
                               int currentBit, HuffmanNode root) {
        if (root.character == eof) {
            return  -1;
        } else if (root.left == null && root.right == null) {
            output.write(root.character);
            return currentBit;
        } else if (currentBit == 0) {
            return writeCharacter(inputStream, output, eof, inputStream.readBit(), root.left);
        } else {
            return writeCharacter(inputStream, output, eof, inputStream.readBit(), root.right);
        }
    }

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

    private void buildTree(Queue<HuffmanNode> leafQueue) {
        while(leafQueue.size() - 1 != 0) {
            HuffmanNode leftLeaf = leafQueue.remove();
            HuffmanNode rightLeaf = leafQueue.remove();
            leafQueue.add(new HuffmanNode(leftLeaf.frequency + rightLeaf.frequency,
                                          leftLeaf, rightLeaf));
        }
        overallRoot = leafQueue.remove();
    }

    private HuffmanNode buildTree(Scanner input, HuffmanNode root) {
        while (input.hasNextLine()) {
            root = buildBranch(Integer.parseInt(input.nextLine()), input.nextLine(), root);
        }
        return root;
    }

    private HuffmanNode buildBranch(int character, String code, HuffmanNode root) {
        if (root == null) {
            root = new HuffmanNode();
        }
        if (code.length() == 1) {
            if (code.charAt(0) == '0') {
                root.left = new HuffmanNode(character);
            } else {
                root.right = new HuffmanNode(character);
            }
        } else {
            if (code.charAt(0) == '0') {
                root.left = buildBranch(character, code.substring(1), root.left);
            } else {
                root.right = buildBranch(character, code.substring(1), root.right);
            }
        }
        return root;
    }

    private void write(PrintStream output, HuffmanNode root, String code ) {
        if (root.left == null && root.right == null) {
            output.println(root.character);
            output.println(code);
        } else {
            write(output, root.left, code + 0);
            write(output, root.right, code + 1);
        }
    }

}
