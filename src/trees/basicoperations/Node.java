package trees.basicoperations;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }

    public static class BasicOperationsinBinarySearchTree {
        public TreeOperations.TreeNode insertIntoBST(TreeOperations.TreeNode root, int val) {
            if (root == null)
                return new TreeOperations.TreeNode(val);
            TreeOperations.TreeNode curr = root;
            while (true) {
                if (curr.data <= val) {
                    if (curr.right != null) {
                        curr = curr.right;
                    } else {
                        curr.right = new TreeOperations.TreeNode(val);
                        break;
                    }
                } else {
                    if (curr.left != null) {
                        curr = curr.left;
                    } else {
                        curr.left = new TreeOperations.TreeNode(val);
                        break;
                    }

                }
            }
            return root;
        }
    }
}
