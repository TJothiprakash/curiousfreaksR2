package trees.binarysearchtree;


/*constructing a BST (Binary Search Tree) from preorder traversal only. Since a BST has a specific structure (left < root < right), this makes it possible to reconstruct it from just preorder.

Here are 3 methods to construct a BST from preorder traversal:

✅ 1. Recursive with Boundaries (Efficient O(n))
🧠 Intuition:
Traverse preorder and build BST by enforcing valid value range (min, max).

Each node must lie within bounds.

Start with bounds (-∞, +∞) and move through preorder.

🔧 Example:
java
Copy
Edit
TreeNode buildBST(int[] preorder, int[] index, int lower, int upper)
✅ Time: O(n)
✅ Space: O(h) recursion stack

✅ 2. Recursive using Index + Partition (Less Optimal)
🧠 Intuition:
For each root in preorder, find the first greater element → this is the split between left and right subtrees.

Recurse on both parts.

🔧 Steps:
Pick root from preorder.

Partition remaining values into left < root and right > root.

Recurse.

⚠️ Time: O(n²) in worst case (e.g., sorted input)
✅ Space: O(h)

✅ 3. Stack-based Iterative (O(n), Optimal)
🧠 Intuition:
Use a stack to simulate the path.

For each node:

If it's smaller than top, it's the left child.

If it's bigger, pop until you find parent and insert as right child.

🔧 Steps:
Start with root = first element, push to stack.

Traverse rest of preorder:

If current < stack.peek(): it’s the left child.

Else: pop until you find where it becomes the right child.

✅ Time: O(n)
✅ Space: O(n)

🔄 Summary Table
Method	Time	Space	Notes
Recursive with Bounds	O(n)	O(h)	Best choice — clean and fast
Recursive with Partition	O(n²)	O(h)	Simple but less efficient
Stack-based Iterative	O(n)	O(n)	Iterative alternative to recursion

Would you like Java code for all three methods?






You said:
give code as well


ChatGPT said:
Su*/

import java.util.Stack;

class BSTFromPreorder {
    public static void main(String[] args) {
        int[] preorder = {10, 5, 1, 7, 40, 50};

        TreeNode root1 = buildUsingBounds(preorder);
        printPostorder(root1);  // Expected postorder

        TreeNode root2 = buildUsingPartition(preorder);
        printPostorder(root2);

        TreeNode root3 = buildUsingStack(preorder);
        printPostorder(root3);
    }

    public static void printPostorder(TreeNode root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * Constructs BST using stack. For each node:
     * - If it's smaller than stack top → left child
     * - Else → find parent from stack and insert as right child
     * Time: O(n), Space: O(n)
     */
    public static TreeNode buildUsingStack(int[] preorder) {
        if (preorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            TreeNode parent = stack.peek();

            // Find the right parent to attach this node
            while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                parent = stack.pop();
            }

            if (preorder[i] < parent.val) {
                parent.left = node;
            } else {
                parent.right = node;
            }

            stack.push(node);
        }

        return root;
    }


    /**
     * Constructs BST by finding the partition point where right subtree starts.
     * Time: O(n^2), Space: O(h)
     */
    public static TreeNode bstFromPreorderPartition(int[] preorder, int start, int end) {
        if (start > end) return null;

        TreeNode root = new TreeNode(preorder[start]);

        int i;
        for (i = start + 1; i <= end; i++) {
            if (preorder[i] > root.val) break;
        }

        root.left = bstFromPreorderPartition(preorder, start + 1, i - 1);
        root.right = bstFromPreorderPartition(preorder, i, end);
        return root;
    }

    public static TreeNode buildUsingPartition(int[] preorder) {
        return bstFromPreorderPartition(preorder, 0, preorder.length - 1);
    }


    // Index tracker wrapped in array to allow mutation
    static int idx = 0;

    /**
     * Constructs BST using preorder traversal and valid range boundaries.
     * Time: O(n), Space: O(h)
     */
    public static TreeNode bstFromPreorderBounded(int[] preorder, int lower, int upper) {
        if (idx == preorder.length) return null;

        int val = preorder[idx];
        if (val < lower || val > upper) return null;

        TreeNode root = new TreeNode(val);
        idx++;
        root.left = bstFromPreorderBounded(preorder, lower, val);
        root.right = bstFromPreorderBounded(preorder, val, upper);
        return root;
    }

    public static TreeNode buildUsingBounds(int[] preorder) {
        idx = 0;
        return bstFromPreorderBounded(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
