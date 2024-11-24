package trees;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
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
        traversal.postOrder(root);
    }
}
