package dec_25_practice_session;

public class TwoPointerImpl {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5,6,45};
        int target = 46;

        System.out.println(isPairSum(arr, target));
    }

    private static boolean isPairSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

}
