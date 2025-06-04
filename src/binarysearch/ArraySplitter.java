package binarysearch;

public class ArraySplitter {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 3;
        System.out.println(splitArray(arr, k));  // Output: 4
    }

    public static int splitArray(int[] arr, int k) {
        int max = 0, sum = 0;
        for (int num : arr) {
            max = Math.max(max, num);
            sum += num;
        }

        int low = max, high = sum, answer = sum;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(arr, k, mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean isPossible(int[] arr, int k, int maxAllowedSum) {
        int currentSum = 0;
        int count = 1;  // We always need at least one subarray

        for (int num : arr) {
            if (currentSum + num > maxAllowedSum) {
                count++;
                currentSum = num;
                if (count > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }

        return true;
    }
}
