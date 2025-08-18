package arrays.traversals;

/*Interpolation Search

🔹 Best for uniformly distributed sorted arrays.
Improvement over binary search by guessing position with formula:
pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low])
⏱️ Complexity:
Best: O(log log n)
Worst: O(n) (if distribution uneven)*/

public class InterpolationSearch {
    static int interpolationSearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;

        while (low <= high && x >= arr[low] && x <= arr[high]) {
            int pos = low + (int)(((double)(high - low) / (arr[high] - arr[low])) * (x - arr[low]));

            if (arr[pos] == x) return pos;
            if (arr[pos] < x) low = pos + 1;
            else high = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10,20,30,40,50,60,70};
        System.out.println(interpolationSearch(arr, 40)); // 3
    }
}
