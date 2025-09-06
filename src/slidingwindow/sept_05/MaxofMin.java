package slidingwindow.sept_05;

import java.util.*;

public class MaxofMin {

    public int[] maxOfMinBruteForce(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // for each window size k
        for (int k = 1; k <= n; k++) {
            int maxOfMins = Integer.MIN_VALUE;

            // slide window of size k
            for (int i = 0; i <= n - k; i++) {
                int minInWindow = Integer.MAX_VALUE;

                // compute min in current window
                for (int j = i; j < i + k; j++) {
                    minInWindow = Math.min(minInWindow, nums[j]);
                }

                maxOfMins = Math.max(maxOfMins, minInWindow);
            }

            ans[k - 1] = maxOfMins;
        }

        return ans;
    }

    // optimalway
    public int[] maxOfMin(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];   // previous smaller index
        int[] right = new int[n];  // next smaller index
        int[] res = new int[n + 1]; // answers for window size

        // Step 1: Previous Smaller Element (PSE)
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        // Step 2: Next Smaller Element (NSE)
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        // Step 3: Fill candidates for each window size
        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 1;
            res[len] = Math.max(res[len], nums[i]);
        }

        // Step 4: Propagate answers backward
        for (int i = n - 1; i >= 1; i--) {
            res[i] = Math.max(res[i], res[i + 1]);
        }

        // Step 5: Build final output (ignore res[0])
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = res[i + 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxofMin sol = new MaxofMin();
        int[] nums = {10, 20, 30, 50, 10, 70, 30};
        System.out.println(Arrays.toString(sol.maxOfMinBruteForce(nums)));
        // Expected: [70, 30, 20, 10, 10, 10, 10]
    }
}
