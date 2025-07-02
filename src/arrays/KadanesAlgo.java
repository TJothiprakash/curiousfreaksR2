package arrays;

public class KadanesAlgo {

}
 class MaximumSubarraySum {
//    O(n) O(1)
    public static int maxSubArraySum(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;

        int current_sum = arr[0]; // Starting with the first element
        int max_sum = arr[0]; // Initialize max_sum with the first element

        // Loop through the array starting from the second element
        for (int i = 1; i < n; i++) {
            // Update current_sum: either extend the current subarray or start a new subarray from arr[i]
            current_sum = Math.max(current_sum + arr[i], arr[i]);

            // Update max_sum: the largest sum encountered so far
            max_sum = Math.max(max_sum, current_sum);
        }

        return max_sum;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, -8, 7, -1, 2, 3};
        System.out.println(maxSubArraySum(arr1)); // Output: 11

        int[] arr2 = {-2, -4};
        System.out.println(maxSubArraySum(arr2)); // Output: -2

        int[] arr3 = {5, 4, 1, 7, 8};
        System.out.println(maxSubArraySum(arr3)); // Output: 25
    }
}
