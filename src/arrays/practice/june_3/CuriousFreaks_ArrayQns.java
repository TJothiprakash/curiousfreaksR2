package arrays.practice.june_3;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class CuriousFreaks_ArrayQns {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        CuriousFreaks_ArrayQns obj = new CuriousFreaks_ArrayQns();
        int[] ans = obj.minandMax(arr);
        System.out.println(ans[0] + " " + ans[1]);
    }


    //  O(n) O(1)
    @Contract(pure = true)
    public static int findKthElementUsingTwoPointer(int[] a, int[] b, int k) {
        int i = 0, j = 0, count = 0;
        int result = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result = a[i++];
            } else {
                result = b[j++];
            }
            count++;
            if (count == k) return result;
        }

        while (i < a.length) {
            result = a[i++];
            count++;
            if (count == k) return result;
        }

        while (j < b.length) {
            result = b[j++];
            count++;
            if (count == k) return result;
        }

        return -1; // In case k is out of bounds
    }

    // O(log n) O(1)
    public static int findKthElement(int[] a, int[] b, int k) {
        int n = a.length, m = b.length;

        // Always binary search on the smaller array
        if (n > m) return findKthElement(b, a, k);

        int low = Math.max(0, k - m), high = Math.min(k, n);

        while (low <= high) {
            int cutA = (low + high) / 2;
            int cutB = k - cutA;

            int l1 = (cutA == 0) ? Integer.MIN_VALUE : a[cutA - 1];
            int l2 = (cutB == 0) ? Integer.MIN_VALUE : b[cutB - 1];
            int r1 = (cutA == n) ? Integer.MAX_VALUE : a[cutA];
            int r2 = (cutB == m) ? Integer.MAX_VALUE : b[cutB];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2); // Found the correct partition
            } else if (l1 > r2) {
                high = cutA - 1; // Too many from a[]
            } else {
                low = cutA + 1; // Too few from a[]
            }
        }

        return -1; // Invalid case (shouldn't occur if input is valid)
    }

    //O(n^2) O(1)
    int countZeroSumTriplets(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); // Sort the array
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            // Skip duplicates for first element
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == 0) {
                    count++;
                    System.out.println("Triplet: " + arr[i] + ", " + arr[left] + ", " + arr[right]);

                    // Skip duplicates
                    while (left < right && arr[left] == arr[left + 1]) left++;
                    while (left < right && arr[right] == arr[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    //O(n) O(k)
    int[] rotateArrayByKPlaces(int arr[], int k) {
        int n = arr.length;
        k = k % n; // handle k > n
        int[] temp = new int[k];

        // Step 1: Copy first k elements
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        // Step 2: Shift remaining elements to the left
        for (int i = k; i < n; i++) {
            arr[i - k] = arr[i];
        }

        // Step 3: Copy temp elements to the end
        for (int i = 0; i < k; i++) {
            arr[n - k + i] = temp[i];
        }

        return arr;
    }

    //O(log n) O(1)
    int[] sort012(int[] arr) {
        int low = 0, high = arr.length - 1;

        for (int mid = 0; mid <= high; ) {
            if (arr[mid] == 0) {
                // Swap arr[mid] and arr[low]
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;
                mid++;
                low++;
            } else if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 2) {
                // Swap arr[mid] and arr[high]
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
                // Don't increment mid here!
            }
        }

        return arr;
    }

    //O(n) O(1)
    int[] minandMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }

        }
        return new int[]{min, max};
    }

    //O(n logn) O(1)
    int thirdLargest(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 3];

    }

    //O(n) O(1)
    int findMissingElement(int arr[]) {
        int n = arr[arr.length - 1];
        int sum = (n * (n + 1)) / 2;
        int actualSum = 0;
        for (int i = 0; i < arr.length; i++) {
            actualSum = actualSum + arr[i];
        }
        return sum - actualSum;
    }

    //O(2n)  O(1)
    public static int[] findMissingRepeatingNumbers(int[] a) {
        int n = a.length; // size of the array
        int[] hash = new int[n + 1]; // hash array

        //update the hash array:
        for (int i = 0; i < n; i++) {
            hash[a[i]]++;
        }

        //Find the repeating and missing number:
        int repeating = -1, missing = -1;
        for (int i = 1; i <= n; i++) {
            if (hash[i] == 2) repeating = i;
            else if (hash[i] == 0) missing = i;

            if (repeating != -1 && missing != -1)
                break;
        }
        int[] ans = {repeating, missing};
        return ans;
    }

}
//| Method                          | Your Complexity | Verdict     | Notes                         |
//| ------------------------------- | --------------- | ----------- | ----------------------------- |
//| `findKthElementUsingTwoPointer` | O(k) O(1)       | ✅ Correct   |                               |
//| `findKthElement`                | O(log n) O(1)   | ❌ Needs Fix | Should be `O(log(min(n, m)))` |
//| `countZeroSumTriplets`          | O(n^2) O(1)     | ✅ Correct   |                               |
//| `rotateArrayByKPlaces`          | O(n) O(k)       | ✅ Correct   |                               |
//| `sort012`                       | O(log n) O(1)   | ❌ Needs Fix | Should be `O(n) O(1)`         |
//| `minandMax`                     | O(n) O(1)       | ✅ Correct   |                               |
//| `thirdLargest`                  | O(n log n) O(1) | ✅ Correct   |                               |
//| `findMissingElement`            | O(n) O(1)       | ✅ Correct   |                               |
//| `findMissingRepeatingNumbers`   | O(2n) O(1)      | ❌ Needs Fix | Should be `O(n) O(n)`         |