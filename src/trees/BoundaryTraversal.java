package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BoundaryTraversal {

    // Time: O(n), Space: O(n)
    // Because we visit each node at most once, and store boundary nodes.
    // wrong implementations -- assumes the edge nodes are boundary not wokr for skewed trees
//
//    public static List<Integer> boundaryTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//
//        if (root == null) {
//            return result;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//            int levelSize = queue.size();
//
//            for (int i = 0; i < levelSize; i++) {
//                TreeNode current = queue.poll();
//
//                // Add left boundary, right boundary, or leaf nodes
//                if (i == 0 || i == levelSize - 1 || (current.left == null && current.right == null)) {
//                    result.add(current.data);
//                }
//
//                // Add children to the queue
//                if (current.left != null) {
//                    queue.offer(current.left);
//                }
//                if (current.right != null) {
//                    queue.offer(current.right);
//                }
//            }
//        }
//
//        return result;
//    }

    // Time: O(n), Space: O(n)
    // Each node is visited once; lists used to store boundary and leaves.
    public static List<Integer> boundaryTraversalUsingRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Add root to boundary (if not a leaf node)
        if (!isLeaf(root)) result.add(root.data);

        // Add left boundary
        addLeftBoundary(root.left, result);

        // Add leaf nodes
        addLeaves(root, result);
        System.out.println("root = " + root);

        // Add right boundary (in reverse order)
        List<Integer> rightBoundary = new ArrayList<>();
        addRightBoundary(root.right, rightBoundary);
        for (int i = rightBoundary.size() - 1; i >= 0; i--) {
            result.add(rightBoundary.get(i));
        }

        return result;
    }

    // Time: O(1), Space: O(1)
    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // Time: O(h), Space: O(1)
    // Only visits left boundary nodes, not leaf nodes.
    private static void addLeftBoundary(TreeNode node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) result.add(node.data);
            node = (node.left != null) ? node.left : node.right;
        }
        return;
    }

    // Time: O(h), Space: O(1)
    // Only visits right boundary nodes, not leaf nodes.
    private static void addRightBoundary(TreeNode node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) result.add(node.data);
            node = (node.right != null) ? node.right : node.left;
        }
    }

    // Time: O(n), Space: O(h)
    // Full DFS traversal to collect all leaf nodes
    private static void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (isLeaf(node)) {
            result.add(node.data);
        } else {
            addLeaves(node.left, result);
            addLeaves(node.right, result);
        }
    }

}
