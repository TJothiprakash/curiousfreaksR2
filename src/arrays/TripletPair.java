package arrays;
import java.util.Arrays;
public class TripletPair {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 3, 2};
        System.out.println("Count of triplets: " + new TripletCount().countTriplets(arr1)); // Output: 2

        int[] arr2 = {2, 3, 4}; // No triplets possible
        System.out.println("Count of triplets: " + new TripletCount().countTriplets(arr2)); // Output: 0
    }
}


 class TripletCount {
//    O(n * n ) O(1)
    public static int countTriplets(int[] arr) {
        Arrays.sort(arr); // Step 1: Sort the array
        int n = arr.length;
        int count = 0;

        // Step 2: Iterate over the array for the largest element
        for (int i = n - 1; i >= 2; i--) {
            int left = 0, right = i - 1;

            // Step 3: Two-pointer approach
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == arr[i]) {
                    count++;
                    left++;
                    right--;
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 3, 2};
        System.out.println("Count of triplets: " + countTriplets(arr1)); // Output: 2

        int[] arr2 = {2, 3, 4};
        System.out.println("Count of triplets: " + countTriplets(arr2)); // Output: 0
    }
}

