package linkedlist;

import java.util.HashSet;


public class RemoveDuplicatesUnsortedList {

    // Example usage
    public static void main(String[] args) {
        RemoveDuplicatesUnsortedList list = new RemoveDuplicatesUnsortedList();

        // Creating a sample unsorted linked list: 3 -> 5 -> 2 -> 3 -> 5 -> 4
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(4);

        System.out.println("Original List:");
        list.printList(head);

        // Removing duplicates
        ListNode updatedHead = list.removeDuplicates(head);

        System.out.println("List after removing duplicates:");
        list.printList(updatedHead);
    }

    // Function to remove duplicates from an unsorted linked list
//    O(n)O(n)
    public ListNode removeDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        // HashSet to store the values of the nodes
        HashSet<Integer> seen = new HashSet<>();
        ListNode current = head;
        ListNode prev = null;

        // Traverse the linked list
        while (current != null) {
            if (seen.contains(current.val)) {
                // If the value is already in the set, remove the node
                prev.next = current.next;
            } else {
                // Otherwise, add the value to the set
                seen.add(current.val);
                prev = current;
            }
            current = current.next;
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
}

