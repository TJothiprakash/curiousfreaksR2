package linkedlist;

public class RemoveLoop {
    public static void removeLoop(Node head) {
        if (head == null || head.next == null) {
            return; // No loop possible
        }

        Node slow = head;
        Node fast = head;

        // Step 1: Detect the loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) { // Loop detected
                break;
            }
        }

        // If no loop exists, return
        if (fast == null || fast.next == null) {
            return;
        }

        // Step 2: Find the start of the loop
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 3: Find the last node of the loop
        Node loopStart = slow;
        Node current = loopStart;

        while (current.next != loopStart) {
            current = current.next;
        }

        // Step 4: Break the loop
        current.next = null;
    }

    public static void main(String[] args) {
        // Example
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next; // Create a loop at node 3

        removeLoop(head);

        // Print the list to verify the loop is removed
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

