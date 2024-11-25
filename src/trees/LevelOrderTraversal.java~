package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class LevelOrderTraversal {
    // private
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).offer(root);


        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = ((LinkedList<TreeNode>) queue).poll();
                if (current.left != null) {
                    ((LinkedList<TreeNode>) queue).offer(current.left);
                }
                if (current.right != null) {
                    ((LinkedList<TreeNode>) queue).offer(current.right);
                }

                currentLevel.add(current.data);


            }
            result.add(currentLevel);
        }


        return result;
    }
}
