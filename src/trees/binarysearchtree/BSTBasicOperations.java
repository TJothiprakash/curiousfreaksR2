package trees.binarysearchtree;

//search in BST,insert a node in BST,
/*find min and max ,find kth largest & Kth smallest element , check for BST?,
 * find closeset element in BST,
 * count BST nodes in given range, Largest BST in BT,
 * Lowest common ancestor in BST, Merge two BST, Inorder Successor & Predesscor
 * Populate inorder successor for all nodes,
 * BST to greater sum tree
 * Delete given node from BST*/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Class containing all basic and advanced BST operations
 */
public class BSTBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Search in BST.
     * Intuition: Use BST property: go left if value < node, else go right.
     */
    public TreeNode search(TreeNode root, int key) {
        while (root != null && root.val != key) {
            root = key < root.val ? root.left : root.right;
        }
        return root;
    }

    /**
     * Insert a node in BST.
     * Intuition: Traverse recursively and place node where null is found.
     */
    public TreeNode insert(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);
        if (key < root.val)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);
        return root;
    }

    /**
     * Find minimum in BST.
     * Intuition: Go as left as possible.
     */
    public int findMin(TreeNode root) {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        while (root.left != null) root = root.left;
        return root.val;
    }

    /**
     * Find maximum in BST.
     * Intuition: Go as right as possible.
     */
    public int findMax(TreeNode root) {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        while (root.right != null) root = root.right;
        return root.val;
    }

    /**
     * Find kth smallest in BST.
     * Intuition: Inorder traversal gives sorted list.
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }

    /**
     * Find kth largest in BST.
     * Intuition: Reverse inorder traversal.
     */
    public int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.left;
        }
    }

    /**
     * Check if a binary tree is a valid BST.
     * Intuition: Node values must follow min-max constraints.
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    /**
     * Find closest element to a target in BST.
     * Intuition: Keep track of closest while traversing.
     */
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }

    /**
     * Count BST nodes within given range [low, high].
     * Intuition: Use BST property to skip subtrees.
     */
    public int countInRange(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val < low)
            return countInRange(root.right, low, high);
        else if (root.val > high)
            return countInRange(root.left, low, high);
        else
            return 1 + countInRange(root.left, low, high) + countInRange(root.right, low, high);
    }

    /**
     * Find size of largest BST in binary tree.
     * Intuition: Postorder traversal with validation at each node.
     */
    class BSTInfo {
        boolean isBST;
        int size, min, max;

        BSTInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public int largestBST(TreeNode root) {
        return largestBSTHelper(root).size;
    }

    private BSTInfo largestBSTHelper(TreeNode root) {
        if (root == null) return new BSTInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        BSTInfo left = largestBSTHelper(root.left);
        BSTInfo right = largestBSTHelper(root.right);
        if (left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            return new BSTInfo(true, left.size + right.size + 1,
                    Math.min(root.val, left.min), Math.max(root.val, right.max));
        }
        return new BSTInfo(false, Math.max(left.size, right.size), 0, 0);
    }

    /**
     * Lowest Common Ancestor in BST.
     * Intuition: Navigate down based on value comparisons.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * Merge two BSTs into sorted list.
     * Intuition: Do inorder traversal and merge two sorted arrays.
     */
    public List<Integer> mergeBSTs(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);
        return merge(list1, list2);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) < l2.get(j)) merged.add(l1.get(i++));
            else merged.add(l2.get(j++));
        }
        while (i < l1.size()) merged.add(l1.get(i++));
        while (j < l2.size()) merged.add(l2.get(j++));
        return merged;
    }

    /**
     * Inorder Successor in BST.
     * Intuition: If right exists, go to leftmost in right; else track ancestor.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val < root.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }

    /**
     * Inorder Predecessor in BST.
     * Intuition: If left exists, go to rightmost in left; else track ancestor.
     */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        TreeNode predecessor = null;
        while (root != null) {
            if (p.val > root.val) {
                predecessor = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return predecessor;
    }

    /**
     * Populate Inorder Successor for all nodes.
     * Intuition: Reverse inorder traversal keeping track of next node.
     */
    public void populateInorderSuccessors(TreeNode root, Map<TreeNode, TreeNode> map) {
        populateInorderSuccessorsHelper(root, map, new TreeNode[]{null});
    }

    private void populateInorderSuccessorsHelper(TreeNode root, Map<TreeNode, TreeNode> map, TreeNode[] next) {
        if (root == null) return;
        populateInorderSuccessorsHelper(root.right, map, next);
        map.put(root, next[0]);
        next[0] = root;
        populateInorderSuccessorsHelper(root.left, map, next);
    }

    /**
     * Convert BST to Greater Sum Tree.
     * Intuition: Reverse inorder and keep running sum.
     */
    public void bstToGreaterSum(TreeNode root) {
        convertToGreater(root, new int[]{0});
    }

    private void convertToGreater(TreeNode node, int[] sum) {
        if (node == null) return;
        convertToGreater(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        convertToGreater(node.left, sum);
    }

    /**
     * Delete a node from BST.
     * Intuition: Three cases - no child, one child, two children (replace with inorder successor).
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            TreeNode successor = findMinNode(root.right);
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
