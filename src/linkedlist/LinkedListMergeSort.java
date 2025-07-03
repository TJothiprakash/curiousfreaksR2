package linkedlist;


public class LinkedListMergeSort {

    // Example usage
    public static void main(String[] args) {
        LinkedListMergeSort listSort = new LinkedListMergeSort();

        // Creating a sample linked list: 3 -> 5 -> 2 -> 4 -> 1
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(1);

        System.out.println("Original List:");
        listSort.printList(head);

        // Sorting the linked list
        ListNode sortedList = listSort.sortList(head);

        System.out.println("Sorted List:");
        listSort.printList(sortedList);
    }

    // Main function to sort the linked list
// O(n log n) time, O(log n) space (due to recursion stack)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;  // Base case: a list with 0 or 1 element is already sorted
        }

        // Step 1: Split the list into two halves
        ListNode middle = getMiddle(head);
        ListNode nextOfMiddle = middle.next;
        middle.next = null;  // Split the list into two halves

        // Step 2: Recursively sort the two halves
        ListNode left = sortList(head);
        ListNode right = sortList(nextOfMiddle);

        // Step 3: Merge the two sorted halves
        return merge(left, right);
    }

    // Helper function to find the middle node of the linked list
    private ListNode getMiddle(ListNode head) {
        if (head == null) return head;
        ListNode slow = head, fast = head;

        // Move fast by 2 and slow by 1 step until fast reaches the end
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Helper function to merge two sorted linked lists
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        ListNode mergedHead = null;
        if (left.val <= right.val) {
            mergedHead = left;
            mergedHead.next = merge(left.next, right);
        } else {
            mergedHead = right;
            mergedHead.next = merge(left, right.next);
        }
        return mergedHead;
    }

    // Utility function to print the linked list
//    O(n)O(1)
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

