package practicesessions.revisions.striver_sde_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day_2 {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
      /*  System.out.println("Original Array");
        extracted(arr);
        System.out.println("Rotated Array");
        //rotateArray(arr);
        rotateArrayInPlace(arr);
        extracted(arr);*/
        //merge intervals
       /* int arr1[][] = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        mergeIntervals(arr1);*/
        // find duplicates
        int[] arr1 = new int[]{1, 2, 3, 3, 4, 5,};
        System.out.println(findDuplicateUsingVisitedIndexMethod(arr1));
    }

    // rotate array using extra space
    public static int[][] rotateArray(int[][] arr) {
        int[][] result = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                result[j][arr.length - i - 1] = arr[i][j];
            }
        }

        extracted(result);
        return result;
    }

    private static void extracted(int[][] result) {
        //print array
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] rotateArrayInPlace(int[][] arr) {
        //TODO
        // logic : transpose the matrix and reverse the rows

        int temp = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr[0].length; j++) {
                temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        // reverse the rows
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                temp = arr[i][j];
                arr[i][j] = arr[i][arr[0].length - j - 1];
                arr[i][arr[0].length - j - 1] = temp;
            }
        }
        return arr;
    }

    // merge overlapping intervals
    public static List<List<Integer>> mergeIntervals(int[][] arr) {
        //TODO
        // sort the array based on the first element
        // iterate trhough the lsit and merge the intervals


        List<List<Integer>> list = new ArrayList<>();
        // Use Arrays.asList to convert each row into a List<Integer>
        for (int[] row : arr) {
            // Convert each row (1D array) to a List<Integer> and add it to the list
            List<Integer> innerList = new ArrayList<>();
            for (int num : row) {
                innerList.add(num); // Add each element to the inner list
            }
            list.add(innerList); // Add the inner list to the main list
        }

        Collections.sort(list, (a, b) -> a.get(0) - b.get(0));
        System.out.println(list);
        List<List<Integer>> result = new ArrayList<>();
        result.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).get(0) <= result.get(result.size() - 1).get(1)) {
                result.get(result.size() - 1).set(1, Math.max(result.get(result.size() - 1).get(1), list.get(i).get(1)));
            } else {
                result.add(list.get(i));
            }
        }

        System.out.println(result);
        return result;
    }

    // merge two sorted arrays without extra space
    public static void mergeSortedArrays(int[] arr1, int[] arr2) {
        //TODO
        // logic : compare the last element of arr1 with first element of arr2
        // if arr1[i] > arr2[j] then swap the elements
        // sort the arr1 and arr2
        // print the arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0, j = 0;
        List<Integer> mergedList = new ArrayList<>();

        // Two-pointer approach to merge the arrays
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                mergedList.add(arr1[i]);
                i++;
            } else {
                mergedList.add(arr2[j]);
                j++;
            }
        }

        // Add remaining elements from arr1
        while (i < arr1.length) {
            mergedList.add(arr1[i]);
            i++;
        }

        // Add remaining elements from arr2
        while (j < arr2.length) {
            mergedList.add(arr2[j]);
            j++;
        }

        // Print the merged list
        System.out.println("Merged List: " + mergedList);
    }

    public static int findDuplicateUsingVisitedIndexMethod(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (nums[index] < 0) {
                return index; // Duplicate found
            }
            nums[index] = -nums[index];
        }
        return -1; // No duplicate found (edge case)
    }

    // count no of inversions
    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //Modification 1: cnt variable to count the pairs:
        int cnt = 0;

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                cnt += (mid - left + 1); //Modification 2
                right++;
            }
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return cnt; // Modification 3
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;
        int mid = (low + high) / 2;
        cnt += mergeSort(arr, low, mid);  // left half
        cnt += mergeSort(arr, mid + 1, high); // right half
        cnt += merge(arr, low, mid, high);  // merging sorted halves
        return cnt;
    }

    public static int numberOfInversions(int[] a, int n) {
        // Count the number of pairs:
        return mergeSort(a, 0, n - 1);
    }

    // find the duplicate element in the array
    //TODO
    // use bruteforce sort the array and iterate thfough it O log n
    // use frequency array O n
    public int findDuplicate(int[] arr) {

        int[] freq = new int[arr.length + 1];
        for (int num : arr) {
            freq[num]++;
            if (freq[num] > 1) {
                return num; // Duplicate found
            }
        }
        return -1;
    }


}
