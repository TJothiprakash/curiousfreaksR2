package linkedlist.june_5;

import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class LinkedListSubtractionTest {

    LinkedListSubtraction llSub = new LinkedListSubtraction();

    // Helper to create linked list from int array
    private Node arrayToList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // Helper to convert linked list to int array
    private int[] listToArray(Node head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) {
            list.add(head.data);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    @Test
    void testSubtract_100minus12() {
        Node l1 = arrayToList(new int[]{1,0,0});
        Node l2 = arrayToList(new int[]{1,2});
        Node result = llSub.subtract(l1, l2);
        assertArrayEquals(new int[]{8,8}, listToArray(result));
    }

    @Test
    void testSubtract_63minus710() {
        Node l1 = arrayToList(new int[]{6,3});
        Node l2 = arrayToList(new int[]{7,1,0});
        Node result = llSub.subtract(l1, l2);
        assertArrayEquals(new int[]{6,4,7}, listToArray(result));
    }

    @Test
    void testSubtract_equalNumbers() {
        Node l1 = arrayToList(new int[]{9,9,9});
        Node l2 = arrayToList(new int[]{9,9,9});
        Node result = llSub.subtract(l1, l2);
        assertArrayEquals(new int[]{0}, listToArray(result));
    }

    @Test
    void testSubtract_zeroMinusZero() {
        Node l1 = new Node(0);
        Node l2 = new Node(0);
        Node result = llSub.subtract(l1, l2);
        assertArrayEquals(new int[]{0}, listToArray(result));
    }

    @Test
    void testSubtract_differentLengths() {
        Node l1 = arrayToList(new int[]{1,0,0,0});
        Node l2 = arrayToList(new int[]{9,9});
        Node result = llSub.subtract(l1, l2);
        assertArrayEquals(new int[]{9,0,1}, listToArray(result)); // 1000 - 99 = 901
    }
}
