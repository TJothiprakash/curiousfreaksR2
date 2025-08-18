package arrays.traversals;

/*Fibonacci Search

🔹 Works on sorted array using Fibonacci numbers for splitting.

Intuition:

Similar to binary search but splits according to Fibonacci numbers.

Useful for systems where only addition/subtraction is cheaper than division.
⏱️ Complexity: O(log n)*/
public class FibonacciSearch {
    static int fibSearch(int arr[], int x) {
        int n = arr.length;
        int fibMMm2 = 0; // (m-2)'th Fibonacci
        int fibMMm1 = 1; // (m-1)'th Fibonacci
        int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        int offset = -1;

        while (fibM > 1) {
            int i = Math.min(offset + fibMMm2, n - 1);

            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            } else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            } else return i;
        }

        if (fibMMm1 == 1 && arr[offset + 1] == x) return offset + 1;

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10,22,35,40,45,50,80,82,85,90};
        System.out.println(fibSearch(arr, 85)); // 8
    }
}
