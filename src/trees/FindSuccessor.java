package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindSuccessor {
    public TreeNode findSuccessor(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        List<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).offer(root);


        while (!queue.isEmpty()) {
           // int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            TreeNode current = ((LinkedList<TreeNode>) queue).poll();
            if (current.left != null) {
                ((LinkedList<TreeNode>) queue).offer(current.left);
            }
            if (current.right != null) {
                ((LinkedList<TreeNode>) queue).offer(current.right);
            }

            if (current.data == key) {
                break;
            }


        }


        return ((LinkedList<TreeNode>) queue).peek();
    }
}
