package trees;

/**
 * 📌 Problem:
 * Maximize the sum of node values in a binary tree such that
 * no two directly connected nodes are selected.
 * <p>
 * 💡 This is a Tree DP problem (a variant of House Robber III).
 * <p>
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(h) for recursion stack
 */

public class MaxSumWithoutAdjacent {

    // Class to hold include and exclude values
    static class Pair {
        int include, exclude;

        Pair(int in, int ex) {
            this.include = in;
            this.exclude = ex;
        }
    }

    public static int getMaxSubsetSum(TreeNode root) {
        Pair result = dfs(root);
        return Math.max(result.include, result.exclude);
    }

    // Post-order traversal that returns max sum pair
    private static Pair dfs(TreeNode node) {
        if (node == null) return new Pair(0, 0);

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        // Include current → skip children
        int include = node.data + left.exclude + right.exclude;

        // Exclude current → pick max of left and right
        int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);

        return new Pair(include, exclude);
    }

    // 🧪 Test Cases
    public static void main(String[] args) {
        /*
             Tree 1:
                11
               /  \
              1    2
            Output: 11

             Tree 2:
                1
               / \
              2   3
             /     \
            4       5
                       \
                        6
            Output: 16 (1 + 4 + 5 + 6)
         */

        TreeNode root1 = new TreeNode(11);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(2);
        System.out.println("Max subset sum (Tree 1): " + getMaxSubsetSum(root1)); // 11

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.right = new TreeNode(6);
        System.out.println("Max subset sum (Tree 2): " + getMaxSubsetSum(root2)); // 16
    }
}
