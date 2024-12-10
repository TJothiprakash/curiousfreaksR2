package arrays.nov_06_revision;

public class RemoveDuplicates {
    public int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;

        int writeIndex = 1; // Pointer for the next unique position
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[writeIndex] = arr[i];
                writeIndex++;
            }
        }
        return writeIndex; // Length of the array with unique elements
    }
}
