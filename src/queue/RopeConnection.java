package queue;

import java.util.PriorityQueue;

public class RopeConnection {

    public static int connectRopes(int[] arr) {
        // Edge case: if there is only one rope, no cost to connect
        if (arr.length == 1) return 0;

        // Create a priority queue (min-heap)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all rope lengths to the heap
        for (int length : arr) {
            minHeap.add(length);
        }

        int totalCost = 0;

        // While there is more than one rope to connect
        while (minHeap.size() > 1) {
            // Extract the two smallest ropes
            int first = minHeap.poll();
            int second = minHeap.poll();

            // Cost to connect the two ropes
            int cost = first + second;
            totalCost += cost;

            // Add the new rope back into the heap
            minHeap.add(cost);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // Example test cases
        int[] arr1 = {4, 3, 2, 6};
        System.out.println(connectRopes(arr1));  // Output: 29

        int[] arr2 = {4, 2, 7, 6, 9};
        System.out.println(connectRopes(arr2));  // Output: 62

        int[] arr3 = {10};
        System.out.println(connectRopes(arr3));  // Output: 0
    }
}

