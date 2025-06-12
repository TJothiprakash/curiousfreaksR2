package trees.june_11.pathanddistance;


public class MaxPathSumAnyNode {

    static class Result {
        int maxSum = Integer.MIN_VALUE;
    }

    public static int findMaxPathSum(TreeNode root) {
        Result res = new Result();
        maxPathDown(root, res);
        return res.maxSum;
    }

    private static int maxPathDown(TreeNode node, Result res) {
        if (node == null) return 0;

        // Recur left and right — discard negative paths
        int left = Math.max(0, maxPathDown(node.left, res));
        int right = Math.max(0, maxPathDown(node.right, res));

        // Case when path passes through this node (not going up)
        int currentMaxPath = left + node.data + right;
        res.maxSum = Math.max(res.maxSum, currentMaxPath);

        // Return path sum up (only one child can be included)
        return node.data + Math.max(left, right);
    }

    // Test
    public static void main(String[] args) {
        // Tree 1: [10, 2, 10, 20, 1, N, -25, N, N, N, N, 3, 4]
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(10);
        root1.left.left = new TreeNode(20);
        root1.left.right = new TreeNode(1);
        root1.right.right = new TreeNode(-25);
        root1.right.right.left = new TreeNode(3);
        root1.right.right.right = new TreeNode(4);
        System.out.println("Max Path Sum = " + findMaxPathSum(root1)); // 42

        // Tree 2: [-17, 11, 4, 20, -2, 10]
        TreeNode root2 = new TreeNode(-17);
        root2.left = new TreeNode(11);
        root2.right = new TreeNode(4);
        root2.left.left = new TreeNode(20);
        root2.left.right = new TreeNode(-2);
        root2.right.left = new TreeNode(10);
        System.out.println("Max Path Sum = " + findMaxPathSum(root2)); // 31
    }
}
