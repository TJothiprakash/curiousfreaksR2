package linkedlist.june_5;


import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LongestPalindromeInLLTest {

    LongestPalindromeInLL solver = new LongestPalindromeInLL();

    private Node createList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    @Test
    void testExample1() {
        Node head = createList(new int[]{2, 3, 7, 3, 2, 12, 24});
        assertEquals(5, solver.maxPalindrome(head)); // 2->3->7->3->2
    }

    @Test
    void testExample2() {
        Node head = createList(new int[]{12, 4, 4, 3, 14});
        assertEquals(2, solver.maxPalindrome(head)); // 4->4
    }

    @Test
    void testSingleNode() {
        Node head = createList(new int[]{5});
        assertEquals(1, solver.maxPalindrome(head));
    }

    @Test
    void testAllUnique() {
        Node head = createList(new int[]{1, 2, 3, 4});
        assertEquals(1, solver.maxPalindrome(head));
    }

    @Test
    void testAllSame() {
        Node head = createList(new int[]{6, 6, 6, 6});
        assertEquals(4, solver.maxPalindrome(head));
    }

    @Test
    void testLongEvenPalindrome() {
        Node head = createList(new int[]{1, 2, 2, 1, 3, 4});
        assertEquals(4, solver.maxPalindrome(head)); // 1->2->2->1
    }

    @Test
    void testLongOddPalindrome() {
        Node head = createList(new int[]{1, 2, 3, 2, 1, 4, 5});
        assertEquals(5, solver.maxPalindrome(head)); // 1->2->3->2->1
    }

}

