package dynamic_programming.dp_onlongestincreasingsubsequence;

public class LIS {
}


class TUF {
    static int longestIncreasingSubsequence(int arr[], int n) {

        int next[] = new int[n + 1];
        int cur[] = new int[n + 1];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

                int notTake = 0 + next[prev_index + 1];

                int take = 0;

                if (prev_index == -1 || arr[ind] > arr[prev_index]) {

                    take = 1 + next[ind + 1];
                }

                cur[prev_index + 1] = Math.max(notTake, take);
            }
            next = cur.clone();
        }

        return cur[0];
    }

    public static void main(String args[]) {

        int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));

    }
}
