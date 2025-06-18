package slidingwindow.practice;

import java.util.*;

public class Practice {
    public static void main(String[] args) {
        System.out.println(new Practice().maximumSumSubarray(new int[]{1, 2, 3, 4, 5}, 3));
    }

    public int maximumSumSubarray(int[] nums, int k) {
        int sum = 0;
        List<Integer> maxValueinEveryWindow = new ArrayList<>();

        int maxValue = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }
            sum += nums[i];
        }
        maxValueinEveryWindow.add(maxValue);
        maxSum = Math.max(maxSum, sum);

        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] > maxValue) {
                maxValue = findMax(nums, i - k + 1, i);
            }
            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }
            maxValueinEveryWindow.add(maxValue);
            sum += nums[i] - nums[i - k]; // Add new element and remove the oldest
            maxSum = Math.max(maxSum, sum
            );
        }


        return maxSum;

/*
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        // Edge case: If the array length is less than k
        if (arr.length < k) {
            return -1; // Or throw an exception, depending on the requirement
        }

        int sum = 0;
        int maxsum = Integer.MIN_VALUE;

        // Calculate the initial sum of the first 'k' elements
        for (int i = 0; i < k; i++) {
            set.add(arr[i]);
            //sum += arr[i];
        }
        for(int num :set){
            sum+=num;
        }

        maxsum = sum;

        // Sliding window
        for (int j = k; j < arr.length; j++) {

            sum = sum + arr[j] - arr[j - k];
            maxsum = Math.max(maxsum, sum);
        }

        return maxsum;
   */
    }

    private int findMax(int[] nums, int i, int i1) {

        int max = Integer.MIN_VALUE;
        for (int j = i; j <= i1; j++) {
            if (nums[j] > max) {
                max = nums[j];
            }
        }
        return max;
    }
}
