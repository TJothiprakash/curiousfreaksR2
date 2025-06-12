package trees.binarysearchtree;

public class RangeofValuesinBST {

    // Helper function to count nodes in range [l, h]
    static int countNodesInRange(Node_ root, int l, int h) {
        if (root == null) {
            return 0;
        }

        int count = 0;

        // If current node's value is within the range [l, h]
        if (root.data >= l && root.data <= h) {
            count = 1; // Include the current node
        }

        // If current node's value is greater than l, explore the left subtree
        if (root.data > l) {
            count += countNodesInRange(root.left, l, h);
        }

        // If current node's value is smaller than h, explore the right subtree
        if (root.data < h) {
            count += countNodesInRange(root.right, l, h);
        }

        return count;
    }

    // Main function to count nodes in range [l, h]
    static int MaincountNodesInRange(Node_ root, int l, int h) {
        return countNodesInRange(root, l, h);
    }

}
