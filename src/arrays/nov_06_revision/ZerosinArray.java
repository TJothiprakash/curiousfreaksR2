package arrays.nov_06_revision;
// move zeros to the end of the array

public class ZerosinArray {
    public void moveZeroes(int[] arr) {
        int nonZeroIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            // Move non-zero elements to the 'nonZeroIndex' position
            if (arr[i] != 0) {
                // Only assign if it's not already at the correct index
                if (i != nonZeroIndex) {
                    arr[nonZeroIndex] = arr[i];
                    arr[i] = 0;  // Set the current element to zero
                }
                nonZeroIndex++;
            }
        }
    }
}