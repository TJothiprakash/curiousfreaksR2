package linkedlist.july_10;

//15 -> 11 -> 6 -> 3 ✅
//
//        ---
//
//        ## ✅ Code (Java - Single Complete File)
//
//```java
public class DeleteNodesWithGreaterValueOnRight {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // Main function to delete nodes
    public Node compute(Node head) {
        if (head == null || head.next == null) return head;

        // Step 1: Reverse the list
        head = reverseList(head);

        // Step 2: Traverse and filter nodes
        Node maxNode = head;
        Node curr = head;

        while (curr != null && curr.next != null) {
            if (curr.next.data < maxNode.data) {
                // Skip curr.next
                curr.next = curr.next.next;
            } else {
                // Move forward
                curr = curr.next;
                maxNode = curr;
            }
        }

        // Step 3: Reverse back
        return reverseList(head);
    }

    // Helper function to reverse a list
    private Node reverseList(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // Utility to print the list
    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // Sample driver
    public static void main(String[] args) {
        DeleteNodesWithGreaterValueOnRight d = new DeleteNodesWithGreaterValueOnRight();

        Node head = new Node(12);
        head.next = new Node(15);
        head.next.next = new Node(10);
        head.next.next.next = new Node(11);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(3);

        System.out.print("Original List: ");
        d.printList(head);

        Node result = d.compute(head);

        System.out.print("Modified List: ");
        d.printList(result);
    }
}
