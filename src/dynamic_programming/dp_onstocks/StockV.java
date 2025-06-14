package dynamic_programming.dp_onstocks;
/*You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like

 (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:


After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0


Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
*/

import java.util.HashMap;
import java.util.Map;

public class StockV {
    private Map<String, Integer> memo = new HashMap<>();


    public int maxProfit(int[] prices) {
        return dp(prices, 0, false);
    }

    private int dp(int[] prices, int day, boolean holding) {
        if (day >= prices.length) return 0;

        String key = day + "," + holding;
        if (memo.containsKey(key)) return memo.get(key);

        int doNothing = dp(prices, day + 1, holding);

        if (holding) {
            int sell = dp(prices, day + 2, false) + prices[day];
            memo.put(key, Math.max(doNothing, sell));
        } else {
            int buy = dp(prices, day + 1, true) - prices[day];
            memo.put(key, Math.max(doNothing, buy));
        }

        return memo.get(key);
    }
}
