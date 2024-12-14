package trees;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       /* TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        TraversalsinTree traversal = new TraversalsinTree();
        System.out.println("Preorder traversal: ");
        traversal.preOrder(root);
        System.out.println();
        System.out.println("Inorder traversal: ");
        traversal.inOrder(root);
        System.out.println();
        System.out.println("Postorder traversal: ");
        traversal.postOrder(root);*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        List<List<Integer>> answer = new LevelOrderTraversal().levelOrder(root);
        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < answer.get(i).size(); j++) {
                System.out.print(answer.get(i).get(j) + " ");
            }

        }
        // System.out.println(boundaryTraversalUsingRecursion(root));
        // System.out.println(boundaryTraversal(root));
    }
}
