package trees.june_11.pathanddistance;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int val) {
        this.data = val;
    }
}

public class RootToLeafPaths {

    public static OptionalInt roottoLeafMaxPathSum(TreeNode root) {
        List<List<Integer>> result = allRootToLeafPaths(root);
        return result.stream()
                .mapToInt(path -> path.stream().mapToInt(Integer::intValue).sum())
                .max(); // This gives the maximum sum among all root-to-leaf paths
    }

    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, result);
        return result;
    }

    private static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.data); // Add current node to path

        // If it's a leaf node, save the path
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            // Traverse left and right children
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }

        path.remove(path.size() - 1); // Backtrack
    }

    public static void main(String[] args) {
        /*
              1
             / \
            2   3
           / \   \
          4   5   6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        List<List<Integer>> allPaths = allRootToLeafPaths(root);

        System.out.println("Root to Leaf Paths:");
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }
        System.out.println("Max Path Sum: " + roottoLeafMaxPathSum(root));
    }
}
