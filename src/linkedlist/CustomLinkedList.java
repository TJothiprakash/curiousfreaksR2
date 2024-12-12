package linkedlist;

import java.util.Stack;

import static linkedlist.Loops.detectLoop;

public class CustomLinkedList {
    public static void main(String[] args) {
      /*  Node second = new Node(20);// mam 10 20 10
        Node third = new Node(10);
        Node head = new Node(10);
        Node fourth = new Node(40);
        Node fifth = new Node(50);

        head.next = second;
        head.next.next = third;
        head.next.next.next = fourth;
        head.next.next.next.next = fifth;
        // System.out.println(fourth.data);
        System.out.println("before reverse");
        printList(head);
        // reverseUsingStack(head);
        // Node ans=  recursiveApproach(head);
        System.out.println("\nAfter reverse");
        // printList(ans);

        //    System.out.println(isPalindrome(head));
        Node res = middleElement(head);
        System.out.println("middle element is "+res.data);
   */

        // Create first linked list: 1 → 2 → 3 → 4 → 6
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(6);
        head1.next.next.next.next=head1.next;
        System.out.println("first list");
        //printList(head1);
        System.out.println(detectLoop(head1));
        // Create second linked list: 2 → 4 → 5 → 7
        Node head2 = new Node(2);
        head2.next = new Node(4);
        head2.next.next = new Node(5);
        head2.next.next.next = new Node(7);
        /*System.out.println("second list");
        printList(head2);
        System.out.println();*/
        // Get the union of the two linked lists
        //Node result = unionofList(head1, head2);
        //printList(result);

    }


    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next; // Store the next node
            current.next = prev;      // Reverse the current node's pointer
            prev = current;           // Move prev to the current node
            current = next;           // Move to the next node
        }
// At this point, 'prev' is the new head of the reversed list
        head = prev; // Update head to point to the new head of the reversed list

// Print the reversed list
        Node temp = head; // Use a temporary pointer to traverse the list
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        //return prev; // New head of the reversed list
    }

    public static void reverseUsingStack(Node head) {

        Stack<Integer> stack = new Stack<>();
      /*  Node temp = head;
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }
        temp = head;
        while (!stack.isEmpty()) {
            temp.data = stack.pop();
            // System.out.print(stack.pop() + " ");
            temp = temp.next;
        }*/

        Node temp = head;
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }

// Reset the temp pointer to head to traverse the list again
        temp = head;

// Pop from the stack and update the linked list in reversed order
        while (!stack.isEmpty()) {
            temp.data = stack.pop();
            temp = temp.next;
        }

    }
}
