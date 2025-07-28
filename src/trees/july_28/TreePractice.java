package trees.july_28;
import java.util.*;

public class TreePractice {
    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // 1. Insert node in a tree (level order)
    public static TreeNode insert(TreeNode root, int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) return newNode;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.left == null) {
                curr.left = newNode;
                break;
            } else q.offer(curr.left);

            if (curr.right == null) {
                curr.right = newNode;
                break;
            } else q.offer(curr.right);
        }
        return root;
    }

    // 2. Height of the tree
    public static int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // 3. Diameter of the tree
    static int maxDiameter = 0;
    public static int diameter(TreeNode root) {
        maxDiameter = 0;
        heightForDiameter(root);
        return maxDiameter;
    }

    private static int heightForDiameter(TreeNode node) {
        if (node == null) return 0;
        int lh = heightForDiameter(node.left);
        int rh = heightForDiameter(node.right);
        maxDiameter = Math.max(maxDiameter, lh + rh);
        return 1 + Math.max(lh, rh);
    }

    // 4. Check if 2 trees are identical
    public static boolean isIdentical(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.data == b.data) &&
                isIdentical(a.left, b.left) &&
                isIdentical(a.right, b.right);
    }

    // 5. Check if subtree
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isIdentical(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // 6. Check for balanced tree
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int lh = checkHeight(node.left);
        int rh = checkHeight(node.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    // 7. Lowest common ancestor in BT
    public static TreeNode lca(TreeNode root, int n1, int n2) {
        if (root == null || root.data == n1 || root.data == n2) return root;

        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    // 8. Sum tree check
    public static boolean isSumTree(TreeNode root) {
        return sumTreeHelper(root) != -1;
    }

    private static int sumTreeHelper(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return node.data;

        int leftSum = sumTreeHelper(node.left);
        int rightSum = sumTreeHelper(node.right);

        if (leftSum == -1 || rightSum == -1 || node.data != leftSum + rightSum) return -1;

        return node.data + leftSum + rightSum;
    }

    // 9. Symmetric tree
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.data == b.data) &&
                isMirror(a.left, b.right) &&
                isMirror(a.right, b.left);
    }

    // 10. Mirror of tree
    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 11. Check if isomorphic
    public static boolean isIsomorphic(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.data != b.data) return false;

        return (isIsomorphic(a.left, b.left) && isIsomorphic(a.right, b.right)) ||
                (isIsomorphic(a.left, b.right) && isIsomorphic(a.right, b.left));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        insert(root, 2);
        insert(root, 3);
        insert(root, 4);
        insert(root, 5);
        insert(root, 6);

        System.out.println("Height: " + height(root));
        System.out.println("Diameter: " + diameter(root));
        System.out.println("Is Balanced: " + isBalanced(root));
        System.out.println("Is Symmetric: " + isSymmetric(root));
    }
}
