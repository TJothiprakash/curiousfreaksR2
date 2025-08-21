package linkedlist.aug_20;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Practice {
    static Node root = new Node(1);

    static void main() {

        root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);
        Node node8 = new Node(9);
        Node node9 = new Node(10);
        Node node10 = new Node(11);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = null;
        printList(root);
        System.out.println();
        Node ans = deleteNode(1);
        System.out.println("after deletion");
        printList(ans);
    }

    public static Node deleteNode(int data) {
        // Case 1: if head needs to be deleted
        if (root != null && root.data == data) {
            return root.next;
        }

        Node temp = root;
        while (temp != null && temp.next != null) {
            if (temp.next.data == data) {
                temp.next = temp.next.next; // unlink the node
                break; // exit after deleting
            }
            temp = temp.next;
        }
        return root;
    }

    private static void printList(Node root) {
        Node temp = root;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

    }

    public int search(int data) {
        Node temp = root;
        int index = 0;
        while (temp != null) {

            if (temp.data == data) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }

    public static Node reverse(Node root) {
        Node curr = root;
        Node prev = null;
//        Node curr = null;
//        Node next = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }


    public Node reverseUsingRecursion(Node root) {
        if (root == null || root.next == null) {
            return root;
        }

        Node newHead = reverseUsingRecursion(root.next);
        root.next.next = root;
        root.next = null;

        return newHead;
    }

    public boolean isPalindrome(Node root) {
        if (root == null || root.next == null) return true;

        // find the half way
        Node slow = root, fast = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node secondHalf = reverse(slow);

        Node p1 = root;
        Node p2 = secondHalf;
        while (p2 != null) {
            if (p1.data != p2.data) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;

    }

    Node middleElement(Node root) {
        Node slow = root, fast = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    Node intersectionPoint(Node p1, Node p2) {
        Node t1 = p1;
        Node t2 = p2;
        Node intersection = null;
        while (t1 != t2) {
            t1 = (t1 == null) ? p2 : t1.next;
            t2 = (t1 == null) ? p1 : t2.next;
            return t2;
        }
        return null;
    }

    Node Union(Node p1, Node p2) {
        if (p1 == null) return p2;
        if (p2 == null) return p1;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (p1 != null) {
            list1.add(p1.data);
            p1 = p1.next;
        }
        while (p2 != null) {
            list1.add(p2.data);
            p2 = p2.next;
        }
        list1.sort(Comparator.comparingInt(Integer::intValue));
        list2.sort(Comparator.comparingInt(Integer::intValue));
        int l1 = list1.size();
        int l2 = list2.size();
        Node union = null;

        while (l1 > 0 && l2 > 0) {

//            if(list1.get())

        }
        return null;
    }
}

