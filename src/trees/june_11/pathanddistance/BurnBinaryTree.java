package trees.june_11.pathanddistance;

import java.util.*;

/**
 * 📌 Problem:
 * Find the minimum time to burn the entire binary tree starting from a target node.
 * <p>
 * 🔥 Fire spreads to parent, left, and right nodes every second.
 * <p>
 * 🧠 Approach:
 * - Map each node to its parent using BFS.
 * - Locate the target node.
 * - From target, run BFS across the graph (left, right, parent edges).
 * - Count the time (level of BFS) to reach all nodes.
 * <p>
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(n)
 */

public class BurnBinaryTree {

    public static int minTimeToBurn(TreeNode root, int targetVal) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode target = mapParentsAndFindTarget(root, parentMap, targetVal);

        if (target == null) return 0;

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);

        int time = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            time++; // Each level = 1 second

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                for (TreeNode neighbor : getNeighbors(curr, parentMap)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return time;
    }

    private static TreeNode mapParentsAndFindTarget(TreeNode root, Map<TreeNode, TreeNode> parentMap, int targetVal) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode target = null;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.data == targetVal) target = curr;

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }

        return target;
    }

    private static List<TreeNode> getNeighbors(TreeNode node, Map<TreeNode, TreeNode> parentMap) {
        List<TreeNode> neighbors = new ArrayList<>();
        if (node.left != null) neighbors.add(node.left);
        if (node.right != null) neighbors.add(node.right);
        if (parentMap.containsKey(node)) neighbors.add(parentMap.get(node));
        return neighbors;
    }

    // 🧪 Test Cases
    public static void main(String[] args) {
        /*
              1
             / \
            2   3
           / \   \
          4   5   7
         /
        8
         \
         10

         🔥 Target = 10
         Time = 5
         */

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(7);
        root1.left.left.left = new TreeNode(8);
        root1.left.left.left.right = new TreeNode(10);

        System.out.println("🔥 Time to burn tree from node 10: " + minTimeToBurn(root1, 10)); // Output: 5

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);

        System.out.println("🔥 Time to burn tree from node 2: " + minTimeToBurn(root2, 2)); // Output: 3
    }
}
