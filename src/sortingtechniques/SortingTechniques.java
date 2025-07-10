package sortingtechniques;

import org.jetbrains.annotations.NotNull;

// File: SortingTechniques.java
public class SortingTechniques {

    // 1. Bubble Sort
    // Intuition: Bubble the largest elements to the end with each pass.
    // Method: Repeatedly compare adjacent elements and swap if needed.
    public static void bubbleSort(int @NotNull [] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) { // n-1 passes
            for (int j = 0; j < n - i - 1; j++) { // compare adjacent
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 2. Selection Sort
    // Intuition: Select the minimum element and move it to the front.
    // Method: Find min in unsorted part and swap with first unsorted index.
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // index of minimum element
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    // 3. Insertion Sort
    // Intuition: Build a sorted part one element at a time.
    // Method: Insert the current element into its correct position in the sorted part.
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            // Shift elements right to make space
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 4. Merge Sort
    // Intuition: Divide and conquer — split array, sort both halves, merge.
    // Method: Recursively divide, then merge two sorted halves.
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }
    }

    // 5. Quick Sort
    // Intuition: Pick a pivot, partition array, recursively sort parts.
    // Method: Move smaller elements left, greater elements right of pivot.
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high);
            quickSort(arr, low, pIndex - 1);
            quickSort(arr, pIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // boundary for smaller elements
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Utility swap function
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    // Utility function to print array
    public static void printArray(String label, int[] arr) {
        System.out.print(label + ": ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String[] args) {
        int[] original = {5, 2, 9, 1, 5, 6};

        int[] bubble = original.clone();
        bubbleSort(bubble);
        printArray("Bubble Sort", bubble);

        int[] selection = original.clone();
        selectionSort(selection);
        printArray("Selection Sort", selection);

        int[] insertion = original.clone();
        insertionSort(insertion);
        printArray("Insertion Sort", insertion);

        int[] merge = original.clone();
        mergeSort(merge, 0, merge.length - 1);
        printArray("Merge Sort", merge);

        int[] quick = original.clone();
        quickSort(quick, 0, quick.length - 1);
        printArray("Quick Sort", quick);
    }
}
