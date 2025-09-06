package dynamic_programming;
/*
There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.



Example 1:

Input: n = 3
Output: 3
Explanation: Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Example 2:

Input: n = 1
Output: 0


Constraints:

        1 <= n <= 1000

*/
import java.util.*;

public class TwoKeysKeyboard {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int minSteps(int n) {
        if (n == 1) return 0; // already have "A"
        if (memo.containsKey(n)) return memo.get(n);

        int ans = n; // worst case: paste 1 by 1
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                // case 1: build i first, then repeat (n/i) times
                ans = Math.min(ans, minSteps(i) + n / i);
                // case 2: build n/i first, then repeat i times
                ans = Math.min(ans, minSteps(n / i) + i);
            }
        }

        memo.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        TwoKeysKeyboard sol = new TwoKeysKeyboard();
        System.out.println(sol.minSteps(3)); // 3
        System.out.println(sol.minSteps(1)); // 0
        System.out.println(sol.minSteps(9)); // 6
    }
}
