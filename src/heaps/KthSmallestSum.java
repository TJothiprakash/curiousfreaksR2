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
