package trees.june_11.pathanddistance;

/**
 * 📌 Question:
 * Given a binary tree, find the maximum difference between a node A and its descendant B,
 * such that A is an ancestor of B and difference A - B is maximized.
 * <p>
 * 💡 Intuition:
 * For each node A, the maximum difference can be calculated by finding the minimum
 * value in its subtree (i.e., the smallest B under A).
 * <p>
 * 🧠 Approach:
 * - Use post-order DFS
 * - At each node, find the minimum value in its subtree.
 * - Compute current difference: node.data - min
 * - Update maxDiff if this is higher
 * <p>
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(h), where h = tree height
 */

public class MaxAncestorDifference {

    private static int maxDiff;

    public static int maxAncestorDiff(TreeNode root) {
        maxDiff = Integer.MIN_VALUE;
        dfs(root);
        return maxDiff;
    }

    // Returns the minimum value in the subtree rooted at node
    private static int dfs(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;

        // Leaf node → return its value
        if (node.left == null && node.right == null) {
            return node.data;
        }

        int leftMin = dfs(node.left);
        int rightMin = dfs(node.right);

        int minDescendant = Math.min(leftMin, rightMin);

        // Update max difference (A - B)
        maxDiff = Math.max(maxDiff, node.data - minDescendant);

        // Return min value including current node
        return Math.min(node.data, minDescendant);
    }

    // 🧪 Test cases
    public static void main(String[] args) {
        /*
            Tree 1:
                5
               / \
              2   1
            Output: 4 (5 - 1)

            Tree 2:
                1
               / \
              2   3
                   \
                    7
            Output: -1 (1 - 2)
         */

        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(1);
        System.out.println("Max ancestor diff (Tree 1): " + maxAncestorDiff(root1)); // 4

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(7);
        System.out.println("Max ancestor diff (Tree 2): " + maxAncestorDiff(root2)); // -1
    }
}

