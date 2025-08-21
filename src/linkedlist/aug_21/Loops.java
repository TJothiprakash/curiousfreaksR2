package linkedlist.aug_21;

import linkedlist.aug_20.Node;

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

    Node findStartintPointofLoop(Node root) {

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
        if(!hasLoop) return null;
        fast = root;
        while (fast != slow){
            fast = fast.next;
            slow= slow.next;
        }
        return fast;
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
        if(!hasLoop) return null;
        fast = root;
        Node prev = null;
        while (fast != slow){
            fast = fast.next;
            slow= slow.next;

        }
        // Now 'slow' (or 'fast') is at loop start
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