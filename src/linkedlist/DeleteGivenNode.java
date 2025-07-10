package linkedlist;

public class DeleteGivenNode {
    //    O(n)O(1)
    public static void deleteNode(Node del_node) {
        if (del_node == null || del_node.next == null) {
            throw new IllegalArgumentException("The node to delete cannot be null or the last node.");
        }

        // Copy the data from the next node to the current node
        del_node.data = del_node.next.data;

        // Remove the next node by bypassing it
        del_node.next = del_node.next.next;
    }

    //    //    O(n)O(1)
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
//        new Thread(() -> {
//
//        }).start();

    }

    public static void main(String[] args) {
        // Create the linked list: 10 -> 20 -> 4 -> 30
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(4);
        head.next.next.next = new Node(30);

        System.out.println("Original List:");
        printList(head);

        // Delete node 20
        deleteNode(head.next);

        System.out.println("After deleting node with value 20:");
        printList(head); // Output: 10 -> 4 -> 30 -> null
    }
}

