package slidingwindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxValueinSubArray {
//O(n *k) O(n)
    public static List<Integer> findMaxValue(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();

        // Validate edge cases
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        // Find the maximum value for the first window
        int firstMax = helperFunction(0, k, arr);
        result.add(firstMax);

        // Slide the window
        for (int start = 1; start <= arr.length - k; start++) {
            // Recalculate the max for the current window
            int max = helperFunction(start, start + k, arr);
            result.add(max);
        }

        return result;
    }

    // Helper function to calculate the maximum in a specific window
    private static int helperFunction(int start, int end, int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }


    //efficient approach using queue
//O(n)O(k)
    public static List<Integer> findMaxValueusingQueue(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>(); // Stores indices of array elements

        // Process the first window
        for (int i = 0; i < k; i++) {
            // Remove elements from the deque that are smaller than the current element
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        // Add the maximum of the first window to the result
        result.add(arr[deque.peekFirst()]);

        // Process the remaining windows
        for (int i = k; i < arr.length; i++) {
            // Remove elements that are out of the current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove elements from the deque that are smaller than the current element
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.addLast(i);

            // Add the maximum of the current window to the result
            result.add(arr[deque.peekFirst()]);
        }

        return result;
    }
}

