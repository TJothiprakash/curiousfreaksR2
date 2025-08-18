/*
Problem:
Given an array of non-negative integers `arr[]` and an integer `k`, split the array
into `k` or fewer non-empty contiguous subarrays,
such that the **largest sum among these subarrays is minimized**.

Return that minimum possible largest sum.

Example:
Input: arr = [1,2,3,4], k = 3
Output: 4
Explanation: We can split as [1,2], [3], [4] — max sum is 4.

Intuition:
This is a classic Binary Search on Answer problem:
- The answer lies between max(arr) and sum(arr)
- For a guessed mid (maximum subarray sum), check if it is possible to split the array into at most `k` subarrays where no subarray sum exceeds `mid`.

Dry Run:
arr = [1,2,3,4], k = 3
low = 4, high = 10
Try mid = 7 → Can split into [1,2,3], [4] → 2 subarrays → valid → try smaller
Try mid = 5 → [1,2], [3], [4] → 3 subarrays → valid → try smaller
Try mid = 4 → [1,2], [3], [4] → valid → try smaller
Try mid = 3 → [1], [2], [3], [4] → 4 subarrays → invalid
Result = 4

Code:
*/

package binarysearch;

public class ArraySplitter {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 3;
        System.out.println("✅ Minimum largest subarray sum: " + splitArray(arr, k));  // Output: 4
    }

    // Binary Search: O(n * log(sum - max)), Space: O(1)
    public static int splitArray(int[] arr, int k) {
        int max = 0, sum = 0;
        for (int num : arr) {
            max = Math.max(max, num);
            sum += num;
        }

        int low = max, high = sum, answer = sum;

        System.out.println("🔍 Binary search between " + low + " and " + high);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("➡️ Trying max allowed subarray sum = " + mid);

            if (isPossible(arr, k, mid)) {
                System.out.println("✅ Possible with " + mid + " → try smaller");
                answer = mid;
                high = mid - 1;
            } else {
                System.out.println("❌ Not possible with " + mid + " → increase limit");
                low = mid + 1;
            }
        }

        return answer;
    }

    // Greedy Check: Can we split array into <= k parts with max sum ≤ maxAllowedSum?
    private static boolean isPossible(int[] arr, int k, int maxAllowedSum) {
        int currentSum = 0;
        int count = 1;  // Minimum 1 subarray

        for (int num : arr) {
            if (currentSum + num > maxAllowedSum) {
                count++;               // Start a new subarray
                currentSum = num;

                if (count > k) {
                    return false;     // Too many subarrays
                }
            } else {
                currentSum += num;
            }
        }

        return true;
    }

    /*
    Time Complexity:
        - O(log(sum - max) * n), where:
            sum = total sum of array
            max = maximum element in array
        - isPossible runs in O(n)

    Space Complexity:
        - O(1)
    */
}
