package sortingtechniques;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4354,14,12, 54, 65};

        System.out.println("Before sorting :");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
      //  int[] res = bubbleSort(arr);
        int[] res1 = BubbleSort.bubbleSort(arr);

        System.out.println("\nAfter sorting :");
        for (int i = 0; i < res1.length; i++) {
            System.out.print(res1[i] + " ");
        }
    }
//O(n * n)O(1)
    private static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }


        return arr;
    }
/*1. Bubble Sort 🫧
Logic:
Compares adjacent elements and swaps them if they are in the wrong order.
The largest element bubbles up to the last position in each pass.
The process repeats until the array is sorted.
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
 ) (reverse order)*/
}
