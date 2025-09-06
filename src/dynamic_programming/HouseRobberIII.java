package dynamic_programming;


/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.



Example 1:


Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:


Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.


Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 104
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:*/
public class HouseRobberIII {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    static class Solution {

        public int rob(TreeNode root) {
            int[] res = dfs(root);
            return Math.max(res[0], res[1]);
        }

        // returns [robThisNode, notRobThisNode]
        private int[] dfs(TreeNode node) {
            if (node == null) return new int[]{0, 0};

            int[] left = dfs(node.left);
            int[] right = dfs(node.right);

            int robThis = node.val + left[1] + right[1];      // rob this node → cannot rob children
            int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // skip this node → can choose max of children

            return new int[]{robThis, notRob};
        }

        public static void main(String[] args) {
            Solution sol = new Solution();

            TreeNode root = new TreeNode(3);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.right = new TreeNode(3);
            root.right.right = new TreeNode(1);

            System.out.println(sol.rob(root)); // Output: 7
        }
    }

}
