package trees.july_17;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;

    }

}

public class TreeOperations {
    public static void main(String[] args) {

    }

    public void insert(Node root, int data) {
        if (root == null) {
            Node node = new Node(data);
            return;
        }
        Node newNode = new Node(data);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if (temp.left == null) {
                temp.left = newNode;
                return;
            } else {
                queue.offer(temp.left);
            }

            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else {
                queue.offer(temp.right);
            }

        }


    }

    public int height(Node root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        return 1 + Math.max(left, right);
    }

    private int diameter = 0;

    public int diameter(Node root) {
        findDia(root);
        return diameter;
    }

    private int findDia(Node root) {
        if (root == null) return 0;

        int left = findDia(root.left);
        int right = findDia(root.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right); // height of subtree
    }

    public boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.data != root2.data) return false;

        return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }

    public boolean checkSubtree(Node root, Node sub) {
        if (root == null) return false;

        if (isIdentical(root, sub)) return true;

        return (checkSubtree(root.left, sub) || checkSubtree(root.right, sub));

    }

    public boolean isBalanced(Node root) {
        return check(root) != -1;
    }

    private int check(Node root) {
        if (root == null) return 0;

        int left = check(root.left);
        if (left == -1) return -1;
        int right = check(root.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return 1 + Math.max(left, right);

    }


    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        // Base case: if root is null or either p or q is found, return root
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recursively search left and right subtrees
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are not null, root is the LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, return the non-null node (if found)
        return left != null ? left : right;
    }



}
