package linkedlist;

public class LinkedListQuickSort {

    // Function to sort the linked list using quicksort
    public ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;  // Base case: a list with 0 or 1 element is already sorted
        }

        // Step 1: Partition the list around the pivot
        ListNode[] partitionedLists = partition(head);

        // Step 2: Recursively sort the left and right sublists
        ListNode leftSorted = quickSort(partitionedLists[0]);
        ListNode rightSorted = quickSort(partitionedLists[1]);

        // Step 3: Merge the sorted lists with the pivot
        if (leftSorted == null) {
            return joinLists(leftSorted, head, rightSorted);
        } else {
            return joinLists(leftSorted, head, rightSorted);
        }
    }

    // Helper function to partition the list around a pivot
    private ListNode[] partition(ListNode head) {
        ListNode leftHead = new ListNode(0), rightHead = new ListNode(0);
        ListNode left = leftHead, right = rightHead, pivot = new ListNode(0);

        int pivotValue = head.val;
        ListNode current = head;

        // Partition the list into three parts: less than, equal to, and greater than the pivot
        while (current != null) {
            if (current.val < pivotValue) {
                left.next = current;
                left = left.next;
            } else if (current.val > pivotValue) {
                right.next = current;
                right = right.next;
            } else {
                pivot.next = current;
                pivot = pivot.next;
            }
            current = current.next;
        }

        // Terminate the lists
        left.next = null;
        right.next = null;
        pivot.next = null;

        // Return the three partitioned lists: less, equal, greater
        return new ListNode[]{leftHead.next, rightHead.next};
    }

    // Helper function to join three parts (left, pivot, right)
    private ListNode joinLists(ListNode left, ListNode pivot, ListNode right) {
        if (left == null) {
            pivot.next = right;
            return pivot;
        }
        ListNode temp = left;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = pivot;
        pivot.next = right;
        return left;
    }

    // Utility function to print the linked list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        LinkedListQuickSort listSort = new LinkedListQuickSort();

        // Creating a sample linked list: 1 -> 9 -> 3 -> 8
        ListNode head = new ListNode(1);
        head.next = new ListNode(9);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(8);

        System.out.println("Original List:");
        listSort.printList(head);

        // Sorting the linked list
        ListNode sortedList = listSort.quickSort(head);

        System.out.println("Sorted List:");
        listSort.printList(sortedList);
    }
}

