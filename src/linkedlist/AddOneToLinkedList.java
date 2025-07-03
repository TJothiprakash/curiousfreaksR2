package linkedlist;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class AddOneToLinkedList {

    // Function to add 1 to the number formed by the linked list
//    O(n)O(1)
    public ListNode addOne(ListNode head) {
        // Step 1: Reverse the linked list
        head = reverse(head);

        // Step 2: Add 1 to the reversed list
        ListNode current = head;
        int carry = 1;  // We start with adding 1

        while (current != null && carry > 0) {
            int sum = current.val + carry;
            current.val = sum % 10;  // Update the node's value
            carry = sum / 10;  // Calculate carry for the next node

            if (current.next == null && carry > 0) {
                // If we're at the last node and there's still a carry
                current.next = new ListNode(carry);
                carry = 0;
            }
            current = current.next;  // Move to the next node
        }

        // Step 3: Reverse the list back to restore the original order
        return reverse(head);
    }

    // Function to reverse the linked list
//    O(n)O(1)
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;  // Store the next node
            current.next = prev;  // Reverse the link
            prev = current;  // Move prev to the current node
            current = nextNode;  // Move to the next node
        }

        return prev;  // Return the new head of the reversed list
    }

    // Utility function to print the linked list
//    O(n)O(1)
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        AddOneToLinkedList list = new AddOneToLinkedList();

        // Creating a sample linked list: 9->9->9
        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
        head.next.next = new ListNode(9);

        System.out.println("Original List:");
        list.printList(head);

        // Adding 1 to the list
        ListNode updatedHead = list.addOne(head);

        System.out.println("List after adding 1:");
        list.printList(updatedHead);
    }
}
