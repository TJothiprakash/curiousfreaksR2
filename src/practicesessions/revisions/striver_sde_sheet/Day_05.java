package practicesessions.revisions.striver_sde_sheet;

import java.util.Stack;

class Node {
    int data;
    DoublyNode next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

}

class  ListNode{
    int data;
    ListNode next;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}
public class Day_05 {
    // linked list problems
    // reverse a linked list
    //bruteforce solutions
    //TODO
    // use stack
    public static DoublyNode reverseLLBruteforce(DoublyNode head) {
        if (head == null) {
            return null; // Handle empty list
        }

        Stack<Integer> stack = new Stack<>();
        DoublyNode current = head;

        // Push all node data into the stack
        while (current != null) {
            stack.push(current.data);
            current = current.next;
        }

        // Reset current to head
        current = head;

        // Pop from stack and update the linked list
        while (!stack.isEmpty()) {
            current.data = stack.pop();
            current = current.next;
        }

        return head; // Return the head of the reversed list
    }

    // reverse linkedlist optimal solution
    public static DoublyNode reverseLLOPtimalSolution(DoublyNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoublyNode temp = head;
        DoublyNode prev = null;

        while (temp != null) {
            DoublyNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;

        }
        return prev;
    }

    //reverse ll using recursion
    public static DoublyNode reversellusingRecursion(DoublyNode head) {
        if (head == null || head.next == null) return head;
        DoublyNode newHead = reversellusingRecursion(head.next);
        DoublyNode front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }

    // middle element pf a linkedlist
    public static DoublyNode middleElement(DoublyNode head) {
        if (head == null) return null; // Handle empty list case

        DoublyNode slow = head, fast = head;

        // Move fast pointer two steps and slow pointer one step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // Slow will point to the middle element
    }

    // merge two sorted linkedlist using merge sort
    public static DoublyNode mergeTwoLL(DoublyNode head1, DoublyNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        DoublyNode head = new DoublyNode(-1);
        DoublyNode temp = head;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        if (head1 != null) temp.next = head1;
        if (head2 != null) temp.next = head2;
        return head.next;
    }

    //Problem Statement: Given a linked list and an integer N, the task is to delete the
// Nth node from the end of the linked list and print the updated linked list.
    public static DoublyNode deleteNthNodeFromEnd(DoublyNode head, int n) {
        if (head == null) return null; // Edge case: empty list

        // Create a dummy node to simplify edge cases, such as when the head needs to be deleted.
        DoublyNode dummy = new DoublyNode(-1);
        dummy.next = head;
        DoublyNode slow = dummy, fast = dummy;

        // Move the fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                return head; // If n is greater than the length of the list, return the original list.
            }
        }

        // Move both pointers until the fast pointer reaches the end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Skip the Nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }

    //Problem Statement: Write a function to delete a node in a singly-linked list.
    // You will not be given access to the head of the list instead, you will be given access
    // to the node to be deleted directly.
    // It is guaranteed that the node to be deleted is not a tail node in the list.
    public static void deleteNode(DoublyNode doublyNode) {
        if (doublyNode == null) return; // Handle edge case

        // Copy the data of the next node to the current node
        doublyNode.data = doublyNode.next.data;

        // Delete the next node
        doublyNode.next = doublyNode.next.next;
    }

    /*Problem Statement: Given the heads of two non-empty linked lists representing two non-negative integers.
     The digits are stored in reverse order, and each of their nodes contains a single digit.
     Add the two numbers and return the sum as a linked list.*/

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int carry = 0;
        while( l1 != null || l2 != null || carry == 1) {
            int sum = 0;
            if(l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }

            if(l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }

            sum += carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        return dummy.next;
    }
}
