package slidingwindow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NegativeinEveryWindow {

    public static List<Integer> findFirstNegativeInWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> negatives = new LinkedList<>();

        // Process the first window
        for (int i = 0; i < k; i++) {
            if (nums[i] < 0) {
                negatives.add(i); // Store the index of the negative number
            }
        }

        // Add the first negative of the first window to the result
        if (!negatives.isEmpty()) {
            result.add(nums[negatives.peek()]);
        } else {
            result.add(0); // No negative number in the first window
        }

        // Process the remaining windows
        for (int i = k; i < nums.length; i++) {
            // Remove indices that are out of the current window
            while (!negatives.isEmpty() && negatives.peek() < i - k + 1) {
                negatives.poll();
            }

            // Add the current number if it's negative
            if (nums[i] < 0) {
                negatives.add(i);
            }

            // Add the first negative number of the current window to the result
            if (!negatives.isEmpty()) {
                result.add(nums[negatives.peek()]);
            } else {
                result.add(0); // No negative number in the current window
            }
        }

        return result;
    }
}
