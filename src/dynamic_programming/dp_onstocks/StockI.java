package dynamic_programming.dp_onstocks;


// stock should be bought on once
public class StockI {
    public static void main(String[] args) {
        int []arr = {1,1,34,1,36};
        System.out.println(new StockI().maxProfit(arr));
    }

    public int maxProfit(int[] prices) {
        int buyPrice = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (buyPrice > prices[i]) {
                buyPrice = prices[i];
            }

            profit = Math.max(profit, prices[i] - buyPrice);
        }

        return profit;
    }

}
