package heaps;

import java.util.*;

public class KthSmallestSum {

    // Method to find the k-th smallest sum
    public static int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Min-heap to store the sum and indices of the elements picked from each row
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.sum));

        // Array to store the initial indices (starting with the smallest element in each row)
        int[] indices = new int[m];
        int initialSum = 0;

        // Initial sum of the smallest elements (first element in each row)
        for (int i = 0; i < m; i++) {
            initialSum += mat[i][0];
        }

        // Push the initial sum and indices into the heap
        minHeap.offer(new Pair(initialSum, indices));

        // Set to avoid duplicate combinations of indices
        Set<String> seen = new HashSet<>();
        seen.add(Arrays.toString(indices));

        // Extract the smallest sum k-1 times
        for (int i = 0; i < k - 1; i++) {
            Pair current = minHeap.poll();
            int sum = current.sum;
            int[] currentIndices = current.indices;

            // Generate the next possible sums by incrementing one index at a time
            for (int j = 0; j < m; j++) {
                // Create a new set of indices by incrementing the j-th row index
                if (currentIndices[j] + 1 < n) {
                    int[] newIndices = Arrays.copyOf(currentIndices, m);
                    newIndices[j] = currentIndices[j] + 1;

                    String newIndex = Arrays.toString(newIndices);
                    if (!seen.contains(newIndex)) {
                        seen.add(newIndex);

                        // Calculate the new sum by replacing one element from the row
                        int newSum = sum - mat[j][currentIndices[j]] + mat[j][newIndices[j]];
                        minHeap.offer(new Pair(newSum, newIndices));
                    }
                }
            }
        }

        // Return the k-th smallest sum
        return minHeap.peek().sum;
    }

    public static void main(String[] args) {
        int[][] mat1 = {
                {1, 3, 11},
                {2, 4, 6}
        };
        int k1 = 9;
        System.out.println("Kth smallest sum: " + kthSmallest(mat1, k1)); // Output: 7

        int[][] mat2 = {
                {1, 10, 10},
                {1, 4, 5},
                {2, 3, 6}
        };
        int k2 = 7;
        System.out.println("Kth smallest sum: " + kthSmallest(mat2, k2)); // Output: 9
    }

    // Pair class to store both sum and the indices of elements chosen
    static class Pair {
        int sum;
        int[] indices;

        Pair(int sum, int[] indices) {
            this.sum = sum;
            this.indices = indices;
        }
    }
}
/*import java.util.*;

public class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;

        // Min-heap (priority queue) to store the current sum and row indices
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize the heap with the sum of the first elements in each row
        int initialSum = 0;
        int[] indices = new int[m]; // indices of the elements from each row
        for (int i = 0; i < m; i++) {
            initialSum += mat[i][0];
        }
        pq.add(new int[] {initialSum, 0, 0}); // sum, row, column index

        // Keep track of visited indices to avoid duplicates
        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(indices));

        while (k > 0) {
            int[] current = pq.poll(); // Get the current smallest sum
            int currentSum = current[0];
            int[] currentIndices = Arrays.copyOfRange(current, 1, current.length);

            // If we have reached the kth smallest sum, return it
            if (--k == 0) {
                return currentSum;
            }

            // Try moving to the next element in each row (only one move per row)
            for (int i = 0; i < m; i++) {
                if (currentIndices[i] + 1 < n) {
                    int[] nextIndices = Arrays.copyOf(currentIndices, m);
                    nextIndices[i]++; // Move to the next element in this row

                    // Avoid revisiting the same indices
                    if (!visited.contains(Arrays.toString(nextIndices))) {
                        visited.add(Arrays.toString(nextIndices));

                        // Compute the new sum and add it to the heap
                        int newSum = currentSum - mat[i][currentIndices[i]] + mat[i][nextIndices[i]];
                        pq.add(new int[] {newSum, nextIndices[0], nextIndices[1]});
                    }
                }
            }
        }
        return -1;
    }
}
*/