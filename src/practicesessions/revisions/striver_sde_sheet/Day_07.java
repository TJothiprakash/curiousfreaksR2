package practicesessions.revisions.striver_sde_sheet;

import java.util.HashMap;

public class Day_07 {


    //rotate linked list by k times
    //utility function to rotate list by k times
    static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        //calculating length
        Node temp = head;
        int length = 1;
        while (temp.next != null) {
            ++length;
            temp = temp.next;
        }
        //link last node to first node
        temp.next = head;
        k = k % length; //when k is more than length of list
        int end = length - k; //to get end of the list
        while (end-- != 0) temp = temp.next;
        //breaking last node link and pointing to NULL
        head = temp.next;
        temp.next = null;

        return head;
    }

    // clone a linked list with random pointer
    // Function to clone the linked list
    public Node cloneLL(Node head) {
        Node temp = head;
        // Create a HashMap to map original nodes
        // to their corresponding copied nodes
        HashMap<Node, Node> map = new HashMap<>();

        // Step 1: Create copies of each
        // node and store them in the map
        while (temp != null) {
            // Create a new node with the
            // same data as the original node
            Node newNode = new Node(temp.data);
            // Map the original node to its
            // corresponding copied node in the map
            map.put(temp, newNode);
            // Move to the next node in the original list
            temp = temp.next;
        }

        temp = head;
        // Step 2: Connect the next and random
        // pointers of the copied nodes using the map
        while (temp != null) {
            // Access the copied node corresponding
            // to the current original node
            Node copyNode = map.get(temp);
            // Set the next pointer of the copied node
            // to the copied node mapped to the
            // next node in the original list
            copyNode.next = map.get(temp.next);
            // Set the random pointer of the copied node
            // to the copied node mapped to the
            // random node in the original list
            copyNode.random = map.get(temp.random);
            // Move to the next node in the original list
            temp = temp.next;
        }

        // Return the head of the
        // deep copied list from the map
        return map.get(head);
    }

    //
    void insertCopyInBetween(Node head) {
        Node temp = head;
        while (temp != null) {
            Node nextElement = temp.next;
            // Create a new node with the same data
            Node copy = new Node(temp.data);

            // Point the copy's next to
            // the original node's next
            copy.next = nextElement;

            // Point the original
            // node's next to the copy
            temp.next = copy;

            // Move to the next original node
            temp = nextElement;
        }
    }

    // Function to connect random
// pointers of the copied nodes
    void connectRandomPointers(Node head) {
        Node temp = head;
        while (temp != null) {
            // Access the copied node
            Node copyNode = temp.next;

            // If the original node
            // has a random pointer
            if (temp.random != null) {
                // Point the copied node's random to the
                // corresponding copied random node
                copyNode.random = temp.random.next;
            } else {
                // Set the copied node's random to
                // null if the original random is null
                copyNode.random = null;
            }

            // Move to the next original node
            temp = temp.next.next;
        }
    }

    // Function to retrieve the
// deep copy of the linked list
    Node getDeepCopyList(Node head) {
        Node temp = head;
        // Create a dummy node
        Node dummyNode = new Node(-1);
        // Initialize a result pointer
        Node res = dummyNode;

        while (temp != null) {
            // Creating a new List by
            // pointing to copied nodes
            res.next = temp.next;
            res = res.next;

            // Disconnect and revert back to the
            // initial state of the original linked list
            temp.next = temp.next.next;
            temp = temp.next;
        }

        // Return the deep copy of the
        // list starting from the dummy node
        return dummyNode.next;
    }

    // Function to clone the linked list
    Node cloneLLUsingDeepCOpy(Node head) {
        // If the original list
        // is empty, return null
        if (head == null) return null;

        // Step 1: Insert copy of
        // nodes in between
        insertCopyInBetween(head);
        // Step 2: Connect random
        // pointers of copied nodes
        connectRandomPointers(head);
        // Step 3: Retrieve the deep
        // copy of the linked list
        return getDeepCopyList(head);
    }

    class Node {
        // Data stored in the node
        int data;
        // Pointer to the next node
        Node next;
        // Pointer to a random
        // node in the list
        Node random;

        // Constructors for Node class
        Node() {
            // Default constructor
            this.data = 0;
            this.next = null;
            this.random = null;
        }

        Node(int x) {
            // Constructor with data
            this.data = x;
            this.next = null;
            this.random = null;
        }

        Node(int x, Node nextNode, Node randomNode) {
            // Constructor with data,
            // next, and random pointers
            this.data = x;
            this.next = nextNode;
            this.random = randomNode;
        }
    }

}
