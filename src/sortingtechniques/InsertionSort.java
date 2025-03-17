package sortingtechniques;
/**/
/*3. Insertion Sort 🏗
Logic:
Takes one element at a time and inserts it into its correct position in a sorted part of the array.
Works similarly to arranging playing cards in hand.
Time Complexity:
Best Case:
𝑂
(
𝑛
)
O(n) (already sorted)

Worst Case:
𝑂
(
𝑛
2
)
O(n
2
 ) (reverse order)

Efficient for small or nearly sorted arrays.

*/
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Before sorting :");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        int[] res = insertionSort(arr);
        System.out.println("\nAfter sorting :");
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= arr.length-1; i++) {
            int j=i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }

        return arr;
    }
}
