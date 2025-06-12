/*
package trees.binarysearchtree;

public class ValidateBST {


    // Helper function to check if the tree is a BST
    static boolean isBSTUtil(Node_ root, long min, long max) {
        // Base case: an empty tree is a BST
        if (root == null) {
            return true;
        }

        // Check if the current node's value is within the allowed range
        if (root.data <= min || root.data >= max) {
            return false;
        }

        // Recursively check the left and right subtrees
        return isBSTUtil(root.left, min, root.data) && isBSTUtil(root.right, root.data, max);
    }

    // Function to check if the tree is a BST
    static boolean isBST(Node_ root) {
        // Start with the whole range (-infinity, +infinity)
        return isBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

}
*/
