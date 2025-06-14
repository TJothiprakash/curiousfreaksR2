package dynamic_programming.dp_onsubsequence;
public class CoinChangeCombinations {

    public static int countWays(int[] coins, int sum) {
        int n = coins.length;
        Integer[][] memo = new Integer[n][sum + 1];
        return helper(0, sum, coins, memo);
    }

    private static int helper(int index, int target, int[] coins, Integer[][] memo) {
        if (target == 0) return 1; // one valid combination
        if (index == coins.length || target < 0) return 0;

        if (memo[index][target] != null) return memo[index][target];

        // pick coin[index]
        int pick = helper(index, target - coins[index], coins, memo);
        // skip coin[index]
        int notPick = helper(index + 1, target, coins, memo);

        return memo[index][target] = pick + notPick;
    }

    public static void main(String[] args) {
        System.out.println(countWays(new int[]{1, 2, 3}, 4)); // 4
        System.out.println(countWays(new int[]{2, 5, 3, 6}, 10)); // 5
        System.out.println(countWays(new int[]{3,5, 10}, 3)); // 0
    }
}

