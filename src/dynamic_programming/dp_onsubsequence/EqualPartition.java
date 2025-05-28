package dynamic_programming.dp_onsubsequence;
import java.util.Arrays;
/*Given an array arr[], return true if it can be partitioned into two parts such that the sum of elements in both parts is the same, otherwise, false.

Examples:

Input: arr = [1, 5, 11, 5]
Output: true
Explanation: The two parts are [1, 5, 5] and [11].
Input: arr = [1, 3, 5]
Output: false
Explanation: This array can never be partitioned into two such parts.
Constraints:
1 ≤ arr.size ≤ 100
1 ≤ arr[i] ≤ 1000
n*sum of elements ≤ 5*106

*/



/*class Solution {
    static boolean equalPartition(int arr[]) {
        // code here

        int sum =0;
        for(int i: arr){
            sum+=i;
        }


        if(sum %2 !=0) return false;
        int target = sum/2;
        return canPartition(arr,target, 0);
    }
    static boolean canPartition(int []arr,int target, int index){

        if(target ==0)return true;

        if(index >= arr.length) return false;

        boolean take= false;

        if(arr[index] <= target){
            take = canPartition(arr, target - arr[index], index+1);
        }
        boolean nottake = canPartition (arr, target, index +1);

        return take||nottake;
    }
}*/
public class EqualPartition {
    public static boolean canPartition(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();

        // If the total sum is odd, it cannot be partitioned into two equal subsets
        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        int n = arr.length;

        // Memoization table
        Boolean[][] dp = new Boolean[n + 1][target + 1];
        for (Boolean[] row : dp) Arrays.fill(row, null);

        return isSubsetSum(arr, n, target, dp);
    }

    private static boolean isSubsetSum(int[] arr, int n, int target, Boolean[][] dp) {
        // Base cases
        if (target == 0) return true; // Subset with sum 0 exists (empty subset)
        if (n == 0) return false;    // No elements left and target > 0

        // Check memo table
        if (dp[n][target] != null) return dp[n][target];

        // Exclude the current element
        boolean exclude = isSubsetSum(arr, n - 1, target, dp);

        // Include the current element if it's not greater than the target
        boolean include = (arr[n - 1] <= target)
                ? isSubsetSum(arr, n - 1, target - arr[n - 1], dp)
                : false;

        // Store the result
        dp[n][target] = exclude || include;

        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 11, 5};
        System.out.println(canPartition(arr1)); // Output: true

        int[] arr2 = {1, 3, 5};
        System.out.println(canPartition(arr2)); // Output: false

        int[] arr3 = {1, 2, 3, 4};
        System.out.println(canPartition(arr3)); // Output: true
    }
}

