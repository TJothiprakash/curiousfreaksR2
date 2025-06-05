package linkedlist.june_5;


public class SkipMDeleteN {

    public Node modifyList(Node head, int m, int n) {
        if (head == null || m <= 0) return null;

        Node current = head;

        while (current != null) {
            // Step 1: Skip m nodes
            for (int i = 1; i < m && current != null; i++) {
                current = current.next;
            }

            if (current == null) break;

            // Step 2: Delete next n nodes
            Node temp = current.next;
            for (int i = 0; i < n && temp != null; i++) {
                temp = temp.next;
            }

            // Step 3: Link the m-th node to the (m+n+1)-th node
            current.next = temp;
            current = temp;
        }

        return head;
    }
}

