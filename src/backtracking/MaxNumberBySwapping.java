package backtracking;

/*
Given a number k and string s of digits denoting a positive integer, build the largest number possible by performing swap operations on the digits of s at most k times.

Examples :

Input: s = "1234567", k = 4
Output: 7654321
Explanation: Three swaps can make the input 1234567 to 7654321, swapping 1 with 7, 2 with 6 and finally 3 with 5.
Input: s = "3435335", k = 3
Output: 5543333
Explanation: Three swaps can make the input 3435335 to 5543333, swapping 3 with 5, 4 with 5 and finally 3 with 4.
Input: s = "1034", k = 2
Output: 4301
Explanation: Two swaps can make the input 1034 to 4301, swapping 1 with 4 and finally 0 with 3.
Constraints:
        1 ≤ s.size() ≤ 15
        1 ≤ k ≤ 7

Expecte


ChatGPT said:
Yes! This problem can be solved using backtracking to explore all possible swaps (up to k times) and keep track of the maximum number formed.

        ✅ Approach: Backtracking + Greedy Pruning
Use backtracking to explore all valid swaps.

At each recursive level:

Try swapping the current digit with any digit to its right that is greater (greedy choice).

Track the maximum number seen so far.

Recurse with k-1 after each swap.

Backtrack (undo the swap).

Stop recursion when k == 0.

        ✅ Java Code:
java
  */
public class MaxNumberBySwapping {

    static String maxNumber;

    public static String findMaximumNum(String str, int k) {
        maxNumber = str;
        char[] chars = str.toCharArray();
        backtrack(chars, k, 0);
        return maxNumber;
    }

    private static void backtrack(char[] chars, int k, int index) {
        if (k == 0) return;

        int n = chars.length;
        char maxChar = chars[index];

        // Find the maximum digit to the right of or at index
        for (int i = index + 1; i < n; i++) {
            if (chars[i] > maxChar) {
                maxChar = chars[i];
            }
        }

        // If current digit is already max, no need to swap
        if (maxChar != chars[index]) {
            k--;
        }

        // Try all swaps that can bring max digit to current position
        for (int i = index; i < n; i++) {
            if (chars[i] == maxChar) {
                swap(chars, index, i);

                String current = new String(chars);
                if (current.compareTo(maxNumber) > 0) {
                    maxNumber = current;
                }

                backtrack(chars, k, index + 1);
                swap(chars, index, i); // backtrack
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        if (i == j) return;
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Test it
    public static void main(String[] args) {
        System.out.println(findMaximumNum("1234567", 4)); // 7654321
        System.out.println(findMaximumNum("3435335", 3)); // 5543333
        System.out.println(findMaximumNum("1034", 2));    // 4301
    }
}/*
🔍 Explanation for "1034", k = 2:
Step 1: Swap 1 and 4 → 4031

Step 2: Swap 0 and 3 → 4301

Result = 4301

        ✅ Time Complexity:
At most O(k * n^2) — since we explore up to k swaps and n digit positions.

Safe due to constraints: n ≤ 15, k ≤ 7*/