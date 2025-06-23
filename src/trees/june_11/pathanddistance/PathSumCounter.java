package trees.june_11.pathanddistance;

/**
 * 📌 Question:
 * Given a binary tree and an integer k, count the number of downward-only paths
 * where the sum of node values in the path equals k. A path can start and end
 * at any node but must always move downward (from parent to child).
 * <p>
 * 💡 Intuition:
 * Try starting a path from each node. Do DFS from each node to count how many
 * downward paths sum to k.
 * <p>
 * 🧠 Logic:
 * - From each node, use DFS to find valid paths.
 * - Recursively repeat for all nodes in the tree.
 * - Count matches where accumulated sum == k.
 * <p>
 * ✅ Time Complexity: O(n^2) in worst case (for skewed trees)
 * ✅ Space Complexity: O(h) for recursion (h = height of tree)
 */

public class PathSumCounter {

    // Main function to be called with the root and target sum
    public int pathSum(TreeNode root, int k) {
        if (root == null) return 0;

        // Count paths starting from this node
        int fromRoot = countFromNode(root, k);

        // Count paths in the left and right subtrees
        int fromLeft = pathSum(root.left, k);
        int fromRight = pathSum(root.right, k);

        return fromRoot + fromLeft + fromRight;
    }

    // DFS to count all downward paths from the current node that sum to k
    private int countFromNode(TreeNode node, int k) {
        if (node == null) return 0;

        int count = 0;
        if (node.data == k) count++;

        count += countFromNode(node.left, k - node.data);
        count += countFromNode(node.right, k - node.data);

        return count;
    }

    // TreeNode definition
    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int val) {
            this.data = val;
        }
    }

    // 🧪 Test cases
    public static void main(String[] args) {
        PathSumCounter counter = new PathSumCounter();

        // Constructing the tree
        /*
                  1
                 / \
                2   3
               / \ / \
              1 -1 2  1
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(-1);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(1);

        System.out.println("Test Case 1 (k = 3): " + counter.pathSum(root, 3)); // Expected: 3
        System.out.println("Test Case 2 (k = 2): " + counter.pathSum(root, 2)); // Expected: 3
        System.out.println("Test Case 3 (k = 4): " + counter.pathSum(root, 4)); // Expected: 2
        System.out.println("Test Case 4 (k = 100): " + counter.pathSum(root, 100)); // Expected: 0
    }
}
