package stack.july_13;
/******************************************************************************
 *  CircularNGE_NSE.java
 *
 *  Implements two interview‑favourite problems on arrays treated as **circular**:
 *
 *  1. nextGreaterCircular(int[] nums)   -> O(n) time, O(n) space
 *  2. nextSmallerCircular(int[] nums)   -> O(n) time, O(n) space
 *
 *  Both return an int[] where result[i] is the required neighbour for nums[i],
 *  or -1 if none exists.  Includes a tiny demo in main().
 ******************************************************************************/

import java.util.Arrays;
import java.util.Stack;

public class CircularNGE_NSE {

    /*───────────────────────── 1. PROBLEM ─────────────────────────*/
    /*
     * Given an integer array nums of length n, consider it circular—
     * i.e., after index n‑1 comes index 0.  For every element, find:
     *
     *   – Next Greater Element   ➜ first element to the right (wrapping
     *                             around) that is strictly greater.
     *   – Next Smaller Element   ➜ first element to the right (wrapping
     *                             around) that is strictly smaller.
     *
     * Return -1 if no such element exists.
     */

    /*──────────────────────── 2. INTUITION ─────────────────────────*/
    /*
     * Classic NGE/NSE uses a monotonic stack in O(n).  For the circular
     * twist, we simulate *two passes* over the array (indices 2n‑1 … 0).
     *
     *   • We still traverse **right‑to‑left** so the stack always holds
     *     “candidates to the right”.
     *   • Index mapping: realIndex = i % n
     *   • During the FIRST virtual pass (i >= n) we only build the stack.
     *   • During the SECOND pass (i < n) we record answers.
     *
     * Stack stores **values** (faster) because duplicates are allowed.
     * Condition flips between  ‘<=’  and  ‘>=’  for NGE vs NSE.
     */

    /*──────────────────────── 3. DRY‑RUN (NGE) ─────────────────────*/
    /*
     * nums = [1, 2, 1], n = 3             (expected: [2, -1, 2])
     *
     * i = 5 (idx 2) → stack = [1]
     * i = 4 (idx 1) → pop 1, push 2       stack = [2]
     * i = 3 (idx 0) → (skip recording)    stack = [2,1]
     *
     * i = 2 (idx 2) → pop 1, top 2 ⇒ ans[2]=2, push 1
     * i = 1 (idx 1) → pop 1, pop 2, empty ⇒ ans[1]=-1, push 2
     * i = 0 (idx 0) → top 2 ⇒ ans[0]=2
     */

    /*────────────────────────── 4. CODE ───────────────────────────*/
    /** Circular Next Greater Element. */
    public static int[] nextGreaterCircular(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Stack<Integer> st = new Stack<>();           // monotonic decreasing (values)

        for (int i = 2 * n - 1; i >= 0; i--) {
            int cur = nums[i % n];

            // maintain decreasing stack
            while (!st.isEmpty() && st.peek() <= cur) st.pop();

            if (i < n) {                             // second pass ⇒ record answer
                if (!st.isEmpty()) res[i] = st.peek();
            }
            st.push(cur);
        }
        return res;
    }

    /** Circular Next Smaller Element. */
    public static int[] nextSmallerCircular(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Stack<Integer> st = new Stack<>();           // monotonic increasing (values)

        for (int i = 2 * n - 1; i >= 0; i--) {
            int cur = nums[i % n];

            // maintain increasing stack
            while (!st.isEmpty() && st.peek() >= cur) st.pop();

            if (i < n) {                             // record during second pass
                if (!st.isEmpty()) res[i] = st.peek();
            }
            st.push(cur);
        }
        return res;
    }

    /*──────────────────────── 5. COMPLEXITY ───────────────────────*/
    /*
     * Time  : O(n)  – each element pushed & popped at most once.
     * Space : O(n)  – worst‑case stack size.
     */

    /*────────────────────────── DEMO ──────────────────────────────*/
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 1};
        System.out.println("Input  : " + Arrays.toString(nums1));
        System.out.println("NGE ⟳ : " + Arrays.toString(nextGreaterCircular(nums1))); // [2,-1,2]
        System.out.println("NSE ⟳ : " + Arrays.toString(nextSmallerCircular(nums1))); // [-1,1,-1]

        int[] nums2 = {5, 4, 3, 2, 1};
        System.out.println("\nInput  : " + Arrays.toString(nums2));
        System.out.println("NGE ⟳ : " + Arrays.toString(nextGreaterCircular(nums2))); // [-1,5,5,5,5]
        System.out.println("NSE ⟳ : " + Arrays.toString(nextSmallerCircular(nums2))); // [4,3,2,1,-1]
    }
}
