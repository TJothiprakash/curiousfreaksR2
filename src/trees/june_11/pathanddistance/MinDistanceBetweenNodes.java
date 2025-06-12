package trees.june_11.pathanddistance;


public class MinDistanceBetweenNodes {

    public static int findMinDistance(TreeNode root, int a, int b) {
        TreeNode lca = findLCA(root, a, b);
        int d1 = findLevel(lca, a, 0);
        int d2 = findLevel(lca, b, 0);
        return d1 + d2;
    }

    private static TreeNode findLCA(TreeNode root, int a, int b) {
        if (root == null) return null;
        if (root.data == a || root.data == b) return root;

        TreeNode left = findLCA(root.left, a, b);
        TreeNode right = findLCA(root.right, a, b);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    private static int findLevel(TreeNode root, int val, int level) {
        if (root == null) return -1;
        if (root.data == val) return level;

        int left = findLevel(root.left, val, level + 1);
        if (left != -1) return left;

        return findLevel(root.right, val, level + 1);
    }
}
