package binarysearch;

import java.util.Arrays;

public class BinarySearchImpl {
    public static void main(String[] args) {

        int[] arr = {21, 42, 532, 54, 353, 2, 1, 23,  4, 435, 2, 3, 4, 32, 76, 7654, 75, 66, 4, 6, 5, 5, 43, 3, 23,};
        Arrays.sort(arr);
        //[1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 7, 21,  532, 754, 765, 26567654]
        System.out.println(Arrays.toString(arr));
        int target = 4;
        System.out.println(binarySearchElement(arr, target));
       // System.out.println(findFloor(arr, target));
        //System.out.println(findCeil(arr, target));
    }

    private static int binarySearchElement(int[] arr, int target) {
       int left=0;
       int right=arr.length-1;
       while (left <= right) {
           int mid = left + (right - left) / 2;
           if (arr[mid] == target) {
               int count=0;
               for (int i = mid; i >= 0; i--) {
                   if (arr[mid+1] == arr[mid]) {
                       count++;
                   }
                   break;
               }
               System.out.println("mid values is "+mid+" count is "+count);
               System.out.println(mid+" "+(mid+count)+" ");
               return mid;
           } else if (arr[mid] < target) {
               left = mid + 1;
           } else {
               right = mid - 1;
           }
       }
       return -1;
    }

    public static int findFloor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1; // Initialize to -1 to indicate no floor exists

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return arr[mid]; // Exact match is the floor
            } else if (arr[mid] < target) {
                floor = arr[mid]; // Update floor
                low = mid + 1;    // Search the right half
            } else {
                high = mid - 1;   // Search the left half
            }
        }

        return floor;
    }

    // Find ceil using binary search
    public static int findCeil(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceil = -1; // Initialize to -1 to indicate no ceil exists

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return arr[mid]; // Exact match is the ceil
            } else if (arr[mid] < target) {
                low = mid + 1;    // Search the right half
            } else {
                ceil = arr[mid]; // Update ceil
                high = mid - 1;   // Search the left half
            }
        }

        return ceil;
    }

}
