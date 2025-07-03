package linkedlist;

public class Loops {

//    O(n)O(1)
    public static boolean detectLoop(Node head) {

        if (head == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        // Traverse the list with two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by one step
            fast = fast.next.next; // Move fast pointer by two steps

            // If slow and fast pointers meet, there's a loop
            if (slow == fast) {
                System.out.println("Loop found");
                return true;
            }
        }

        return false; // No loop found

    }
}
