package trees;

import org.jetbrains.annotations.NotNull;

import java.util.*;

class VerticalTraversal {

    public static @NotNull List<Integer> verticalTraversal(TreeNode root) {
        // Map to store nodes grouped by their HD
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        // Queue to perform level-order traversal
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0)); // Pair(node, horizontal distance)

        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            TreeNode currentNode = currentPair.node;
            int hd = currentPair.hd;

            // Add the current node to the map
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(currentNode.data);

            // Add left and right children to the queue with updated HDs
            if (currentNode.left != null) {
                queue.offer(new Pair(currentNode.left, hd - 1));
            }
            if (currentNode.right != null) {
                queue.offer(new Pair(currentNode.right, hd + 1));
            }
        }

        // Combine all values from the TreeMap into the final result list
        List<Integer> result = new ArrayList<>();
        for (List<Integer> group : map.values()) {
            result.addAll(group);
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.right.left.right = new TreeNode(8);
        root1.right.right.right = new TreeNode(9);

        System.out.println(verticalTraversal(root1)); // Output: [4, 2, 1, 5, 6, 3, 8, 7, 9]

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(6);

       // System.out.println(verticalTraversal(root2)); // Output: [4, 2, 1, 5, 3, 6]

        // Example 3
        TreeNode root3 = new TreeNode(7);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.left.right = new TreeNode(5);
        root3.right.right = new TreeNode(6);

       // System.out.println(verticalTraversal(root3)); // Output: [4, 2, 7, 5, 3, 6]
    }

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // Helper class to store a node and its horizontal distance
    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}

