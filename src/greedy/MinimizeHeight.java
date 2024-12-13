package greedy;

/*Given a array arr[] and positive integer k denoting heights of towers, you have to modify the height of each tower either by increasing or decreasing them by k only once.

Find out what could be the possible minimum difference of the height of shortest and longest towers after you have modified each tower.

Note: A slight modification of the problem can be found here.

Examples:

Input: arr[] = [1, 5, 8, 10], k = 2
Output: 5
Explanation: The array can be modified as [3, 3, 6, 8]. The difference between the largest and the smallest is 8 - 3 = 5.
Input: arr[] = [3, 9, 12, 16, 20], k = 3
Output: 11
Explanation: The array can be modified as [6, 12, 9, 13, 17]. The difference between the largest and the smallest is 17 - 6 = 11.
Constraints
1 ≤ k ≤ 104
1 ≤ number of towers ≤ 105
0 ≤ arr[i] ≤ 105

*/

import java.util.Arrays;

public class MinimizeHeight {
    public static void main(String[] args) {
        MinimizeHeight minimizeHeight = new MinimizeHeight();
        int[] arr1 = {1, 5, 8, 10};
        int k1 = 2;
        System.out.println(minimizeHeight.getMinDiff(arr1, k1)); // Output: 5

        int[] arr2 = {3, 9, 12, 16, 20};
        int k2 = 3;
        System.out.println(minimizeHeight.getMinDiff(arr2, k2)); // Output: 11
    }

    public int getMinDiff(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0; // No towers to modify
        }

        int n = arr.length;
        Arrays.sort(arr); // Sort the array to simplify comparisons

        int result = arr[n - 1] - arr[0]; // Initial difference without modifications

        int smallest = arr[0] + k; // Minimum possible height after modification
        int largest = arr[n - 1] - k; // Maximum possible height after modification

        // Swap smallest and largest if smallest > largest
        if (smallest > largest) {
            int temp = smallest;
            smallest = largest;
            largest = temp;
        }

        for (int i = 0; i < n - 1; i++) {
            int minHeight = Math.min(smallest, arr[i + 1] - k); // Minimum height after modification
            int maxHeight = Math.max(largest, arr[i] + k);      // Maximum height after modification
            result = Math.min(result, maxHeight - minHeight);  // Update result with the smallest difference
        }

        return result;
    }
}
