/*
Problem:
Given a linked list with only values `0`, `1`, and `2`, **sort the list** such that all `0`s come first,
followed by all `1`s, and then all `2`s. You must not create a new list, but rearrange the original nodes.

Example:
Input: 1 → 0 → 2 → 1 → 0
Output: 0 → 0 → 1 → 1 → 2

Intuition:
We divide the list into three sublists for 0s, 1s, and 2s using dummy nodes.
Then connect them at the end. This avoids data swapping and preserves node order.

Dry Run:
Input: 1 → 0 → 2 → 1 → 0
0s: 0 → 0
1s: 1 → 1
2s: 2
Connect: 0s → 1s → 2s

Code:
*/

package linkedlist.june_5;

public class Sort012inLL {

    // O(n) time | O(1) space
    Node sort012inLL(Node head) {
        if (head == null || head.next == null) return head;

        // Step 1: Dummy heads and tails for 0s, 1s, 2s
        Node zeroD = new Node(0), oneD = new Node(0), twoD = new Node(0);
        Node zero = zeroD, one = oneD, two = twoD;

        Node curr = head;

        // Step 2: Distribute nodes to respective buckets
        while (curr != null) {
            System.out.println("🔄 Visiting node: " + curr.data);

            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
                System.out.println("🟢 Placed in 0s list");
            } else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
                System.out.println("🟡 Placed in 1s list");
            } else {
                two.next = curr;
                two = two.next;
                System.out.println("🔴 Placed in 2s list");
            }
            curr = curr.next;
        }

        // Step 3: Connect the three lists together
        zero.next = (oneD.next != null) ? oneD.next : twoD.next;
        one.next = twoD.next;
        two.next = null;

        return zeroD.next;
    }

    public static void main(String[] args) {
        Sort012inLL obj = new Sort012inLL();

        Node head = new Node(1);
        head.next = new Node(0);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(0);

        System.out.println("📥 Before sorting:");
        printList(head);

        Node sorted = obj.sort012inLL(head);

        System.out.println("✅ After sorting:");
        printList(sorted);
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " → ");
            head = head.next;
        }
        System.out.println("null");
    }

    /*
    Time Complexity:
        - O(n), where n is the number of nodes

    Space Complexity:
        - O(1), no extra memory used except a few pointers
    */
}
