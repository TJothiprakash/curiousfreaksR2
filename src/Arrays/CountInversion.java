package Arrays;

public class CountInversion {
    public static void main(String[] args) {

    }
}
 class InversionCount {
    // Function to merge two halves and count inversions
    public static int mergeAndCount(int[] arr, int left, int right) {
        if (left >= right) {
            return 0; // No inversions in a single element
        }

        int mid = left + (right - left) / 2;

        // Count inversions in left and right halves
        int invCount = mergeAndCount(arr, left, mid);
        invCount += mergeAndCount(arr, mid + 1, right);

        // Merge the two halves and count cross inversions
        invCount += merge(arr, left, mid, right);

        return invCount;
    }

    // Function to merge two sorted halves and count the cross inversions
    public static int merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays for left and right subarrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left, invCount = 0;

        // Merge the two subarrays back into the original array
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];

                // All remaining elements in leftArr are greater than rightArr[j]
                invCount += (n1 - i); // Count the inversions
            }
        }

        // Copy remaining elements from leftArr, if any
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        // Copy remaining elements from rightArr, if any
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }

        return invCount;
    }

    // Main function to count inversions
    public static int countInversions(int[] arr) {
        return mergeAndCount(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 1, 3, 5};
        System.out.println(countInversions(arr1)); // Output: 3

        int[] arr2 = {2, 3, 4, 5, 6};
        System.out.println(countInversions(arr2)); // Output: 0

        int[] arr3 = {10, 10, 10};
        System.out.println(countInversions(arr3)); // Output: 0
    }
}
