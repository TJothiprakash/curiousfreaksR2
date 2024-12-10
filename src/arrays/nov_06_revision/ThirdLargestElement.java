package arrays.nov_06_revision;

import java.util.PriorityQueue;

public class ThirdLargestElement {
    public static int thirdLargest(int[] arr) {
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
            if (pq.size() > 3) {
                pq.poll();
            }
        }

        if (pq.size() < 3) {
            return -1;
        }

        return pq.peek();
    }
}
