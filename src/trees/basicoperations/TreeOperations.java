package trees.basicoperations;


public class TreeOperations {

    int diameter = 0;

    // A utility function to insert a new node
    // with the given key
    static Node insert(Node root, int key) {

        // If the tree is empty, return a new node
        if (root == null)
            return new Node(key);

        // If the key is already present in the tree,
        // return the node
        if (root.data == key)
            return root;

        // Otherwise, recur down the tree
        if (key < root.data)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);

        // Return the (unchanged) node pointer
        return root;
    }

    // A utility function to do inorder tree traversal
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    static int height(Node root) {
        // Base case: If the tree is empty
        if (root == null) {
            return -1;
        }

        // Recursively find the height of the left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // The height of the tree is the maximum height of the subtrees + 1 for the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Driver method
    public static void main(String[] args) {
        Node root = null;

        // Creating the following BST
        //      50
        //     /  \
        //    30   70
        //   / \   / \
        //  20 40 60 80

        root = insert(root, 50);
        root = insert(root, 30);
        root = insert(root, 20);
        root = insert(root, 40);
        root = insert(root, 70);
        root = insert(root, 60);
        root = insert(root, 80);
        root = insert(root, 100);

        // Print inorder traversal of the BST
        inorder(root);
        System.out.println("\nHeight of the tree is: " + height(root));

    }

    int diameterOfBinaryTree(Node root) {
        // Call the helper function to calculate height and diameter
        heightDia(root);
        return diameter;
    }

    // Helper function to calculate the height of the tree
    // and update the diameter
    int heightDia(Node node) {
        // Base case: if the node is null, return 0 (height of an empty tree)
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of the left and right subtrees
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Calculate the diameter at this node (left height + right height + 1)
        // Update the maximum diameter if the current diameter is larger
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isIdentical(Node r1, Node r2) {
        // Base cases
        if (r1 == null && r2 == null) {
            return true;  // Both trees are empty, so they are identical
        }
        if (r1 == null || r2 == null) {
            return false;  // One tree is empty, and the other is not, so they are not identical
        }

        // Check if the current nodes have the same value and recursively check left and right subtrees
        return (r1.data == r2.data) &&
                isIdentical(r1.left, r2.left) &&
                isIdentical(r1.right, r2.right);
    }

    // Function to check if S is a subtree of T
    public int isSubtree(Node T, Node S) {
        if (T == null) {
            return 0;  // If T is empty, S can't be a subtree of T
        }

        // If current node of T matches the root of S, check if they are identical
        if (isIdentical(T, S)) {
            return 1;  // S is a subtree of T
        }

        // Recursively check the left and right subtrees of T
        return (isSubtree(T.left, S) == 1 || isSubtree(T.right, S) == 1) ? 1 : 0;
    }


    //
    // Function to check if two trees are isomorphic
    public boolean areIsomorphic(Node root1, Node root2) {
        // If both trees are empty, they are isomorphic
        if (root1 == null && root2 == null) {
            return true;
        }

        // If one tree is empty and the other is not, they are not isomorphic
        if (root1 == null || root2 == null) {
            return false;
        }

        // Check if data at the roots are the same
        if (root1.data != root2.data) {
            return false;
        }

        // Check if the subtrees are isomorphic in either of two possible ways:
        // 1. Without swapping the subtrees
        // 2. With swapping the left and right subtrees
        return (areIsomorphic(root1.left, root2.left) && areIsomorphic(root1.right, root2.right)) ||
                (areIsomorphic(root1.left, root2.right) && areIsomorphic(root1.right, root2.left));
    }


    // Function to check if a binary tree is height balanced
    public boolean isBalanced(Node root) {
        // If the tree is balanced, return true, otherwise false
        return checkHeight(root) != -1;
    }

    // Helper function to check the height of the tree and determine if it is balanced
    private int checkHeight(Node node) {
        // Base case: If the node is null, it is balanced with height -1
        if (node == null) {
            return -1;
        }

        // Recursively get the height of the left and right subtrees
        int leftHeight = checkHeight(node.left);
        int rightHeight = checkHeight(node.right);

        // If left or right subtree is unbalanced, return -1
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // If the current node is unbalanced (height difference > 1), return -1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }


    // Function to find the lowest common ancestor in a binary tree
    public Node findLCA(Node root, int n1, int n2) {
        // Base case: If root is null, return null
        if (root == null) {
            return null;
        }

        // If the current node matches n1 or n2, it could be an LCA
        if (root.data == n1 || root.data == n2) {
            return root;
        }

        // Recursively search for n1 and n2 in the left and right subtrees
        Node leftLCA = findLCA(root.left, n1, n2);
        Node rightLCA = findLCA(root.right, n1, n2);

        // If both left and right subtrees return non-null, this is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // Otherwise, return the non-null value from the left or right subtree
        return (leftLCA != null) ? leftLCA : rightLCA;
    }


    // Function to check if the tree is a Sum Tree
    public boolean isSumTree(Node root) {
        // Helper function to validate Sum Tree and calculate sums
        return isSumTreeHelper(root) != -1;
    }

    private int isSumTreeHelper(Node node) {
        // Base case: Empty tree is a Sum Tree with sum 0
        if (node == null) {
            return 0;
        }

        // Base case: Leaf node is a Sum Tree with its own value as sum
        if (node.left == null && node.right == null) {
            return node.data;
        }

        // Recursively calculate the sums of left and right subtrees
        int leftSum = isSumTreeHelper(node.left);
        int rightSum = isSumTreeHelper(node.right);

        // If any subtree is not a Sum Tree, propagate failure
        if (leftSum == -1 || rightSum == -1) {
            return -1;
        }

        // Check the Sum Tree condition for the current node
        if (node.data != leftSum + rightSum) {
            return -1;
        }

        // Return the total sum of the subtree rooted at this node
        return node.data + leftSum + rightSum;
    }


    /*A binary tree is symmetric if:

The left and right subtrees of the root are mirrors of each other.
For any two corresponding nodes:
Their values are equal.
The left subtree of one matches the right subtree of the other, and vice versa.
Algorithm:
Base Case:
An empty tree is symmetric.
Recursive Check:
Compare the left and right subtrees using a helper function.
Ensure the left and right nodes have equal values.
Ensure the left child of one matches the right child of the other, recursively.
Iterative Approach (Optional):
Use a queue to perform level-order traversal while checking for symmetry.
*/
    // Main function to check symmetry
    public boolean isSymmetric(Node root) {
        // An empty tree is symmetric
        if (root == null) return true;

        // Check if the left and right subtrees are mirrors
        return isMirror(root.left, root.right);
    }

    // Helper function to check if two subtrees are mirrors of each other
    private boolean isMirror(Node t1, Node t2) {
        // Base cases: both null or one null
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        // Check if current nodes match and subtrees are mirrors
        return (t1.data == t2.data)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }


    // iteerative approach od isSymmetric
//    /* public boolean isSymmetric(Node root) {
//        if (root == null) return true;
//
//        // Queue for level-order traversal
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root.left);
//        queue.add(root.right);
//
//        while (!queue.isEmpty()) {
//            Node t1 = queue.poll();
//            Node t2 = queue.poll();
//
//            // Both nodes are null: continue
//            if (t1 == null && t2 == null) continue;
//
//            // One of the nodes is null or values mismatch
//            if (t1 == null || t2 == null || t1.data != t2.data) return false;
//
//            // Enqueue children in opposite order to check symmetry
//            queue.add(t1.left);
//            queue.add(t2.right);
//            queue.add(t1.right);
//            queue.add(t2.left);
//        }
//
//        return true;
//    }*/

    // Function to convert the tree into its mirror
    public void mirror1(Node root) {
        if (root == null) return;

        // Recursively mirror the left and right subtrees
        mirror1(root.left);
        mirror1(root.right);

        // Swap the left and right children
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
/*

    // Function for inorder traversal
    public void inorder(Node root) {
        if (root == null) return;

        // Visit left subtree, current node, and right subtree
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
*/

}
