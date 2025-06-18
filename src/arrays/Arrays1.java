package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Arrays1 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array:");
        printArray(arr);

        rotateRight(arr, 2);
        System.out.println("After rotating right by 2 positions:");
        printArray(arr);

        rotateLeft(arr, 2);
        System.out.println("After rotating left by 2 positions:");
        printArray(arr);

        int[] arr2 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum: " + maxSubarraySum(arr2));

        int[] arr3 = {1, 3, 5};
        int[] arr4 = {2, 4, 6};
        System.out.println("Merged sorted arrays:");
        printArray(mergeSortedArrays(arr3, arr4));

        int[] arr5 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Duplicates in array:");
        printArray(findDuplicates(arr5));
    }

    public static void rotateRight(int[] arr, int k) {
        if (arr == null || arr.length == 0) return;
        k = k % arr.length;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    public static void rotateLeft(int[] arr, int k) {
        if (arr == null || arr.length == 0) return;
        k = k % arr.length;
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int maxSubarraySum(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static int[] findDuplicates(int[] arr) {
        int[] temp = new int[arr.length];
        int k = 0;

        for (int i = 0; i < arr.length; i++) {
            int abs = Math.abs(arr[i]);
            if (arr[abs - 1] > 0) {
                arr[abs - 1] = -arr[abs - 1];
            } else {
                temp[k++] = abs;
            }
        }

        int[] result = new int[k];
        System.arraycopy(temp, 0, result, 0, k);
        return result;
    }

    public static ArrayList<int[]> twoSum(int[] nums, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result.add(new int[]{nums[i], nums[j]});
                }
            }
        }
        return result;
    }

    public static HashSet<String> twoSumUnique(int[] nums, int target) {
        HashSet<String> result = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] pair = {Math.min(nums[i], nums[j]), Math.max(nums[i], nums[j])};
                    result.add(pair[0] + "," + pair[1]);
                }
            }
        }
        return result;
    }

    public static ArrayList<int[]> threeSum(int[] nums, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == target) {
                        result.add(new int[]{nums[i], nums[j], nums[k]});
                    }
                }
            }
        }
        return result;
    }

    public static HashSet<String> threeSumUnique(int[] nums, int target) {
        HashSet<String> result = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == target) {
                        result.add(nums[i] + "," + nums[j] + "," + nums[k]);
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<int[]> fourSum(int[] nums, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            result.add(new int[]{nums[i], nums[j], nums[k], nums[l]});
                        }
                    }
                }
            }
        }
        return result;
    }

    public static HashSet<String> fourSumUnique(int[] nums, int target) {
        HashSet<String> result = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            result.add(nums[i] + "," + nums[j] + "," + nums[k] + "," + nums[l]);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<int[]> optimizedThreeSum(int[] nums, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    result.add(new int[]{nums[i], nums[left], nums[right]});
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static ArrayList<int[]> optimizedFourSum(int[] nums, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(new int[]{nums[i], nums[j], nums[left], nums[right]});
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<int[]> findTriplets(int[] arr, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    result.add(new int[]{arr[i], arr[left], arr[right]});
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

}