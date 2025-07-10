package linkedlist.july_10;

import linkedlist.july_09.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice<T> {


    public boolean detectLoop(Node head) {
        Node temp = head;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;


    }

    public boolean detectLoopNaiveApproach(Node head) {
        Set<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (set.contains(temp)) return true;
            set.add(temp);
            temp = temp.next;
        }
        return false;

    }

    public Node findStartingNodeinLoop(Node head) {
        Node temp = head, fast = head, slow = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }

    public Node removeLoop(Node head) {
        Node slow = head, fast = head;

        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;

        // find the first node of the loop
        //if the slow is the first node

        slow = head;
        if (slow == fast) {
            while (fast.next != slow) {
                fast = fast.next;

            }
            fast.next = null;
            return slow;
        }

        Node prev = null;
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;

        }
        prev.next = null;
        return slow;
    }

    // sort 0 1 2
    public Node<T> sort012(Node<T> head) {

        int countOnes = 0, countTwos = 0, countZeros = 0;
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(0)) {
                countZeros++;
            } else if (temp.data.equals(1)) {
                countOnes++;
            } else if (temp.data.equals(2)) {
                countTwos++;
            }
            temp = temp.next;
        }
        // finally create a single node
        Node result = new Node(-1);
        Node prev = result;
        while (countZeros > 0) {
            Node zero = new Node(0);
            result.next = zero;
            result = result.next;
            countZeros--;
        }
        while (countOnes > 0) {
            Node one = new Node<>(1);
            result.next = one;
            result = result.next;
            countOnes--;
        }
        while (countTwos > 0) {
            Node two = new Node(2);
            result.next = two;
            result = result.next;
            countTwos--;

        }
        return prev.next;
    }

    public Node sort012InPlae(Node head) {
        Node temp = head;
        int zeros = 0, onex = 0, twos = 0;
        while (temp != null) {
            if (temp.data.equals(0)) zeros++;
            else if (temp.data.equals(1)) onex++;
            else if (temp.data.equals(2)) twos++;

            temp = temp.next;
        }
        temp = head;
        while (zeros-- > 0) {
            temp.data = 0;
            temp = temp.next;
        }
        while (onex-- > 0) {
            temp.data = 1;
            temp = temp.next;
        }
        while (twos-- > 0) {
            temp.data = 2;
            temp = temp.next;
        }


        return head;
    }

    public Node<Integer> segregateOddEven(Node<Integer> head) {
        if (head == null || head.next == null) return head;

        Node<Integer> oddHead = new Node<>(-1), oddTail = oddHead;
        Node<Integer> evenHead = new Node<>(-1), evenTail = evenHead;

        Node<Integer> temp = head;
        while (temp != null) {
            if (temp.data % 2 != 0) {
                oddTail.next = temp;
                oddTail = oddTail.next;
            } else {
                evenTail.next = temp;
                evenTail = evenTail.next;
            }
            temp = temp.next;
        }

        // Important: break last even node's next to avoid loop
        evenTail.next = null;

        // Combine odd + even
        oddTail.next = evenHead.next;

        return oddHead.next;
    }

    public Node pairwiseSwap(Node head) {
        if (head == null || head.next == null) return head;
        Node dummyhead = new Node(-1), prev = dummyhead, curr = head;
        dummyhead.next = head;
        while (curr != null && curr.next != null) {
            Node first = curr;
            Node second = curr.next;
            Node nextPair = second.next;

            // swap
            prev.next = second;
            second.next = first;
            first.next = nextPair;

            // move ptr
            prev = first;
            curr = nextPair;


        }
        return dummyhead.next;
    }

    public Node deleteEveryOtherNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = head;

        while (temp != null && temp.next != null) {
            temp.next = temp.next.next;  // delete the next node
            temp = temp.next;            // move to next surviving node
        }

        return head;
    }

    public void deleteEvery3rdNode(Node head) {
        if (head == null) return;

        int count = 1;
        Node temp = head;

        while (temp != null && temp.next != null) {
            count++;
            if (count == 3) {
                temp.next = temp.next.next; // delete the 3rd node
                count = 1;                  // reset count after deletion
            } else {
                temp = temp.next;          // only move forward if not deleting
            }
        }
    }

    //    ✅ 1. Move Last Node to Front
