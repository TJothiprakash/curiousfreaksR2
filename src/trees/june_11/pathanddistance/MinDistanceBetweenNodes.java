package trees.june_11.pathanddistance;

/**
 * 📌 Question:
 * Given a binary tree and two node values `a` and `b`, find the **minimum distance** between them
 * (in terms of number of edges).
 *
 * 💡 Intuition:
 * The shortest path between any two nodes in a binary tree goes through their **Lowest Common Ancestor (LCA)**.
 *
 * 🧠 Approach:
 * 1. Find the **LCA** of nodes `a` and `b`.
 * 2. Compute distance from LCA to node `a`.
 * 3. Compute distance from LCA to node `b`.
 * 4. Return the sum of those two distances.
 *
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(h) — for recursion stack
 */

public class MinDistanceBetweenNodes {

    // Main function to find minimum distance between nodes a and b
    public static int findMinDistance(TreeNode root, int a, int b) {
        TreeNode lca = findLCA(root, a, b);
        int d1 = findLevel(lca, a, 0);
        int d2 = findLevel(lca, b, 0);
        return d1 + d2;
    }

    // Finds Lowest Common Ancestor of two nodes
    private static TreeNode findLCA(TreeNode root, int a, int b) {
        if (root == null) return null;
        if (root.data == a || root.data == b) return root;

        TreeNode left = findLCA(root.left, a, b);
        TreeNode right = findLCA(root.right, a, b);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    // Finds level of a node from the root
    private static int findLevel(TreeNode root, int val, int level) {
        if (root == null) return -1;
        if (root.data == val) return level;

        int left = findLevel(root.left, val, level + 1);
        if (left != -1) return left;

        return findLevel(root.right, val, level + 1);
    }

    // 🧪 Test cases
    public static void main(String[] args) {
        /*
              1
             / \
            2   3
           / \
          4   5

          Test Case 1:
          a = 4, b = 5 → LCA = 2 → Distance = 2

          Test Case 2:
          a = 4, b = 3 → LCA = 1 → Distance = 3
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Min distance between 4 and 5: " + findMinDistance(root, 4, 5)); // Output: 2
        System.out.println("Min distance between 4 and 3: " + findMinDistance(root, 4, 3)); // Output: 3
        System.out.println("Min distance between 2 and 4: " + findMinDistance(root, 2, 4)); // Output: 1
    }
}
