package binarysearch;

public class Occurrences {

}

class FirstAndLastOccurrence {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 5, 5, 5, 67, 123, 125};
        int x1 = 5;
        System.out.println(findFirstAndLast(arr1, x1)); // Output: [2, 5]

        int[] arr2 = {1, 3, 5, 5, 5, 5, 7, 123, 125};
        int x2 = 7;
        System.out.println(findFirstAndLast(arr2, x2)); // Output: [6, 6]

        int[] arr3 = {1, 2, 3};
        int x3 = 4;
        int[] result = findFirstAndLast(arr3, x3);
        System.out.println(result[0] + " " + result[1]);
        // Output: [-1, -1]
    }

    //    O(log n) O(1)
    public static int[] findFirstAndLast(int[] arr, int x) {
        int first = findFirst(arr, x);
        int last = findLast(arr, x);
        return new int[]{first, last};
    }

    // Find the first occurrence of x
    private static int findFirst(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int first = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                first = mid;
                high = mid - 1; // Search left for earlier occurrences
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return first;
    }

    // Find the last occurrence of x
//    O(log n)O(1)
    private static int findLast(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int last = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                last = mid;
                low = mid + 1; // Search right for later occurrences
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return last;
    }
}
