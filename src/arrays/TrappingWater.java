package arrays;

public class TrappingWater {
}

 class TrappingRainWater {
    static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int left = 0, right = n - 1;
        int left_max = 0, right_max = 0;
        int waterTrapped = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    waterTrapped += left_max - height[left];
                }
                left++;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    waterTrapped += right_max - height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 0, 0, 2, 0, 4};
        System.out.println(trap(arr1)); // Output: 10

        int[] arr2 = {7, 4, 0, 9};
        System.out.println(trap(arr2)); // Output: 10

        int[] arr3 = {6, 9, 9};
        System.out.println(trap(arr3)); // Output: 0
    }
}
