package practicesessions.sept_18.dsa;

import java.util.Arrays;
import java.util.Comparator;

public class CoinChange {

    // this is a greedy approach it works only when the denominations are fixed.
    public int coinChange(int[] coins, int amount) {
        int[] coinsArray = Arrays.stream(coins).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int count = 0;
        for (int i = 0; i < coinsArray.length; i++) {
            if (amount >= coinsArray[i]) {
                while (amount >= coinsArray[i]) {
                    amount -= coinsArray[i];
                    count++;
                }
            }
        }
        return amount == 0 ? count : -1;
    }


    // dp way
    public int coinChangeDP(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        int ans = dfs(coins, amount, memo);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int dfs(int[] coins, int remaining, int[] memo) {
        if (remaining == 0) return 0;

        if (remaining < 0) return Integer.MAX_VALUE;

        if (memo[remaining] != -1) return memo[remaining];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, remaining - coin, memo);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, res + 1);
            }
        }
        memo[remaining] = min;
        return min;


    }

}


