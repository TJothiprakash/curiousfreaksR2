package dynamic_programming.dp_onstocks;
//stock can be bought daily
public class StockII {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // If the current price is higher than the previous day's price, add the difference to maxProfit
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    // Testing the function with sample inputs
    public static void main(String[] args) {
        StockII solution = new StockII();

        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max profit for prices1: " + solution.maxProfit(prices1)); // Output: 7

        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max profit for prices2: " + solution.maxProfit(prices2)); // Output: 4

        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("Max profit for prices3: " + solution.maxProfit(prices3)); // Output: 0
    }
}
