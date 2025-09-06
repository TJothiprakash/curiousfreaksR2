package dynamic_programming.dp_ongrids;

public class MaxProductSubArray {
}

//IntelliJ IDEA 2025.2.1 (Ultimate Edition)
//Build #IU-252.25557.131, built on August 28, 2025
//Source revision: ee1e6cb62e111
//Licensed to Jothiprakash T
//Subscription is active until September 2, 2025.
//Runtime version: 21.0.8+1-b1038.68 amd64 (JCEF 122.1.9)
//VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
//        Toolkit: sun.awt.windows.WToolkit
//Windows 11.0
//GC: G1 Young Generation, G1 Concurrent GC, G1 Old Generation
//Memory: 2048M
//Cores: 8
//Registry:
//ide.experimental.ui=true
//Kotlin: 252.25557.131-IJ

class Solution {
    public int maxProduct(int[] nums) {
        // Start recursion from index 0
        return helper(nums, 0, nums[0], nums[0], nums[0]);
    }

    private int helper(int[] nums, int index, int currMax, int currMin, int globalMax) {
        // Base case: reached end
        if (index == nums.length - 1) {
            return globalMax;
        }

        int next = nums[index + 1];

        // Compute new max/min for subarray ending at index+1
        int newMax = Math.max(next, Math.max(next * currMax, next * currMin));
        int newMin = Math.min(next, Math.min(next * currMax, next * currMin));

        // Update global maximum
        globalMax = Math.max(globalMax, newMax);

        // Recurse further
        return helper(nums, index + 1, newMax, newMin, globalMax);
    }
}

class Solution1 {
    public int maxProduct(int[] nums) {
        // Initialize with the first element to handle single-element arrays correctly
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];

            // If x is negative, swapping makes the previous min become the new max (sign flip)
            if (x < 0) {
                int tmp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = tmp;
            }

            // Either start fresh at x, or extend previous products
            maxEndingHere = Math.max(x, maxEndingHere * x);
            minEndingHere = Math.min(x, minEndingHere * x);

            // Track global maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

}
