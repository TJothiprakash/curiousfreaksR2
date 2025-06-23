package trees;

import java.util.*;

class DiagnalTraversal {
    public List<Integer> diagonalTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int d = current.hd; // here hd = diagonal level

            if (!map.containsKey(d)) {
                map.put(d, new ArrayList<>());
            }

            map.get(d).add(node.data);
            // map.computeIfAbsent(d, k -> new ArrayList<>()).add(node.data);

            if (node.left != null) {
                queue.offer(new Pair(node.left, d + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, d)); // same diagonal
            }
        }

        for (List<Integer> diagNodes : map.values()) {
            result.addAll(diagNodes);
        }

        return result;
    }

    // Helper class for queue
    static class Pair {
        TreeNode node;
        int hd; // here hd = diagonal level

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}
