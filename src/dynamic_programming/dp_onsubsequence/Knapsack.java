package dynamic_programming.dp_onsubsequence;

import java.util.HashMap;

/*Recursion: We will define a recursive function knapSackRec(i, w) where:

i is the current item index.
w is the current weight capacity of the knapsack.
The goal is to either include the current item in the knapsack (if the weight allows) or exclude it, and find the maximum value.

Memoization: We will use a memoization table (dp[][]) to store the results of subproblems to avoid redundant calculations. The key for the memoization will be the combination of item index i and the current weight w.

Base Case: If we have no items left (i == 0) or the knapsack capacity is 0 (w == 0), then the value is 0 (no items to add or no capacity).

Recurrence Relation:

If the current item's weight (wt[i-1]) is less than or equal to w, we can either include it or exclude it:
Include the item: val[i-1] + knapSackRec(i-1, w - wt[i-1])
Exclude the item: knapSackRec(i-1, w)
The recurrence will be:
𝑑
𝑝
[
𝑖
]
[
𝑤*/
public class Knapsack {

    // Memoization table
    static HashMap<String, Integer> memo = new HashMap<>();

    public static int knapSack(int capacity, int[] val, int[] wt) {
        return knapSackRec(val.length, capacity, val, wt);
    }

    // Recursive function with memoization
    private static int knapSackRec(int i, int w, int[] val, int[] wt) {
        // Base case: No items or no capacity
        if (i == 0 || w == 0) {
            return 0;
        }

        // Memoization key
        String key = i + "," + w;

        // Check if result already exists in memoization table
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // If the current item weight is more than the capacity, skip it
        if (wt[i - 1] > w) {
            return knapSackRec(i - 1, w, val, wt);
        }

        // Include the current item or exclude it
        int include = val[i - 1] + knapSackRec(i - 1, w - wt[i - 1], val, wt);
        int exclude = knapSackRec(i - 1, w, val, wt);

        // Take the maximum of including or excluding the current item
        int result = Math.max(include, exclude);

        // Store the result in the memoization table
        memo.put(key, result);

        return result;
    }

    public static void main(String[] args) {
        int[] val1 = {1, 2, 3};
        int[] wt1 = {4, 5, 1};
        int capacity1 = 4;
        System.out.println("Maximum value: " + knapSack(capacity1, val1, wt1)); // Output: 3

        int[] val2 = {1, 2, 3};
        int[] wt2 = {4, 5, 6};
        int capacity2 = 3;
        System.out.println("Maximum value: " + knapSack(capacity2, val2, wt2)); // Output: 0

        int[] val3 = {10, 40, 30, 50};
        int[] wt3 = {5, 4, 6, 3};
        int capacity3 = 5;
        System.out.println("Maximum value: " + knapSack(capacity3, val3, wt3)); // Output: 50
    }
}
