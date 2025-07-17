package trees.july_17;

public class BSTOperations {
    public static void main(String[] args) {

    }

    public Node insert(Node root, int value) {
        if (root == null) return new Node(value);

        if (root.data < value) {
            root.left = insert(root.left, value);
        } else if (root.data > value) {
            root.right = insert(root.right, value);

        }
        return root;
    }

    public Node search(Node root, int value) {

        if (root == null) return null;

        if (root.data < value) {
            return search(root.left, value);
        } else if (root.data > value) {
            return search(root.right, value);
        } else {
            return root;

        }
    }

    public void findMinandMax(Node root) {
        Node min = findMin(root);

        Node max = findMax(root);
    }

    private Node findMin(Node root) {

        if (root.left != null) {
            return findMin(root.left);
        }
        return root;
    }

    private Node findMax(Node root) {
        if (root.right != null) {
            return findMax(root.right);
        }
        return root;

    }

    Node kthLargest(Node root, int k) {
        return kthLargestHelper(root, new int[]{0}, k);
    }

    private Node kthLargestHelper(Node node, int[] count, int k) {
        if (node == null) return null;

        Node right = kthLargestHelper(node.right, count, k);
        if (right != null) return right;

        count[0]++;
        if (count[0] == k) return node;

        return kthLargestHelper(node.left, count, k);
    }

    Node kthSmallest(Node root, int k) {
        return kthSmallestHelper(root, new int[]{0}, k);
    }

    private Node kthSmallestHelper(Node node, int[] count, int k) {
        if (node == null) return null;

        Node left = kthSmallestHelper(node.left, count, k);
        if (left != null) return left;

        count[0]++;
        if (count[0] == k) return node;

        return kthSmallestHelper(node.right, count, k);
    }

    public boolean isValidBST(Node root, int min, int max) {
        // going left decrease in the value
//            going right increase in teh value
        if (root == null) return true;
        if (root.data <= min || root.data >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.data) && isValidBST(root.right, root.data, max);

    }


    public int findClosest(Node root, int target) {
        return helper(root, target, root.data);
    }

    private int helper(Node node, int target, int closest) {
        if (node == null) return closest;

        if (Math.abs(target - node.data) < Math.abs(target - closest)) {
            closest = node.data;
        }

        if (target < node.data) {
            return helper(node.left, target, closest);
        } else if (target > node.data) {
            return helper(node.right, target, closest);
        } else {
            return node.data; // exact match
        }
    }


}

