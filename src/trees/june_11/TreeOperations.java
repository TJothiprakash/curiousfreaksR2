package trees.june_11;


//insert, height, diameter, check identical,check subtree, check balanced tree, Lowest common ancestor BT,
//sum tree, symmetric tree , mirror tree, chekc isomorphic

import java.util.LinkedList;
import java.util.Queue;

public class TreeOperations {
    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int val) {
            this.data = val;
            left = right = null;
        }
    }
    public static void main(String[] args) {
        // Build tree:
        TreeNode root = new TreeNode(10);
        insert(root, 8);
        insert(root, 2);
        insert(root, 3);
        insert(root, 5);
        insert(root, 1);
        insert(root, 4);

        System.out.println("Height: " + height(root));
        System.out.println("Diameter: " + diameter(root));
        System.out.println("Is Balanced: " + isBalanced(root));

        TreeNode identicalRoot = new TreeNode(10);
        insert(identicalRoot, 8);
        insert(identicalRoot, 2);
        insert(identicalRoot, 3);
        insert(identicalRoot, 5);
        insert(identicalRoot, 1);
        insert(identicalRoot, 4);
        System.out.println("Is Identical: " + isIdentical(root, identicalRoot));

        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(3);
        subTree.right = new TreeNode(5);
        System.out.println("Is Subtree: " + isSubtree(root, subTree));

        System.out.println("Lowest Common Ancestor (3, 5): " + lca(root, 3, 5).data);
        System.out.println("Is Symmetric: " + isSymmetric(root));
        System.out.println("Is Sum Tree: " + isSumTree(new TreeNode(26)));  // only valid if manually constructed

        TreeNode mirror1 = new TreeNode(1);
        mirror1.left = new TreeNode(2);
        mirror1.right = new TreeNode(3);

        TreeNode mirror2 = new TreeNode(1);
        mirror2.left = new TreeNode(3);
        mirror2.right = new TreeNode(2);

        System.out.println("Is Isomorphic: " + isIsomorphic(mirror1, mirror2));

        mirror(root);
        System.out.println("After Mirror, Is Symmetric: " + isSymmetric(root));
    }


    static void insert(TreeNode root, int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left == null) {
                node.left = new TreeNode(key);
                break;
            } else queue.offer(node.left);

            if (node.right == null) {
                node.right = new TreeNode(key);
                break;
            } else queue.offer(node.right);
        }
    }

    static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    static int diameter(TreeNode root) {
        int[] max = new int[1];

        heightForDiameter(root, max);
        return max[0];
    }

    static int heightForDiameter(TreeNode node, int[] max) {
        if (node == null) return 0;

        int lh = heightForDiameter(node.left, max);
        int rh = heightForDiameter(node.right, max);

        max[0] = Math.max(max[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    static boolean isIdentical(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        return a.data == b.data &&
                isIdentical(a.left, b.left) &&
                isIdentical(a.right, b.right);
    }

    static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;

        if (isIdentical(root, subRoot)) return true;

        return isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }

    static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    static int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int lh = checkHeight(node.left);
        if (lh == -1) return -1;

        int rh = checkHeight(node.right);
        if (rh == -1) return -1;

        if (Math.abs(lh - rh) > 1) return -1;

        return 1 + Math.max(lh, rh);
    }

    static TreeNode lca(TreeNode root, int n1, int n2) {
        if (root == null) return null;

        if (root.data == n1 || root.data == n2) return root;

        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    static boolean isSumTree(TreeNode root) {
        return checkSumTree(root) != -1;
    }

    static int checkSumTree(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return node.data;

        int leftSum = checkSumTree(node.left);
        int rightSum = checkSumTree(node.right);

        if (leftSum == -1 || rightSum == -1 || node.data != leftSum + rightSum)
            return -1;

        return node.data + leftSum + rightSum;
    }

    static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        return a.data == b.data &&
                isMirror(a.left, b.right) &&
                isMirror(a.right, b.left);
    }

    static void mirror(TreeNode node) {
        if (node == null) return;

        mirror(node.left);
        mirror(node.right);

        // Swap children
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    static boolean isIsomorphic(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.data != b.data) return false;

        return (isIsomorphic(a.left, b.left) && isIsomorphic(a.right, b.right)) ||
                (isIsomorphic(a.left, b.right) && isIsomorphic(a.right, b.left));
    }

}
