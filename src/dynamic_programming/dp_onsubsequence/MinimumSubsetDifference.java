package dynamic_programming.dp_onsubsequence;
import java.util.Arrays;
/*Given an array arr[]  containing non-negative integers, the task is to divide it
into two sets set1 and set2 such that the absolute difference between their sums is
 minimum and find the minimum difference.

Examples:

Input: arr[] = [1, 6, 11, 5]
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11
Hence, minimum difference is 1.
Input: arr[] = [1, 4]
Output: 3
Explanation:
Subset1 = {1}, sum of Subset1 = 1
Subset2 = {4}, sum of Subset2 = 4
Hence, minimum difference is 3.
Input: arr[] = [1]
Output: 1
Explanation:
Subset1 = {1}, sum of Subset1 = 1
Subset2 = {}, sum of Subset2 = 0
Hence, minimum difference is 1.
Constraints:
1 ≤ arr.size()*|sum of array elements| ≤ 105
1 <= arr[i] <= 105*/
public class MinimumSubsetDifference {
    public static int findMinDifference(int[] arr) {
        int n = arr.length;
        int totalSum = Arrays.stream(arr).sum();

        // DP array to store achievable subset sums
        boolean[] dp = new boolean[totalSum / 2 + 1];
        dp[0] = true; // Sum 0 is always achievable (empty subset)

        // Fill the DP array
        for (int num : arr) {
            for (int j = totalSum / 2; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        // Find the largest S1 such that S1 <= totalSum / 2 and dp[S1] is true
        int S1 = 0;
        for (int j = totalSum / 2; j >= 0; j--) {
            if (dp[j]) {
                S1 = j;
                break;
            }
        }

        // Minimum difference
        return totalSum - 2 * S1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 6, 11, 5};
        System.out.println(findMinDifference(arr1)); // Output: 1

        int[] arr2 = {1, 4};
        System.out.println(findMinDifference(arr2)); // Output: 3

        int[] arr3 = {1};
        System.out.println(findMinDifference(arr3)); // Output: 1

        int[] arr4 = {10, 20, 15, 5, 25};
        System.out.println(findMinDifference(arr4)); // Output: 5
    }
}

