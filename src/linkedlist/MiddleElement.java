package linkedlist;

public class MiddleElement {
//    O(n)O(1)
    public  static Node middleElement(Node head) {

        Node slow = head;
        Node fast=head;
        int count=0;
        // Traverse the list with slow and fast pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;       // slow moves 1 step
            fast = fast.next.next;  // fast moves 2 steps
            count++;
        }

        // Print the correct length of the list
        int length = (fast == null) ? 2 * count : 2 * count + 1;
        System.out.println("Length of the list is " + length);

        return slow;  // slow will be at the middle of the list

    }
}
