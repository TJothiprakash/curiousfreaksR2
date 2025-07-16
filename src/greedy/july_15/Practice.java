package greedy.july_15;

import java.util.Arrays;

public class Practice {

    public static void main(String[] args) {
        int ans = minJumps(new int[]{2, 3, 1, 1, 4});
        System.out.println("ans = " + ans);

    }

    /**
     * Greedy O(n) solution –  one pass, constant extra space.
     */
    public static int minJumps(int[] nums) {
        if (nums.length <= 1) return 0;

        int jumps = 0;
        int currEnd = 0;     // end of the current "layer"
        int farthest = 0;    // farthest index reachable so far

        for (int i = 0; i < nums.length - 1; i++) {   // no need to process last index
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currEnd) {       // must jump to move the layer forward
                jumps++;
                currEnd = farthest;

                if (currEnd >= nums.length - 1) break;
            }
        }
        return jumps;

    }

    int count = 0;

    public void minOperations(int n) {
        if (n == 0) return;
        count++;
        if (n % 2 == 0) {
            minOperations(n / 2);
        } else {
            minOperations(n - 1);
        }
    }

    public void minimizeSum(Integer arr1[], Integer arr2[]) {
        Arrays.stream(arr1).sorted();
        Arrays.sort(arr2, (a, b) -> b - a);
        int mul = 0;
        int sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            mul = arr1[i] * arr2[i];
            sum += mul;
        }
    }

}
