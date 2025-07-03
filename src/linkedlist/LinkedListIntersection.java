package linkedlist;
/*class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}*/

public class LinkedListIntersection {
    //    O( n + m )O(1)
    public static int getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return -1; // No intersection if either list is null
        }

        Node p1 = headA;
        Node p2 = headB;

        while (p1 != p2) {
            // Move p1 to the next node or to headB if it reaches the end of list A
            p1 = (p1 == null) ? headB : p1.next;
            // Move p2 to the next node or to headA if it reaches the end of list B
            p2 = (p2 == null) ? headA : p2.next;
        }

        // If there is an intersection, p1 (or p2) will point to it; otherwise, it will be null
        return (p1 != null) ? p1.data : -1;
    }

    public static void main(String[] args) {
        // Example 1
        Node headA1 = new Node(4);
        headA1.next = new Node(1);
        Node intersection = new Node(8);
        headA1.next.next = intersection;
        intersection.next = new Node(4);
        intersection.next.next = new Node(5);

        Node headB1 = new Node(5);
        headB1.next = new Node(6);
        headB1.next.next = new Node(1);
        headB1.next.next.next = intersection;

        System.out.println(getIntersectionNode(headA1, headB1)); // Output: 8

        // Example 2
        Node headA2 = new Node(1);
        headA2.next = new Node(2);
        headA2.next.next = new Node(3);

        Node headB2 = new Node(4);
        headB2.next = new Node(5);
        headB2.next.next = new Node(6);

        System.out.println(getIntersectionNode(headA2, headB2)); // Output: -1
    }
}

