package practicesessions.sept_18.dsa;

import com.sun.source.tree.Tree;
import org.w3c.dom.Node;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class BinaryTreeRightSideView {


static class TreeNode{
    int data ;
    TreeNode left;
    TreeNode right;
    public TreeNode(int data){
        this.left = this.right = null;
        this.data = data;
    }
}
    public static void main(String[] args) {

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();

        if (root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++){
            TreeNode node = queue.poll();
                if(i == levelSize-1){
                    rightSideView.add(node.data);
                }
                if(node.right!= null){
                    queue.add(node.right);
                }
                if(node.left!= null){
                    queue.add(node.left);
                }

            }
        }
            return rightSideView;

    }

}
