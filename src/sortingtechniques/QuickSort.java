package sortingtechniques;

import java.util.ArrayList;
import java.util.List;
/* Quick Sort ⚡ (Divide & Conquer)
Logic:
Selects a pivot element.
Partitions the array into two parts:
Left: Elements smaller than pivot.
Right: Elements greater than pivot.
Recursively sorts both parts.
Time Complexity:
Best & Average Case:
𝑂
(
𝑛
log
⁡
𝑛
)
O(nlogn)

Worst Case:
𝑂
(
𝑛
2
)
O(n
2
 ) (if pivot is poorly chosen, e.g., smallest/largest element in sorted array).

Used in practical applications due to its speed in most cases.*/
import static sortingtechniques.Solution.quickSort;

public class QuickSort
{
    public static void main(String[] args) {
        int arr[] = {64, 25, 12, 22, 11};
        System.out.println("Before sorting :");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        List<Integer> res = quickSort(arr);
        System.out.println("\nAfter sorting :");
        System.out.println(res);
    }

}



class Solution {
    static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(low);
        int i = low;
        int j = high;

        while (i < j) {
            while (arr.get(i) <= pivot && i <= high - 1) {
                i++;
            }

            while (arr.get(j) > pivot && j >= low + 1) {
                j--;
            }
            if (i < j) {
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(low);
        arr.set(low, arr.get(j));
        arr.set(j, temp);
        return j;
    }

    static void qs(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high);
            qs(arr, low, pIndex - 1);
            qs(arr, pIndex + 1, high);
        }
    }
    public static List<Integer> quickSort(int []arr) {
        // Write your code here.
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        List<Integer> arr1 = new ArrayList<>(list);
        qs(arr1, 0, arr1.size() - 1);
        return arr1;
    }
}
