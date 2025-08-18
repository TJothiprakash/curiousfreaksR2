package arrays.traversals;

/*
Jump Search

🔹 Works on sorted array.
We jump by √n steps and then do linear search inside the block.

Intuition:

Similar to linear search but reduces comparisons by skipping elements.

Best if array is uniformly distributed and sorted.

Dry Run (arr = [1,3,5,7,9,11], target=7):

Jump by √6 ≈ 2 → indices: 0→2→4→6

Stop at index 4 (value=9, exceeds target)

Linear search in [2,4] → found 7 at index 3


⏱️ Complexity:
Time: O(√n)
Space: O(1)*/
public class JumpSearch {
    static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return -1;
        }

        for (int i = prev; i < Math.min(step, n); i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        System.out.println(jumpSearch(arr, 7)); // 3
    }
}
