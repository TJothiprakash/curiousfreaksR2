package linkedlist.june_5;


import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class LinkedListSubtraction {

    // Helper function to get the length of the list
    private int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    // Helper function to compare two lists (returns 1 if l1 > l2, -1 if l1 < l2, 0 if equal)
    private int compare(Node l1, Node l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        if (len1 > len2) return 1;
        if (len2 > len1) return -1;

        // Same length, compare digit by digit
        while (l1 != null && l2 != null) {
            if (l1.data > l2.data) return 1;
            if (l1.data < l2.data) return -1;
            l1 = l1.next;
            l2 = l2.next;
        }
        return 0; // equal
    }

    // Helper function to subtract two lists of same length
    // Returns the head of the result list and borrow value
    private Node subtractSameLength(Node l1, Node l2, int[] borrow) {
        if (l1 == null && l2 == null) return null;

        Node nextNode = subtractSameLength(l1.next, l2.next, borrow);

        int sub = l1.data - borrow[0] - l2.data;
        if (sub < 0) {
            sub += 10;
            borrow[0] = 1;
        } else {
            borrow[0] = 0;
        }

        Node current = new Node(sub);
        current.next = nextNode;
        return current;
    }

    // Helper function to subtract lists where l1 is larger
    // Lengths may be different
    private Node subtractHelper(Node l1, Node l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        if (len1 == len2) {
            int[] borrow = new int[1];
            return subtractSameLength(l1, l2, borrow);
        }

        // If lengths different, pad smaller list with zeros at front
        int diff = len1 - len2;
        Node paddedL2 = padZeros(l2, diff);

        int[] borrow = new int[1];
        return subtractSameLength(l1, paddedL2, borrow);
    }

    // Pad zeros in front of the list
    private Node padZeros(Node head, int count) {
        while (count-- > 0) {
            Node newNode = new Node(0);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    // Remove leading zeros except if the number is zero
    private Node removeLeadingZeros(Node head) {
        while (head != null && head.data == 0 && head.next != null) {
            head = head.next;
        }
        return head;
    }

    public Node subtract(Node l1, Node l2) {
        int comp = compare(l1, l2);

        if (comp == 0) {
            // Numbers are equal, result is 0
            return new Node(0);
        }

        Node larger = comp > 0 ? l1 : l2;
        Node smaller = comp > 0 ? l2 : l1;

        Node result = subtractHelper(larger, smaller);
        result = removeLeadingZeros(result);
        return result;
    }
}

class Node {
    int data;
    Node next;
    public Node(int data) { this.data = data; }
}

