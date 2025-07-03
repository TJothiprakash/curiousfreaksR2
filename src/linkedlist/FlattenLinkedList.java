package linkedlist;

class DoublyNode {
    int val;
    DoublyNode next;
    DoublyNode child;
    DoublyNode prev; // prev pointer to connect the doubly linked list

    DoublyNode(int val) {
        this.val = val;
        this.next = null;
        this.child = null;
        this.prev = null; // Initialize prev as null
    }
}

public class FlattenLinkedList {

    // Example usage
    public static void main(String[] args) {
        FlattenLinkedList solution = new FlattenLinkedList();

        // Creating the linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        DoublyNode head = new DoublyNode(1);
        head.next = new DoublyNode(2);
        head.next.prev = head;
        head.next.next = new DoublyNode(3);
        head.next.next.prev = head.next;
        head.next.next.next = new DoublyNode(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new DoublyNode(5);
        head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next.next = new DoublyNode(6);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        // Adding a child list at node 3
        head.next.next.child = new DoublyNode(7);
        head.next.next.child.next = new DoublyNode(8);
        head.next.next.child.next.prev = head.next.next.child;
        head.next.next.child.next.next = new DoublyNode(9);
        head.next.next.child.next.next.prev = head.next.next.child.next;

        // Flattening the list
        System.out.println("Original List:");
        solution.printList(head);

        DoublyNode flattenedHead = solution.flatten(head);

        System.out.println("Flattened List:");
        solution.printList(flattenedHead);

        System.out.println("Flattened List in Reverse:");
        solution.printListReverse(flattenedHead);
    }

    // Function to flatten the linked list
//    O(n )O(1)
    public DoublyNode flatten(DoublyNode head) {
        if (head == null) {
            return null;
        }

        DoublyNode current = head;

        while (current != null) {
            // If the current node has a child, we need to flatten it
            if (current.child != null) {
                DoublyNode child = current.child;
                // Find the tail of the child list
                while (child.next != null) {
                    child = child.next;
                }
                // Connect the tail of the child list to the next node of the current node
                child.next = current.next;
                if (current.next != null) {
                    current.next.prev = child; // Update the prev pointer of the next node
                }

                // Connect the current node to the head of the child list
                current.next = current.child;
                current.child.prev = current; // Update the prev pointer of the child
                current.child = null; // Clear the child pointer
            }
            current = current.next; // Move to the next node
        }
        return head;
    }

    // Utility function to print the linked list (forward traversal)
    //    O(n)O(1)
    public void printList(DoublyNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Utility function to print the linked list (backward traversal)
//    O(n)O(1)
    public void printListReverse(DoublyNode head) {
        // Traverse to the tail of the list first
        while (head != null && head.next != null) {
            head = head.next;
        }

        // Now print the list in reverse order
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.prev;
        }
        System.out.println();
    }
}
