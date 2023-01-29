package verifyavl;

public class VerifyAVL {
    public static boolean verifyAVL(AVLNode root) {
        /* TODO: Edit this with your code */
        if (root == null) {
            return true;
        } else {
            return verify(root);
        }
    }
    /* TODO: Add private methods if you want (recommended) */
    private static boolean verify (AVLNode root) {
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left != null && root.right != null) {
            if (verifyHeight(getHeight(root.left), getHeight(root.right), root.height) &&
                    (getMaxKey(root.left) < root.key) && (root.key < getMinKey(root.right))) {

                return verify(root.left) && verify(root.right);
            }
        } else if (root.left != null) {
            if (verifyHeight(getHeight(root.left), -1 , root.height) && (getMaxKey(root.left) < root.key)) {
                return verify(root.left);
            }

        } else {
            if (verifyHeight(-1, getHeight(root.right) , root.height) && (root.key < getMinKey(root.right))) {
                return verify(root.right);
            }
        }
        return false;
    }

    private static int getHeight (AVLNode root) {
        if (root.right == null && root.left == null) {
            return 0;
        } else if (root.left != null && root.right != null) {
            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        } else if (root.left != null) {
            return getHeight(root.left) + 1;
        } else {
            return getHeight(root.right) + 1;
        }
    }

    private static boolean verifyHeight(int leftHeight, int rightHeight, int currentHeight) {
        return ((Math.max(leftHeight,rightHeight) + 1) == currentHeight) && (Math.abs(leftHeight - rightHeight) <= 1);
    }


    private static int getMaxKey(AVLNode root) {
        if (root.right == null && root.left == null) {
            return root.key;
        } else if (root.left != null && root.right != null) {
            return Math.max(Math.max(getMaxKey(root.left), root.key), getMaxKey(root.right));
        } else if (root.left != null) {
            return Math.max(getMaxKey(root.left), root.key);
        } else {
            return Math.max(getMaxKey(root.right), root.key);
        }
    }

    private static int getMinKey(AVLNode root) {
        if (root.right == null && root.left == null) {
            return root.key;
        } else if (root.left != null && root.right != null) {
            return Math.min(Math.min(getMinKey(root.left), root.key), getMinKey(root.right));
        } else if (root.left != null) {
            return Math.min(getMinKey(root.left), root.key);
        } else {
            return Math.min(getMinKey(root.right), root.key);
        }
    }
}