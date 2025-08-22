package slidingwindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(solution.slidingWindowMaximum(nums, k));
        // Output: [3, 3, 5, 5, 6, 7]
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(3);
        System.out.println(deque.peekFirst());
    }
//O(n)O(k)
    public List<Integer> slidingWindowMaximum(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // Remove indices that are out of the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements from the back of the deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current index to the deque
            deque.addLast(i);

            // Add the maximum for the current window to the result
            if (i >= k - 1) {  // Ensure the first window is complete
                result.add(nums[deque.peekFirst()]);
            }
        }

        return result;
    }
}

