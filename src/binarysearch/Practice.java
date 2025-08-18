package binarysearch;

public class Practice {
    /**
     * Performs a binary search on a sorted array to find the target element.
     *
     * @param data The target element to search for.
     * @param arr  The sorted array in which to search.
     * @return The index of the target element if found; otherwise, -1.
     */
//    O(log n )O(1)
    public int binarySearch(int data, int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // Handle empty or null array
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Avoid integer overflow in mid calculation
            int mid = left + (right - left) / 2;

            if (arr[mid] == data) {
                return mid; // Element found
            } else if (arr[mid] < data) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return -1; // Element not found
    }

    int bs(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }


        }
        return -1;
    }

}



