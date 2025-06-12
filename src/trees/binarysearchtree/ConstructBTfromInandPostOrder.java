package trees.binarysearchtree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ConstructBTfromInandPostOrder {

    public TreeNode buildTree(Vector<Integer> inorder, Vector<Integer> postorder) {
        if (inorder.size() != postorder.size()) {
            return null;
        }

        // Create a map to store the indices
        // of elements in the inorder traversal
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            hm.put(inorder.get(i), i);
        }

        // Call the recursive function
        // to build the binary tree
        return buildTreePostIn(inorder, 0, inorder.size() - 1, postorder, 0,
                postorder.size() - 1, hm);
    }

    public TreeNode buildTreePostIn(Vector<Integer> inorder, int is, int ie,
                                    Vector<Integer> postorder, int ps, int pe, Map<Integer, Integer> hm) {

        // Base case: If the subtree
        // is empty, return null
        if (ps > pe || is > ie) {
            return null;
        }

        // Create a new TreeNode
        // with the root value from postorder
        TreeNode root = new TreeNode(postorder.get(pe));

        // Find the index of the root
        // value in inorder traversal
        int inRoot = hm.get(postorder.get(pe));

        // Number of nodes in the left subtree
        int numsLeft = inRoot - is;

        // Recursively build the
        // left and right subtrees
        root.left = buildTreePostIn(inorder, is, inRoot - 1, postorder,
                ps, ps + numsLeft - 1, hm);

        root.right = buildTreePostIn(inorder, inRoot + 1, ie, postorder,
                ps + numsLeft, pe - 1, hm);

        // Return the root of
        // the constructed subtree
        return root;
    }
}

// Function to print the
// inorder traversal of a tree
class TreeTraversal {
    public static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
}

class Main {
    // Function to print the given vector
    public static void printVector(Vector<Integer> vec) {
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example input vectors
        Vector<Integer> inorder = new Vector<>();
        inorder.addAll(Arrays.asList(40, 20, 50, 10, 60, 30));

        Vector<Integer> postorder = new Vector<>();
        postorder.addAll(Arrays.asList(40, 50, 20, 60, 30, 10));

        // Display the input vectors
        System.out.print("Inorder Vector: ");
        printVector(inorder);

        System.out.print("Postorder Vector: ");
        printVector(postorder);

        ConstructBTfromInandPostOrder sol = new ConstructBTfromInandPostOrder();

        // Build the binary tree and
        // print its inorder traversal
        TreeNode root = sol.buildTree(inorder, postorder);

        System.out.println("Inorder of Unique Binary Tree Created: ");
        TreeTraversal.printInorder(root);
        System.out.println();
    }
}
                            
                        