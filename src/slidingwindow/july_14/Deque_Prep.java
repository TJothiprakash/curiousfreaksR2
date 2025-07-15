package slidingwindow.july_14;

import java.util.Deque;
import java.util.LinkedList;

public class Deque_Prep {
    public static void main(String[] args) {
        Deque<String> dq = new LinkedList<>();

        dq.addLast("L1");  // Back: L1
        dq.addLast("L2");  // Back: L1, L2
        dq.addFirst("F1"); // Front: F1, L1, L2
        dq.addFirst("F2"); // Front: F2, F1, L1, L2

        System.out.println(dq.pollFirst()); // F2
        System.out.println(dq.pollFirst()); // F1
        System.out.println(dq.pollFirst()); // L1
        System.out.println(dq.pollFirst()); // L2

    }
}
