package queue;

import java.util.PriorityQueue;

// nearly sorted array (priority queue based qn)
public class KSortedArray {
// O(n log n)O(n)
    public static void sortArray(int[] arr, int k) {
        // Create a min-heap (priority queue)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Insert first k+1 elements into the heap
        for (int i = 0; i <= k; i++) {
            minHeap.add(arr[i]);
        }

        int index = 0;

        // Process the rest of the elements in the array
        for (int i = k + 1; i < arr.length; i++) {
            // Extract the minimum element from the heap and place it at the correct position
            arr[index++] = minHeap.poll();
            // Insert the next element into the heap
            minHeap.add(arr[i]);
        }

        // Extract the remaining elements from the heap
        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        // Example test case 1
        int[] arr1 = {6, 5, 3, 2, 8, 10, 9};
        int k1 = 3;
        sortArray(arr1, k1);
        for (int num : arr1) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: [2, 3, 5, 6, 8, 9, 10]

        // Example test case 2
        int[] arr2 = {1, 4, 5, 2, 3, 6, 7, 8, 9, 10};
        int k2 = 2;
        sortArray(arr2, k2);
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
}

