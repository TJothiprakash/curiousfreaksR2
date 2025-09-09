package practicesessions.sept_8;

import java.util.Arrays;

public class UniqueBinrayTree {
    public int numTrees(int n) {
        // Recursive function
        int[]memo = new int[n+1];
        Arrays.fill(memo,0);
        return countBST(n, memo);
    }

    private int countBST(int n,int[]memo) {
        if (n == 0 || n == 1) return 1; // base case

        if(memo[n] != 0) return memo[n];

        int total = 0;
        for (int root = 1; root <= n; root++) {
            int left = countBST(root - 1, memo);
            int right = countBST(n - root, memo);

            total += left * right;

        }
        memo[n]= total;
        return memo[n];
    }
}