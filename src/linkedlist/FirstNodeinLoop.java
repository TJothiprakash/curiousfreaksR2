package linkedlist;

public class FirstNodeinLoop {
//    //    O(2n~ n)O(1)
    public static int findFirstNodeOfLoop(Node head) {
        if (head == null || head.next == null) {
            return -1; // No loop possible in these cases
        }

        Node slow = head;
        Node fast = head;

        // Step 1: Detect if a loop exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Loop detected
                break;
            }
        }

        // If no loop exists, return -1
        if (fast == null || fast.next == null) {
            return -1;
        }

        // Step 2: Find the first node of the loop
        slow = head; // Reset slow to head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow.data; // The data of the first node of the loop
    }

    public static void main(String[] args) {
        // Example 1
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next; // Create a loop at node 3

        System.out.println("First node of loop: " + findFirstNodeOfLoop(head)); // Output: 3

        // Example 2
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);

        System.out.println("First node of loop: " + findFirstNodeOfLoop(head2)); // Output: -1
    }
}
