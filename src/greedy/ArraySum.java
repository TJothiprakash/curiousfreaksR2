package greedy;

/*You are given an array arr of numbers. Return the sum of all the elements except the first and last elements.

Examples:

Input: arr[] = [5, 24, 39, 60, 15, 28, 27, 40, 50, 90]
Output: 283
Explanation: The sum of all the elements except the first and last element is 283.
Input: arr[] = [5, 10, 1, 11]
Output: 11
Explanation: The sum of all the elements except the first and last element is 11.
Input: arr[] = [5, 10]
Output: 0
Explanation: The sum of all the elements except the first and last element is 0.
Constraints:
2<=arr.size()<=105
2<=arr[i]<=105
Plan:
Edge Case Handling:
If the array has only two elements, the sum excluding the first and last elements is 0, since there's nothing left in between.
General Case:
For an array with more than two elements, the sum of elements excluding the first and last can be computed by iterating over the elements starting from the second element and ending just before the last element.
Approach:
If arr has 2 elements, return 0.
Otherwise, return the sum of elements from arr[1] to arr[n-2].
*/
public class ArraySum {

    public static int sumExcludingFirstAndLast(int[] arr) {
        // Edge case: If the array has only 2 elements, return 0.
        if (arr.length <= 2) {
            return 0;
        }

        int sum = 0;
        // Start from the second element and go up to the second-last element
        for (int i = 1; i < arr.length - 1; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {5, 24, 39, 60, 15, 28, 27, 40, 50, 90};
        System.out.println(sumExcludingFirstAndLast(arr1)); // Output: 283

        // Test case 2
        int[] arr2 = {5, 10, 1, 11};
        System.out.println(sumExcludingFirstAndLast(arr2)); // Output: 11

        // Test case 3
        int[] arr3 = {5, 10};
        System.out.println(sumExcludingFirstAndLast(arr3)); // Output: 0
    }
}
