package arrays;

import java.util.Arrays;

public class ArraySubset {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 1, 3, 2, 5, 4, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 3, 3};
        String flag = isSubset(arr1, arr2);
        System.out.println(flag);
    }

    //O(nlogn+ mlogn)  O(1)
    public static String isSubset(int[] a1, int[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);

        int i = 0, j = 0;

        // Two-pointer technique
        while (i < a1.length && j < a2.length) {
            if (a1[i] == a2[j]) {
                j++; // Move pointer in a2 if a match is found
            }
            i++; // Always move pointer in a1
        }

        return (j == a2.length) ? "Yes" : "No";
    }
}
