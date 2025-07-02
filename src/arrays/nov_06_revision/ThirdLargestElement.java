package arrays.nov_06_revision;

import java.util.PriorityQueue;

public class ThirdLargestElement {
    public static int thirdLargest(int[] arr) {
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
//O(n) O(1)
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
//| Complexity Type | Your Comment | Corrected Verdict | Explanation                   |
//| --------------- | ------------ | ----------------- | ----------------------------- |
//| Time            | `O(n)`       | ✅ Correct         | Heap ops are O(1) since k=3   |
//| Space           | `O(1)`       | ✅ Correct         | Heap holds at most 4 elements |