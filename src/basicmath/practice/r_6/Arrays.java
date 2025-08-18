package basicmath.practice.r_6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arrays {
    int[] sort012(int[] arr) {
        int left = 0, mid = 0, right = arr.length - 1;

        while (mid <= right) {
            if (arr[mid] == 0) {
                swap(mid, left, arr);
                left++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else { // arr[mid] == 2
                swap(mid, right, arr);
                right--;
            }
        }
        return arr;
    }

    static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    boolean pairWithGivenSum(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(target - num)) {
                return true; // Found the pair
            }
            set.add(num);
        }
        return false;
    }


}

