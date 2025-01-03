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

}
