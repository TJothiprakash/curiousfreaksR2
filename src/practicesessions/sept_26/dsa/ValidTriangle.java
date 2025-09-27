package practicesessions.sept_26.dsa;

import java.util.Arrays;

public class ValidTriangle {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int k = nums.length - 1; k >= 2; k--) {
            int i = 0, j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i); // all pairs (i..j-1, j) are valid
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    void main() {
    }
}