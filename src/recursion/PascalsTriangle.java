package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PascalsTriangle {

    /*Variation	Method	Time Complexity
Entire nth Row	getPascalRow(n)	O(n)
Specific C(n, r)	pascalValue(n, r)	O(r)
Full Triangle	generatePascalTriangle(n)	O(n²)
Print Using nCr	printPascalUsingNCR(n)	O(n²)
Check if Exists	existsInPascal(num)	O(n²)
Max in Row	maxInPascalRow(n)	O(n)

*/
    public List<Integer> getPascalRow(int n) {
        List<Integer> row = new ArrayList<>();
        long val = 1;
        row.add(1); // C(n, 0) = 1
        for (int i = 1; i < n; i++) {
            val = val * (n - i) / i;
            row.add((int) val);
        }
        return row;
    }

    public int pascalValue(int n, int r) {
        long res = 1;
        for (int i = 0; i < r; i++) {
            res *= (n - i);
            res /= (i + 1);
        }
        return (int) res;
    }

    public List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            int val = 1;
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) row.add(1);
                else row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
            }
            triangle.add(row);
        }
        return triangle;
    }

    public void printPascalUsingNCR(int n) {
        for (int i = 0; i < n; i++) {
            long val = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(val + " ");
                val = val * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }

    public boolean existsInPascal(int num) {
        for (int n = 0; n <= num; n++) {
            long val = 1;
            for (int r = 0; r <= n; r++) {
                if (val == num) return true;
                val = val * (n - r) / (r + 1);
            }
        }
        return false;
    }

    public int maxInPascalRow(int n) {
        List<Integer> row = getPascalRow(n);
        return Collections.max(row);
    }

    //   ----------------------using recursion---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    public List<Integer> getPascalRowRecursive(int row) {
        if (row == 1) return List.of(1);
        List<Integer> prev = getPascalRowRecursive(row - 1);
        List<Integer> curr = new ArrayList<>();
        curr.add(1);
        for (int i = 1; i < prev.size(); i++) {
            curr.add(prev.get(i - 1) + prev.get(i));
        }
        curr.add(1);
        return curr;
    }

    public int pascalRecursive(int n, int r) {
        if (r == 0 || r == n) return 1;
        return pascalRecursive(n - 1, r - 1) + pascalRecursive(n - 1, r);
    }

    public void printTriangleRecursive(int n) {
        for (int i = 0; i < n; i++) {
            printRow(i);
            System.out.println();
        }
    }

    private void printRow(int row) {
        for (int col = 0; col <= row; col++) {
            System.out.print(pascalRecursive(row, col) + " ");
        }
    }

    public int nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }

    public void printUsingNCR(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(nCr(i, j) + " ");
            }
            System.out.println();
        }
    }

    public boolean existsInPascalRecursive(int num) {
        for (int n = 0; n <= num; n++) {
            for (int r = 0; r <= n; r++) {
                if (nCr(n, r) == num) return true;
            }
        }
        return false;
    }

    public int maxInPascalRecursive(int n) {
        int max = 0;
        for (int r = 0; r <= n; r++) {
            int val = pascalRecursive(n, r);
            max = Math.max(max, val);
        }
        return max;
    }

    //----------------lets memoize ---------------------------------------------------------------------------

    /*Approach	Time Complexity
    Pure recursion	Exponential
    With Memoization	O(n²)*/
// Approach	Time Complexity
// Pure recursion	Exponential
// With Memoization	O(n²)
    int[][] dp;

    public int pascalMemo(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (dp[n][r] != -1) return dp[n][r];
        dp[n][r] = pascalMemo(n - 1, r - 1) + pascalMemo(n - 1, r);
        return dp[n][r];
    }

    public List<Integer> getRowMemo(int n) {
        dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        List<Integer> res = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            res.add(pascalMemo(n - 1, r));
        }
        return res;
    }

    public void printPascalTriangle(int n) {
        dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(pascalMemo(i, j) + " ");
            }
            System.out.println();
        }
    }

    public boolean existsInPascalMemo(int num) {
        for (int n = 0; n <= num; n++) {
            for (int r = 0; r <= n; r++) {
                if (pascalMemo(n, r) == num) return true;
            }
        }
        return false;
    }

    public int maxInRow(int n) {
        dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        int max = 0;
        for (int r = 0; r <= n; r++) {
            max = Math.max(max, pascalMemo(n, r));
        }
        return max;
    }

}
