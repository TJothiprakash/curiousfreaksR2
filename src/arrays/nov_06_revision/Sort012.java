package arrays.nov_06_revision;

public class Sort012 {
    //    O(n) O(1)
    public static void sortArray(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            switch (arr[mid]) {
                case 0: // Current element is 0
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                case 1: // Current element is 1
                    mid++;
                    break;
                case 2: // Current element is 2
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 2, 0, 1, 2};
        //  int[] arr2 = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};

        sortArray(arr1);
        //sortArray(arr2);

        System.out.println(java.util.Arrays.toString(arr1)); // Output: [0, 0, 1, 1, 2, 2]
        //  System.out.println(java.util.Arrays.toString(arr2)); // Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]
    }
}

//| Complexity Type | Your Comment | Verdict   | Notes                       |
//| --------------- | ------------ | --------- | --------------------------- |
//| Time            | `O(n)`       | ✅ Correct | Single pass, constant swaps |
//| Space           | `O(1)`       | ✅ Correct | In-place sorting            |


/*public class Sort012TwoPass {
    public static void sortArray(int[] arr) {
        int count0 = 0, count1 = 0, count2 = 0;

        // First pass: Count occurrences of 0, 1, and 2
        for (int num : arr) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else if (num == 2) count2++;
        }

        // Second pass: Overwrite array based on counts
        int index = 0;
        while (count0-- > 0) arr[index++] = 0;
        while (count1-- > 0) arr[index++] = 1;
        while (count2-- > 0) arr[index++] = 2;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 0, 1, 2, 1, 0};
        sortArray(arr);

        System.out.println(java.util.Arrays.toString(arr)); // Output: [0, 0, 0, 1, 1, 1, 2, 2]
    }
}
*/