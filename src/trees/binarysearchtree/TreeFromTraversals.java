package trees.binarysearchtree;

import java.util.*;

public class TreeFromTraversals {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private static int preIndex = 0;

    /**
     * Constructs a binary tree from the given preorder and inorder traversal arrays.
     *
     * 🔍 Intuition:
     * - Preorder traversal gives you the root node first, followed by left and right subtrees.
     * - Inorder traversal gives the left subtree, then the root, then the right subtree.
     * - By taking the current root from preorder and locating it in inorder,
     *   you can recursively construct the left and right subtrees.
     *
     * @param preorder The preorder traversal array.
     * @param inorder The inorder traversal array.
     * @return The root node of the reconstructed binary tree.
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();

        // Preprocess inorder array for O(1) lookups of indices
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1, inorderIndexMap);
    }

    /**
     * Helper function to recursively build the tree using preorder and inorder traversal.
     *
     * @param preorder Preorder array.
     * @param inStart Start index of inorder segment.
     * @param inEnd End index of inorder segment.
     * @param inorderMap Map from node value to its index in inorder array.
     * @return Subtree root.
     */
    private static TreeNode build(int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd) return null;

        // The next element in preorder is always the root
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Get index of the root in inorder array
        int inIndex = inorderMap.get(rootVal);

        // Recursively build left and right subtrees
        root.left = build(preorder, inStart, inIndex - 1, inorderMap);
        root.right = build(preorder, inIndex + 1, inEnd, inorderMap);

        return root;
    }

    /**
     * Generates the postorder traversal from a binary tree.
     *
     * 🔍 Intuition:
     * - Postorder traversal visits: Left → Right → Root
     * - We recursively visit left and right subtrees, then add the root to result.
     *
     * @param root The root of the binary tree.
     * @return A list of node values in postorder traversal.
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    /**
     * Helper method for postorder traversal.
     *
     * @param node Current node.
     * @param result List accumulating the postorder result.
     */
    private static void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }

    // Sample Test
    public static void main(String[] args) {
        int[] preorder = {1, 4, 5, 2, 3};
        int[] inorder = {2, 5, 4, 1, 3};

        TreeNode root = buildTree(preorder, inorder);
        List<Integer> postorder = postorderTraversal(root);

        // Output should be: [2, 5, 4, 3, 1]
        System.out.println(postorder);
    }
}
