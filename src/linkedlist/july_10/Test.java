package linkedlist.july_10;


public class Test {
    public static void main(String[] args) {

    }

    public Node reverse(Node head) {

        Node curr = head, prev = null;

        while (curr != null) {
            Node next = curr.next; // save the next node
            curr.next = prev; // reverse the link
            prev = curr; // move teh prev node
            curr = next;// move the curr node
        }

        return prev; // prev will our new head

    }

    public Node middle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean detectLoop(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public void removeDuplicatesfromSorted(Node head) {
        Node temp = head;

        while (temp != null && temp.next != null) {
            if (temp.data == temp.next.data) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

    }

    public boolean isPalindrome(Node head) {
        Node temp = head;
        Node middle = new Test().middle(head);
        Node reverse = new Test().reverse(middle.next);
        Node firstHalf = head;
        while (reverse != null) {
            if (firstHalf.data != reverse.data) return false;
            reverse = reverse.next;
            firstHalf = firstHalf.next;
        }
        return true;
    }

    public Node mergeTwoSortedList(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        Node dummy = new Node(-1);
        Node result = dummy.next;
        while (temp1 != null && temp2 != null) {
            if ((int) temp1.data < (int) temp2.data) {
                dummy.next = temp1;
                temp1 = temp1.next;
            } else if ((int) temp1.data > (int) temp2.data) {
                dummy.next = temp2;
                temp2 = temp2.next;
            } else {
                dummy.next = temp1;
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            dummy = dummy.next;
        }
        if (temp1 != null) dummy.next = temp1;
        if (temp2 != null) dummy.next = temp2;

        return dummy.next;

    }

    public void deleteNodewithOnePointer(Node delNode) {

        Node temp = delNode;
        delNode.data = delNode.next.data;
        delNode.next = delNode.next.next;

    }
    // medium level questions

    public Node intersectionPoint(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        while (temp1 != temp2) {
            temp1 = temp1 != null ? temp1.next : head2;
            temp2 = temp2 != null ? temp2.next : head1;

        }
        return temp1;
    }

    public Node removeNthNodefromEnd(Node head, int n) {
        int length = len(head);
        int nthNode = length - n;

        // Edge case: remove the head
        if (nthNode == 0) {
            return head.next;
        }

        Node temp = head;
        for (int i = 0; i < nthNode - 1; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;
        return head;
    }

    private int len(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public Node reverseKGroup(Node head, int k) {
        if (head == null || k == 1) return head;

        Node dummy = new Node(-1);
        dummy.next = head;

        Node prevGroupTail = dummy;

        while (true) {
            Node kth = getKthNode(prevGroupTail, k);
            if (kth == null) break;

            Node groupNext = kth.next;

            // Reverse the group
            Node prev = kth.next;
            Node curr = prevGroupTail.next;

            while (curr != groupNext) {
                Node temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect previous group with reversed group
            Node temp = prevGroupTail.next;
            prevGroupTail.next = kth;
            prevGroupTail = temp;
        }

        return dummy.next;
    }

    // Helper: Get k-th node from current node
    private Node getKthNode(Node start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }

    public Node segregateOddEven(Node head) {
        if (head == null || head.next == null) return head;

        Node oddDummy = new Node(-1);   // Dummy node for odd list
        Node evenDummy = new Node(-1);  // Dummy node for even list
        Node oddTail = oddDummy;
        Node evenTail = evenDummy;

        Node current = head;

        while (current != null) {
            int value = (int) current.data;

            if (value % 2 == 0) {
                evenTail.next = current;
                evenTail = evenTail.next;
            } else {
                oddTail.next = current;
                oddTail = oddTail.next;
            }

            current = current.next;
        }

        // Finish the even list
        evenTail.next = null;

        // Attach even list after odd list
        oddTail.next = evenDummy.next;

        // Return head of the new list (skip dummy node)
        return oddDummy.next;
    }

    public Node rearrangeInZigZag(Node head) {
        if (head == null || head.next == null) return head;

        // Step 1: Find the middle
        Node slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        Node secondHalf = reverse(slow.next);
        slow.next = null; // Cut first and second halves

        // Step 3: Merge in zig-zag fashion
        Node first = head;
        Node dummy = new Node(-1);
        Node current = dummy;

        while (first != null || secondHalf != null) {
            if (first != null) {
                current.next = first;
                current = current.next;
                first = first.next;
            }
            if (secondHalf != null) {
                current.next = secondHalf;
                current = current.next;
                secondHalf = secondHalf.next;
            }
        }

        return dummy.next;
    }

    public Node partitionListWithX(Node head, int x) {
        Node lessDummy = new Node(-1);   // Dummy head for < x list
        Node greaterDummy = new Node(-1); // Dummy head for >= x list

        Node lessTail = lessDummy;
        Node greaterTail = greaterDummy;

        Node current = head;
        while (current != null) {
            int num = (int) current.data;
            if (num < x) {
                lessTail.next = current;
                lessTail = current;
            } else {
                greaterTail.next = current;
                greaterTail = current;
            }
            current = current.next;
        }

// Join the two lists
        lessTail.next = greaterDummy.next;
        greaterTail.next = null;

// New head is the start of less list
        return lessDummy.next;

    }

}
