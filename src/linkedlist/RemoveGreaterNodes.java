package linkedlist;


public class RemoveGreaterNodes {

    // Example usage
    public static void main(String[] args) {
        RemoveGreaterNodes solution = new RemoveGreaterNodes();

        // Creating the linked list: 12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3
        ListNode head = new ListNode(12);
        head.next = new ListNode(15);
        head.next.next = new ListNode(10);
        head.next.next.next = new ListNode(11);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next = new ListNode(3);

        // Print the original list
        System.out.println("Original List:");
        solution.printList(head);

        // Remove greater nodes
        ListNode modifiedHead = solution.removeGreaterNodes(head);

        // Print the modified list
        System.out.println("Modified List:");
        solution.printList(modifiedHead);
    }

    // Helper function to reverse the linked list
    public ListNode reverse(ListNode head) {
        ListNode prev = null, current = head, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // Function to remove nodes with greater nodes on their right
//    O(n)O(1)
    public ListNode removeGreaterNodes(ListNode head) {
        // Step 1: Reverse the list
        head = reverse(head);

        // Step 2: Traverse the reversed list and remove nodes
        ListNode current = head;
        ListNode prev = null;
        int maxVal = Integer.MIN_VALUE;

        while (current != null) {
            // If the current node's value is greater than or equal to maxVal, keep it
            if (current.val >= maxVal) {
                maxVal = current.val;
                prev = current;
            } else {
                // Remove the node by skipping it
                if (prev != null) {
                    prev.next = current.next;
                }
            }
            current = current.next;
        }

        // Step 3: Reverse the list again to restore original order
        return reverse(head);
    }

    // Helper function to print the list
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
