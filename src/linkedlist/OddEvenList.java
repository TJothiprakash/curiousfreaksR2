package linkedlist;


public class OddEvenList {

    // Example usage
    public static void main(String[] args) {
        OddEvenList list = new OddEvenList();

        // Creating a sample linked list: 1->2->3->4->5->6
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println("Original List:");
        list.printList(head);

        // Segregating odd and even nodes
        ListNode updatedHead = list.oddEvenList(head);

        System.out.println("List after segregating odd and even nodes:");
        list.printList(updatedHead);
    }

    // Function to segregate odd and even nodes
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // If list is empty or has only one element, return as is
        }

        // Initialize pointers for odd and even lists
        ListNode oddHead = new ListNode(0); // Dummy head for odd list
        ListNode evenHead = new ListNode(0); // Dummy head for even list
        ListNode odd = oddHead; // Pointer for odd list
        ListNode even = evenHead; // Pointer for even list
        ListNode current = head; // Pointer to traverse the original list

        // Traverse the original list
        while (current != null) {
            if (current.val % 2 != 0) { // Odd node
                odd.next = current;
                odd = odd.next;
            } else { // Even node
                even.next = current;
                even = even.next;
            }
            current = current.next;
        }

        // End the even list
        even.next = null;

        // Connect the odd list with the even list
        odd.next = evenHead.next;

        return oddHead.next; // Return the head of the modified list
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
