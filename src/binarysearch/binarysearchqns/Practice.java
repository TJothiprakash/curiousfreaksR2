package binarysearch.binarysearchqns;

import java.util.ArrayList;

/**
 * A collection of binary search based utility algorithms for common problems.
 */
public class Practice {

    /**
     * Finds the floor of a given number x in a sorted array.
     * The floor is the greatest element smaller than or equal to x.
     *
     * @param arr The sorted array.
     * @param n   The size of the array.
     * @param x   The target number.
     * @return The floor value or -1 if it doesn't exist.
//     */
//    O( log n base 2 ) O( 1)
    static int findFloor(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= x) {
                ans = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * Finds the ceil of a given number x in a sorted array.
     * The ceil is the smallest element greater than or equal to x.
     *
     * @param arr The sorted array.
     * @param n   The size of the array.
     * @param x   The target number.
     * @return The ceil value or -1 if it doesn't exist.
    //    O( log n base 2 ) O( 1)
     */
    static int findCeil(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Finds the first occurrence of a given element in a sorted array.
     *
     * @param arr The array.
     * @param n   The size of the array.
     * @param k   The target element.
     * @return Index of the first occurrence, or -1 if not found.
     */
//    O( log n base 2 ) O( 1)
    public static int firstOccurrence(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        int first = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) {
                first = mid;
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first;
    }

    /**
     * Finds the last occurrence of a given element in a sorted array.
     *
     * @param arr The array.
     * @param n   The size of the array.
     * @param k   The target element.
     * @return Index of the last occurrence, or -1 if not found.
     */
//    O( log n base 2 ) O( 1)

    public static int lastOccurrence(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        int last = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) {
                last = mid;
                low = mid + 1;
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    /**
     * Returns both first and last positions of a target in the array.
     *
     * @param arr The array.
     * @param n   The size of the array.
     * @param k   The target element.
     * @return Array of size 2 containing [first, last] indices or [-1, -1] if not found.
     */
//    2 *O( log n base 2 ) O( 1)
    public static int[] firstAndLastPosition(int[] arr, int n, int k) {
        int first = firstOccurrence(arr, n, k);
        if (first == -1) return new int[]{-1, -1};
        int last = lastOccurrence(arr, n, k);
        return new int[]{first, last};
    }

    /**
     * Counts the number of occurrences of an element in the array.
     *
     * @param arr The array.
     * @param n   The size of the array.
     * @param x   The target element.
     * @return Count of occurrences.
     */
//    O( log n base 2 ) O( 1)

    public static int countNoOfOccurrence(int[] arr, int n, int x) {
        int[] ans = firstAndLastPosition(arr, n, x);
        if (ans[0] == -1) return 0;
        return (ans[1] - ans[0] + 1);
    }

    /**
     * Searches a target element in a rotated sorted array that may contain duplicates.
     *
     * @param arr The rotated array.
     * @param k   The target element.
     * @return True if found, false otherwise.
    //    O( log n base 2 ) O( 1)
     */
    public static boolean searchInARotatedSortedArrayII(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == k) return true;

            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
                continue;
            }

            if (arr[low] <= arr[mid]) {
                if (arr[low] <= k && k <= arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (arr[mid] <= k && k <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * Finds the minimum element in a rotated sorted array.
     *
     * @param arr The rotated array.
     * @return Minimum element.
     */
//    O( log n base 2 ) O( 1)

    public static int findMin(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[low] <= arr[high]) {
                ans = Math.min(ans, arr[low]);
                break;
            }

            if (arr[low] <= arr[mid]) {
                ans = Math.min(ans, arr[low]);
                low = mid + 1;
            } else {
                ans = Math.min(ans, arr[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * Finds the number of rotations in a rotated sorted array.
     *
     * @param arr The rotated array.
     * @return Index of the minimum element (rotation count).
     */
//    O( log n base 2 ) O( 1)

    public static int findKRotation(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;
        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[low] <= arr[high]) {
                if (arr[low] < ans) {
                    index = low;
                    ans = arr[low];
                }
                break;
            }

            if (arr[low] <= arr[mid]) {
                if (arr[low] < ans) {
                    index = low;
                    ans = arr[low];
                }
                low = mid + 1;
            } else {
                if (arr[mid] < ans) {
                    index = mid;
                    ans = arr[mid];
                }
                high = mid - 1;
            }
        }
        return index;
    }

    /**
     * Finds the single non-duplicate element in a sorted array of pairs.
     *
     * @param arr Array with all elements appearing twice except one.
     * @return The unique element.
     */
//    O( log n base 2 ) O( 1)

    public static int singleNonDuplicate(ArrayList<Integer> arr) {
        int n = arr.size();

        if (n == 1) return arr.get(0);
        if (!arr.get(0).equals(arr.get(1))) return arr.get(0);
        if (!arr.get(n - 1).equals(arr.get(n - 2))) return arr.get(n - 1);

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (!arr.get(mid).equals(arr.get(mid + 1)) && !arr.get(mid).equals(arr.get(mid - 1))) {
                return arr.get(mid);
            }

            if ((mid % 2 == 1 && arr.get(mid).equals(arr.get(mid - 1)))
                    || (mid % 2 == 0 && arr.get(mid).equals(arr.get(mid + 1)))) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Finds a peak element in the array.
     * A peak is greater than its neighbors.
     *
     * @param arr Array to search.
     * @return Index of a peak element.
     */
//    O( log n base 2 ) O( 1)

    public static int findPeakElement(ArrayList<Integer> arr) {
        int n = arr.size();

        if (n == 1) return 0;
        if (arr.get(0) > arr.get(1)) return 0;
        if (arr.get(n - 1) > arr.get(n - 2)) return n - 1;

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr.get(mid - 1) < arr.get(mid) && arr.get(mid) > arr.get(mid + 1)) {
                return mid;
            }

            if (arr.get(mid) > arr.get(mid - 1)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Standard binary search in sorted array.
     *
     * @param arr    The array.
     * @param target Target value.
     * @return Index or -1 if not found.
     */
//    O( log n base 2 ) O( 1)

    int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return -1;
    }

    /**
     * Finds lower bound of target in array.
     * First index where arr[i] >= target.
     *
     * @param arr    The array.
     * @param target The target value.
     * @return Index or -1.
     */
//    O( log n base 2 ) O( 1)

    int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    /**
     * Finds upper bound of target in array.
     * Last index where arr[i] <= target.
     *
     * @param arr    The array.
     * @param target The target value.
     * @return Index or -1.
     */
//    O( log n base 2 ) O( 1)

    int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    /**
     * Searches a target in a rotated sorted array.
     *
     * @param arr    The rotated sorted array.
     * @param target The target value.
     * @return Index or -1.
     */
//    O( log n base 2 ) O( 1)

    int searchInRotatedSortedArray(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;

            if (arr[left] <= arr[mid]) {
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
