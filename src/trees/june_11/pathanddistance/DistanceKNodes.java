package trees.june_11.pathanddistance;

import java.util.*;

/**
 * 📌 Question:
 * Given a binary tree, a target node value, and an integer `k`, return all nodes that are at
 * distance `k` from the target node. The nodes can be upward (toward parent) or downward.
 *
 * 💡 Intuition:
 * We need to simulate the tree as an undirected graph. This means each node has connections to:
 * - Its left child
 * - Its right child
 * - Its parent
 * Once we do this, we can run a BFS starting from the target node and stop at level `k`.
 *
 * 🧠 Approach:
 * 1. Do a BFS traversal to map all nodes to their parents.
 * 2. Find the target node while mapping.
 * 3. Start BFS from the target, traverse left, right, and parent at each level.
 * 4. Stop once we reach level `k`. Collect all nodes in the queue.
 *
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(n)
 */
public class DistanceKNodes {

    // Main function: returns all nodes at distance K from target value
    public static List<Integer> nodesAtDistanceK(TreeNode root, int targetVal, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode target = findAndMapParents(root, targetVal, parentMap);

        if (target == null) return new ArrayList<>();

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);

        int level = 0;

        while (!queue.isEmpty()) {
            if (level == k) break;

            int size = queue.size();
            while (size-- > 0) {
                TreeNode current = queue.poll();

                for (TreeNode neighbor : getNeighbors(current, parentMap)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            level++;
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().data);
        }

        Collections.sort(result); // optional, for consistent order
        return result;
    }

    // Maps child to parent for all nodes, and finds the target node
    private static TreeNode findAndMapParents(TreeNode root, int targetVal, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode targetNode = null;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.data == targetVal) {
                targetNode = node;
            }
            if (node.left != null) {
                parentMap.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                queue.offer(node.right);
            }
        }

        return targetNode;
    }

    // Returns all neighbors (left, right, parent) of the current node
    private static List<TreeNode> getNeighbors(TreeNode node, Map<TreeNode, TreeNode> parentMap) {
        List<TreeNode> neighbors = new ArrayList<>();
        if (node.left != null) neighbors.add(node.left);
        if (node.right != null) neighbors.add(node.right);
        if (parentMap.containsKey(node)) neighbors.add(parentMap.get(node));
        return neighbors;
    }

    // 🧪 Test cases
    public static void main(String[] args) {
        /*
              1
             / \
            2   3
           / \
          4   5

          Target = 2, K = 1
          Expected Output: [1, 4, 5]

          Target = 3, K = 0
          Expected Output: [3]

          Target = 1, K = 2
          Expected Output: [4, 5]
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Nodes at distance 1 from 2: " + nodesAtDistanceK(root, 2, 1)); // [1, 4, 5]
        System.out.println("Nodes at distance 0 from 3: " + nodesAtDistanceK(root, 3, 0)); // [3]
        System.out.println("Nodes at distance 2 from 1: " + nodesAtDistanceK(root, 1, 2)); // [4, 5]
    }
}
