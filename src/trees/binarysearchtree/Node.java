package trees.binarysearchtree;

class Node {
    int data;     // Value of the node
    Node left;    // Reference to the left child
    Node right;   // Reference to the right child

    // Constructor to initialize the node
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
