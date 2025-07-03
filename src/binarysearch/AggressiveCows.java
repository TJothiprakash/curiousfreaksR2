/*
Problem:
You are given `n` stalls and `k` cows. You need to place the cows in the stalls such that the minimum distance between any two cows is as large as possible.
Return the **largest minimum distance** possible.

Intuition:
- Sort the stalls to ensure they are in increasing order.
- Use binary search on the answer (i.e., the minimum distance between cows).
- For each distance `mid`, check if we can place all cows with at least `mid` distance apart using a greedy placement strategy.

Dry Run:
Stalls = {1, 2, 4, 8, 9}, Cows = 3
Sorted: [1, 2, 4, 8, 9]
Search range: low = 1, high = 8
Try mid = 4 → Can place only 2 cows → too big → high = 3
Try mid = 2 → Can place all 3 cows at 1, 4, 8 → valid → result = 2, try more → low = 3
Try mid = 3 → Place at 1, 4, 8 → valid → result = 3, try more → low = 4
End loop → Return result = 3

Code:
*/

package binarysearch;

import java.util.Arrays;

public class AggressiveCows {

    public static int maxMinDistance(int[] stalls, int k) {
        Arrays.sort(stalls); // Step 1: Sort the stalls
        System.out.println("🐄 Sorted stalls: " + Arrays.toString(stalls));

        int low = 1; // Minimum possible distance
        int high = stalls[stalls.length - 1] - stalls[0]; // Maximum possible distance
        int result = 0;

        System.out.println("🔍 Binary search on distance from " + low + " to " + high);

        while (low <= high) {
            int mid = low + (high - low) / 2; // Try middle distance
            System.out.println("➡️ Trying distance: " + mid);

            if (canPlaceCows(stalls, k, mid)) {
                System.out.println("✅ Can place " + k + " cows with " + mid + " distance");
                result = mid;      // Save current valid distance
                low = mid + 1;     // Try to maximize
            } else {
                System.out.println("❌ Cannot place " + k + " cows with " + mid + " distance");
                high = mid - 1;    // Try smaller distances
            }
        }

        System.out.println("🎯 Largest minimum distance = " + result);
        return result;
    }

    // Helper to check if `k` cows can be placed with at least `distance` between them
    private static boolean canPlaceCows(int[] stalls, int k, int distance) {
        int cowsPlaced = 1; // Place first cow at first stall
        int lastPos = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= distance) {
                cowsPlaced++;
                lastPos = stalls[i];
                System.out.println("   🐄 Placed cow at " + stalls[i] + ", total placed = " + cowsPlaced);

                if (cowsPlaced == k) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] stalls1 = {1, 2, 4, 8, 9};
        int k1 = 3;
        System.out.println("Result for stalls1: " + maxMinDistance(stalls1, k1)); // Output: 3

        System.out.println();

        int[] stalls2 = {10, 1, 2, 7, 5};
        int k2 = 3;
        System.out.println("Result for stalls2: " + maxMinDistance(stalls2, k2)); // Output: 4

        System.out.println();

        int[] stalls3 = {2, 12, 11, 3, 26, 7};
        int k3 = 5;
        System.out.println("Result for stalls3: " + maxMinDistance(stalls3, k3)); // Output: 1
    }

    /*
    Time Complexity: O(n * log(maxDistance))
        - log(maxDistance) for binary search
        - O(n) to check feasibility for each distance

    Space Complexity: O(1)
    */
}
