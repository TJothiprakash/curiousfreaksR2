package linkedlist;


public class RemoveDuplicatesSortedList {

    // Function to remove duplicates from a sorted linked list
//    O(n)O(1)
    public ListNode removeDuplicates(ListNode head) {
        // Edge case: if the list is empty or has only one node
        if (head == null) {
            return head;
        }

        ListNode current = head;

        // Traverse the linked list
        while (current != null && current.next != null) {
            // If the current node's value is the same as the next node's value
            if (current.val == current.next.val) {
                // Skip the next node (duplicate node)
                current.next = current.next.next;
            } else {
                // Otherwise, just move to the next node
                current = current.next;
            }
        }

        return head;
    }

    // Utility function to print the linked list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        RemoveDuplicatesSortedList list = new RemoveDuplicatesSortedList();

        // Creating a sample linked list: 2 -> 2 -> 4 -> 5
        ListNode head = new ListNode(2);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);

        System.out.println("Original List:");
        list.printList(head);

        // Removing duplicates
        ListNode updatedHead = list.removeDuplicates(head);

        System.out.println("List after removing duplicates:");
        list.printList(updatedHead);
    }
}

