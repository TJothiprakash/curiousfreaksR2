/*
Problem:
You are given two integers `m` and `n`, and a positive integer `k`. You have a multiplication table of size `m x n`.
Your task is to find the k-th smallest number in this table.

The table is structured such that `table[i][j] = i * j` for `1 <= i <= m` and `1 <= j <= n`.

Example:
Input: m = 3, n = 3, k = 5
Multiplication Table:
1 2 3
2 4 6
3 6 9
All elements: [1, 2, 2, 3, 3, 4, 6, 6, 9] → sorted → 5th smallest = 3

Intuition:
- We can't generate the whole table due to constraints (up to 3*10^4).
- Instead, use **Binary Search on Answer**: values range from 1 to m * n.
- For each guess `mid`, count how many elements in the table are ≤ mid.
- If count ≥ k → try smaller; else → try larger.

Dry Run:
m = 3, n = 3, k = 5
Range = [1, 9]
Try mid = 5 → count = 7 → enough → try smaller
Try mid = 3 → count = 5 → enough → try smaller
Try mid = 2 → count = 2 → not enough → increase
Return = 3

Code:
*/

package binarysearch;

public class KthSmallestNumberinMultiplicationTable {

    public static void main(String[] args) {
        KthSmallestNumberinMultiplicationTable obj = new KthSmallestNumberinMultiplicationTable();
        int m = 3, n = 3, k = 5;
        System.out.println("✅ K-th smallest number: " + obj.findKthNumber(m, n, k)); // Output: 3
    }

    // O(n log (m*n)) time, O(1) space
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;

        System.out.println("🔍 Binary search between " + left + " and " + right);

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessEqual(mid, m, n);
            System.out.println("➡️ Trying mid = " + mid + " → count of elements ≤ mid = " + count);

            if (count < k) {
                System.out.println("❌ Too few elements ≤ " + mid + " → increase lower bound");
                left = mid + 1;
            } else {
                System.out.println("✅ Enough elements ≤ " + mid + " → try smaller");
                right = mid;
            }
        }

        return left;
    }

    // Count how many numbers ≤ x exist in the multiplication table
    // O(m) time, O(1) space
    private int countLessEqual(int x, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int contribution = Math.min(x / i, n);
            count += contribution;
            System.out.println("   Row " + i + " contributes " + contribution + " elements");
        }
        return count;
    }

    /*
    Time Complexity:
        - Binary Search: O(log(m * n))
        - For each mid, we count in O(m)
        - Total: O(m * log(m * n))

    Space Complexity:
        - O(1)
    */
}
