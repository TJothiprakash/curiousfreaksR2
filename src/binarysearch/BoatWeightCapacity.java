package binarysearch;

public class BoatWeightCapacity {

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int d = 2;
        System.out.println(leastWeightCapacity(arr.length, d, arr));  // Output: 3
    }

    public static int leastWeightCapacity(int n, int d, int[] arr) {
        int max = 0, sum = 0;
        for (int weight : arr) {
            max = Math.max(max, weight);
            sum += weight;
        }

        int low = max, high = sum, answer = sum;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canShipInDays(arr, mid, d)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean canShipInDays(int[] arr, int capacity, int days) {
        int currentLoad = 0;
        int requiredDays = 1;

        for (int weight : arr) {
            if (currentLoad + weight > capacity) {
                requiredDays++;
                currentLoad = weight;
            } else {
                currentLoad += weight;
            }
        }

        return requiredDays <= days;
    }
}
