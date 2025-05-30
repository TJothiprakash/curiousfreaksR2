package arrays;
public class NextPermutation {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        NextPermutation sol = new NextPermutation();
        sol.nextPermutation(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        // Step 1: Find the first decreasing element from the end
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: Find the element just greater than nums[i]
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap nums[i] and nums[j]
            swap(nums, i, j);
        }

        // Step 3: Reverse the part from i+1 to end
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
