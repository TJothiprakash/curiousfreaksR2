package binarysearch;

import java.util.Arrays;

public class BinarySearchImpl {
    public static void main(String[] args) {
        int[] arr = {21, 42, 532, 54, 353, 2, 1, 23, 4, 435, 2, 3, 4, 32, 76, 7654, 75, 66, 4, 6, 5, 5, 43, 3, 23};
        Arrays.sort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));

        int target = 4;
        System.out.println("Binary Search Index: " + binarySearchElement(arr, target));
        System.out.println("Floor of " + target + ": " + findFloor(arr, target));
        System.out.println("Ceil of " + target + ": " + findCeil(arr, target));
    }

    // Optimized Binary Search that returns the first and last occurrence of target
    private static int binarySearchElement(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int mid, first = -1, last = -1;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                first = last = mid;

                // Find first occurrence
                while (first > 0 && arr[first - 1] == target) {
                    first--;
                }
                // Find last occurrence
                while (last < arr.length - 1 && arr[last + 1] == target) {
                    last++;
                }

                System.out.println("Target appears from index " + first + " to " + last);
                return first;  // or return last based on requirement
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }

    // Find Floor using Binary Search
    public static int findFloor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1; // Default to -1 (no floor exists)

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return arr[mid]; // Exact match is the floor
            } else if (arr[mid] < target) {
                floor = arr[mid]; // Update floor
                low = mid + 1;    // Search right half
            } else {
                high = mid - 1;   // Search left half
            }
        }

        return floor; // If no valid floor, returns -1
    }

    // Find Ceil using Binary Search
    public static int findCeil(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceil = -1; // Default to -1 (no ceil exists)

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return arr[mid]; // Exact match is the ceil
            } else if (arr[mid] < target) {
                low = mid + 1;    // Search right half
            } else {
                ceil = arr[mid]; // Update ceil
                high = mid - 1;   // Search left half
            }
        }

        return ceil; // If no valid ceil, returns -1
    }
}
