package heaps.july_14;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/*| Goal                                             | Heap Strategy                                               | How to “grab” the result                                                                                                  |
| ------------------------------------------------ | ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| **Get the k largest values (list)**              | Build a max‑heap (or a min‑heap of size k).                 | Loop `k` times and `poll()`; collect into a list (reverse later if you used min‑heap).                                    |
| **Get only the k‑th largest value (single int)** | Maintain a **min‑heap of size k** while scanning the array. | After processing every element, just look at `minHeap.peek()` – that root *is* the k‑th largest. No extra polling needed. |
*/
public class KthSmallandLargets {
    public static void main(String[] args) {

    }

    /*public Queue<Integer> kthLargest(int arr[]) {
    Queue<Integer> maxheap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i : arr) {
        maxheap.add((i));
    }
    return maxheap; // <- returns all elements, not just top k
}
*/

    public Queue<Integer> kthSmallest(int arr[]) {
        Queue<Integer> minheap = new PriorityQueue<>();
        for (int i : arr) {
            minheap.add(i);
        }

        return minheap;

    }

    public Queue<Integer> kthLargest(int arr[]) {
        Queue<Integer> maxheap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : arr) {
            maxheap.add((i));
        }
        return maxheap;

    }
}
