package trees;

import java.util.*;

public class BTZigZaglevelorderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();
         queue.offer(root);


        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current =  queue.poll();
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
