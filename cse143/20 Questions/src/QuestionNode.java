// Lefei (Sebastian) Liu
// 05/27/21
// CSE143
// TA: Randair Porter
// Homework 7 (20 Questions)
//
// Class QuestionNode can be used to store a single node of a question tree
// which can be used in a QuestionTree class

public class QuestionNode {
    public String data; // a string that stores the data of a node
    public QuestionNode left; // a link to the left subtree
    public QuestionNode right; // a link to the right subtree

    // post: constructs an answer node(leaf node) of a question tree using the given data
    public QuestionNode(String data) {
        this(data, null, null);
    }

    // post: constructs a question node(branch node) of a question tree with given data,
    // left subtree, right subtree
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
