package dynamic_programming.dp_onstocks;


/*You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


Constraints:

1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
*/
public class StockIV {
    public static void main(String[] args) {
        StockIV solution = new StockIV();

        int k1 = 2;
        int[] prices1 = {2, 4, 1};
        System.out.println("Max Profit: " + solution.maxProfit(k1, prices1)); // Output: 2

        int k2 = 2;
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println("Max Profit: " + solution.maxProfit(k2, prices2)); // Output: 7
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // Memoization table
        Integer[][][] memo = new Integer[n][k + 1][2];
        return getMaxProfit(prices, 0, k, true, memo);
    }

    private int getMaxProfit(int[] prices, int currentDay, int transactionsLeft, boolean canBuy, Integer[][][] memo) {
        // Base cases
        if (currentDay >= prices.length || transactionsLeft == 0) {
            return 0;
        }

        // Memoization: Return cached value if already computed
        if (memo[currentDay][transactionsLeft][canBuy ? 1 : 0] != null) {
            return memo[currentDay][transactionsLeft][canBuy ? 1 : 0];
        }

        int profit;
        if (canBuy) {
            // Option 1: Buy the stock
            int buy = -prices[currentDay] + getMaxProfit(prices, currentDay + 1, transactionsLeft, false, memo);
            // Option 2: Skip this day
            int skip = getMaxProfit(prices, currentDay + 1, transactionsLeft, true, memo);
            profit = Math.max(buy, skip);
        } else {
            // Option 1: Sell the stock
            int sell = prices[currentDay] + getMaxProfit(prices, currentDay + 1, transactionsLeft - 1, true, memo);
            // Option 2: Skip this day
            int skip = getMaxProfit(prices, currentDay + 1, transactionsLeft, false, memo);
            profit = Math.max(sell, skip);
        }

        // Store the result in memo
        memo[currentDay][transactionsLeft][canBuy ? 1 : 0] = profit;
        return profit;
    }


}
