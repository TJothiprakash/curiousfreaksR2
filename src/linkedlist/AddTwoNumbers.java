package linkedlist;


import static linkedlist.CustomLinkedList.reverse;

public class AddTwoNumbers {

    // Example usage
    public static void main(String[] args) {
        AddTwoNumbers solution = new AddTwoNumbers();

        // Creating first linked list: 2 -> 4 -> 3 (represents number 342)
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // Creating second linked list: 5 -> 6 -> 4 (represents number 465)
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println("Linked List 1:");
        solution.printList(l1);

        System.out.println("Linked List 2:");
        solution.printList(l2);

        // Adding the two numbers
        ListNode result = solution.addTwoNumbers(l1, l2);
        String reversed=result.toString();
        System.out.println("Resultant Linked List (Sum):");
//        System.out.println(reversed.toString());

     solution.printList(result);
    }

    // Function to add two numbers represented by linked lists
    /*O(n)O(n)*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // Dummy node to start the result list
        ListNode current = dummy;  // Pointer to build the result list
        int carry = 0;  // Initial carry is 0

        // Traverse both lists
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;  // Start with the carry from the previous step

            // Add the value from l1 if it exists
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;  // Move to the next node in l1
            }

            // Add the value from l2 if it exists
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;  // Move to the next node in l2
            }

            // Update the carry and the current node's value
            carry = sum / 10;  // If sum >= 10, carry will be 1
            current.next = new ListNode(sum % 10);  // Create a new node for the current digit
            current = current.next;  // Move to the next node in the result list
        }


        // Return the next of dummy, as the dummy node was just a placeholder
        return dummy.next;
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
}

