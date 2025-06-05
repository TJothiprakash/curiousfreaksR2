package linkedlist.june_5;
public class ReorderList {

    public void reorder(Node head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle
        Node slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node second = reverse(slow.next);
        slow.next = null;

        // Step 3: Merge two halves
        Node first = head;
        while (second != null) {
            Node temp1 = first.next;
            Node temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }

    private Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }
}
