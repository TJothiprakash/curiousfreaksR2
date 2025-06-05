/*
package linkedlist.june_5;

import org.testng.annotations.Test;

import linkedlist.practice.feb_15.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class MergeAndQuickSortLLTest {

    MergeAndQuickSortLL listOps = new MergeAndQuickSortLL();

    private int[] toArray(Node head) {
        return java.util.stream.IntStream.iterate(0, i -> head != null)
                .map(i -> {
                    int val = head.data;
                    head = head.next;
                    return val;
                }).limit(1000).toArray();
    }

    private Node arrayToList(int[] arr) {
        return MergeAndQuickSortLL.createList(arr);
    }

    private void assertListEquals(int[] expected, Node actualHead) {
        Node current = actualHead;
        for (int val : expected) {
            assertNotNull(current, "List ended early.");
            assertEquals(val, current.data);
            current = current.next;
        }
        assertNull(current.toString(), "List is longer than expected.");
    }

    @Test
    void testQuickSort_Unsorted() {
        Node head = arrayToList(new int[]{4, 2, 1, 5, 3});
        Node tail = MergeAndQuickSortLL.getTail(head);
        Node sorted = listOps.quickSort(head, tail);
        assertListEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }

    @Test
    void testQuickSort_AlreadySorted() {
        Node head = arrayToList(new int[]{1, 2, 3});
        Node tail = MergeAndQuickSortLL.getTail(head);
        Node sorted = listOps.quickSort(head, tail);
        assertListEquals(new int[]{1, 2, 3}, sorted);
    }

    @Test
    void testQuickSort_SingleElement() {
        Node head = arrayToList(new int[]{1});
        Node tail = MergeAndQuickSortLL.getTail(head);
        Node sorted = listOps.quickSort(head, tail);
        assertListEquals(new int[]{1}, sorted);
    }

    @Test
    void testQuickSort_WithDuplicates() {
        Node head = arrayToList(new int[]{3, 1, 2, 3});
        Node tail = MergeAndQuickSortLL.getTail(head);
        Node sorted = listOps.quickSort(head, tail);
        assertListEquals(new int[]{1, 2, 3, 3}, sorted);
    }

    @Test
    void testMergeSortedLists_NormalCase() {
        Node h1 = arrayToList(new int[]{1, 3, 5});
        Node h2 = arrayToList(new int[]{2, 4, 6});
        Node merged = listOps.mergeSortedLists(h1, h2);
        assertListEquals(new int[]{1, 2, 3, 4, 5, 6}, merged);
    }

    @Test
    void testMergeSortedLists_OneEmpty() {
        Node h1 = arrayToList(new int[]{1, 2});
        Node h2 = null;
        Node merged = listOps.mergeSortedLists(h1, h2);
        assertListEquals(new int[]{1, 2}, merged);
    }

    @Test
    void testMergeSortedLists_BothEmpty() {
        Node merged = listOps.mergeSortedLists(null, null);
        assertNull(merged);
    }

    @Test
    void testMergeSortedLists_Duplicates() {
        Node h1 = arrayToList(new int[]{1, 3, 5});
        Node h2 = arrayToList(new int[]{1, 3, 5});
        Node merged = listOps.mergeSortedLists(h1, h2);
        assertListEquals(new int[]{1, 1, 3, 3, 5, 5}, merged);
    }
}
*/
