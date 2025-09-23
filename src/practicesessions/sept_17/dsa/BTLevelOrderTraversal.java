package practicesessions.sept_17.dsa;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = right = null;
    }
}

public class BTLevelOrderTraversal {
    public static void main(String[] args) {

    }

    private static void levelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> levelOrder = new ArrayList<>();
        if (root == null) return;
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int lvlSize = queue.size();
            for (int i = 0; i < lvlSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

            }
            levelOrder.add(new ArrayList<>(level));
        }
        System.out.println(levelOrder);
    }
}

