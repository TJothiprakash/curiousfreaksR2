package dynamic_programming;

import java.util.*;


/*You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.



Example 1:

Input: pairs = [[1,2],[2,3],[3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4].
Example 2:

Input: pairs = [[1,2],[7,8],[4,5]]
Output: 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].


Constraints:

n == pairs.length
1 <= n <= 1000
-1000 <= lefti < righti <= 1000

*/


import java.util.*;
import java.util.*;

 class SolutionGreedy {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]); // sort by end
        int count = 0, curEnd = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (pair[0] > curEnd) {
                count++;
                curEnd = pair[1];
            }
        }
        return count;
    }
}


public class LongestChainPair {
    private int[][] pairs;
    private Integer[] dp;

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]); // sort by start
        this.pairs = pairs;
        this.dp = new Integer[pairs.length];
        int ans = 0;
        for (int i = 0; i < pairs.length; i++) {
            ans = Math.max(ans, helper(i));
        }
        return ans;
    }

    private int helper(int i) {
        if (dp[i] != null) return dp[i];

        int maxLen = 1; // at least the pair itself
        for (int j = i + 1; j < pairs.length; j++) {
            if (pairs[i][1] < pairs[j][0]) {
                maxLen = Math.max(maxLen, 1 + helper(j));
            }
        }
        return dp[i] = maxLen;
    }

    public static void main(String[] args) {
        LongestChainPair sol = new LongestChainPair();
        int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(sol.findLongestChain(pairs)); // 2
    }
}
