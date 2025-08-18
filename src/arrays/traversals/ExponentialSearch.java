package arrays.traversals;
import java.util.Arrays;
/*Exponential Search

🔹 Works on unbounded/infinite sorted arrays.

Intuition:

Find range by doubling index (2^i) until exceeding target.

Then apply binary search in that range.
⏱️ Complexity: O(log n)*/
public class ExponentialSearch {
    static int exponentialSearch(int[] arr, int x) {
        if (arr[0] == x) return 0;

        int i = 1;
        while (i < arr.length && arr[i] <= x) i = i * 2;

        return Arrays.binarySearch(arr, i / 2, Math.min(i, arr.length), x);
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,10,40,50,60,70};
        System.out.println(exponentialSearch(arr, 40)); // 4
    }
}
