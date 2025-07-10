package linkedlist.july_10;
// ✅ Clone Linked List with Next and Random Pointers
public class CloneList {
    static class Node {
        int data;
        Node next, random;

        Node(int data) {
            this.data = data;
            this.next = this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Interleave cloned nodes
        Node curr = head;
        while (curr != null) {
            Node clone = new Node(curr.data);
            clone.next = curr.next;
            curr.next = clone;
            curr = clone.next;
        }

        // Step 2: Assign random pointers for clones
        curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        // Step 3: Detach original and clone lists
        curr = head;
        Node cloneHead = head.next;
        while (curr != null) {
            Node clone = curr.next;
            curr.next = clone.next;
            if (clone.next != null)
                clone.next = clone.next.next;
            curr = curr.next;
        }

        return cloneHead;
    }

    // Helper: print list (for debugging)
    public void printList(Node head) {
        while (head != null) {
            System.out.print("[" + head.data + ", ");
            System.out.print(head.random != null ? head.random.data : "NULL");
            System.out.print("] -> ");
            head = head.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        CloneList cl = new CloneList();
        Node a = new Node(1);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(9);

        a.next = b;
        b.next = c;
        c.next = d;

        a.random = c;
        b.random = c;
        c.random = null;
        d.random = c;

        System.out.println("Original:");
        cl.printList(a);

        Node clonedHead = cl.copyRandomList(a);

        System.out.println("\nCloned:");
        cl.printList(clonedHead);
    }
}
