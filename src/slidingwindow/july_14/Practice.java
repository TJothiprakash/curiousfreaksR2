package slidingwindow.july_14;

import java.util.*;

public class Practice {
    public static void main(String[] args) {
        new Practice().firstNegativeinEveryWindow(new int[]{1, 2, -3, 4, 5, -1, 7}, 3);
    }

    public void maxSubArraySum(int arr[], int k) {
        if (arr == null || arr.length < k || k <= 0) {
            return;
        }
        int maxSum = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        maxSum = sum;
        for (int i = k; i < arr.length; i++) {
            sum += arr[i] - arr[i - k];
//            if (sum > maxSum) maxSum = sum;
            maxSum = Math.max(sum, maxSum);
        }
        System.out.println("Max sum of subarray is " + maxSum);
    }

    public void distinctElementinEveryWindow(int arr[], int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }

        result.add(freqMap.size());

        for (int i = k; i < arr.length; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
            freqMap.put(arr[i - k], freqMap.getOrDefault(arr[i - k], 0) - 1);
            if (freqMap.get(arr[i - k]) == 0) freqMap.remove(arr[i - k]);
            result.add(freqMap.size());
        }

    }

    public void firstNegativeinEveryWindow(int arr[], int k) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                deque.addLast(i);
                System.out.println("index i is " + i);
            }
        }

        if (!deque.isEmpty()) {
            res.add(arr[deque.peekFirst()]);
        } else {
            res.add(0);
        }

        for (int i = k; i < arr.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                System.out.println("deque.peekfirst() is " + deque.peekFirst() + "   and i-k is " + (i - k));
                deque.removeFirst();
            }
            if (arr[i] < 0) {
                deque.addLast(i);
            }
            if (!deque.isEmpty()) {
                res.add(arr[deque.peekFirst()]);
            } else {
                res.add(0);
            }

        }
    }

    public void subArrayMaximumSizeK(int arr[], int k) {
        int max = Integer.MIN_VALUE;
        Queue<Integer> maxHeap = new
                PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
            if (arr[i] > max)
                max = arr[i];
        }
        List<Integer> res = new ArrayList<>();
        res.add(maxHeap.peek());

        for (int i = k; i < arr.length; i++) {
            maxHeap.add(arr[i]);
            maxHeap.remove(arr[i - k]);
            res.add(maxHeap.peek());
        }
        System.out.println(res);
    }

    public void subArrayMax(int arr[], int k) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        res.add(arr[deque.peekFirst()]);

        for (int i = k; i < arr.length; i++) {

            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }
            deque.addLast(i);

            res.add(arr[deque.peekFirst()]);

        }
        System.out.println("Max in every window is " + res);
    }

    public void substringsWithKMinusOneDistinctElements(String s, int k) {
        if (s == null || s.length() < k) return;

        Map<Character, Integer> freqMap = new HashMap<>();
        int count = 0;

        // Initial window
        for (int i = 0; i < k; i++) {
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        if (freqMap.size() == k - 1) count++;

        // Sliding the window
        for (int i = k; i < s.length(); i++) {
            char outgoing = s.charAt(i - k);
            char incoming = s.charAt(i);

            // Remove outgoing char
            freqMap.put(outgoing, freqMap.get(outgoing) - 1);
            if (freqMap.get(outgoing) == 0) {
                freqMap.remove(outgoing);
            }

            // Add incoming char
            freqMap.put(incoming, freqMap.getOrDefault(incoming, 0) + 1);

            // Check if the window has exactly k - 1 distinct characters
            if (freqMap.size() == k - 1) {
                count++;
            }
        }

        System.out.println("Count = " + count);
    }


}
