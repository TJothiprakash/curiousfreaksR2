package linkedlist.aug_21;

import graphs.july_19.Pair;
import linkedlist.aug_20.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PairSum {

    List<List<Integer>> findValidPairs(Node head1, Node head2, int target) {
        List<List<Integer>> pairs = new ArrayList<>();

        Node t1 = head1, t2 = head2;
        int l1 = len(head1);
        int l2 = len(head2);

        HashSet<Integer> hashSet = new HashSet<>();
//        Pair<Integer,Integer> pairs = new Pair<>();
        List<List<Integer>> list = new ArrayList<>();

        while (head1 != null) {
            hashSet.add(head1.data);
            head1 = head1.next;

        }

        while (head2 != null) {
            if (hashSet.contains(target - head2.data)) {
                List<Integer> temp = new ArrayList<>();
                temp.add(head2.data);
                temp.add(target - head2.data);
                list.add(temp);
            }
            head2 = head2.next;
        }

        return list;

    }

    private int len(Node head1) {
        int l = 0;
        while (head1 != null) {
            l++;
            head1 = head1.next;
        }

        return l;

    }


 // Main function to reverse in groups of k
        public static Node reverseInGroup(Node head, int k) {
            if (head == null || k <= 1) return head;

            Node dummy = new Node(0);
            dummy.next = head;
            Node prevGroupEnd = dummy;

            while (true) {
                // Find the kth node from prevGroupEnd
                Node kth = getKthNode(prevGroupEnd, k);
                if (kth == null) break; // Not enough nodes left

                Node groupStart = prevGroupEnd.next;
                Node nextGroupStart = kth.next;

                // Reverse this group
                reverse(groupStart, kth);

                // Connect
                prevGroupEnd.next = kth;
                groupStart.next = nextGroupStart;

                // Move prevGroupEnd to end of this group
                prevGroupEnd = groupStart;
            }

            return dummy.next;
        }

        // Utility: Get the kth node from current
        private static Node getKthNode(Node curr, int k) {
            while (curr != null && k > 0) {
                curr = curr.next;
                k--;
            }
            return curr;
        }

        // Utility: Reverse nodes between start and end
        private static void reverse(Node start, Node end) {
            Node prev = null, curr = start, stop = end.next;
            while (curr != stop) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
        }
}
