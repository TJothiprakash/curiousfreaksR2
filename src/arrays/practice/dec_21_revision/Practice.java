package arrays.practice.dec_21_revision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Practice {
    // 1. largest element in an array
    // sort the array and return the last element && also u acan use recursion
    public static void main(String[] args) {
        int[] arr = {4, 0, 1, 20, 0, 1};
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        //System.out.println(new Practice().largestElement(arr));
        // int ans[] = new Practice().rightRotateArray(arr);
        // int ans[] = new Practice().leftRotateArraybyDPlaces(arr, 6);
        int[] ans = new Practice().findUnion(arr1, arr2);

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }


    int largestElement(int[] arr) {
        int max = 0;
        return findLargestNo(arr, arr.length - 1, max);
    }

    int findLargestNo(int[] arr, int n, int max) {
        if (n == 0) {
            return max;
        }
        if (arr[n] > max) {
            max = arr[n];
        }
        return findLargestNo(arr, n - 1, max);
    }

    boolean ifArrayisSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    int[] removeDuplicates(int[] arr) {
        int n = arr.length;
        int i = 0, count = 0;
        for (int j = 1; j < n; j++) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
                count = i;
            }

            // return arr;
        }

        return Arrays.copyOf(arr, count + 1);
    }

    int[] leftRotateArray(int[] arr) {
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = temp;
        return arr;
    }

    int[] rightRotateArray(int[] arr) {
        int temp = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;
        return arr;
    }

    int[] leftRotateArraybyDPlaces(int[] arr, int d) {
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }
        for (int i = d; i < arr.length; i++) {
            arr[i - d] = arr[i];
        }
        for (int i = 0; i < d; i++) {
            arr[arr.length - d + i] = temp[i];
        }
        return arr;
    }


    int[] moveZeroestoEnd(int[] arr) {
        int nonZeroIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroIndex] = arr[i];
                nonZeroIndex++;

            }
            //arr[i] = 0;

        }
        for (int i = nonZeroIndex; i < arr.length; i++) {
            arr[i] = 0;
        }

        return arr;
    }

    // find union using map

    int[] findUnion(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>(arr1.length + arr2.length);
        for (int i : arr1) map.put(i, map.getOrDefault(i, 0) + 1);
        for (int i : arr2) map.put(i, map.getOrDefault(i, 0) + 1);
        int[] union = new int[map.size()];
        int index = 0;
        for (int i : map.keySet()) {
            union[index++] = i;

        }

        return union;
    }

    // using two pointers
    int[] findUnionUsingTwoPointers(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        int m = arr1.length, n = arr2.length;
        int[] union = new int[m + n];
        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                union[i] = arr1[i];
                i++;
            } else if (arr1[i] > arr2[j]) {
                union[j] = arr2[j];
                j++;
            } else {
                union[i] = arr1[i];
                i++;
                j++;
            }
        }
        while (i < m) {
            union[i] = arr1[i];
            i++;
        }
        while (j < n) {
            union[i] = arr2[j];
            j++;
            i++;
        }
        return union;
    }


}
