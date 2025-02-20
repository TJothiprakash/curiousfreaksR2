package sortingtechniques;

import java.util.Arrays;
/*2. Selection Sort ✅
Logic:
Finds the smallest (or largest) element in the unsorted part of the array.
Swaps it with the first unsorted element.
Repeats for the rest of the array until sorted.
Time Complexity:
Always
𝑂
(
𝑛
2
)
O(n
2
 ) because it always scans the entire unsorted part.
Good for small datasets but inefficient for large ones.*/
public class SelectionSort {
    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }

            }
            //swap
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        //System.out.println(Arrays.asList(arr));

        return arr;
    }

    public static void main(String[] args) {
        int arr[] = {64, 25, 12, 22, 11};
        System.out.println("Before sorting :");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        int[] res = selectionSort(arr);
        System.out.println("\nAfter sorting :");
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
