/*
Problem:
Given the head of a singly linked list:
L₀ → L₁ → L₂ → ... → Lₙ₋₁ → Lₙ

Reorder it to:
L₀ → Lₙ → L₁ → Lₙ₋₁ → L₂ → Lₙ₋₂ → ...

You must do this **in-place** without altering the node values.

Intuition:
1. Find the middle node using slow and fast pointers.
2. Reverse the second half of the list.
3. Merge the first and second halves by alternating nodes.

Dry Run:
Input: 1 → 2 → 3 → 4 → 5
Middle = 3
Second half reversed: 5 → 4
Merge:
→ 1 → 5 → 2 → 4 → 3

Code:
*/

package linkedlist.june_5;

public class ReorderList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        ReorderList rl = new ReorderList();
        System.out.println("📥 Original List:");
        printList(head);

        rl.reorder(head);

        System.out.println("✅ Reordered List:");
        printList(head);
    }

    // O(n) time, O(1) space
    public void reorder(Node head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle
        Node slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("📍 Middle node: " + slow.data);

        // Step 2: Reverse second half
        Node second = reverse(slow.next);
        slow.next = null;
        System.out.println("🔁 Reversed second half:");
        printList(second);

        // Step 3: Merge two halves
        Node first = head;
        while (second != null) {
            Node temp1 = first.next;
            Node temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }

    // O(n) time, O(1) space
    private Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " → ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    /*
    Time Complexity:
        - Finding middle: O(n)
        - Reversing second half: O(n/2)
        - Merging: O(n)
        - Total: O(n)

    Space Complexity:
        - O(1), no extra space used
    */
}

