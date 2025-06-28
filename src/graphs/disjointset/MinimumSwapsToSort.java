package graphs.disjointset;
/*
PROBLEM:
Given a distinct integer array, return the minimum number of swaps to sort it in strictly increasing order.

INTUITION:
- Model the index change as a graph.
- Find cycles in this graph.
- Each cycle of size k needs (k - 1) swaps.

DRY RUN:
arr = [10, 19, 6, 3, 5]
sorted = [3, 5, 6, 10, 19]
position map: original index → target index
cycle1: 0 -> 3 -> 0 ⇒ 2 nodes ⇒ 1 swap
cycle2: 1 -> 4 -> 1 ⇒ 2 nodes ⇒ 1 swap
=> Total = 2 swaps

TIME & SPACE:
- Time: O(N log N)
- Space: O(N)
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumSwapsToSort {

    public static int minSwaps(int[] arr) {
        int n = arr.length;

        // Pair: value and original index
        List<Pair> arrPos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrPos.add(new Pair(arr[i], i));
        }

        // Sort by value
        arrPos.sort(Comparator.comparingInt(p -> p.value));

        // Visited array
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // Already visited or already in correct position
            if (visited[i] || arrPos.get(i).index == i) continue;

            // Compute the size of the cycle
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arrPos.get(j).index;
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    static class Pair {
        int value, index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 8, 5, 4};
        int[] arr2 = {10, 19, 6, 3, 5};
        int[] arr3 = {1, 3, 4, 5, 6};

        System.out.println("Output 1: " + minSwaps(arr1)); // 1
        System.out.println("Output 2: " + minSwaps(arr2)); // 2
        System.out.println("Output 3: " + minSwaps(arr3)); // 0
    }
}
