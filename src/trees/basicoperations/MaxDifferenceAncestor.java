package trees.basicoperations;
/*Approach:
Recursive Traversal:

Traverse the tree recursively using a helper function.
Track Minimum Value:

Keep track of the minimum value along the path from the root to the current node.
Compute the Maximum Difference:

At each node, compute the difference between the current node value and the minimum value tracked so far.
Update the result if this difference is greater than the current maximum difference.
Combine Results:

Recursively calculate the result for both left and right subtrees and merge them with the current result.
*/


public class MaxDifferenceAncestor {
    private static int maxDifference;

    public static int maxDiff(TreeNode root) {
        maxDifference = Integer.MIN_VALUE; // Initialize result
        findMaxDiff(root, root.data);
        return maxDifference;
    }

    private static int findMaxDiff(TreeNode node, int minAncestor) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        // Update maxDifference for the current node
        int diff = minAncestor - node.data;
        maxDifference = Math.max(maxDifference, diff);

        // Update minAncestor for the next recursive calls
        int currentMin = Math.min(minAncestor, node.data);

        // Recurse for left and right subtrees
        int leftMin = findMaxDiff(node.left, currentMin);
        int rightMin = findMaxDiff(node.right, currentMin);

        // Return the minimum value in the subtree
        return Math.min(currentMin, Math.min(leftMin, rightMin));
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(1);
        System.out.println("Maximum Difference: " + maxDiff(root1)); // Output: 4

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(7);
        System.out.println("Maximum Difference: " + maxDiff(root2)); // Output: -1
    }
}
