package binarysearch;

import java.util.Arrays;

/**
 * Demonstrates Binary Search, Floor, and Ceil operations on an integer array.
 */
public class BinarySearchImpl {
    public static void main(String[] args) {
        int[] arr = {21, 42, 532, 54, 353, 2, 1, 23, 4, 435, 2, 3, 4, 32, 76, 7654, 75, 66, 4, 6, 5, 5, 43, 3, 23};
        Arrays.sort(arr); // Sort the array before applying binary search
        System.out.println("Sorted Array: " + Arrays.toString(arr));

        int target = 4;
        System.out.println("Binary Search Index: " + binarySearchElement(arr, target));
        System.out.println("Floor of " + target + ": " + findFloor(arr, target));
        System.out.println("Ceil of " + target + ": " + findCeil(arr, target));
    }

    /**
     * Performs binary search and returns the first occurrence index of the target.
     * Also prints the range (first to last) if target has multiple occurrences.
     *
     * @param arr    Sorted array of integers
     * @param target Value to find
     * @return Index of first occurrence or -1 if not found
     */
//    O( log n) O(1)
    private static int binarySearchElement(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int mid, first = -1, last = -1;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                first = last = mid;

                // Expand left to find first occurrence
                while (first > 0 && arr[first - 1] == target) {
                    first--;
                }
                // Expand right to find last occurrence
                while (last < arr.length - 1 && arr[last + 1] == target) {
                    last++;
                }

                System.out.println("Target appears from index " + first + " to " + last);
                return first;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }

    /**
     * Finds the floor of a target value.
     * Floor = greatest element less than or equal to target.
     *
     * @param arr    Sorted array
     * @param target Value to find the floor of
     * @return Floor value or -1 if no such value exists
     */
//    O( log n ) O(1)
    public static int findFloor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                floor = arr[mid]; // Candidate floor
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return floor;
    }

    /**
     * Finds the ceil of a target value.
     * Ceil = smallest element greater than or equal to target.
     *
     * @param arr    Sorted array
     * @param target Value to find the ceil of
     * @return Ceil value or -1 if no such value exists
     */
//    O( log n) O(1)
    public static int findCeil(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                ceil = arr[mid]; // Candidate ceil
                high = mid - 1;
            }
        }

        return ceil;
    }
}
