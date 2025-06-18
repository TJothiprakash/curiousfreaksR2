package linkedlist;
/*class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}*/

public class ReverseKNodes {

    // Function to find the length of the linked list
    public static int getLength(Node head) {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    // Function to reverse a group of k nodes
    public static Node reverseGroup(Node head, int k) {
        Node prev = null;
        Node current = head;
        Node next = null;
        int count = 0;

        // Reverse the first k nodes
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        // Return the new head of the reversed group
        return prev;
    }

    // Function to reverse every k nodes in the linked list
    public static Node reverseKGroup(Node head, int k) {
        int length = getLength(head);

        // Fake node handles edge cases better.
        Node dummy = new Node(0);
        dummy.next = head;
        Node prevGroupEnd = dummy;
        Node current = head;

        while (length >= k) {
            // Reverse the next k nodes
            Node groupStart = current;
            Node groupEnd = current;
            for (int i = 1; i < k; i++) {
                groupEnd = groupEnd.next;
            }
            Node nextGroupStart = groupEnd.next;

            // Reverse the k nodes between groupStart and groupEnd
            groupEnd.next = null;

            // Connect the reversed group to the previous part of the list
            prevGroupEnd.next = reverseGroup(groupStart, k);
            groupStart.next = nextGroupStart;

            // Move prevGroupEnd to the end of the reversed group
            prevGroupEnd = groupStart;
            current = nextGroupStart;

            // Update the length
            length -= k;
        }

        return dummy.next;
    }

    // Helper function to print the linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " → ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Create a linked list: 1 → 2 → 2 → 4 → 5 → 6 → 7 → 8
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        System.out.print("Original Linked List: ");
        printList(head);

        // Call reverseKGroup with k = 4
        Node result = reverseKGroup(head, 4);

        System.out.print("Linked List after reversing every 4 nodes: ");
        printList(result);
    }
}

