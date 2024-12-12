package linkedlist.practice;

public class LinkedListOperations {
    public Node searchElement(Node head, int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // reverse linkedlist using iterative method
    public Node reverse(Node head) {
        // 1 -> 2 ->3 -> 4
        // 4 -> 3 -> 2 -> 1

        Node prev = null; //null
        Node current = head;// 1
        Node next = null;//null
        while (current != null) {
            next = current.next; //2
            current.next = prev;//null
            prev = current;//1
            current = next; // 2
        }
        return prev; //1
    }

    // reverse uing recursion
    public Node reverseRecursively(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverseRecursively(head.next);
       /* head.next.next = head;
        head.next = null;*/
        System.out.println("head is " + head.data);
        Node front = head.next;
        System.out.println("front is " + front.data);
        front.next = head;
        System.out.println("front next is " + front.next.data);
        head.next = null;
        System.out.println("head next is " + head.next);
        return newHead;
    }

    public Node copyLL(Node head) {
        Node current = head;
        Node dummy = new Node(0);
        Node temp = dummy;
        while (current != null) {
            temp.next = new Node(current.data);
            current = current.next;

        }
        return dummy.next;
    }
}
