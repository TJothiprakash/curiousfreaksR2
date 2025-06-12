package trees.binarysearchtree;


class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        if (root.val < low)
            return rangeSumBST(root.right, low, high); // skip left

        if (root.val > high)
            return rangeSumBST(root.left, low, high); // skip right

        // root.val is within range
        return root.val +
                rangeSumBST(root.left, low, high) +
                rangeSumBST(root.right, low, high);
    }
}
