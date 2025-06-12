package trees.binarysearchtree;


/*
 * 1. searching
 * 2. Min & max value
 * 3. Find kth largest
 * 4. Find kth smallest
 * */
public class SearchinBST {
    static class Node_ {
        int data;
        Node_ left, right;
        Node_(int data) {
            this.data = data;
            left = right = null;
        }
    }


    static int count = 0; // Counter variable to keep track of visited nodes
    //  static int count = 0; // Counter to keep track of visited nodes

    // Find the minimum element in a BST
    static int findMin(Node_ root) {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // Find the maximum element in a BST
    static int findMax(Node_ root) {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    // Helper function to find the k-th largest element
    static int kthLargestUtil(Node_ root, int k) {
        // Base case: if root is null or k elements are already visited
        if (root == null) {
            return -1;
        }

        // Traverse the right subtree first (reverse inorder)
        int result = kthLargestUtil(root.right, k);
        if (result != -1) {
            return result; // Return result if found
        }

        // Increment count of visited nodes
        count++;
        if (count == k) {
            return root.data; // Return the k-th largest element
        }

        // Traverse the left subtree
        return kthLargestUtil(root.left, k);
    }

    // Function to find the k-th largest element
    static int kthLargest(Node_ root, int k) {
        count = 0; // Reset counter before starting
        return kthLargestUtil(root, k);
    }

    // Helper function to find the k-th smallest element
    static int kthSmallestUtil(Node_ root, int k) {
        // Base case: if root is null or k elements are already visited
        if (root == null) {
            return -1;
        }

        // Traverse the left subtree first (inorder)
        int result = kthSmallestUtil(root.left, k);
        if (result != -1) {
            return result; // Return result if found
        }

        // Increment count of visited nodes
        count++;
        if (count == k) {
            return root.data; // Return the k-th smallest element
        }

        // Traverse the right subtree
        return kthSmallestUtil(root.right, k);
    }
    // kth smallest

    // Function to find the k-th smallest element
    static int kthSmallest(Node_ root, int k) {
        count = 0; // Reset counter before starting
        return kthSmallestUtil(root, k);
    }

    // search in bst
    boolean search(Node_ root, int x) {
        // Base case: If the root is null, the value is not found
        if (root == null) {
            return false;
        }

        // If the current node's data matches x, return true
        if (root.data == x) {
            return true;
        }

        // Recur to the left subtree if x is smaller than root's data
        if (x < root.data) {
            return search(root.left, x);
        }

        // Otherwise, recur to the right subtree
        return search(root.right, x);
    }

    // iterative search in bst
    boolean searchUsingIteration(Node_ root, int x) {
        while (root != null) {
            if (root.data == x) {
                return true; // Found the node
            } else if (x < root.data) {
                root = root.left; // Move to the left subtree
            } else {
                root = root.right; // Move to the right subtree
            }
        }
        return false; // Not found
    }


}
