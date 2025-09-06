package linkedlist.aug_30.flatten;

class Node {
    int data;
    Node next;
    Node bottom;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.bottom = null;
    }
}

public class FlattenLinkedList {

    // Function to merge two bottom linked lists
    private Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node result;
        if (a.data < b.data) {
            result = a;
            result.bottom = merge(a.bottom, b);
        } else {
            result = b;
            result.bottom = merge(a, b.bottom);
        }

        return result;
    }

    // Function to flatten the list
    public Node flatten(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        // Recursively flatten the rest
        root.next = flatten(root.next);
        // Merge current column with the flattened right side
        root = merge(root, root.next);
        return root;
    }
}
