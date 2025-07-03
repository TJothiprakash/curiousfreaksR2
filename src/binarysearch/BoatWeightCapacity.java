/*
Problem:
You are given an array `arr[]` where each element represents the weight of a package, and an integer `d` representing the number of days.
You must ship all packages within `d` days. Each day you can carry at most `capacity` weight, and you must ship packages in order.

Goal:
Return the **minimum possible ship capacity** such that all packages are shipped in `d` days.

Intuition:
This is a Binary Search on Answer problem:
- The minimum possible capacity is the heaviest package.
- The maximum possible capacity is the sum of all packages.
- For a guessed capacity (mid), simulate shipping and check if it fits within `d` days.

Dry Run:
arr = [1, 2, 1], d = 2
min capacity = max(arr) = 2
max capacity = sum(arr) = 4
Try mid = 3 → Day 1: 1+2, Day 2: 1 → valid → try smaller
Try mid = 2 → Day 1: 1, Day 2: 2, Day 3: 1 → too many days → invalid
Answer = 3

Code:
*/

package binarysearch;

public class BoatWeightCapacity {

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int d = 2;
        System.out.println("✅ Minimum required capacity: " + leastWeightCapacity(arr.length, d, arr));  // Output: 3
    }

    // Time: O(n * log(sum - max)), Space: O(1)
    public static int leastWeightCapacity(int n, int d, int[] arr) {
        int max = 0, sum = 0;
        for (int weight : arr) {
            max = Math.max(max, weight);
            sum += weight;
        }

        int low = max, high = sum, answer = sum;

        System.out.println("📦 Binary search between capacity " + low + " and " + high);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("➡️ Trying capacity = " + mid);

            if (canShipInDays(arr, mid, d)) {
                System.out.println("✅ Possible with capacity = " + mid);
                answer = mid;
                high = mid - 1;
            } else {
                System.out.println("❌ Not possible with capacity = " + mid);
                low = mid + 1;
            }
        }

        return answer;
    }

    // Helper: check if we can ship within `days` using given `capacity`
    private static boolean canShipInDays(int[] arr, int capacity, int days) {
        int currentLoad = 0;
        int requiredDays = 1;

        for (int weight : arr) {
            if (currentLoad + weight > capacity) {
                requiredDays++;
                currentLoad = weight;
                System.out.println("   📅 New day: currentLoad = " + weight + ", totalDays = " + requiredDays);
            } else {
                currentLoad += weight;
            }
        }

        return requiredDays <= days;
    }

    /*
    Time Complexity:
        O(n * log(sum - max))
        - Binary search over the range [max, sum]
        - Each simulation takes O(n)

    Space Complexity:
        O(1)
    */
}
