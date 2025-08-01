package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given an array arr of positive integers and another number target. Determine whether two elements exist in arr whose sum is exactly target or not. Return a boolean value true if two elements exist in arr else return false.

Examples:

Input: arr[] = [1, 4, 45, 6, 10, 8], target =16
Output: true
Explanation: arr[3] + arr[4] = 6 + 10 = 16
Input: arr[] = [1, 2, 4, 3, 6], target = 11
Output: false
Explanation: None of the pair makes a sum of 11
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(n)*/
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 45, 6, 10, 8, 3};//`1,4,6,8,10,45
        int target = 11;
        //  System.out.println(twoSum(arr, target));
//        System.out.println(threeSum(arr, target));
//        List<List<Integer>> ans = new TwoSum().twoSumTestFindAll(arr, target);
//        System.out.println(ans);
        boolean hasThreeSum = threeSum(arr, target);
        System.out.println(hasThreeSum);

    }

    //  O( n^3) O(1)
    private static boolean threeSums(int[] arr, int target) {
        Arrays.sort(arr);
        // int left = 0, right = arr.length-1;
        for (int k = 0; k < arr.length; k++) {

            int sum = arr[k];
            for (int i = 1; i < arr.length; i++) {
                for (int j = 2; j < arr.length; j++) {
                    if (sum + arr[i] + arr[j] == target) {

                        System.out.println("sum is " + sum + arr[i] + arr[j] );
                        return true;
                    }
                }

            }
        }
        return false;
    }

    // using while loop
//    O(n * n) O(1)
    private static boolean threeSum(int[] arr, int target) {
        boolean result = false;
        Arrays.sort(arr); // Sort the array
        for (int k = 0; k < arr.length - 2; k++) { // Fix the first element
            int left = k + 1; // Left pointer
            int right = arr.length - 1; // Right pointer

            while (left < right) {
                int sum = arr[k] + arr[left] + arr[right];
                if (sum == target) {
                    result = true;
                    break;// Triplet found
                } else if (sum < target) {
                    left++; // Increase the left pointer
                } else {
                    right--; // Decrease the right pointer
                }
            }
            if (result) break;
        }
        // No triplet found
        return result;
    }

    // two sum using sliding window
//    O(n log n ) O(1)
    public static boolean twoSumTest(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static List<List<Integer>> twoSumTestFindAll(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                result.add(Arrays.asList(arr[left], arr[right]));

                // Skip duplicates
                int leftVal = arr[left], rightVal = arr[right];
                while (left < right && arr[left] == leftVal) left++;
                while (left < right && arr[right] == rightVal) right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

}

       /* HashSet<Integer> seen = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true; // Pair found
            }
            seen.add(num);
        }
        return false;*/ // No pair found



