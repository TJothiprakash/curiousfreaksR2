package practicesessions.april_29;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayPractice {
    public static void main(String[] args) {
        int[] arr = {2, 1, 0, 1, 2, 0};
        new ArrayPractice().sort012(arr);
    }

    void minandMax(int[] arr) {
        int min = arr[0], max = arr[0];

        for (int i = 1; i < arr.length; i++) {


            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }

        }
        System.out.println("Min  is " + min + " Max is " + max);
    }

    int thirdLargest(int[] arr) {
        if (arr.length < 3) {
            throw new IllegalArgumentException("Array must have at least 3 elements");
        }
        Arrays.sort(arr);

        int[] arr2 = {5, 2, 9, 1, 9, 6, 5};

        int thirdLargest = Arrays.stream(arr)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Array must have at least 3 distinct elements"));

        //  System.out.println("Third largest is " + thirdLargest);  // Output: 5
        return arr[arr.length - 3];
    }

    // sort arrays of 0s 1s 2s

    public void sort012(int[] arr) {
//        int zeros = 0, ones = 0, twos = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] == 0) {
//                zeros++;
//            } else if (arr[i] == 1) {
//                ones++;
//            } else {
//                twos++;
//            }
//
//
//        }
//        for (int i = 0; i <zeros; i++) {
//
//        }
//        for (int i = 0; i < ones; i++) {
//
//        }
//        for (int i = 0; i < twos; i++) {
//
//        }

        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    swap(arr, low++, mid++);
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high--);
                    break;
            }
        }
    }


    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public void sortingUsingTwoPtrs012(int[] arr) {
        int low = 0, high = arr.length - 1, i = 0;

        while (i <= high) {
            if (arr[i] == 0) {
                swap(arr, i, low);
                i++;
                low++;
            } else if (arr[i] == 2) {
                swap(arr, i, high);
                high--;
            } else {
                i++;
            }
        }
    }

    public int[] rotateright(int[] arr) {
        int n = arr.length;
        int last = arr[n - 1];  // store the last element

        for (int i = n - 1; i > 0; i--) {
            arr[i] = arr[i - 1];  // shift elements to the right
        }

        arr[0] = last;  // place the last element at the beginning

        return arr;
    }

    public int[] rotateleft(int[] arr) {
        int n = arr.length;
        int first = arr[0];  // store the last element

        for (int i = 0; i < n - 1; i++) {
            arr[i + 1] = arr[i];  // shift elements to the right
        }

        arr[n - 1] = first;  // place the last element at the beginning

        return arr;
    }

    public int[] rotatekElements(int arr[], int k){
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }
        // do the same like above

        return  arr;
    }
}

