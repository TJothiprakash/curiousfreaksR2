package linkedlist.aug_21;



import linkedlist.aug_20.Node;
import java.util.HashSet;

public class Loops {

    boolean detectLoop(Node root) {
        Node fast = root, slow = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static int lengthOfLoop(Node head) {
        Node loopStart = findLoopStart(head);
        if (loopStart == null) return 0; // no loop

        int count = 1;
        Node temp = loopStart.next;
        while (temp != loopStart) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    static Node findLoopStart(Node head) {
        Node slow = head, fast = head;
        boolean hasLoop = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) return null;

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast; // loop entry point
    }

    Node removeLoop(Node root) {

        Node fast = root, slow = root;
        boolean hasLoop = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) return null;
        fast = root;
        Node prev = null;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;

        }
        // Now 'slow' &&  'fast') is at loop start
        Node loopStart = slow;

        // Phase 3: Find last node in loop and break it
        Node ptr = loopStart;
        while (ptr.next != loopStart) {
            ptr = ptr.next;
        }
        ptr.next = null; // remove loop

        return root;
//        return  prev;
    }
}