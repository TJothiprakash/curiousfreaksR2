package linkedlist;

public class SegregateLinkedList {

    public static Node segregate(Node head) {
        if (head == null) return null;

        Node zeroHead = new Node(0);  // Dummy node for 0's list
        Node oneHead = new Node(0);   // Dummy node for 1's list
        Node twoHead = new Node(0);   // Dummy node for 2's list

        Node zero = zeroHead, one = oneHead, two = twoHead;
        Node current = head;

        // Traverse the original list and separate the nodes based on their values
        while (current != null) {
            if (current.data == 0) {
                zero.next = current;
                zero = zero.next;
            } else if (current.data == 1) {
                one.next = current;
                one = one.next;
            } else {
                two.next = current;
                two = two.next;
            }
            current = current.next;
        }

        // Connect the three lists: 0s → 1s → 2s
        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next; // Connect 0's list to 1's or 2's
        one.next = twoHead.next;  // Connect 1's list to 2's list
        two.next = null;  // End the 2's list

        // The head of the new linked list will be the next of zeroHead
        return zeroHead.next;
    }

    // Utility method to print the linked list
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test the solution
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(0);
        head.next.next.next.next.next.next = new Node(2);

        System.out.println("Original List:");
        printList(head);

        Node result = segregate(head);

        System.out.println("Segregated List:");
        printList(result);
    }
}
