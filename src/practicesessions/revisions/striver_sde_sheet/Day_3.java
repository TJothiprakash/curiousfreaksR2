package practicesessions.revisions.striver_sde_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day_3 {
    public static void main(String[] args) {
        System.out.println(GridUniquePaths.uniquePathsMemo(3, 7));

    }

    // search in a sorted 2d matrix
    public static int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{-1, -1}; // Indicating the target is not found
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int low = 0;
        int high = rows * cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid overflow

            int midValue = matrix[mid / cols][mid % cols]; // Accessing mid in 2D matrix

            if (midValue == target) {
                return new int[]{mid / cols, mid % cols}; // Return row and column
            } else if (midValue < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new int[]{-1, -1}; // Target not found
    }


    //TODO
    // calculate x power of n times efficientlly
    public static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }

        int temp = power(x, n / 2);

        if (n % 2 == 0) {
            return temp * temp;
        } else {
            return x * temp * temp;
        }


    }

    public static int powerUsingIteration(int x, int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * x;
        }
        return ans;
    }

    // majority element
    public static int majorityElement(int[] v) {
        //size of the given array:
        int n = v.length;
        int cnt = 0; // count
        int el = 0; // Element

        //applying the algorithm:
        for (int i = 0; i < n; i++) {
            if (cnt == 0) {
                cnt = 1;
                el = v[i];
            } else if (el == v[i]) cnt++;
            else cnt--;
        }

        //checking if the stored element
        // is the majority element:
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == el) cnt1++;
        }

        if (cnt1 > (n / 2)) return el;
        return -1;
    }

    public static List<Integer> majorityElementExtendedVersionOfBoyerMoorsAlgo(int[] v) {
        int n = v.length; //size of the array

        int cnt1 = 0, cnt2 = 0; // counts
        int el1 = Integer.MIN_VALUE; // element 1
        int el2 = Integer.MIN_VALUE; // element 2

        // applying the Extended Boyer Moore's Voting Algorithm:
        for (int i = 0; i < n; i++) {
            if (cnt1 == 0 && el2 != v[i]) {
                cnt1 = 1;
                el1 = v[i];
            } else if (cnt2 == 0 && el1 != v[i]) {
                cnt2 = 1;
                el2 = v[i];
            } else if (v[i] == el1) cnt1++;
            else if (v[i] == el2) cnt2++;
            else {
                cnt1--;
                cnt2--;
            }
        }

        List<Integer> ls = new ArrayList<>(); // list of answers

        // Manually check if the stored elements in
        // el1 and el2 are the majority elements:
        cnt1 = 0;
        cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == el1) cnt1++;
            if (v[i] == el2) cnt2++;
        }

        int mini = (int) (n / 3) + 1;
        if (cnt1 >= mini) ls.add(el1);
        if (cnt2 >= mini) ls.add(el2);

        // Uncomment the following line
        // if it is told to sort the answer array:
        //Collections.sort(ls); //TC --> O(2*log2) ~ O(1);

        return ls;
    }

    //grid paths
    public class GridUniquePaths {

        // Memoization DP approach
        public static int uniquePathsMemo(int m, int n) {
            int[][] memo = new int[m][n];
            // Initialize memoization table with -1
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return countPaths(0, 0, m, n, memo);
        }

        // Helper recursive function with memoization
        private static int countPaths(int i, int j, int m, int n, int[][] memo) {
            // Base case: If we reach the bottom-right corner, return 1
            if (i == m - 1 && j == n - 1) {
                return 1;
            }

            // If we go out of bounds, return 0
            if (i >= m || j >= n) {
                return 0;
            }

            // Check if the value is already computed
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            // Recursively count the paths from the right and down
            int rightPaths = countPaths(i, j + 1, m, n, memo);
            int downPaths = countPaths(i + 1, j, m, n, memo);

            // Store the result in memo table and return it
            memo[i][j] = rightPaths + downPaths;
            return memo[i][j];
        }



    }

    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        int cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && arr[i] > 2 * arr[right]) right++;
            cnt += (right - (mid + 1));
        }
        return cnt;
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;
        int mid = (low + high) / 2 ;
        cnt += mergeSort(arr, low, mid);  // left half
        cnt += mergeSort(arr, mid + 1, high); // right half
        cnt += countPairs(arr, low, mid, high); //Modification
        merge(arr, low, mid, high);  // merging sorted halves
        return cnt;
    }

    public static int team(int[] skill, int n) {
        return mergeSort(skill, 0, n - 1);
    }


}
