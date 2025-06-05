package linkedlist.june_5;


public class PairwiseSwapElements {

    public static void main(String[] args) {
        // Sample creation and testing
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = new PairwiseSwapElements().swapPairwise(head);
        printList(head);
    }

    public Node swapPairwise(Node head) {
        // Base cases
        if (head == null || head.next == null) return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            Node first = prev.next;
            Node second = first.next;

            // Swap
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move prev pointer two steps ahead
            prev = first;
        }

        return dummy.next;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}
