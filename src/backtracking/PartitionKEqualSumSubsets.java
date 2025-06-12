package backtracking;
/*Given an integer array arr[ ] and an integer k, the task is to check if the array arr[ ]
could be divided into k non-empty subsets with equal sum of elements.
Note: All elements of this array should be part of exactly one partition.

Examples:

Input: arr[] = [2, 1, 4, 5, 6], k = 3
Output: true
Explanation: We can divide above array into 3 parts with equal sum as (2, 4), (1, 5), (6)
Input: arr[] = [2, 1, 5, 5, 6], k = 3
Output: false
Explanation: It is not possible to divide above array into 3 parts with equal sum.
Constraints:
1 ≤ k ≤ arr.size() ≤ 10
1 ≤ arr[i] ≤ 100

Key Observations:
If sum(arr) % k != 0, return false immediately.

Backtracking is used to try to assign each element into one of the k subsets.

We use a boolean array visited[] to mark used elements.L̥*/
public class PartitionKEqualSumSubsets {

    public static boolean canPartitionKSubsets(int[] arr, int k) {
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        if (k == 0 || totalSum % k != 0) return false;

        int target = totalSum / k;
        boolean[] visited = new boolean[arr.length];

        return backtrack(arr, visited, 0, k, 0, target);
    }

    private static boolean backtrack(int[] arr, boolean[] visited, int startIndex, int k, int currentSum, int target) {
        // Base case: only 1 subset left, so it's guaranteed to be valid
        if (k == 1) return true;

        // Subset filled correctly, try to fill next subset
        if (currentSum == target) {
            return backtrack(arr, visited, 0, k - 1, 0, target);
        }

        for (int i = startIndex; i < arr.length; i++) {
            if (!visited[i] && currentSum + arr[i] <= target) {
                visited[i] = true;
                if (backtrack(arr, visited, i + 1, k, currentSum + arr[i], target)) {
                    return true;
                }
                visited[i] = false; // backtrack
            }
        }
        return false;
    }

    // Test driver
    public static void main(String[] args) {
        int[] arr1 = {2, 1, 4, 5, 6};
        int[] arr2 = {2, 1, 5, 5, 6};

        System.out.println(canPartitionKSubsets(arr1, 3)); // true
        System.out.println(canPartitionKSubsets(arr2, 3)); // false
    }
}
