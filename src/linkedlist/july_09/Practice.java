package linkedlist.july_09;


import java.util.*;

public class Practice<T> {

    public Node reverseI(Node root) {
        if (root == null) {
            System.out.println("list is empty");
            return root;
        }
        Node current = root;
        Node prevNode = null;
        while (current != null) {
            Node nextNode = current.next;
            current.next = prevNode;
            prevNode = current;
            current = nextNode;
        }
        return prevNode;

    }

    public boolean isPalindrome(Node<T> root) {
        Node slow = root;
        Node fast = root;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        Node<T> reversedSecondHalf = reverseI(slow);
//        Node<T> copySecondHalf = reversedSecondHalf;


        Node temp = root;
        while (reversedSecondHalf != null) {
            if (!temp.data.equals(reversedSecondHalf.data)) {
                return false;
            }
            temp = temp.next;
            reversedSecondHalf = reversedSecondHalf.next;

        }
        return true;
    }

    public Node<T> middelElement(Node<T> root) {
        if (root == null) {
            System.out.println("the list is empty");
            throw new NoSuchElementException("List is empty, no middle element.");
        }
//        Node<T> temp = root;
        Node<T> slow = root;
        Node<T> fast = root;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // find interseciotn pt of Y shaped list
    public Node<T> intersection(Node<T> head1, Node<T> head2) {
        if (head1 == null || head2 == null) return null;

        Node a = head1;
        Node b = head2;
        while (a != b) {
            a = a == null ? head2 : a.next;
            b = b == null ? head1 : b.next;
        }
        return a;
    }

    // union & intersection of two lists
    public Node<T> Union_Intersection(Node<T> head1, Node<T> head2) {
        Set<T> set = new HashSet<T>();
        Node<T> temp1 = head1;
        Node<T> temp2 = head2;
        while (temp1 != null) {
            set.add(temp1.data);
            temp1 = temp1.next;
        }


        while (temp2 != null) {
            set.add(temp2.data);
            temp2 = temp2.next;
        }
        List<T> list = new ArrayList<T>(set);
        Collections.sort((List<Comparable>) list); // cast only if T is Comparable

        Node<T> root = new Node(-1);
        Node<T> temp = root;
        for (T l : list) {
            temp.next = new Node(l);
            temp = temp.next;
        }
        return root.next;
    }

    // delete given node wihtout head ptr
    public void deleteGivenNode(Node<T> del_node) {
        if (del_node == null || del_node.next == null) {
            throw new IllegalArgumentException("Cannot delete the last node or null node");
        }
        del_node.data = del_node.next.data;
        del_node.next = del_node.next.next;
    }

    public Node<T> groupReverse(Node<T> head, int k) {
        if (head == null || k <= 1) return head;

        Node<T> dummy = new Node<>(null); // dummy simplifies head handling
        dummy.next = head;

        Node<T> prevGroupTail = dummy;
        Node<T> current = head;

        while (current != null) {
            Node<T> groupStart = current;
            int count = 0;

            // Check if at least k nodes are present
            Node<T> temp = current;
            while (temp != null && count < k) {
                temp = temp.next;
                count++;
            }

            if (count < k) {
                // skip reversing the last partial group
                prevGroupTail.next = current;
                break;
            }

            // Save the pointer to next group
            Node<T> nextGroupStart = getKPlusOneNode(current, k);

            // Reverse k nodes
            Node<T> reversedHead = reverseI(current, k); // your existing method
            prevGroupTail.next = reversedHead;

            // Move prevGroupTail to the end of reversed segment
            prevGroupTail = groupStart;

            // Move current to the next group
            current = nextGroupStart;
        }

        return dummy.next;
    }

    private Node<T> getKPlusOneNode(Node<T> node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }


    private int findLength(Node<T> head) {
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public Node<T> reverseI(Node<T> head, int k) {
        Node<T> prev = null;
        Node<T> current = head;
        int count = 0;

        while (current != null && count < k) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        return prev; // new head of reversed k-group
    }


}

