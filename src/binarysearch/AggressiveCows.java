package binarysearch;

import java.util.Arrays;

public class AggressiveCows {
    public static int maxMinDistance(int[] stalls, int k) {
        Arrays.sort(stalls); // Step 1: Sort the stalls

        int low = 1; // Min possible distance
        int high = stalls[stalls.length - 1] - stalls[0]; // Max possible distance
        int result = 0;

        while (low <= high) { // Step 2: Binary search on the answer
            int mid = low + (high - low) / 2; // Mid distance
            if (canPlaceCows(stalls, k, mid)) { // Step 3: Check feasibility
                result = mid; // Store the possible answer
                low = mid + 1; // Try to maximize min distance
            } else {
                high = mid - 1; // Reduce search space
            }
        }

        return result;
    }

    // Step 3: Function to check if we can place k cows with at least 'distance' distance apart
    private static boolean canPlaceCows(int[] stalls, int k, int distance) {
        int cowsPlaced = 1; // Place the first cow at the first stall
        int lastPos = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= distance) { // Place next cow if gap is at least 'distance'
                cowsPlaced++;
                lastPos = stalls[i]; // Update last placed cow position

                if (cowsPlaced == k) { // All cows placed successfully
                    return true;
                }
            }
        }

        return false; // Not possible to place all k cows
    }

    public static void main(String[] args) {
        int[] stalls1 = {1, 2, 4, 8, 9};
        int k1 = 3;
        System.out.println(maxMinDistance(stalls1, k1)); // Output: 3

        int[] stalls2 = {10, 1, 2, 7, 5};
        int k2 = 3;
        System.out.println(maxMinDistance(stalls2, k2)); // Output: 4

        int[] stalls3 = {2, 12, 11, 3, 26, 7};
        int k3 = 5;
        System.out.println(maxMinDistance(stalls3, k3)); // Output: 1
    }
}
