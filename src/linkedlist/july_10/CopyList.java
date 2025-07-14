package linkedlist.july_10;

public class CopyList {

    class Node {
        int data;
        Node next;
        Node random;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }

    }

    public Node copy(Node head) {
        //copy teh data, connect the next nodes
//        connect teh random ptrs

        if (head == null) return null;

        // Step 1: Clone and interleave
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.data);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Copy random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate copied list
        curr = head;
        Node dummy = new Node(0);
        Node copyTail = dummy;
        while (curr != null) {
            Node copy = curr.next;
            curr.next = copy.next;

            copyTail.next = copy;
            copyTail = copy;

            curr = curr.next;
        }

        return dummy.next;
    }

}

