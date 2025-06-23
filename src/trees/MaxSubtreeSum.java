package trees;

/**
 * 📌 Problem:
 * Find the sum of the subtree with the maximum sum in a binary tree.
 * <p>
 * 🧠 Approach:
 * - Use post-order traversal to compute the sum at each node.
 * - Keep track of the maximum subtree sum using a global variable.
 * <p>
 * ✅ Time: O(n)
 * ✅ Space: O(h)
 */
public class MaxSubtreeSum {

    private static int maxSum;

    public static int findLargestSubtreeSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        postOrderSum(root);
        return maxSum;
    }

    // Post-order traversal to calculate subtree sum
    private static int postOrderSum(TreeNode node) {
        if (node == null) return 0;

        int leftSum = postOrderSum(node.left);
        int rightSum = postOrderSum(node.right);

        int totalSum = node.data + leftSum + rightSum;

        maxSum = Math.max(maxSum, totalSum);

        return totalSum;
    }

    // 🧪 Test Cases
    public static void main(String[] args) {
        /*
             Tree 1:
                  1
                /   \
               2     3
              / \   / \
             4  5  6   7
            Expected output: 28

             Tree 2:
                  1
                /   \
              -2     3
              / \   / \
             4   5 -6  2
            Expected output: 7
         */

        // Test case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        System.out.println("Max subtree sum (Tree 1): " + findLargestSubtreeSum(root1)); // 28

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(-2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(-6);
        root2.right.right = new TreeNode(2);
        System.out.println("Max subtree sum (Tree 2): " + findLargestSubtreeSum(root2)); // 7
    }
}