//Input: 1 → 2 → 3 → 4 → 5
//Output: 5 → 1 → 2 → 3 → 4
    public Node moveLastNodetoFront(Node head) {
        Node temp = head, prev = null;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }

        prev.next = null;
        temp.next = head;

        return temp;

    }

    public Node mergeTwoSortedLists(Node head1, Node head2) {
        Node temp1 = head1, temp2 = head2;
        Node dummy = new Node(-1);
        Node result = dummy;

        while (temp1 != null && temp2 != null) {
            if ((int) temp1.data < (int) temp2.data) {
                result.next = temp1;
                temp1 = temp1.next;
            } else if ((int) temp1.data > (int) temp2.data) {
                result.next = temp2;
                temp2 = temp2.next;
            } else {
                // both values equal: add one and move both
                result.next = temp1;
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            result = result.next;
        }

        // attach remaining nodes
        if (temp1 != null) result.next = temp1;
        if (temp2 != null) result.next = temp2;

        return dummy.next;
    }

    public Node mergeSortinLL(Node head) {
        if (head == null || head.next == null) return head; // base case

        // Step 1: Split into two halves
        Node middle = getMiddle(head);
        Node secondHalf = middle.next;
        middle.next = null; // break the list

        // Step 2: Recursively sort both halves
        Node left = mergeSortinLL(head);
        Node right = mergeSortinLL(secondHalf);

        // Step 3: Merge the two sorted halves
        return mergeTwoSortedLists(left, right);
    }

    // Helper to find middle (slow/fast pointer)
    private Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void removeDuplicatesfromSortedList(Node head) {
        Node temp = head;
        while (temp != null && temp.next != null) {
            Node next = temp.next;
            if ((int) temp.data == (int) next.data) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
    }

    public Node removeAllDuplicatesIncludingOriginals(Node head) {
        if (head == null) return null;

        Map<Integer, Integer> freq = new HashMap<>();
        Node temp = head;

        // Pass 1: Count frequencies
        while (temp != null) {
            freq.put((int) temp.data, freq.getOrDefault((int) temp.data, 0) + 1);
            temp = temp.next;
        }

        // Pass 2: Filter nodes with freq == 1
        Node dummy = new Node(-1);
        dummy.next = head;
        Node prev = dummy;
        temp = head;

        while (temp != null) {
            if (freq.get((int) temp.data) > 1) {
                prev.next = temp.next; // skip it
            } else {
                prev = temp;
            }
            temp = temp.next;
        }

        return dummy.next;
    }

    public Node reverse(Node head) {

        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }

    public Node add1toLL(Node head) {
        if (head == null) return new Node(1);

        head = new Practice<>().reverse(head);

        Node temp = head;
        int carry = 1;
        while (temp != null && carry > 0) {
            int sum = (int) temp.data + carry;
            temp.data = sum % 10;
            carry = sum / 10;
            if (temp.next == null && carry > 0) {
                temp.next = new Node(carry);
                carry = 0;
            }

            temp = temp.next;

        }
        return reverse(head);
    }

    // ➕ Add two linked lists
    public Node addNumbertoLL(Node head1, Node head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);

        Node dummy = new Node(-1);
        Node current = dummy;
        int carry = 0;

        Node temp1 = head1, temp2 = head2;

        while (temp1 != null || temp2 != null || carry > 0) {
            int val1 = (temp1 != null) ? (int) temp1.data : 0;
            int val2 = (temp2 != null) ? (int) temp2.data : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            current.next = new Node(sum % 10);
            current = current.next;

            if (temp1 != null) temp1 = temp1.next;
            if (temp2 != null) temp2 = temp2.next;
        }

        return reverse(dummy.next);
    }

    // 📏 Get length of linked list
    private int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    // 🔍 Compare two linked lists: returns +ve if l1 > l2, 0 if equal, -ve if l1 < l2
    private int compare(Node l1, Node l2) {
        int len1 = getLength(l1), len2 = getLength(l2);
        if (len1 != len2) return len1 - len2;

        while (l1 != null && l2 != null) {
            if (l1.data != l2.data) return (int) l1.data - (int) l2.data;
            l1 = l1.next;
            l2 = l2.next;
        }
        return 0;
    }

    // 🔧 Remove leading zeros
    private Node removeLeadingZeros(Node head) {
        while (head != null && head.data.equals(0)) {
            head = head.next;
        }
        return (head == null) ? new Node(0) : head;
    }

    // 🎯 Subtract smaller number from larger
    public Node subtract(Node l1, Node l2) {
        if (l1 == null) return null;
        if (l2 == null) return l1;

        // Ensure l1 is greater than l2
        if (compare(l1, l2) < 0) {
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }

        // Reverse both lists
        l1 = reverse(l1);
        l2 = reverse(l2);

        Node dummy = new Node(-1), tail = dummy;
        int borrow = 0;

        while (l1 != null) {
            int d1 = (int) l1.data;
            int d2 = (l2 != null) ? (int) l2.data : 0;

            int sub = d1 - d2 - borrow;
            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            tail.next = new Node(sub);
            tail = tail.next;

            l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Reverse result and remove leading zeros
        return removeLeadingZeros(reverse(dummy.next));
    }

    // Function to reorder the list
    public void reorderList(Node head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node second = reverseList(slow.next);
        slow.next = null; // Break the list

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

    // Helper function to reverse a list
    private Node reverseList(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}




