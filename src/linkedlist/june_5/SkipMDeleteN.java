/*
Problem:
Given a linked list, delete `n` nodes after every `m` nodes, until the end of the list.

You are given a function `modifyList(Node head, int m, int n)`.
- Traverse and keep `m` nodes,
- Then delete the next `n` nodes,
- Repeat until the list ends.

Example:
Input: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → null, m = 2, n = 2
Output: 1 → 2 → 5 → 6 → null

Intuition:
- Traverse through the list, skipping `m` nodes,
- Then for the next `n` nodes, unlink (delete) them,
- Repeat this cycle until the list ends.

Dry Run:
List: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8, m = 2, n = 2
1 → 2 (keep), 3 → 4 (delete), 5 → 6 (keep), 7 → 8 (delete)

Final List: 1 → 2 → 5 → 6

Code:
*/

package linkedlist.june_5;

public class SkipMDeleteN {

    public static void main(String[] args) {
        // Constructing the list: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
        Node head = new Node(1);
        Node current = head;
        for (int i = 2; i <= 8; i++) {
            current.next = new Node(i);
            current = current.next;
        }

        System.out.println("📥 Original List:");
        printList(head);

        SkipMDeleteN solution = new SkipMDeleteN();
        Node modified = solution.modifyList(head, 2, 2);

        System.out.println("✅ Modified List (Skip 2, Delete 2):");
        printList(modified);
    }

    // O(n) time | O(1) space
    public Node modifyList(Node head, int m, int n) {
        if (head == null || m <= 0) return null;

        Node current = head;

        while (current != null) {
            // Step 1: Skip m nodes
            for (int i = 1; i < m && current != null; i++) {
                System.out.println("➡️ Skipping node: " + current.data);
                current = current.next;
            }

            if (current == null) break;

            System.out.println("📍 Reached m-th node: " + current.data);

            // Step 2: Delete next n nodes
            Node temp = current.next;
            for (int i = 0; i < n && temp != null; i++) {
                System.out.println("❌ Deleting node: " + temp.data);
                temp = temp.next;
            }

            // Step 3: Link current to the (m+n+1)-th node
            current.next = temp;
            current = temp;
        }

        return head;
    }

    private static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " → ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /*
    Time Complexity:
        - O(n), where n is the total number of nodes

    Space Complexity:
        - O(1), no extra space used
    */
}


