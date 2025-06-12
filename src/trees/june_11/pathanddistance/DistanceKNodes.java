package trees.june_11.pathanddistance;

import java.util.*;


public class DistanceKNodes {

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

        Collections.sort(result);
        return result;
    }

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

    private static List<TreeNode> getNeighbors(TreeNode node, Map<TreeNode, TreeNode> parentMap) {
        List<TreeNode> neighbors = new ArrayList<>();
        if (node.left != null) neighbors.add(node.left);
        if (node.right != null) neighbors.add(node.right);
        if (parentMap.containsKey(node)) neighbors.add(parentMap.get(node));
        return neighbors;
    }
}
