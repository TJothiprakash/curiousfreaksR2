package arrays.practice.may_30;

import java.util.*;

public class Practice {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6};
        int target = 8;
        boolean isThreesum = threeSum(arr, target);
        System.out.println(isThreesum);
    }

    boolean twoSum(int arr[], int target) {
        if (arr.length < 2) return false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return true;
                }
            }

        }
        return false;
    }

    // using two pointer technique assume array is sorted
    boolean twoSum2(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] + arr[right] == target) {
                return true;
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                right--;
            }

        }
        return false;
    }

    static boolean threeSum(int[] arr, int target) {
        Arrays.sort(arr);
        if (arr.length < 3) return false;
        List<List<Integer>> result = new java.util.ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int left = i + 1, right = arr.length - 1;
            if (i > 0 && arr[i] == arr[i - 1]) continue;// skip duplivate values

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    System.out.println(result);
                    left++;
                    right--;

                    // Skip duplicates
                    while (left < right && arr[left] == arr[left - 1]) left++;
                    while (left < right && arr[right] == arr[right + 1]) right--;
                    return true;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }


        }
        return false;
    }


    static boolean fourSum(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        if (n < 4) return false;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) continue;

                int left = j + 1, right = n - 1;

                while (left < right) {
                    int sum = arr[i] + arr[j] + arr[left] + arr[right];

                    if (sum == target) {
                        System.out.println("Found: " + Arrays.asList(arr[i], arr[j], arr[left], arr[right]));
                        return true;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return false;
    }

    static int findUnion(int[] arr1, int[] arr2) {
        int ptr1 = 0, ptr2 = 0;
        List<Integer> union = new ArrayList<>();

        while (ptr1 < arr1.length && ptr2 < arr2.length) {
            if (arr1[ptr1] == arr2[ptr2]) {
                if (union.isEmpty() || union.get(union.size() - 1) != arr1[ptr1]) {
                    union.add(arr1[ptr1]);
                }
                ptr1++;
                ptr2++;
            } else if (arr1[ptr1] < arr2[ptr2]) {
                if (union.isEmpty() || union.get(union.size() - 1) != arr1[ptr1]) {
                    union.add(arr1[ptr1]);
                }
                ptr1++;
            } else {
                if (union.isEmpty() || union.get(union.size() - 1) != arr2[ptr2]) {
                    union.add(arr2[ptr2]);
                }
                ptr2++;
            }
        }

        // Process remaining elements
        while (ptr1 < arr1.length) {
            if (union.isEmpty() || union.get(union.size() - 1) != arr1[ptr1]) {
                union.add(arr1[ptr1]);
            }
            ptr1++;
        }

        while (ptr2 < arr2.length) {
            if (union.isEmpty() || union.get(union.size() - 1) != arr2[ptr2]) {
                union.add(arr2[ptr2]);
            }
            ptr2++;
        }

        System.out.println("Union: " + union);
        return union.size();
    }

    // intesection of two arrays
    public static List<Integer> intersect(int[] arr1, int[] arr2) {
        List<Integer> intersection = new ArrayList<>();
        int ptr1 = 0, ptr2 = 0;

        while (ptr1 < arr1.length && ptr2 < arr2.length) {
            if (arr1[ptr1] == arr2[ptr2]) {
                intersection.add(arr1[ptr1]);
                ptr1++;
                ptr2++;
            } else if (arr1[ptr1] < arr2[ptr2]) {
                ptr1++;
            } else {
                ptr2++;
            }
        }

        return intersection;
    }


    public int kthElementinTwoSortedArrays(int a[], int b[], int k) {
        int n = a.length;
        int m = b.length;

        int i = 0, j = 0;
        int count = 0;
        int result = 0;

        while (i < n && j < m) {
            if (a[i] <= b[j]) {
                result = a[i];
                i++;
            } else {
                result = b[j];
                j++;
            }
            count++;
            if (count == k) return result;
        }

        // If elements are left in a[]
        while (i < n) {
            result = a[i];
            i++;
            count++;
            if (count == k) return result;
        }

        // If elements are left in b[]
        while (j < m) {
            result = b[j];
            j++;
            count++;
            if (count == k) return result;
        }

        return -1; // shouldn't reach here if k is valid
    }


    public static int longestSubarrayWithSumK(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // sum -> earliest index
        int sum = 0, maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            // Case 1: whole array up to i has sum == k
            if (sum == k) {
                maxLen = i + 1;
            }

            // Case 2: subarray from some index + 1 to i has sum == k
            if (map.containsKey(sum - k)) {
                int len = i - map.get(sum - k);
                maxLen = Math.max(maxLen, len);
            }

            // Store only the first occurrence of each prefix sum
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxLen;
    }

    public static int maxSubArraySum(int[] arr) {
        int maxEndingHere = arr[0];
        int maxSoFar = arr[0];

        for (int i = 1; i < arr.length; i++) {
            // Either extend the subarray or start fresh
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

}


