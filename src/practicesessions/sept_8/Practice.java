package practicesessions.sept_8;

public class Practice {
        public static void main(String[] args) {
            Practice p = new Practice();

            // Test 1: Reverse Linked List
            Practice.Node n1 = new Practice.Node(1);
            n1.next = new Practice.Node(2);
            n1.next.next = new Practice.Node(3);
            Practice.Node rev = p.reverse(n1);
            printList(rev); // Expected: 3 -> 2 -> 1

            // Test 2: Find Length
            Practice.Node n2 = new Practice.Node(5);
            n2.next = new Practice.Node(10);
            n2.next.next = new Practice.Node(15);
            int len = p.findLength(n2);
            System.out.println("Length: " + len); // Expected: 3

            // Test 3: Merge Two Sorted Linked Lists
            Practice.Node l1 = new Practice.Node(1);
            l1.next = new Practice.Node(3);
            l1.next.next = new Practice.Node(5);

            Practice.Node l2 = new Practice.Node(2);
            l2.next = new Practice.Node(3);
            l2.next.next = new Practice.Node(6);

            Practice.Node merged = p.mergeTwoSortedLL(l1, l2);
            printList(merged); // Expected: 1 -> 2 -> 3 -> 5 -> 6
        }

        static void printList(Practice.Node head) {
            while (head != null) {
                System.out.print(head.data + (head.next != null ? " -> " : ""));
                head = head.next;
            }
            System.out.println();
        }


    static class Node {
        int data;
        Node next;

        public Node(int data) {

            this.data = data;
            this.next = null;
        }
    }

    public Node reverse(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;

    }

    public int findLength(Node root) {
        Node temp = root;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;

        }
        return length;
    }


    public Node mergeTwoSortedLL(Node head1, Node head2) {
        Node dummy = new Node(-1); // dummy node
        Node root = dummy;
        Node temp1 = head1, temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) {
                root.next = new Node(temp1.data);
                temp1 = temp1.next;
            } else if (temp1.data == temp2.data) {
                root.next = new Node(temp1.data);
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else {
                root.next = new Node(temp2.data);
                temp2 = temp2.next;
            }
            root = root.next;
        }

        while (temp1 != null) {
            root.next = new Node(temp1.data);
            temp1 = temp1.next;
            root = root.next;
        }

        while (temp2 != null) {
            root.next = new Node(temp2.data);
            temp2 = temp2.next;
            root = root.next;
        }

        return dummy.next;
    }




}

