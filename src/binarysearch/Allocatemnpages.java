package binarysearch;

import java.util.Arrays;

public class Allocatemnpages {

    public static void main(String[] args) {
        int[] pages = new int[]{12,42,543,21,435,536,753,65,54,76};
        Arrays.sort(pages);
        int ans = allocateBooks(pages,4);
        System.out.println(ans);
    }
   static boolean isPossible(int[] arr, int k, int mid) {
        int students = 1;
        int pagesSum = 0;
        for (int pages : arr) {
            if (pages > mid) return false; // single book pages > mid means impossible
            if (pagesSum + pages <= mid) {
                pagesSum += pages;
            } else {
                students++;
                pagesSum = pages;
                if (students > k) return false;
            }
        }
        return true;
    }

    static int allocateBooks(int[] arr, int k) {
        if (arr.length < k) return -1;

        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();

        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(arr, k, mid)) {
                result = mid;
                high = mid - 1; // try to minimize max pages
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

}
