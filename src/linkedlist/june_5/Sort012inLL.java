package linkedlist.june_5;

/*class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}*/

public class Sort012inLL {

    Node sort012inLL(Node head) {
        Node zeros = null;
        Node ones = null;
        Node twos = null;
        Node temp = head;

        // Traverse the linked list and segregate nodes into zeros, ones, and twos
        while (temp != null) {
            if (temp.data == 0) {
                if (zeros == null) {
                    zeros = temp;
                } else {
                    appendNode(zeros, temp);
                }
            } else if (temp.data == 1) {
                if (ones == null) {
                    ones = temp;
                } else {
                    appendNode(ones, temp);
                }
            } else { // temp.data == 2
                if (twos == null) {
                    twos = temp;
                } else {
                    appendNode(twos, temp);
                }
            }
            temp = temp.next;
        }

        // Connect zeros, ones, and twos lists
        if (zeros != null) {
            head = zeros;
            if (ones != null) {
                tail(zeros).next = ones;
            } else {
                tail(zeros).next = twos;
            }
        } else if (ones != null) {
            head = ones;
            tail(ones).next = twos;
        } else {
            head = twos;
        }

        // Ensure the last node of the twos list points to null
        if (twos != null) {
            tail(twos).next = null;
        }

        return head;
    }

    // Utility function to append a node to the end of a linked list
    private void appendNode(Node list, Node node) {
        Node tail = tail(list);
        tail.next = node;
    }

    // Utility function to find the tail of a linked list
    private Node tail(Node head) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    public static void main(String[] args) {

    }
}
