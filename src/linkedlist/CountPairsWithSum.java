package linkedlist;

import java.util.HashSet;


public class CountPairsWithSum {
//    //    O(n)O(n)
    public static int countPairs(Node head1, Node head2, int x) {
        // Create a hash set to store elements of the first linked list
        HashSet<Integer> set = new HashSet<>();
        Node temp = head1;

        // Add all elements from head1 to the set
        while (temp != null) {
            set.add(temp.data);
            temp = temp.next;
        }

        // Traverse the second list and count valid pairs
        int count = 0;
        temp = head2;
        while (temp != null) {
            // Check if the complementary value exists in the set
            if (set.contains(x - temp.data)) {
                count++;
            }
            temp = temp.next;
        }

        return count;
    }

    public static void main(String[] args) {
        // Example 1
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);

        Node head2 = new Node(11);
        head2.next = new Node(12);
        head2.next.next = new Node(13);

        int x = 15;
        System.out.println("Count of pairs: " + countPairs(head1, head2, x)); // Output: 3

        // Example 2
        Node head3 = new Node(7);
        head3.next = new Node(5);
        head3.next.next = new Node(1);
        head3.next.next.next = new Node(3);

        Node head4 = new Node(3);
        head4.next = new Node(5);
        head4.next.next = new Node(2);
        head4.next.next.next = new Node(8);

        x = 10;
        System.out.println("Count of pairs: " + countPairs(head3, head4, x)); // Output: 2
    }
}

