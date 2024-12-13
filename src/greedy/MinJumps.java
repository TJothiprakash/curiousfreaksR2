package greedy;
/*
Given an array arr[] of non-negative integers. Each array element represents the maximum length of the jumps that can be made forward from that element. This means if arr[i] = x, then we can jump any distance y such that y ≤ x. Find the minimum number of jumps to reach the end of the array starting from the first element. If an element is 0, then you cannot move through that element.

        Note:  Return -1 if you can't reach the end of the array.

Examples :

Input: arr[] = [1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9]
Output: 3
Explanation: First jump from 1st element to 2nd element with value 3. From here we jump to 5th element with value 9, and from here we will jump to the last.
        Input: arr = [1, 4, 3, 2, 6, 7]
Output: 2
Explanation: First we jump from the 1st to 2nd element and then jump to the last element.
        Input: arr = [0, 10, 20]
Output: -1
Explanation: We cannot go anywhere from the 1st element.
Constraints:
        2 ≤ arr.size() ≤ 106
        0 ≤ arr[i] ≤ 105*/

public class MinJumps {
    public static void main(String[] args) {
        MinJumps sol = new MinJumps();

        // Example 1
        int[] arr1 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(sol.minJumps(arr1));  // Output: 3

        // Example 2
        int[] arr2 = {1, 4, 3, 2, 6, 7};
        System.out.println(sol.minJumps(arr2));  // Output: 2

        // Example 3
        int[] arr3 = {0, 10, 20};
        System.out.println(sol.minJumps(arr3));  // Output: -1
    }

    public int minJumps(int[] arr) {
        int n = arr.length;

        // If the length of the array is 1, no jumps are needed.
        if (n == 1) return 0;

        // If the first element is 0, it's impossible to move anywhere.
        if (arr[0] == 0) return -1;

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n; i++) {
            // Update the farthest point we can reach
            farthest = Math.max(farthest, i + arr[i]);

            // If we have reached the end of the current jump range
            if (i == currentEnd) {

                // If we've reached or passed the target, return the jump count
                if (currentEnd >= n - 1) return jumps;

                jumps++;
                // Update the end of the current jump range
                currentEnd = farthest;
            }

            // If we cannot move forward from this point, return -1
            if (farthest <= i) return -1;
        }

        return jumps;
    }
}
