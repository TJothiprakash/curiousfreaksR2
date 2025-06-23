package trees;

/**
 * 📌 Problem:
 * Flatten a binary tree into a linked list following preorder traversal.
 * <p>
 * 🧠 Approach:
 * - Use reverse preorder traversal (right → left → root)
 * - Maintain a global `prev` pointer to link nodes
 * <p>
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(h) - for recursion stack
 */

public class FlattenBinaryTree {

    private static TreeNode prev = null;

    public static void flatten(TreeNode root) {
        if (root == null) return;

        // Recurse right → left → root
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    // Print the flattened tree (linked list style)
    public static void printRightList(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    // 🧪 Test Cases
    public static void main(String[] args) {
        /*
         Input:
             1
            / \
           2   5
          / \   \
         3   4   6

         Output: 1 2 3 4 5 6
         */

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(6);

        flatten(root1);
        System.out.print("Flattened Tree 1: ");
        printRightList(root1);

        // Reset prev before next test
        prev = null;

        /*
         Input:
             1
            / \
           3   4
              /
             2
              \
               5

         Output: 1 3 4 2 5
         */
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(2);
        root2.right.left.right = new TreeNode(5);

        flatten(root2);
        System.out.print("Flattened Tree 2: ");
        printRightList(root2);
    }
}
