package heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {
    public static List<Integer> mergeKSortedArrays(int[][] arr, int k) {
        // Min-heap to store the elements (value, row, column)
        PriorityQueue<Element> minHeap = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element a, Element b) {
                return a.value - b.value;  // Min-heap based on the value
            }
        });

        // Step 1: Insert the first element of each row into the heap
        for (int i = 0; i < k; i++) {
            minHeap.offer(new Element(arr[i][0], i, 0));
        }

        List<Integer> result = new ArrayList<>();

        // Step 2: Extract the minimum element from the heap and insert the next element of the same row
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            // If there's another element in the same row, add it to the heap
            if (current.col + 1 < k) {
                minHeap.offer(new Element(arr[current.row][current.col + 1], current.row, current.col + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int k = 3;

        List<Integer> result = mergeKSortedArrays(arr, k);
        System.out.println(result); // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    static class Element {
        int value;
        int row;
        int col;

        Element(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }
}
