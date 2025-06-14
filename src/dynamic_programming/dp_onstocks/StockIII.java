package dynamic_programming.dp_onstocks;

/*You are given an array prices where prices[i] is the price of
 a given stock on the ith day.

Find the maximum profit you can achieve.
You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
*/
public class StockIII {

    public static void main(String[] args) {
        StockIII solution = new StockIII();

        int[] prices1 = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Max Profit: " + solution.maxProfit(prices1)); // Output: 6

        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max Profit: " + solution.maxProfit(prices2)); // Output: 4

        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit: " + solution.maxProfit(prices3)); // Output: 0
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int[] leftProfit = new int[n]; // Max profit if we sell on or before day i
        int[] rightProfit = new int[n]; // Max profit if we buy on or after day i

        // Step 1: Calculate maximum profit for the left half
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPrice);
        }/*
<<<<<<<<<<<<<<  ✨ Codeium Command ⭐ >>>>>>>>>>>>>>>>
        // At the end of the loop, leftProfit[i] is the maximum profit we can get
        // if we sell on or before day i. The maximum profit is the sum of the
        // profit from the left half and the right half.

<<<<<<<  c6576f8d-98d5-4b08-8754-66ee64840f5a  >>>>>>>*/
        // Step 2: Calculate maximum profit for the right half
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
        }

        // Step 3: Combine the two profits
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, leftProfit[i] + rightProfit[i]);
        }

        return maxProfit;
    }
/*  public int maxProfit(int[] prices) {
        int n = prices.length;
        // Initialize a 3D array for memoization
        Integer[][][] memo = new Integer[n][3][2];
        return getMaxProfit(prices, 0, 2, true, memo);
    }

    private int getMaxProfit(int[] prices, int currentDay, int transactionsLeft, boolean canBuy, Integer[][][] memo) {
        // Base case: no days left or no transactions left
        if (currentDay >= prices.length || transactionsLeft == 0) {
            return 0;
        }

        // Memoization: Check if this state has already been computed
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
    */


}
