package linkedlist;


class MergeSortedLists {
    public static void main(String[] args) {
        MergeSortedLists msl = new MergeSortedLists();

        // Test Case 1: Both lists non-empty, interleaved
        Node head1 = createList(new int[]{1, 3, 5});
        Node head2 = createList(new int[]{2, 4, 6});
        System.out.print("Test Case 1: ");
        printList(msl.merge(head1, head2)); // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> 6

        // Test Case 2: One list is empty
        head1 = createList(new int[]{1, 2, 3});
        head2 = null;
        System.out.print("Test Case 2: ");
        printList(msl.merge(head1, head2)); // Expected: 1 -> 2 -> 3

        // Test Case 3: Both lists empty
        head1 = null;
        head2 = null;
        System.out.print("Test Case 3: ");
        printList(msl.merge(head1, head2)); // Expected: null

        // Test Case 4: Lists with duplicate elements
        head1 = createList(new int[]{1, 3, 5});
        head2 = createList(new int[]{1, 3, 5});
        System.out.print("Test Case 4: ");
        printList(msl.merge(head1, head2)); // Expected: 1 -> 1 -> 3 -> 3 -> 5 -> 5
    }


    Node merge(Node head1, Node head2) {
        Node dummy = new Node(-1);
        Node tail = dummy;

        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }

        // Attach the remaining nodes (if any)
        if (head1 != null) {
            tail.next = head1;
        }
        if (head2 != null) {
            tail.next = head2;
        }

        return dummy.next; // Head of the merged list
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    static Node createList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }


}
