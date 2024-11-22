package linkedlist;

import static linkedlist.ReverseLinkedList.recursiveApproach;

public class CheckPalindrome {
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true; // A single-node or empty list is a palindrome
        }

        // Step 1: Create a copy of the original list
        Node original = copyList(head);

        // Step 2: Reverse the copied list
        Node reversed = recursiveApproach(original);

        // Step 3: Compare the original and reversed lists
        while (reversed != null && head != null) {
            if (reversed.data != head.data) {
                return false; // If any mismatch is found, it's not a palindrome
            }
            reversed = reversed.next;
            head = head.next; // Traverse the original list
        }
        return true; // All nodes match, so it's a palindrome
    }

    // Helper method to copy a linked list
    private static Node copyList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(0); // Dummy node to simplify copying
        Node temp = dummy;
        while (head != null) {
            temp.next = new Node(head.data); // Copy the data
            temp = temp.next;
            head = head.next;
        }
        return dummy.next;
    }

}
