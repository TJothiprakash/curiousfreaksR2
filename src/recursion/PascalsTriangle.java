package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PascalsTriangle {

    /*
    Pascal's Triangle Formulas and Concepts:
    C(n, r) = n! / (r! * (n - r)!) - Combination formula
    C(n, r) = C(n-1, r-1) + C(n-1, r) - Recursive relation
    */

    /*/*
Summary of Pascal Triangle Variations:

Variation             Method                      Time Complexity
-----------------------------------------------------------------
Entire nth Row        getPascalRow(n)             O(n)
Specific C(n, r)      pascalValue(n, r)           O(r)
Full Triangle         generatePascalTriangle(n)   O(n²)
Print Using nCr       printPascalUsingNCR(n)      O(n²)
Check if Exists       existsInPascal(num)         O(n²)
Max in Row            maxInPascalRow(n)           O(n)

Recursive Row         getPascalRowRecursive(n)    Exponential
Recursive C(n, r)     pascalRecursive(n, r)       Exponential
Memoized C(n, r)      pascalMemo(n, r)            O(n²)
Memoized Row          getRowMemo(n)               O(n²)
*/


    // Returns the entire nth row (0-based indexing) using iterative combination logic
    public List<Integer> getPascalRow(int n) {
        List<Integer> row = new ArrayList<>();
        long val = 1;
        row.add(1); // C(n, 0) = 1 is always the first element
        for (int i = 1; i < n; i++) {
            val = val * (n - i) / i; // Use property: C(n, r) = C(n, r-1) * (n - r + 1) / r
            row.add((int) val); // Add current value to row
        }
        return row;
    }

    // Computes C(n, r) using iteration and avoiding factorials
    public int pascalValue(int n, int r) {
        long res = 1;
        for (int i = 0; i < r; i++) {
            res *= (n - i); // Multiply numerator
            res /= (i + 1);  // Divide by denominator
        }
        return (int) res;
    }

    // Generates the full Pascal's Triangle up to numRows using DP pattern
    public List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) row.add(1); // First and last elements are always 1
                else row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j)); // Sum of top two
            }
            triangle.add(row); // Add the row to the triangle
        }
        return triangle;
    }

    // Prints Pascal's triangle using iterative nCr formula
    public void printPascalUsingNCR(int n) {
        for (int i = 0; i < n; i++) {
            long val = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(val + " "); // Print current value
                val = val * (i - j) / (j + 1); // Update value for next element
            }
            System.out.println();
        }
    }

    // Checks if a number exists anywhere in Pascal's triangle
    public boolean existsInPascal(int num) {
        for (int n = 0; n <= num; n++) {
            long val = 1;
            for (int r = 0; r <= n; r++) {
                if (val == num) return true; // Match found
                val = val * (n - r) / (r + 1); // Move to next value
            }
        }
        return false;
    }

    // Returns maximum value in the nth row
    public int maxInPascalRow(int n) {
        List<Integer> row = getPascalRow(n);
        return Collections.max(row); // Find maximum using Java Collections
    }

    // ---------------------- Using Recursion ----------------------

    // Returns the nth row recursively (1-based index)
    public List<Integer> getPascalRowRecursive(int row) {
        if (row == 1) return List.of(1); // Base case: first row
        List<Integer> prev = getPascalRowRecursive(row - 1); // Get previous row
        List<Integer> curr = new ArrayList<>();
        curr.add(1); // First element is always 1
        for (int i = 1; i < prev.size(); i++) {
            curr.add(prev.get(i - 1) + prev.get(i)); // Sum of two above elements
        }
        curr.add(1); // Last element is always 1
        return curr;
    }

    // Recursive calculation of C(n, r) using Pascal's Identity
    public int pascalRecursive(int n, int r) {
        if (r == 0 || r == n) return 1; // Base case
        return pascalRecursive(n - 1, r - 1) + pascalRecursive(n - 1, r); // Recursive step
    }

    // Prints Pascal's triangle using recursive pascalRecursive method
    public void printTriangleRecursive(int n) {
        for (int i = 0; i < n; i++) {
            printRow(i); // Print each row
            System.out.println();
        }
    }

    private void printRow(int row) {
        for (int col = 0; col <= row; col++) {
            System.out.print(pascalRecursive(row, col) + " "); // Print each C(row, col)
        }
    }

    // Simple nCr recursive utility function
    public int nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }

    // Prints triangle using nCr function
    public void printUsingNCR(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(nCr(i, j) + " ");
            }
            System.out.println();
        }
    }

    // Recursive search for existence of number using nCr()
    public boolean existsInPascalRecursive(int num) {
        for (int n = 0; n <= num; n++) {
            for (int r = 0; r <= n; r++) {
                if (nCr(n, r) == num) return true;
            }
        }
        return false;
    }

    // Returns the max value in row `n` using pascalRecursive
    public int maxInPascalRecursive(int n) {
        int max = 0;
        for (int r = 0; r <= n; r++) {
            int val = pascalRecursive(n, r);
            max = Math.max(max, val);
        }
        return max;
    }

    // ---------------------- Memoization ----------------------

    // Use memoization (top-down DP) to compute Pascal values
    int[][] dp;

    public int pascalMemo(int n, int r) {
        if (r == 0 || r == n) return 1; // Base case
        if (dp[n][r] != -1) return dp[n][r]; // Reuse cached value
        dp[n][r] = pascalMemo(n - 1, r - 1) + pascalMemo(n - 1, r); // Compute and store
        return dp[n][r];
    }

    // Returns a row using memoized Pascal logic
    public List<Integer> getRowMemo(int n) {
        dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1); // Initialize memo array

        List<Integer> res = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            res.add(pascalMemo(n - 1, r));
        }
        return res;
    }

    // Print triangle using memoized values
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

    // Checks existence using memoized C(n, r)
    public boolean existsInPascalMemo(int num) {
        for (int n = 0; n <= num; n++) {
            for (int r = 0; r <= n; r++) {
                if (pascalMemo(n, r) == num) return true;
            }
        }
        return false;
    }

    // Returns max value in row n using memoized Pascal logic
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
