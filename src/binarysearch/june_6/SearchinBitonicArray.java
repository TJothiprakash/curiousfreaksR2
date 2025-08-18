package binarysearch.june_6;
public class SearchinBitonicArray {

    // Function to find target in bitonic array
    static boolean searchBitonic(int[] arr, int target) {
        int peak = findPeak(arr);

        // Search in increasing part
        if (binarySearch(arr, 0, peak, target, true)) {
            return true;
        }

        // Search in decreasing part
        return binarySearch(arr, peak + 1, arr.length - 1, target, false);
    }

    // Step 1: Find peak index
    static int findPeak(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < arr[mid + 1]) {
                low = mid + 1; // peak is on right
            } else {
                high = mid; // peak is mid or left
            }
        }
        return low; // peak index
    }

    // Step 2: Binary search (order can be ascending/descending)
    static boolean binarySearch(int[] arr, int low, int high, int target, boolean asc) {
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) return true;

            if (asc) { // ascending order
                if (target < arr[mid]) high = mid - 1;
                else low = mid + 1;
            } else {  // descending order
                if (target < arr[mid]) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }

    // Main function
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 12, 4, 2};
        int target1 = 4;
        int target2 = 10;

        System.out.println("Searching 4 → " + searchBitonic(arr, target1));  // true
        System.out.println("Searching 10 → " + searchBitonic(arr, target2)); // false
    }
}
