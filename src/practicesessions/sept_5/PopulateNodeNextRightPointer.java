package practicesessions.sept_5;


import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int data;
    TreeNode left, right, next;

    public TreeNode(int data) {
        this.data = data;
        this.left = this.right = this.next = null;

    }
}

public class PopulateNodeNextRightPointer {

    void populate(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode node = queue.poll();
            TreeNode prev = null;
            for (int i = 0; i <levelSize ; i++) {

                if(prev != null){
                    prev.next = node;
                }
                prev = node;
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
                prev.next = null;
        }
    }

}
