package sortingtechniques;

public class MergeSort {
    public  static int[] mergeSort(int[] arr) {
        int low = 0, high = arr.length - 1;
        return mergeSort(arr, low, high);

    }

    private static int[] mergeSort(int[] arr, int low, int high) {
        if (low <high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            mergeArrays(arr, low, mid, high);
        }
        return arr;
    }

    private static void mergeArrays(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = arr[low + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = arr[mid + 1 + i];
        }
        int i = 0, j = 0, k = low;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
/*Merge Sort 🧩 (Divide & Conquer)
Logic:
Divide: Splits the array into two halves until each subarray contains only one element.
Conquer: Merges these halves back in sorted order.
Time Complexity:
Always
𝑂
(
𝑛
log
⁡
𝑛
)
O(nlogn)

Stable sorting algorithm (preserves the order of equal elements).

Used in external sorting and large datasets.

*/