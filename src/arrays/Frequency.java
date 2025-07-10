package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Frequency {
    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 3, 5, 9};
        System.out.println(Arrays.toString(arr));
        int[] hasharray = createHashArray(arr);

        System.out.println("Hasharray (frequencies from 0 to max):");
        System.out.println(Arrays.toString(hasharray));
        Map<Integer, Integer> frequencis = new Frequency().UsingHashmap(arr);
        System.out.println("Hashmap (frequencies from 0 to max):" + frequencis);
    }

    public Map<Integer, Integer> UsingHashmap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);

        }
        return map;
    }

    //    O(n) O(maxElement +1)
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
    public Map<Integer, Integer> usingTwoPointer(int[] arr) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        Arrays.sort(arr); // Ensure the array is sorted

        int ptr1 = 0;
        int n = arr.length;

        while (ptr1 < n) {
            int frequency = 1;
            int ptr2 = ptr1 + 1;

            while (ptr2 < n && arr[ptr2] == arr[ptr1]) {
                frequency++;
                ptr2++;
            }

            frequencies.put(arr[ptr1], frequency);
            ptr1 = ptr2; // Move to the next unique element
        }

        return frequencies;
    }
}
