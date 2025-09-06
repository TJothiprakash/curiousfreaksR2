package dynamic_programming.dp_ongrids;


import java.util.Arrays;

/*. Perfect Squares
Medium
Topics
premium lock icon
Companies
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer;
in other words, it is the product of some integer with itself.
For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.



Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.


Constraints:

1 <= n <= 104

*/
public class PerfectSquares {


    public int numSquares(int n) {
        int offset = (int) Math.sqrt(n);
        int memo[][] = new int[n][offset];
        for(int[]row : memo)
        Arrays.fill(row, -1);

        return helper(n, offset, memo);

    }

    private int helper(int n, int largestSquare, int[][] memo) {
        if(n == 0) return 0;
        if( n < 0 || largestSquare == 0)return Integer.MAX_VALUE / 2;

        if(memo[n][largestSquare] != -1)return memo[n][largestSquare];
        int take = 1 + helper(n - largestSquare * largestSquare,largestSquare, memo);
        int notTake = helper(n, largestSquare - 1, memo);
        memo[n][largestSquare]=Math.min(take, notTake);
        return memo[n][largestSquare];
    }
}