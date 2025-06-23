package trees.june_11.pathanddistance;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/**
 * 📌 Question:
 * Given a binary tree, find all **root-to-leaf paths**, and return the path
 * that has the **maximum sum** of node values.
 *
 * 💡 Intuition:
 * A root-to-leaf path must start at the root and end at a leaf node.
 * We can use DFS to explore all such paths, track their sums, and return the maximum.
 *
 * 🧠 Approach:
 * - Use DFS to build all root-to-leaf paths.
 * - At each leaf node, save the current path.
 * - After collecting all paths, calculate the sum of each and return the maximum.
 *
 * ✅ Time Complexity: O(n) for DFS traversal (where n is number of nodes)
 * ✅ Space Complexity: O(h) for recursion + O(n) for storing paths
 */
public class RootToLeafPaths {

    // Returns the maximum sum among all root-to-leaf paths
    public static OptionalInt rootToLeafMaxPathSum(TreeNode root) {
        List<List<Integer>> result = allRootToLeafPaths(root);
        return result.stream()
                .mapToInt(path -> path.stream().mapToInt(Integer::intValue).sum())
                .max(); // Get maximum sum path
    }

    // Collects all root-to-leaf paths as a list of lists
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, result);
        return result;
    }

    // DFS to collect paths
    private static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.data);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path)); // Save complete path
        } else {
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }

        path.remove(path.size() - 1); // Backtrack
    }

    // 🧪 Test cases
    public static void main(String[] args) {
        /*
            Sample Tree:
                  1
                 / \
                2   3
               / \   \
              4   5   6

            Expected Paths:
            - 1 → 2 → 4 = 7
            - 1 → 2 → 5 = 8
            - 1 → 3 → 6 = 10

            Expected Output: Max Path Sum = 10
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        List<List<Integer>> allPaths = allRootToLeafPaths(root);

        System.out.println("🛤️ Root to Leaf Paths:");
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }

        System.out.println("🔝 Max Root to Leaf Path Sum: " + rootToLeafMaxPathSum(root).orElse(-1));
    }
}

// TreeNode class (you can keep it in a separate file if preferred)
class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int val) {
        this.data = val;
    }
}
