package practicesessions.sept_8;

import trees.TreeNode;
import java.util.*;

public class DeleteNodesandReturnForest {

    public static void main(String[] args) {
        // Example test
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int[] to_delete = {3, 5};

        DeleteNodesandReturnForest obj = new DeleteNodesandReturnForest();
        List<TreeNode> forest = obj.deleteNodesandReturnForest(root, to_delete);

        System.out.println("Forest roots:");
        for (TreeNode r : forest) {
            System.out.println(r.data);
        }
    }

    // build parent map using BFS
    private Map<Integer, TreeNode> hashparent(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root.data, null); // root has no parent

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    parentMap.put(node.left.data, node);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    parentMap.put(node.right.data, node);
                    queue.add(node.right);
                }
            }
        }
        return parentMap;
    }

    public List<TreeNode> deleteNodesandReturnForest(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new ArrayList<>();
        if (root == null) return forest;

        Set<Integer> deleteSet = new HashSet<>();
        for (int val : to_delete) deleteSet.add(val);

        Map<Integer, TreeNode> parentMap = hashparent(root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (deleteSet.contains(node.data)) {
                // children become new roots
                if (node.left != null) forest.add(node.left);
                if (node.right != null) forest.add(node.right);

                // disconnect from parent
                TreeNode parent = parentMap.get(node.data);
                if (parent != null) {
                    if (parent.left == node) parent.left = null;
                    if (parent.right == node) parent.right = null;
                }
            }

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        // if root wasn't deleted, add it
        if (!deleteSet.contains(root.data)) {
            forest.add(root);
        }

        return forest;
    }
}
