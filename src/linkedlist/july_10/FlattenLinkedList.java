package linkedlist.july_10;

public class FlattenLinkedList {

    // Definition for Node
    static class Node {
        int data;
        Node next;
        Node bottom;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    // Function to flatten the linked list
    public Node flatten(Node root) {
        if (root == null || root.next == null) return root;

        // Recur for list on right
        root.next = flatten(root.next);

        // Merge current list with right list
        root = mergeTwoLists(root, root.next);

        return root;
    }

    // Merge two bottom-sorted lists
    private Node mergeTwoLists(Node a, Node b) {
        Node dummy = new Node(-1);
        Node curr = dummy;

        while (a != null && b != null) {
            if (a.data < b.data) {
                curr.bottom = a;
                a = a.bottom;
            } else {
                curr.bottom = b;
                b = b.bottom;
            }
            curr = curr.bottom;
        }

        if (a != null) curr.bottom = a;
        else curr.bottom = b;

        return dummy.bottom;
    }

    // Utility to print list (using bottom pointer)
    public void printFlattened(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.bottom;
        }
        System.out.println();
    }

    // Sample test
    public static void main(String[] args) {
        FlattenLinkedList fl = new FlattenLinkedList();

        Node head = new Node(5);
        head.bottom = new Node(7);
        head.bottom.bottom = new Node(8);
        head.bottom.bottom.bottom = new Node(30);

        head.next = new Node(10);
        head.next.bottom = new Node(20);

        head.next.next = new Node(19);
        head.next.next.bottom = new Node(22);
        head.next.next.bottom.bottom = new Node(50);

        head.next.next.next = new Node(28);
        head.next.next.next.bottom = new Node(35);
        head.next.next.next.bottom.bottom = new Node(40);
        head.next.next.next.bottom.bottom.bottom = new Node(45);

        Node result = fl.flatten(head);
        System.out.print("Flattened List: ");
        fl.printFlattened(result);
    }
}
