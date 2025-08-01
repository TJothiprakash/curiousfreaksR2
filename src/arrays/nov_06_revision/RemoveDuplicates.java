package arrays.nov_06_revision;

public class RemoveDuplicates {
    //    O(n) O(1)
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
//| Complexity Type | Your Comment | Verdict   | Explanation        |
//| --------------- | ------------ | --------- | ------------------ |
//| Time            | `O(n)`       | ✅ Correct | Single linear scan |
//| Space           | `O(1)`       | ✅ Correct | In-place update    |