package arrays;

public class MaxProduct {
}

class MaximumProductSubarray {

    //    O(n) O(1)
//    similar to kadane just handled for negative values
    public static int maxProduct(int[] arr) {
        // Edge case: if the array is empty
        if (arr.length == 0) return 0;

        int max_prod = arr[0]; // Maximum product at the current position
        int min_prod = arr[0]; // Minimum product at the current position
        int result = arr[0];   // Global maximum product

        // Start iterating from the second element
        for (int i = 1; i < arr.length; i++) {
            // If the current element is negative, we swap max_prod and min_prod
            // because multiplying a negative number by a negative product will result in a larger positive product.
            if (arr[i] < 0) {
                int temp = max_prod;
                max_prod = min_prod;
                min_prod = temp;
            }

            // Update max_prod and min_prod
            max_prod = Math.max(arr[i], max_prod * arr[i]);
            min_prod = Math.min(arr[i], min_prod * arr[i]);

            // Update result
            result = Math.max(result, max_prod);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, 6, -3, -10, 0, 2};
        System.out.println(maxProduct(arr1)); // Output: 180

        int[] arr2 = {-1, -3, -10, 0, 60};
        System.out.println(maxProduct(arr2)); // Output: 60

        int[] arr3 = {2, 3, 4};
        System.out.println(maxProduct(arr3)); // Output: 24
    }
}
