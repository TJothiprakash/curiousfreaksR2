package trees.june_11.pathanddistance;

public class MaxDiff {
}


class Solution {
    int maxDiff = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        helper(root);
        return maxDiff;
    }

    private int helper(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;

        // Leaf node returns its own value
        if (node.left == null && node.right == null)
            return node.data;

        int leftMin = helper(node.left);
        int rightMin = helper(node.right);

        int minDescendant = Math.min(leftMin, rightMin);

        // Update global maxDiff with current node minus min of subtree
        maxDiff = Math.max(maxDiff, node.data - minDescendant);

        // Return the minimum value in the current subtree
        return Math.min(node.data, minDescendant);
    }
}
