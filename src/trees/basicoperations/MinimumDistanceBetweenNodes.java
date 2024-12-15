package trees.basicoperations;

/*pproach:
Find the Lowest Common Ancestor (LCA):

The LCA is the deepest node in the tree that has both
𝑎
a and
𝑏
b as descendants.
If
𝑎
a and
𝑏
b are in the left and right subtrees of a node, that node is their LCA.
If both
𝑎
a and
𝑏
b are in the same subtree, continue searching recursively.
Find Distance from LCA to
𝑎
a and
𝑏
b:

Use a recursive function to find the level (distance from the node to the target) of a node starting from the LCA.
Calculate Total Distance:

The minimum distance between
𝑎
a and
𝑏
b is the sum of the distances from the LCA to
𝑎
a and
𝑏
b.
*/
class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class MinimumDistanceBetweenNodes {
    public static int findMinDistance(TreeNode root, int a, int b) {
        TreeNode lca = findLCA(root, a, b);
        int distA = findLevel(lca, a, 0);
        int distB = findLevel(lca, b, 0);
        return distA + distB;
    }

    // Function to find the Lowest Common Ancestor (LCA)
    private static TreeNode findLCA(TreeNode root, int a, int b) {
        if (root == null || root.data == a || root.data == b) {
            return root;
        }

        TreeNode leftLCA = findLCA(root.left, a, b);
        TreeNode rightLCA = findLCA(root.right, a, b);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    // Function to find the level of a node from a given root
    private static int findLevel(TreeNode root, int target, int level) {
        if (root == null) {
            return -1;
        }
        if (root.data == target) {
            return level;
        }

        int left = findLevel(root.left, target, level + 1);
        if (left != -1) {
            return left;
        }
        return findLevel(root.right, target, level + 1);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        System.out.println("Distance between 2 and 3: " + findMinDistance(root1, 2, 3)); // Output: 2

        // Example 2
        TreeNode root2 = new TreeNode(11);
        root2.left = new TreeNode(22);
        root2.right = new TreeNode(33);
        root2.left.left = new TreeNode(44);
        root2.left.right = new TreeNode(55);
        root2.right.left = new TreeNode(66);
        root2.right.right = new TreeNode(77);

        System.out.println("Distance between 77 and 22: " + findMinDistance(root2, 77, 22)); // Output: 3

        // Example 3
        System.out.println("Distance between 1 and 3: " + findMinDistance(root1, 1, 3)); // Output: 1
    }
}
