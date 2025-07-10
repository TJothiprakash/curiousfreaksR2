package linkedlist.july_10;


public class DeleteNafterMSkip {

    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Function to perform skip m, delete n operation
    public static Node skipMdeleteN(Node head, int m, int n) {
        if (head == null || m == 0) return null;

        Node current = head;

        while (current != null) {

            // Skip m nodes
            for (int i = 1; i < m && current != null; i++) {
                current = current.next;
            }

            // If we've reached the end, break
            if (current == null) break;

            // Start deleting next n nodes
            Node temp = current.next;
            for (int i = 1; i <= n && temp != null; i++) {
                temp = temp.next;
            }

            // Connect current node to the (n+1)th node after current
            current.next = temp;

            // Move to next segment
            current = temp;
        }

        return head;
    }

    // Utility function to print the linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) System.out.print(" -> ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Main function to test
    public static void main(String[] args) {
        // Create sample list: 9 -> 1 -> 3 -> 5 -> 9 -> 4 -> 10 -> 1
        Node head = new Node(9);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(9);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(10);
        head.next.next.next.next.next.next.next = new Node(1);

        System.out.println("Original list:");
        printList(head);

        int m = 2, n = 1;
        head = skipMdeleteN(head, m, n);

        System.out.println("Modified list after skipping " + m + " and deleting " + n + " nodes:");
        printList(head);
    }
}
