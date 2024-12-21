package arrays;

import java.util.Arrays;

public class Frequency {
    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 3, 5,9};

        int[] hasharray = createHashArray(arr);

        System.out.println("Hasharray (frequencies from 0 to max):");
        System.out.println(Arrays.toString(hasharray));
    }

    private static int[] createHashArray(int[] arr) {
        // Step 1: Find the maximum number in the array
        int max = Arrays.stream(arr).max().getAsInt();

        // Step 2: Create a hasharray of size max + 1
        int[] hasharray = new int[max + 1];

        // Step 3: Count frequencies
        for (int num : arr) {
            hasharray[num]++;
        }

        return hasharray;
    }
}
