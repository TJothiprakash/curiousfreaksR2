package trees.basicoperations;
/*This problem involves finding the minimum time required to burn a binary tree starting from a given target node, with the fire spreading to parent, left, and right children in 1 second. The solution requires two key steps:

Tree traversal to establish parent relationships:

Use a level-order traversal to build a mapping from each node to its parent for easy access during the burning process.
Burn process simulation using BFS:

Use a queue to simulate the fire spreading, starting from the target node. Keep track of visited nodes to prevent re-burning.
Approach:
Establish Parent Mapping:

Perform a traversal to map every node to its parent.
Breadth-First Search (BFS):

Use a queue to propagate the fire starting from the target node.
Track the visited nodes to avoid revisiting them.
Count the number of seconds until all nodes are burned.
Output the Total Time:

The number of levels processed during the BFS gives the time required to burn the tree.
*/

import java.util.*;


public class BurnBinaryTree {
    public static int minTime(TreeNode root, int target) {
        // Step 1: Map each node to its parent
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode targetNode = mapParents(root, parentMap, target);

        // Step 2: Use BFS to burn the tree
        return burnTree(targetNode, parentMap);
    }

    private static TreeNode mapParents(TreeNode root, Map<TreeNode, TreeNode> parentMap, int target) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode targetNode = null;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.data == target) {
                targetNode = node;
            }

            if (node.left != null) {
                parentMap.put(node.left, node);
                queue.add(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                queue.add(node.right);
            }
        }

        return targetNode;
    }

    private static int burnTree(TreeNode targetNode, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(targetNode);
        visited.add(targetNode);

        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // Burn the left child
                if (current.left != null && !visited.contains(current.left)) {
                    queue.add(current.left);
                    visited.add(current.left);
                    burned = true;
                }

                // Burn the right child
                if (current.right != null && !visited.contains(current.right)) {
                    queue.add(current.right);
                    visited.add(current.right);
                    burned = true;
                }

                // Burn the parent
                if (parentMap.containsKey(current) && !visited.contains(parentMap.get(current))) {
                    queue.add(parentMap.get(current));
                    visited.add(parentMap.get(current));
                    burned = true;
                }
            }

            if (burned) {
                time++;
            }
        }

        return time;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);

        System.out.println(minTime(root, 8)); // Output: 7
    }
}
