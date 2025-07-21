package graphs.july_21;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumSwapsToSort {

    static class Pair {
        int value, originalIndex;

        Pair(int value, int index) {
            this.value = value;
            this.originalIndex = index;
        }
    }

    public static int minSwaps(int[] arr) {
        int n = arr.length;
        Pair[] pairs = new Pair[n];

        // Step 1: Pair each element with its original index
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(arr[i], i);
        }

        // Step 2: Sort pairs by value
        Arrays.sort(pairs, Comparator.comparingInt(p -> p.value));

        // Step 3: Cycle detection using visited array
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // already in correct place or visited
            if (visited[i] || pairs[i].originalIndex == i) {
                continue;
            }

            // count size of cycle
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = pairs[j].originalIndex;
                cycleSize++;
            }

            // swap count for this cycle = size - 1
            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 8, 5, 4};
        int[] arr2 = {10, 19, 6, 3, 5};
        int[] arr3 = {1, 3, 4, 5, 6};

        System.out.println("Minimum swaps (arr1): " + minSwaps(arr1)); // 1
        System.out.println("Minimum swaps (arr2): " + minSwaps(arr2)); // 2
        System.out.println("Minimum swaps (arr3): " + minSwaps(arr3)); // 0
    }
}
