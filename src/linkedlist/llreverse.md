We’ll reverse:

1 → 2 → 3 → null

🔁 Recursive Reverse Code Reminder
public Node reverseUsingRecursion(Node head) {
if (head == null || head.next == null) return head;

    Node newHead = reverseUsingRecursion(head.next);

    head.next.next = head;
    head.next = null;

    return newHead;
}

🪜 Step-by-Step Execution
Step 1: Calls go deep
reverse(1)
└─> reverse(2)
└─> reverse(3)


At head = 3:

Since head.next == null, base case returns 3.

Step 2: Unwind at head = 2

Current pointers:

2 → 3 → null


newHead = 3

Now apply lines:

head.next.next = head;
→ 3.next = 2
→ rewires link backward:

3 → 2


head.next = null;
→ break old link 2 → 3:

3 → 2 → null


Return newHead (3).

Step 3: Unwind at head = 1

Current pointers:

1 → 2 → null
and separately: 3 → 2 → null


newHead = 3

head.next.next = head;
→ 2.next = 1
→ rewires:

3 → 2 → 1


head.next = null;
→ break old link 1 → 2:

3 → 2 → 1 → null


Return newHead (3).

✅ Final Answer
3 → 2 → 1 → null

🔑 Intuition

head.next.next = head → flips the pointer backward.

head.next = null → avoids cycles by breaking the old forward link.

newHead is always the tail node from the deepest recursion, passed back up unchanged.