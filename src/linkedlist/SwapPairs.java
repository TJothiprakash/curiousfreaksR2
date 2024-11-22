package linkedlist;
public class SwapPairs {

    public static Node swapPairs(Node head) {
        // Base case: if the list is empty or contains only one node, no swap is needed
        if (head == null || head.next == null) {
            return head;
        }

        // Create a dummy node to simplify handling of the head node
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        // Iterate through the list in pairs
        while (prev.next != null && prev.next.next != null) {
            Node first = prev.next;
            Node second = prev.next.next;

            // Swap the pair
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move the prev pointer two steps ahead for the next pair
            prev = first;
        }

        return dummy.next; // Return the new head of the list
    }

    // Utility function to print the linked list
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
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        printList(head);

        Node result = swapPairs(head);

        System.out.println("List After Pairwise Swap:");
        printList(result);
    }
}
