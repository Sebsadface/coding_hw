// Lefei (Sebastian) Liu
// 05/27/21
// CSE143
// TA: Randair Porter
// Homework 7 (20 Questions)
//
// Class QuestionTree can be used to play a game which is like a big game of 20 questions where
// each question is a yes/no question. The computer will try to guess the object that the player
// has in mind by asking you a series of yes or no questions. Eventually the computer will make
// a guess about what player's object is. If this guess is correct, the computer wins; if not,
// you win. The computer grows more intelligent each time it loses a game by taking in a new
// question it can ask to help it in future games from the user.

import java.util.*;
import java.io.*;

public class QuestionTree {
    // a question node used to keep track of the overall root of the question tree
    private QuestionNode overallRoot;
    private Scanner console; // a scanner used to take in user input

    // post: constructs a console scanner and a question tree with one answer node(leaf node)
    //       representing the object "computer"
    public QuestionTree() {
        overallRoot = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    // post: replaces the current tree by reading another file with given input scanner
    //       the file is assumed to be legal and in standard format: a tree is specified
    //       by a nonempty sequence of line pairs, one for each node of the tree. The first
    //       line of each pair should contain either the text “Q:” to indicate that it is a
    //       question node (a branch node) or the text “A:” to indicate that it is an
    //       answer node (a leaf node). The second line of each pair should contain the
    //       text for that node (the question or answer). The nodes should appear in preorder
    //       (in the order produced by a preorder traversal of the tree).
    //       it assumes any node on the question tree is either a question node(a branch node
    //       with both left and right subtrees being nonempty) or a answer node(a leaf node)
    public void read(Scanner input) {
        overallRoot = read(input, overallRoot);
    }

    // post: stores the current tree to an output file with given output PrintStream
    //       in standard format: a tree is specified by a nonempty sequence of line pairs,
    //       one for each node of the tree. The first line of each pair should contain either
    //       the text “Q:” to indicate that it is a question node (a branch node) or the text
    //       “A:” to indicate that it is an answer node (a leaf node). The second line of each
    //       pair should contain the (in the order produced by a preorder traversal of the tree).
    //       it assumes any node on the question tree is either a question node(a branch node
    //       with both left and right subtrees being nonempty) or a answer node(a leaf node)
    public void write(PrintStream output) {
        write(output, overallRoot);
    }

    // post: asks the user a series of yes/no questions with the current question tree until it
    //       guesses the object correctly or it fails, in which case it will expands the tree
    //       to include user's object and a new question to distinguish user's object from
    //       the others.
    //       it assumes any node on the question tree is either a question node(a branch node
    //       with both left and right subtrees being nonempty) or a answer node(a leaf node)
    public void askQuestions() {
        overallRoot = askQuestions(overallRoot);
    }

    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
   }

   // post: helps the public read method to replace the current tree by reading
   //       another file with given input scanner and question tree root
   //       the file is assumed to be legal and in standard format
   //       it assumes any node on the question tree is either a question node(a branch node
   //       with both left and right subtrees being nonempty) or a answer node(a leaf node)
   private QuestionNode read(Scanner input, QuestionNode root) {
        if (input.hasNextLine()) {
            if (input.nextLine().equals("Q:")) {
                root = new QuestionNode(input.nextLine());
                root.left = read(input, root.left);
                root.right = read(input, root.right);
            } else {
                root = new QuestionNode(input.nextLine());
            }
        }
        return root;
   }


   // post: helps the public write method to store the current tree to an output
   //       file with given output PrintStream and question tree root in standard format
   //       it assumes any node on the question tree is either a question node(a branch node
   //       with both left and right subtrees being nonempty) or a answer node(a leaf node)
   private void write(PrintStream output, QuestionNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                output.println("A:");
            } else {
                output.println("Q:");
            }
            output.println(root.data);
            write(output, root.left);
            write(output, root.right);
        }
   }

   // post: helps the public askQuestions method to ask the user a series of yes/no questions
   //       with the current question tree and returns a updated version of the question tree
   //       if it fails guessing the object it assumes any node on the question tree is either
   //       a question node(a branch node with both left and right subtrees being nonempty) or
   //       a answer node(a leaf node)
   private QuestionNode askQuestions(QuestionNode root) {
        if (root.left == null && root.right == null) {
            if (yesTo("Would your object happen to be " + root.data + "?")) {
                System.out.println("Great, I got it right!");
            } else {
                String name = getName();
                String question = getQuestion();
                if (yesTo("And what is the answer for your object?")) {
                    root = new QuestionNode(question, new QuestionNode(name), root);
                } else {
                    root = new QuestionNode(question, root, new QuestionNode(name));
                }
            }
        } else {
            if (yesTo(root.data)) {
               root.left = askQuestions(root.left);
            } else {
                root.right = askQuestions(root.right);
            }
        }
        return root;
   }

   // post: returns the name of the object that the user has in mind
   private String getName() {
       System.out.print("What is the name of your object? ");
       return console.nextLine();
   }

   // post: returns the question that the user provide for distinguishing
   //       its objects from the others
   private String getQuestion() {
       System.out.println("Please give me a yes/no question that");
       System.out.println("distinguishes between your object");
       System.out.print("and mine--> ");
       return console.nextLine();
   }
}
