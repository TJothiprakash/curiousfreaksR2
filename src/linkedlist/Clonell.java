package linkedlist;

class NodeWithRandom {
    int val;
    NodeWithRandom next;
    NodeWithRandom random;

    public NodeWithRandom(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Clonell {

    public static void main(String[] args) {
        Clonell solution = new Clonell();

        // Creating the linked list: 1 -> 2 -> 3 -> 4
        NodeWithRandom head = new NodeWithRandom(1);
        NodeWithRandom second = new NodeWithRandom(2);
        NodeWithRandom third = new NodeWithRandom(3);
        NodeWithRandom fourth = new NodeWithRandom(4);

        head.next = second;
        second.next = third;
        third.next = fourth;

        // Setting random pointers
        head.random = second;
        second.random = fourth;

        // Copy the list
        NodeWithRandom copiedList = solution.copyRandomList(head);

        // Print the original list
        System.out.println("Original List:");
        solution.printList(head);

        // Print the copied list
        System.out.println("Copied List:");
        solution.printList(copiedList);
    }

    public NodeWithRandom copyRandomList(NodeWithRandom head) {
        if (head == null) {
            return null;
        }

        // Step 1: Insert copy nodes
        NodeWithRandom current = head;
        while (current != null) {
            NodeWithRandom copy = new NodeWithRandom(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }

        // Step 2: Set random pointers for the copy nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;  // Set the random pointer of the copy node
            }
            current = current.next.next;
        }

        // Step 3: Separate the original and copied list
        current = head;
        NodeWithRandom newHead = head.next;  // The head of the new copied list
        while (current != null) {
            NodeWithRandom copy = current.next;
            current.next = copy.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            current = current.next;
        }

        return newHead;
    }

    // Helper function to print the list
    public void printList(NodeWithRandom head) {
        while (head != null) {
            System.out.print("Node Value: " + head.val + ", Random Value: ");
            if (head.random != null) {
                System.out.println(head.random.val);
            } else {
                System.out.println("null");
            }
            head = head.next;
        }
    }
}
