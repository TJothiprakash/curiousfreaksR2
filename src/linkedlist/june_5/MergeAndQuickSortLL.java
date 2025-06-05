package linkedlist.june_5;



public class MergeAndQuickSortLL {

    public static void main(String[] args) {
        MergeAndQuickSortLL listOps = new MergeAndQuickSortLL();

        // Test Case 1: Unsorted list
        Node head = createList(new int[]{4, 2, 1, 5, 3});
        System.out.print("Original: ");
        printList(head);
        head = listOps.quickSort(head, getTail(head));
        System.out.print("Sorted:   ");
        printList(head);

        // Test Case 2: Already sorted
        head = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Sorted Input: ");
        head = listOps.quickSort(head, getTail(head));
        printList(head);

        // Test Case 3: Single element
        head = createList(new int[]{1});
        System.out.print("Single Element: ");
        head = listOps.quickSort(head, getTail(head));
        printList(head);

        // Test Case 4: Duplicates
        head = createList(new int[]{3, 5, 3, 2, 4});
        System.out.print("With Duplicates: ");
        head = listOps.quickSort(head, getTail(head));
        printList(head);
    }

    // QuickSort Helpers

    Node quickSort(Node head, Node end) {
        if (head == null || head == end) return head;

        Node[] partition = partitionList(head, end);
        Node newHead = partition[0];
        Node pivot = partition[1];
        Node newEnd = partition[2];

        if (newHead != pivot) {
            Node temp = newHead;
            while (temp.next != pivot) temp = temp.next;
            temp.next = null;

            newHead = quickSort(newHead, temp);

            temp = getTail(newHead);
            temp.next = pivot;
        }

        pivot.next = quickSort(pivot.next, newEnd);
        return newHead;
    }

    Node[] partitionList(Node head, Node end) {
        Node pivot = end;
        Node prev = null, curr = head, tail = pivot;
        Node newHead = null, newEnd = pivot;

        while (curr != pivot) {
            Node next = curr.next;
            if (curr.data < pivot.data) {
                if (newHead == null) newHead = curr;
                prev = curr;
                curr = next;
            } else {
                if (prev != null) prev.next = curr.next;
                else head = curr.next;

                tail.next = curr;
                tail = curr;
                tail.next = null;

                curr = next;
            }
        }

        if (newHead == null) newHead = pivot;
        newEnd = tail;

        return new Node[]{newHead, pivot, newEnd};
    }

    static Node getTail(Node head) {
        while (head != null && head.next != null) {
            head = head.next;
        }
        return head;
    }

    // Merge function for sorted lists
    Node mergeSortedLists(Node h1, Node h2) {
        Node dummy = new Node(-1);
        Node tail = dummy;

        while (h1 != null && h2 != null) {
            if (h1.data < h2.data) {
                tail.next = h1;
                h1 = h1.next;
            } else {
                tail.next = h2;
                h2 = h2.next;
            }
            tail = tail.next;
        }
        tail.next = (h1 != null) ? h1 : h2;
        return dummy.next;
    }

    // Utility
    static Node createList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}
