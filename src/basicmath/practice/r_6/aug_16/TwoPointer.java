package basicmath.practice.r_6.aug_16;

import java.util.*;

public class TwoPointer {


    public static List<Integer> nextPermutation(int[] arr) {
        int n = arr.length;

        // Step 1: Find pivot
        int pivotIndex = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                pivotIndex = i;
                break;
            }
        }

        // Step 2: If no pivot found -> reverse whole array
        if (pivotIndex == -1) {
            reverse(arr, 0, n - 1);
        } else {
            // Step 3: Find element just larger than pivot (from right)
            for (int j = n - 1; j > pivotIndex; j--) {
                if (arr[j] > arr[pivotIndex]) {
                    swap(arr, j, pivotIndex);
                    break;
                }
            }
            // Step 4: Reverse the suffix
            reverse(arr, pivotIndex + 1, n - 1);
        }

        // Convert result to List<Integer>
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            result.add(num);
        }
        return result;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }


    public static long maxProductSubarray(int[] arr) {
        long maxEndingHere = arr[0];
        long minEndingHere = arr[0];
        long maxProduct = arr[0];

        for (int i = 1; i < arr.length; i++) {
            long temp = maxEndingHere; // store before swapping
            if (arr[i] < 0) {
                // swap
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            maxEndingHere = Math.max(arr[i], arr[i] * maxEndingHere);
            minEndingHere = Math.min(arr[i], arr[i] * minEndingHere);

            maxProduct = Math.max(maxProduct, maxEndingHere);
        }
        return maxProduct;
    }

    int countInversions(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    int mergeSortBasedCOuntInversion(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);

    }

    static int mergeSort(int[] arr, int start, int end) {
        int count = 0;

        while (start < end) {
            int mid = start + end / 2;
            count += mergeSort(arr, start, mid);
            count += mergeSort(arr, mid + 1, end);
            count += merge(arr, start, mid, end);

        }
        return count;
    }

    private static int merge(int[] arr, int start, int mid, int end) {
        int[] leftArr = new int[mid - start + 1];
        int[] rightArr = new int[end - mid];

        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[start + i];

        }
        for (int j = 0; j < rightArr.length; j++) {
            rightArr[j] = arr[mid + j];

        }
        int i = 0, j = 0, k = start, count = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                count += (leftArr.length - i); // remaining left elements > rightArr[j]

            }
            while (i < leftArr.length) arr[k++] = leftArr[i++];
            while (j < rightArr.length) arr[k++] = rightArr[j++];

        }
        return count;

    }


    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }


    int kadanes(int arr[]) {
        int sum = arr[0];        // current running sum
        int maxSum = arr[0];     // overall max sum so far

        for (int i = 1; i < arr.length; i++) {
            // either extend the current subarray OR start fresh from arr[i]
            sum = Math.max(arr[i], sum + arr[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // longest subarray with sum k
    int longestSubarraywithSumK(int arr[], int target) {
        int len = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum == target) {
                return len;
            } else if (sum < target) {
                len++;
                sum += arr[i];
            } else {
                sum = 0;
                len = 0;
            }
        }
        return -1;// not found
    }


    int longestSubArrayUsingPrefixSum(int arr[], int target) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {

            prefixSum += arr[i];

            if (prefixSum == target) {
                maxLen = i + 1;
            }

            if (hashmap.containsKey(prefixSum - target)) {
                int index = hashmap.get(prefixSum - target);
                maxLen = Math.max(maxLen, i - index);

            }
            hashmap.putIfAbsent(prefixSum, i);

        }
        return maxLen;

    }
//    threesum, foursum

    public static boolean threeSum(int arr[], int target) {
        Arrays.sort(arr);
        int numneeded = 3;
        int sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        return helper(0, arr.length, arr, target, numneeded, sum, list, result);
    }

    private static boolean helper(int start, int end, int[] arr, int target, int numneeded, int sum, List<Integer> list, List<List<Integer>> result) {
        if (numneeded != 2) {
            for (int i = start; i < end - numneeded + 1; i++) {
                sum += arr[start];
                list.add(arr[start]);
                helper(start + 1, end, arr, target, numneeded - 1, sum, list, result);
                list.remove(list.size() - 1);
            }
        }
        int left = start, right = end;

        while (left < right) {
            sum = arr[left] + arr[right];

            if (sum == target) {
                list.add(arr[left]);
                list.add(arr[right]);
                result.add(new ArrayList<>(list));

                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static boolean twoSum(int[] arr, int target) {
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

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 8, 10};
        int target = 14;
        int[] height = {9, 4, 2, 0, 3, 2, 5};
        System.out.println("Trapped water = " + trap(height)); // Output: 9

//        System.out.println(twoSum(arr, target)); // true
    }


}
