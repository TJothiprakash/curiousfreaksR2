package binarysearch.july_15;

import java.util.Arrays;

import static java.util.Arrays.sort;
import static java.util.Arrays.stream;

public class Practice {
    public static void main(String[] args) {

    }

    // min distance b/w two cows is max (max of min)
    public int aggressiveCows(int arr[], int k) {
        sort(arr);
        int low = 1;
        int high = arr[arr.length - 1] - arr[0];
        int bestPossible = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(arr, k, mid)) {
                bestPossible = mid;
                low = mid + 1;
            } else {
                high = mid - 1;

            }

        }
        return bestPossible;
    }

    private boolean canPlace(int[] arr, int k, int mid) {
        int cowsPlaced = 1;
        int lastPlaced = arr[0];

        for (int i = 0; i < arr.length && (cowsPlaced < k); i++) {
            if (arr[i] - lastPlaced >= mid) {
                cowsPlaced++;
                lastPlaced = arr[i];
            }
        }
        if (cowsPlaced >= k) {
            return true;
        }
        return false;
    }

    //min of max
    public int bookAllocation(int pages[], int noOfStudents) {
//        Arrays.sort(pages);
        int left = 1;
        int right = stream(pages).sum();

        int bestPossible = 0;
        while (left <= right) {

            int mid = left + (right - left) / 2;
            if (allocate(pages, noOfStudents, mid)) {
                bestPossible = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }
        return bestPossible;

    }

    public boolean allocate(int[] pages, int noOfStudents, int mid) {
        int studentsCOunt = 1;
        int recdPages = pages[0];
        for (int i = 0; i < pages.length && (studentsCOunt < noOfStudents); i++) {
            if (recdPages >= mid) {
                studentsCOunt++;
                recdPages = 0;
            }
            recdPages += pages[i];
        }

        return studentsCOunt >= noOfStudents;

    }

    public int painterPartition(int[] boards, int noOfPainters) {
        int left = Arrays.stream(boards).max().getAsInt();
        int right = Arrays.stream(boards).sum();
        int bestPossible = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canPaint(boards, noOfPainters, mid)) {
                bestPossible = mid;
                right = mid - 1; // try better
            } else {
                left = mid + 1;
            }
        }

        return bestPossible;
    }

    private boolean canPaint(int[] boards, int noOfPainters, int limit) {
        int painters = 1;
        int sum = 0;

        for (int b : boards) {
            if (b > limit) return false;
            if (sum + b > limit) {
                painters++;
                sum = b;
            } else {
                sum += b;
            }
        }

        return painters <= noOfPainters;
    }
    public int splitArrayLargest(int[] arr, int k) {
        int left = Arrays.stream(arr).max().getAsInt(); // at least one element
        int right = Arrays.stream(arr).sum();           // one group = all elements
        int bestPossible = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (findMinPossible(arr, k, mid)) {
                bestPossible = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return bestPossible;
    }

    private boolean findMinPossible(int[] arr, int k, int mid) {
        int splits = 1;
        int currentSum = 0;

        for (int num : arr) {
            if (num > mid) return false; // individual element too big
            if (currentSum + num > mid) {
                splits++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }

        return splits <= k;
    }

}