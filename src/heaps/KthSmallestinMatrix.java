package heaps;

import java.util.PriorityQueue;

public class KthSmallestinMatrix {


    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Min-heap: store elements as a pair of (value, row, col)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Step 1: Push the first element of each row into the heap
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{matrix[i][0], i, 0}); // (value, row, col)
        }

        // Step 2: Pop elements from the heap and push the next element from the same row
        int count = 0;
        while (count < k) {
            int[] current = minHeap.poll(); // Pop the smallest element
            int value = current[0];
            int row = current[1];
            int col = current[2];

            // Step 3: If there's another element in the same row, add it to the heap
            if (col + 1 < n) {
                minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }

            // Increment the count
            count++;
            if (count == k) {
                return value; // The kth smallest element
            }
        }

        return -1; // This line will never be reached if the input is valid
    }
}

